package com.ananops.imc.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.generator.UniqueIdGenerator;
import com.ananops.common.core.service.BaseService;
import com.ananops.common.enums.WsMsgType;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.StringUtils;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.imc.domain.AnImcInspectionItem;
import com.ananops.imc.domain.AnImcSingleFile;
import com.ananops.imc.dto.*;
import com.ananops.imc.enums.*;
import com.ananops.imc.mapper.AnImcInspectionItemMapper;
import com.ananops.imc.mapper.AnImcInspectionTaskLogMapper;
import com.ananops.imc.mapper.AnImcSingleFileMapper;
import com.ananops.imc.service.IAnImcInspectionItemService;
import com.ananops.imc.utils.PdfUtil;
import com.ananops.imc.utils.WaterMark;
import com.ananops.system.domain.SysOss;
import com.ananops.system.domain.SysUser;
import com.ananops.system.dto.FileUploadDto;
import com.ananops.system.feign.RemoteOssService;
import com.ananops.system.feign.RemoteUserService;
import com.ananops.websocket.dto.MsgDto;
import com.ananops.websocket.feign.RemoteWebSocketService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.imc.mapper.AnImcInspectionTaskMapper;
import com.ananops.imc.domain.AnImcInspectionTask;
import com.ananops.imc.service.IAnImcInspectionTaskService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

/**
 * 巡检任务表Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionTaskServiceImpl extends BaseService<AnImcInspectionTask> implements IAnImcInspectionTaskService
{

    @Autowired
    private AnImcInspectionTaskMapper anImcInspectionTaskMapper;

    @Autowired
    private AnImcInspectionItemMapper anImcInspectionItemMapper;

    @Autowired
    private IAnImcInspectionItemService anImcInspectionItemService;

    @Autowired
    private AnImcInspectionTaskLogMapper anImcInspectionTaskLogMapper;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteWebSocketService remoteWebSocketService;

    @Autowired
    private AnImcSingleFileMapper anImcSingleFileMapper;

    @Autowired
    private RemoteOssService remoteOssService;

    /**
     * 查询巡检任务表
     * 
     * @param id 巡检任务表ID
     * @return 巡检任务表
     */
    @Override
    public ImcInspectionTaskDto selectAnImcInspectionTaskById(Long id)
    {
        AnImcInspectionTask imcInspectionTask = anImcInspectionTaskMapper.selectAnImcInspectionTaskById(id);
        logger.info("imcInspectionTask = {}",imcInspectionTask);
        if(null == imcInspectionTask){
            return new ImcInspectionTaskDto();
        }
        List<AnImcInspectionTask> imcInspectionTasks = new ArrayList<>();
        imcInspectionTasks.add(imcInspectionTask);
        return transform(imcInspectionTasks).get(0);
    }

    /**
     * 查询巡检任务表列表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 巡检任务表
     */
    @Override
    public List<AnImcInspectionTask> selectAnImcInspectionTaskList(AnImcInspectionTask anImcInspectionTask)
    {
        return anImcInspectionTaskMapper.selectAnImcInspectionTaskList(anImcInspectionTask);
    }

    /**
     * 新增巡检任务表
     * 
     * @param imcAddInspectionTaskDto 巡检任务表
     * @return 结果
     */
    @Override
    public ImcAddInspectionTaskDto insertAnImcInspectionTask(ImcAddInspectionTaskDto imcAddInspectionTaskDto, LoginAuthDto user)
    {
        //Bean拷贝
        AnImcInspectionTask anImcInspectionTask = new AnImcInspectionTask();
        BeanUtils.copyProperties(imcAddInspectionTaskDto,anImcInspectionTask);
        UpdateInfoUtil.setInsertInfo(anImcInspectionTask,user);
        //新建巡检任务
        Integer inspectionType = imcAddInspectionTaskDto.getInspectionType();
        Date startTime = imcAddInspectionTaskDto.getScheduledStartTime();
        anImcInspectionTask.setScheduledStartTime(startTime);
        if(TaskTypeEnum.FROM_PROJECT.getStatusNum() == inspectionType){
            //如果该任务是从项目中发起的，则无需甲方负责人审核
            anImcInspectionTask.setStatus(TaskStatusEnum.WAITING_FOR_ACCEPT.getStatusNum());
        }else{
            //将巡检任务的状态设置为等待甲方负责人审核
            anImcInspectionTask.setStatus(TaskStatusEnum.WAITING_FOR_PRINCIPAL.getStatusNum());
        }
        anImcInspectionTaskMapper.insert(anImcInspectionTask);
        logger.info("新增一条巡检任务记录：{}",anImcInspectionTask);
        Long taskId = anImcInspectionTask.getId();

        //获取所有的巡检任务子，并持久化
        List<ImcAddInspectionItemDto> imcAddInspectionItemDtoList = imcAddInspectionTaskDto.getImcAddInspectionItemDtoList();
        if(null!=imcAddInspectionItemDtoList){
            //保存新创建的巡检任务子项
            imcAddInspectionItemDtoList.forEach(item->{
                item.setInspectionTaskId(taskId);//设置巡检任务子项对应的任务id
                item.setDays(anImcInspectionTask.getDays());//设置巡检任务子项对应的巡检周期
                item.setFrequency(anImcInspectionTask.getFrequency());//设置巡检任务子项对应的巡检频率
                item.setScheduledStartTime(anImcInspectionTask.getScheduledStartTime());//设置巡检任务子项的对应的计划开始时间
                item.setStatus(ItemStatusEnum.WAITING_FOR_MAINTAINER.getStatusNum());
                //创建新的巡检任务子项，并更新返回结果
                BeanUtils.copyProperties(anImcInspectionItemService.insertAnImcInspectionItem(item,user),item);
            });
            BeanUtils.copyProperties(imcAddInspectionItemDtoList,imcAddInspectionTaskDto);
        }
        //更新返回结果
        BeanUtils.copyProperties(anImcInspectionTask,imcAddInspectionTaskDto);

        this.handleWsMsgSend(anImcInspectionTask,user);

        return imcAddInspectionTaskDto;
    }

    /**
     * 修改巡检任务表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask)
    {
        anImcInspectionTask.setUpdateTime(DateUtils.getNowDate());
        return anImcInspectionTaskMapper.updateAnImcInspectionTask(anImcInspectionTask);
    }

    /**
     * 删除巡检任务表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionTaskByIds(String ids)
    {
        return anImcInspectionTaskMapper.deleteAnImcInspectionTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务表信息
     * 
     * @param id 巡检任务表ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionTaskById(Long id)
    {
        return anImcInspectionTaskMapper.deleteAnImcInspectionTaskById(id);
    }

    /**
     * 更改巡检任务状态
     * @param imcTaskChangeStatusDto
     * @param user
     * @return
     */
    @Override
    public ImcTaskChangeStatusDto modifyTaskStatus(ImcTaskChangeStatusDto imcTaskChangeStatusDto, LoginAuthDto user){
        imcTaskChangeStatusDto.setStatusMsg(TaskStatusEnum.getStatusMsg(imcTaskChangeStatusDto.getStatus()));
        imcTaskChangeStatusDto.setLoginAuthDto(user);
        Long taskId = imcTaskChangeStatusDto.getTaskId();
        Integer status = imcTaskChangeStatusDto.getStatus();
        AnImcInspectionTask anImcInspectionTask = new AnImcInspectionTask();
        UpdateInfoUtil.setModifyInfo(anImcInspectionTask,user);
        anImcInspectionTask.setId(taskId);
        anImcInspectionTask.setStatus(status);
        logger.info("anImcInspectionTask is {}",anImcInspectionTask);
        switch (TaskStatusEnum.getEnum(status)){
            case WAITING_FOR_PAY:
                //如果当前任务状态修改为等待支付，意味着任务已经被确认
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)>0){
                    //获取全部的任务子项
                    Example example = new Example(AnImcInspectionItem.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("inspectionTaskId",taskId);
                    List<AnImcInspectionItem> anImcInspectionItems = anImcInspectionItemMapper.selectByExample(example);
                    if(null != anImcInspectionItems && anImcInspectionItems.size() > 0){
                        anImcInspectionItems.forEach(item->{
                            //任务已经巡检完毕，将全部任务子项的状态修改为已完成
                            ImcItemChangeStatusDto imcItemChangeStatusDto = new ImcItemChangeStatusDto();
                            imcItemChangeStatusDto.setLoginAuthDto(user);
                            imcItemChangeStatusDto.setStatus(ItemStatusEnum.VERIFIED.getStatusNum());
                            imcItemChangeStatusDto.setItemId(item.getId());
                            anImcInspectionItemService.modifyImcItemStatus(imcItemChangeStatusDto,user);
                            //用户确认完成后需要将巡检单据中的用户确认字段填入
                            //TODO
                        });
                    }
                    logger.info("任务状态已修改为待支付");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
            case WAITING_FOR_CONFIRM:
                //如果当前状态处于巡检完成等待甲方负责人确认的阶段
                //更新巡检完成的实际时间
                anImcInspectionTask.setActualFinishTime(new Date(System.currentTimeMillis()));
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)<=0) {
                    throw new BusinessException("任务状态修改失败");
                }else{
                    logger.info("任务状态已被修改为完成待确认");
                }
                break;
            case INSPECTION_OVER:
                //如果巡检结束，自动生成附件
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)>0){
                    //自动生成附件
                    //TODO
                    logger.info("任务状态已被修改为完成");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
            default:
                //如果是其他状态
                if(anImcInspectionTaskMapper.modifyTaskStatus(anImcInspectionTask)>0){
                    //直接修改任务状态
                    logger.info("任务状态已被修改");
                }else{
                    throw new BusinessException("任务状态修改失败");
                }
                break;
        }
        AnImcInspectionTask task = anImcInspectionTaskMapper.selectByPrimaryKey(taskId);
        this.handleWsMsgSend(task,user);
        return imcTaskChangeStatusDto;
    }

    /**
     * 判断巡检任务是否完成
     * @param taskId
     * @return
     */
    @Override
    public boolean isTaskFinish(Long taskId){
        Example example = new Example(AnImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId",taskId);
        List<AnImcInspectionItem> imcInspectionItemList = anImcInspectionItemMapper.selectByExample(example);
        for(int i=0;i<imcInspectionItemList.size();i++){
            if(imcInspectionItemList.get(i).getStatus()<ItemStatusEnum.INSPECTION_OVER.getStatusNum()){
                //如果还有巡检任务子项没完成
                return false;
            }
        }
        //如果巡检任务子项都完成了，则巡检任务也完成了
        return true;
    }

    /**
     * 根据项目id查询对应的巡检任务列表
     * @param taskQueryDto
     * @return
     */
    @Override
    public PageInfo getTaskByProjectId(TaskQueryDto taskQueryDto){
        Example example = new Example(AnImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        Long projectId = taskQueryDto.getProjectId();
        String taskName = taskQueryDto.getTaskName();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        criteria.andEqualTo("projectId",projectId);
        example.setOrderByClause("update_time DESC");
        Page page = PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        PageInfo pageInfo = new PageInfo<>(transform(anImcInspectionTaskMapper.selectByExample(example)));
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());
        pageInfo.setPageNum(page.getPageNum());
        return pageInfo;
    }

    /**
     *根据用户id获取对应的巡检任务（可分页）
     * @param taskQueryDto
     * @return
     */
    @Override
    public PageInfo getTaskByUserId(TaskQueryDto taskQueryDto){
        Integer role = taskQueryDto.getRole();
        Page page = PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
        PageInfo pageInfo;
        if(null == role){
            throw new BusinessException("用户角色为空");
        }
        RoleEnum roleEnum = RoleEnum.getEnum(role);
        switch (roleEnum){
            case PRINCIPAL:
                //如果是甲方负责人
                pageInfo = new PageInfo<>(transform(getTaskByPrincipalId(taskQueryDto)));
                pageInfo.setTotal(page.getTotal());
                pageInfo.setPages(page.getPages());
                pageInfo.setPageNum(page.getPageNum());
                return pageInfo;
            case FACILITATOR:
                //如果是服务商负责人
                pageInfo = new PageInfo<>(transform(getTaskByFacilitatorId(taskQueryDto)));
                pageInfo.setTotal(page.getTotal());
                pageInfo.setPages(page.getPages());
                pageInfo.setPageNum(page.getPageNum());
                return pageInfo;
            default:
                throw new BusinessException("查无此角色");
        }
    }

    /**
     * 根据服务商id查询对应的全部巡检任务
     * @param taskQueryDto
     * @return
     */
    @Override
    public List<AnImcInspectionTask> getTaskByFacilitatorId(TaskQueryDto taskQueryDto){
        Long facilitatorId = taskQueryDto.getUserId();
        Example example = new Example(AnImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        String taskName = taskQueryDto.getTaskName();
        Integer status = taskQueryDto.getStatus();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        if(null != status){
            criteria.andEqualTo("status",status);
        }
        criteria.andEqualTo("facilitatorId",facilitatorId);
        example.setOrderByClause("update_time DESC");
        return anImcInspectionTaskMapper.selectByExample(example);
    }

    /**
     * 根据甲方负责人id查询全部的巡检任务
     * @param taskQueryDto
     * @return
     */
    @Override
    public List<AnImcInspectionTask> getTaskByPrincipalId(TaskQueryDto taskQueryDto){
        Long principalId = taskQueryDto.getUserId();
        Example example = new Example(AnImcInspectionTask.class);
        Example.Criteria criteria = example.createCriteria();
        String taskName = taskQueryDto.getTaskName();
        Integer status = taskQueryDto.getStatus();
        if(StringUtils.isNotBlank(taskName)){
            taskName = "%" + taskName + "%";
            criteria.andLike("taskName",taskName);
        }
        if(null != status){
            criteria.andEqualTo("status",status);
        }
        criteria.andEqualTo("principalId",principalId);
        example.setOrderByClause("update_time DESC");
        return anImcInspectionTaskMapper.selectByExample(example);
    }

    /**
     * 根据用户id和角色获取全部的巡检任务数目
     * @param taskQueryDto
     * @return
     */
    @Override
    public int getImcTaskNumberByUserIdAndRole(TaskQueryDto taskQueryDto){
        Integer role = taskQueryDto.getRole();
        Long userId = taskQueryDto.getUserId();
        int count = 0;
        Example example;
        if(null != role && null != userId){
            if(role == RoleEnum.PRINCIPAL.getStatusNum()){
                //如果是用户负责人
                example = new Example(AnImcInspectionTask.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("principalId",userId);
                count = anImcInspectionTaskMapper.selectCountByExample(example);
            }else if(role == RoleEnum.FACILITATOR.getStatusNum()){
                //如果是服务商负责人
                example = new Example(AnImcInspectionTask.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("facilitatorId",userId);
                count = anImcInspectionTaskMapper.selectCountByExample(example);
            }
        }else throw new BusinessException("参数异常");
        return count;
    }

    /**
     * 查询全部未分配工程师的巡检任务
     * @param  taskQueryDto
     * @return
     */
    @Override
    public PageInfo getAllUnDistributedTask(TaskQueryDto taskQueryDto){
        Long facilitatorId = taskQueryDto.getUserId();
        if(null != facilitatorId){
            Page page = PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
            List<AnImcInspectionTask> imcInspectionTasks = anImcInspectionTaskMapper.getAllUnDistributedTask(facilitatorId);
            PageInfo pageInfo = new PageInfo<>(transform(imcInspectionTasks));
            pageInfo.setTotal(page.getTotal());
            pageInfo.setPages(page.getPages());
            pageInfo.setPageNum(page.getPageNum());
            return pageInfo;
        }else throw new BusinessException("参数异常");
    }

    /**
     * 获取全部当前服务商已完成的巡检任务
     * @param taskQueryDto
     * @return
     */
    @Override
    public PageInfo getAllFinishedTaskByFacilitatorId(TaskQueryDto taskQueryDto){
        Long facilitatorId = taskQueryDto.getUserId();
        if(null != facilitatorId){
            Page page = PageHelper.startPage(taskQueryDto.getPageNum(),taskQueryDto.getPageSize());
            List<AnImcInspectionTask> imcInspectionTasks = anImcInspectionTaskMapper.getAllFinishedTaskByFacilitatorId(facilitatorId);
            PageInfo pageInfo = new PageInfo<>(transform(imcInspectionTasks));
            pageInfo.setTotal(page.getTotal());
            pageInfo.setPages(page.getPages());
            pageInfo.setPageNum(page.getPageNum());
            return pageInfo;
        }else throw new BusinessException("参数异常");
    }

    @Override
    public String getTaskReport (Long taskId, LoginAuthDto user) {
        AnImcSingleFile singleFile = anImcSingleFileMapper.selectByPrimaryKey(taskId);
        if (singleFile != null) {
            return singleFile.getUrl();
        } else {
            generateImcTaskPdf(taskId,user);
            singleFile = anImcSingleFileMapper.selectByPrimaryKey(taskId);
            return singleFile.getUrl();
        }
    }

    /**
     * 生成巡检任务报表
     * @param taskId
     * @param user
     * @return
     */
    @Override
    public FileUploadDto generateImcTaskPdf (Long taskId,LoginAuthDto user) {
        AnImcInspectionTask task = anImcInspectionTaskMapper.selectByPrimaryKey(taskId);
        if (null == task) throw new BusinessException("查无此任务");
        List<AnImcInspectionTask> tasks = new ArrayList<>();
        tasks.add(task);
        //获取巡检任务信息
        ImcInspectionTaskDto taskDto = this.transform(tasks).get(0);
        //获取巡检任务对应的全部子项信息
        Example example = new Example(AnImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId",taskId);
        List<AnImcInspectionItem> items = anImcInspectionItemMapper.selectByExample(example);
        List<ImcInspectionItemDto> itemDtos = anImcInspectionItemService.transform(items);
        ImcTaskReportDto taskReportDto = new ImcTaskReportDto();
        taskReportDto.setImcInspectionTaskDto(taskDto);
        taskReportDto.setImcInspectionItemDtos(itemDtos);
        return createPdf(taskReportDto,user);
    }

    /**
     * 生成报表pdf
     * @param taskReportDto
     * @param user
     * @return
     */
    private FileUploadDto createPdf(ImcTaskReportDto taskReportDto,LoginAuthDto user) {
        FileUploadDto fileUploadDto = new FileUploadDto();
        ImcInspectionTaskDto imcInspectionTaskDto = taskReportDto.getImcInspectionTaskDto();
        List<ImcInspectionItemDto> imcInspectionItemDtos = taskReportDto.getImcInspectionItemDtos();
        logger.info("taskReportDto={}",taskReportDto);
        //创建文档对象
        Document document = new Document(PageSize.A4);
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document,out);

            writer.setPageEvent(new WaterMark("安安运维（北京）科技有限公司"));// 水印

            document.open();
            document.addTitle("安安运维巡检报告");
            //基本文字格式
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font titlefont = new Font(bfChinese, 16, Font.BOLD);
            Font headfont = new Font(bfChinese, 14, Font.BOLD);
            Font keyfont = new Font(bfChinese, 10, Font.BOLD);
            Font textfont = new Font(bfChinese, 10, Font.NORMAL);

            //日期转化工具
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 段落
            Paragraph paragraph = PdfUtil.createParagraph("安安运维巡检报告");

            //直线
            Paragraph p1 = new Paragraph();
            p1.add(new Chunk(new LineSeparator()));

            document.add(paragraph);
            document.add(p1);

            //增添巡检任务表单
            PdfPTable table = PdfUtil.createTable(2,10);
            table.addCell(PdfUtil.createCell("巡检报告：",headfont, Element.ALIGN_LEFT, 6, false));

            table.addCell(PdfUtil.createCell("巡检任务Id", textfont));

            table.addCell(PdfUtil.createCell(String.valueOf(imcInspectionTaskDto.getId()), textfont));

            table.addCell(PdfUtil.createCell("巡检任务名称", textfont));

            table.addCell(PdfUtil.createCell(imcInspectionTaskDto.getTaskName(), textfont));

            table.addCell(PdfUtil.createCell("巡检类型", textfont));

            table.addCell(PdfUtil.createCell(imcInspectionTaskDto.getInspectionType()==1? "计划巡检":"临时巡检", textfont));

            table.addCell(PdfUtil.createCell("项目名称", textfont));

            table.addCell(PdfUtil.createCell(imcInspectionTaskDto.getProjectName(), textfont));

            table.addCell(PdfUtil.createCell("项目负责人姓名", textfont));

            table.addCell(PdfUtil.createCell(imcInspectionTaskDto.getPrincipalName(), textfont));

            table.addCell(PdfUtil.createCell("服务商名称", textfont));

            table.addCell(PdfUtil.createCell(imcInspectionTaskDto.getFacilitatorName(), textfont));

            table.addCell(PdfUtil.createCell("计划起始时间", textfont));

            table.addCell(PdfUtil.createCell(formatter.format(imcInspectionTaskDto.getScheduledStartTime()), textfont));

            table.addCell(PdfUtil.createCell("巡检周期", textfont));

            table.addCell(PdfUtil.createCell(String.valueOf(imcInspectionTaskDto.getFrequency()), textfont));

            table.addCell(PdfUtil.createCell("实际完成时间", textfont));

            table.addCell(PdfUtil.createCell(formatter.format(imcInspectionTaskDto.getActualFinishTime()), textfont));

            table.addCell(PdfUtil.createCell("巡检任务内容", textfont));

            table.addCell(PdfUtil.createCell(imcInspectionTaskDto.getContent(), textfont));

            document.add(table);

            //添加巡检任务子项表
            ImcInspectionItemDto imcInspectionItemDto;
            for(int i=0;i<imcInspectionItemDtos.size();i++){
                imcInspectionItemDto = imcInspectionItemDtos.get(i);
                table = PdfUtil.createTable(2,10);
                table.addCell(PdfUtil.createCell("巡检子项" + (i+1) + ":",headfont, Element.ALIGN_LEFT, 6, false));

                table.addCell(PdfUtil.createCell("巡检子项Id", textfont));

                table.addCell(PdfUtil.createCell(String.valueOf(imcInspectionItemDto.getId()), textfont));

                table.addCell(PdfUtil.createCell("巡检子项名称", textfont));

                table.addCell(PdfUtil.createCell(imcInspectionItemDto.getItemName(), textfont));

                table.addCell(PdfUtil.createCell("巡检网点", textfont));

                table.addCell(PdfUtil.createCell(imcInspectionItemDto.getLocation(), textfont));

                table.addCell(PdfUtil.createCell("计划起始时间", textfont));

                table.addCell(PdfUtil.createCell(formatter.format(imcInspectionItemDto.getScheduledStartTime()), textfont));

                table.addCell(PdfUtil.createCell("计划完成天数", textfont));

                table.addCell(PdfUtil.createCell(String.valueOf(imcInspectionItemDto.getDays()), textfont));

                table.addCell(PdfUtil.createCell("实际起始时间", textfont));

                table.addCell(PdfUtil.createCell(formatter.format(imcInspectionItemDto.getActualStartTime()), textfont));

                table.addCell(PdfUtil.createCell("实际完成时间", textfont));

                table.addCell(PdfUtil.createCell(formatter.format(imcInspectionItemDto.getActualFinishTime()), textfont));

                table.addCell(PdfUtil.createCell("维修工姓名", textfont));

                table.addCell(PdfUtil.createCell(imcInspectionItemDto.getMaintainerName(), textfont));

                table.addCell(PdfUtil.createCell("巡检子项内容", textfont));

                table.addCell(PdfUtil.createCell(imcInspectionItemDto.getDescription(), textfont));

                table.addCell(PdfUtil.createCell("巡检结果描述", textfont));

                table.addCell(PdfUtil.createCell(imcInspectionItemDto.getResult(), textfont));

                document.add(table);
            }

            // 添加图片
            Image image = Image.getInstance("Logo.png");
            image.setAlignment(Image.ALIGN_CENTER);
            image.scalePercent(40); //依照比例缩放

            document.add(image);

            document.close();

            String filename = "巡检任务报表" + imcInspectionTaskDto.getId() + ".pdf";

            fileUploadDto = uploadReportPdf(out.toByteArray(),filename,user,imcInspectionTaskDto.getId());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
        }
        return fileUploadDto;
    }

    /**
     * 报表上传
     * @param data
     * @param fileName
     * @param user
     * @param taskId
     * @return
     */
    private FileUploadDto uploadReportPdf(byte[] data, String fileName, LoginAuthDto user, Long taskId) {
        FileUploadDto fileUploadDto = new FileUploadDto();
        fileUploadDto.setUser(user);
        fileUploadDto.setFileName(fileName);
        fileUploadDto.setData(data);

        //附件上传
        SysOss result = remoteOssService.editSave(fileUploadDto);
        if (null != result) {
            AnImcSingleFile singleFile = new AnImcSingleFile();
            singleFile.setUniqueId(taskId);
            singleFile.setUrl(result.getUrl());
            anImcSingleFileMapper.insert(singleFile);
        } else {
            throw new BusinessException("附件上传失败");
        }
        return fileUploadDto;
    }

    /**
     * 获取任务对应的全部操作日志
     * @param taskId
     * @return
     */
    public List<TaskLogDto> getTaskLogs(Long taskId){
        List<TaskLogDto> taskLogDtos = anImcInspectionTaskLogMapper.getTaskLogs(taskId);
        if(null != taskLogDtos && taskLogDtos.size()>0){
            taskLogDtos.forEach(taskLogDto -> {
                taskLogDto.setStatusMsg(TaskStatusEnum.getStatusMsg(taskLogDto.getStatus()));
            });
        }
        return taskLogDtos;
    }

    private void handleWsMsgSend(AnImcInspectionTask task,LoginAuthDto user) {
        Long taskId = task.getId();
        Integer status = task.getStatus();
        Long principalId = task.getPrincipalId();
        Long facilitatorId = task.getFacilitatorId();
        TaskWsDto taskWsDto = new TaskWsDto();
        MsgDto<TaskWsDto> msgDto = new MsgDto<>();
        taskWsDto.setStatus(status);
        taskWsDto.setStatusMsg(TaskStatusEnum.getStatusMsg(status));
        taskWsDto.setTaskId(taskId);
        msgDto.setMsgType(WsMsgType.IMC_TASK_STATUS.getType());
        msgDto.setMsg(taskWsDto);
        msgDto.setUser(user);
        if (status==-1||status==0||status==1||status==3||status==4||status==5||status==6||status==7) {
            //发给甲方负责人
            msgDto.setId(String.valueOf(principalId));
        } else if (status==2) {
            //发给服务商的消息
            msgDto.setId(String.valueOf(facilitatorId));
        } else {
            throw new BusinessException("查无此状态");
        }
        remoteWebSocketService.createWebsocketMsg(msgDto);
    }

    private List<ImcInspectionTaskDto> transform(List<AnImcInspectionTask> imcInspectionTasks){
        List<ImcInspectionTaskDto> imcInspectionTaskDtos = new ArrayList<>();
        Map<Long,String> nameMap = new HashMap<>();
        for(AnImcInspectionTask imcInspectionTask : imcInspectionTasks){
            ImcInspectionTaskDto imcInspectionTaskDto = new ImcInspectionTaskDto();
            BeanUtils.copyProperties(imcInspectionTask,imcInspectionTaskDto);
            logger.info("imcInspectionTask = {},imcInspectionTaskDto = {}",imcInspectionTask,imcInspectionTaskDto);
            //装入已安排的点位数
            Example example = new Example(AnImcInspectionItem.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("inspectionTaskId",imcInspectionTask.getId());
            List<AnImcInspectionItem> items = anImcInspectionItemMapper.selectByExample(example);
            int count = 0;
            if (null != items && items.size() > 0){
                for(AnImcInspectionItem item :items){
                    count += item.getCount() != null ? item.getCount() : 0;
                }
            }
            imcInspectionTaskDto.setAlreadyPoint(count);
            //转换用户名
            Long principalId = imcInspectionTask.getPrincipalId();
            if (nameMap.containsKey(principalId)) {
                imcInspectionTaskDto.setPrincipalName(nameMap.get(principalId));
            } else {
                SysUser user = remoteUserService.selectSysUserByUserId(principalId);
                if ( null != user) {
                    nameMap.put(principalId,user.getLoginName());
                    imcInspectionTaskDto.setPrincipalName(user.getUserName());
                }
            }
            //转换项目名称
            //TODO
            //转换服务商名称
            //TODO
            imcInspectionTaskDtos.add(imcInspectionTaskDto);
        }
        return imcInspectionTaskDtos;
    }
}
