package com.ananops.mdmc.aop;

import com.ananops.common.core.domain.R;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.mdmc.domain.AnMdmcTask;
import com.ananops.mdmc.domain.AnMdmcTaskLog;
import com.ananops.mdmc.dto.MdmcAddTaskDto;
import com.ananops.mdmc.dto.MdmcChangeStatusDto;
import com.ananops.mdmc.mapper.AnMdmcTaskMapper;
import com.ananops.mdmc.service.IAnMdmcTaskLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by huqiaoqian on 2020/5/28
 */
@Slf4j
@Aspect
@Component
public class AnMdmcLogAspect {
    private ThreadLocal<Date> threadLocal = new ThreadLocal<>();
    @Resource
    private IAnMdmcTaskLogService taskLogService;

    @Resource
    private AnMdmcTaskMapper taskMapper;

    /**
     * Log annotation.
     */
    @Pointcut("@annotation(com.ananops.mdmc.aop.AnMdmcLog)")//定义切点
    public void logAnnotation() {
    }

    /**
     * Do before.
     */
    @Before("logAnnotation()")//在切点方法之前执行
    public void doBefore() {
        this.threadLocal.set(new Date(System.currentTimeMillis()));
    }

    /**
     * Do after.
     *
     * @param joinPoint   the join point
     * @param returnValue the return value
     */
    @AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")//在切点方法返回后执行
    public void doAfter(final JoinPoint joinPoint, final Object returnValue) {
        if (returnValue instanceof R) {
            R result = (R) returnValue;
            if (result.get("data")!=null && ("success".equals(((String)(result.get("msg")))))) {//如果操作结果非空，并且成功
                this.handleLog(joinPoint, result);//处理操作日志
            }
        }
    }

    private void handleLog(final JoinPoint joinPoint, final Object result) {//日志处理
        try{
            AnMdmcLog relog=giveController(joinPoint);
            if(relog == null){
                return ;
            }

            R r=(R)result;
            if(r.get("data").getClass().getName().equals(MdmcAddTaskDto.class.getName())){
                MdmcAddTaskDto addTaskDto=(MdmcAddTaskDto) r.get("data");
                AnMdmcTaskLog log=new AnMdmcTaskLog();
                log.setTaskId(addTaskDto.getId());
                BeanUtils.copyProperties(addTaskDto,log);
                if (taskLogService.insertAnMdmcTaskLog(log)<=0){
                    throw new BusinessException("新增日志失败");
                }
                else{
                    System.out.print("日志创建成功");
                }
            }
            else if(r.get("data").getClass().getName().equals(AnMdmcTask.class.getName())){
                AnMdmcTask task=(AnMdmcTask)r.get("data");
                AnMdmcTaskLog log=new AnMdmcTaskLog();
                log.setTaskId(task.getId());
                BeanUtils.copyProperties(task,log);
                log.setId(null);
                if (taskLogService.insertAnMdmcTaskLog(log)<=0){
                    throw new BusinessException("新增日志失败");
                }
                else{
                    System.out.print("日志创建成功");
                }
            }
            else if (r.get("data").getClass().getName().equals(MdmcChangeStatusDto.class.getName())){
                MdmcChangeStatusDto changeStatusDto=(MdmcChangeStatusDto)r.get("data");
                AnMdmcTaskLog log=new AnMdmcTaskLog();
                Long taskId=changeStatusDto.getTaskId();
                log.setTaskId(taskId);
                AnMdmcTask task=taskMapper.selectByPrimaryKey(taskId);
                BeanUtils.copyProperties(task,log);
                if (taskLogService.insertAnMdmcTaskLog(log)<=0){
                    throw new BusinessException("新增日志失败");
                }
                else{
                    System.out.print("日志创建成功");
                }
            }
        } catch (Exception e){
            log.error("获取注解类出现异常={}", e.getMessage(), e);
        }
    }

    /**
     * 是否存在注解, 如果存在就记录日志
     */
    private static AnMdmcLog giveController(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        String methodName = joinPoint.getSignature().getName();
        if (null != methods && 0 < methods.length) {
            for (Method met : methods) {
                AnMdmcLog relog = met.getAnnotation(AnMdmcLog.class);
                if (null != relog && methodName.equals(met.getName())) {
                    return relog;
                }
            }
        }

        return null;
    }
}
