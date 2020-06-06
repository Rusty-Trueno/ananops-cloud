package com.ananops.mdc.service.impl;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.generator.UniqueIdGenerator;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdc.domain.AnMdcFormTemplate;
import com.ananops.mdc.domain.AnMdcFormTemplateItem;
import com.ananops.mdc.dto.DeviceDesc;
import com.ananops.mdc.dto.FormDataDto;
import com.ananops.mdc.dto.InspcDetail;
import com.ananops.mdc.enums.ErrorCodeEnum;
import com.ananops.mdc.mapper.MdcFormTemplateItemMapper;
import com.ananops.mdc.service.IAnMdcFormTemplateEnService;
import com.ananops.mdc.service.IAnMdcFormTemplateItemService;
import com.ananops.mdc.service.IAnMdcFormTemplateService;
import com.ananops.mdc.util.PublicUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnMdcFormTemplateEnServiceImpl implements IAnMdcFormTemplateEnService {

    @Autowired
    private IAnMdcFormTemplateService anMdcFormTemplateService;

    @Autowired
    private IAnMdcFormTemplateItemService anMdcFormTemplateItemService;

    @Resource
    private MdcFormTemplateItemMapper mdcFormTemplateItemMapper;

    @Override
    public Integer saveFormTemplate(FormDataDto mdcFormDataDto, LoginAuthDto loginAuthDto) {
        if (mdcFormDataDto.getTemplateId() == null) {
            AnMdcFormTemplate mdcFormTemplate = new AnMdcFormTemplate();
            BeanUtils.copyProperties(mdcFormDataDto,mdcFormTemplate);
            UpdateInfoUtil.setInsertInfo(mdcFormTemplate,loginAuthDto);
            mdcFormTemplate.setDr(String.valueOf(0));
            mdcFormTemplate.setDeptId(loginAuthDto.getDeptId());
            anMdcFormTemplateService.insertAnMdcFormTemplate(mdcFormTemplate);
            // 存储所有子项
            insertTemplateItem(mdcFormDataDto, mdcFormDataDto.getTemplateId());
        } else {
            AnMdcFormTemplate mdcFormTemplate = anMdcFormTemplateService.selectAnMdcFormTemplateById(mdcFormDataDto.getTemplateId());
            if (mdcFormTemplate == null) {
                throw new BusinessException(String.format(ErrorCodeEnum.MDC10021037.msg(),mdcFormDataDto.getTemplateId()));
            }
            // 先删除所有模板子项
            Example example = new Example(AnMdcFormTemplate.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("templateId", mdcFormTemplate.getId());
            mdcFormTemplateItemMapper.deleteByExample(example);
            // 重新存储所有子项内容
            insertTemplateItem(mdcFormDataDto, mdcFormTemplate.getId());
        }

        return null;
    }

    // 插入模板子项工具类
    private void insertTemplateItem(FormDataDto mdcFormDataDto, Long templateId) {
        List<DeviceDesc> mdcDeviceDescs = mdcFormDataDto.getAssetList();
        List<InspcDetail> mdcInspcDetails = mdcFormDataDto.getInspcDetailList();
        // 存储资产列表模板子项
        if (mdcDeviceDescs != null) {
            long i = 1;
            for (DeviceDesc mdcDeviceDesc : mdcDeviceDescs) {
                Long deviceItemId = UniqueIdGenerator.generateId();
                AnMdcFormTemplateItem mdcFormTemplateItem = new AnMdcFormTemplateItem();
                mdcFormTemplateItem.setId(deviceItemId);
                mdcFormTemplateItem.setTemplateId(templateId);
                mdcFormTemplateItem.setType("device");
                mdcFormTemplateItem.setContent(mdcDeviceDesc.getDevice());
                mdcFormTemplateItem.setSort(i++);
                anMdcFormTemplateItemService.insertAnMdcFormTemplateItem(mdcFormTemplateItem);
            }
        }
        // 存储巡检内容模板子项
        if (mdcInspcDetails != null) {
            long i = 1;
            for (InspcDetail mdcInspcDetail : mdcInspcDetails) {
                Long itemId = UniqueIdGenerator.generateId();
                AnMdcFormTemplateItem mdcFormTemplateItem = new AnMdcFormTemplateItem();
                mdcFormTemplateItem.setId(itemId);
                mdcFormTemplateItem.setTemplateId(templateId);
                mdcFormTemplateItem.setType("inspcContent");
                mdcFormTemplateItem.setContent(mdcInspcDetail.getItemContent());
                mdcFormTemplateItem.setSort(i++);
                anMdcFormTemplateItemService.insertAnMdcFormTemplateItem(mdcFormTemplateItem);
            }
        }
    }

    @Override
    public Integer updateFormTemplate(AnMdcFormTemplate mdcFormTemplate, LoginAuthDto loginAuthDto) {
        // 关联的项目查询
        if (mdcFormTemplate.getProjectId() != null) {
            AnMdcFormTemplate query=new AnMdcFormTemplate();
            query.setProjectId(mdcFormTemplate.getProjectId());
            List<AnMdcFormTemplate> mdcFormTemplates = anMdcFormTemplateService.selectAnMdcFormTemplateList(query);
            if (mdcFormTemplates.size() > 0) {
                throw new BusinessException(String.format(ErrorCodeEnum.MDC10021038.msg(),mdcFormTemplates.get(0).getId()));
            }
        }
        UpdateInfoUtil.setInsertInfo(mdcFormTemplate,loginAuthDto);
        // 首先增量更新变动项
        anMdcFormTemplateService.updateAnMdcFormTemplate(mdcFormTemplate);

        // 如果项目名称或者项目Id为空时取消关联
        if (PublicUtil.isEmpty(mdcFormTemplate.getProjectId()) || PublicUtil.isEmpty(mdcFormTemplate.getProjectName())) {
            AnMdcFormTemplate mdcFormTemplateRecord = anMdcFormTemplateService.selectAnMdcFormTemplateList(mdcFormTemplate).get(0);
            UpdateInfoUtil.setInsertInfo(mdcFormTemplateRecord,loginAuthDto);
            mdcFormTemplateRecord.setProjectId(null);
            mdcFormTemplateRecord.setProjectName(null);
            anMdcFormTemplateService.updateAnMdcFormTemplate(mdcFormTemplateRecord);
        }
        return null;
    }

    @Override
    public List<AnMdcFormTemplate> getFormTemplateList(LoginAuthDto loginAuthDto) {
        // 这里默认只能管理员登录，管理员账号直接挂在公司组织下的，所以用户组织就是公司组织
        Long deptId = loginAuthDto.getDeptId();
        List<AnMdcFormTemplate> res = new ArrayList<>();
        AnMdcFormTemplate anMdcFormTemplate=new AnMdcFormTemplate();
        anMdcFormTemplate.setDeptId(-1L);
        anMdcFormTemplate.setDr(String.valueOf(0));
        List<AnMdcFormTemplate> mdcFormTemplates = anMdcFormTemplateService.selectAnMdcFormTemplateList(anMdcFormTemplate);
        if (mdcFormTemplates.size()>0){
            res.addAll(mdcFormTemplates);
        }
        if (deptId!=null){
            anMdcFormTemplate.setDeptId(deptId);
            List<AnMdcFormTemplate> mdcFormTemplates1 = anMdcFormTemplateService.selectAnMdcFormTemplateList(anMdcFormTemplate);
            if(mdcFormTemplates1.size()>0){
                res.addAll(mdcFormTemplates1);
            }
        }
        return res;
    }

    @Override
    public AnMdcFormTemplate queryById(Long templateId) {
        return anMdcFormTemplateService.selectAnMdcFormTemplateById(templateId);
    }

    @Override
    public FormDataDto queryDetailsById(Long templateId) {
        FormDataDto mdcFormDataDto = new FormDataDto();
        AnMdcFormTemplate mdcFormTemplate = anMdcFormTemplateService.selectAnMdcFormTemplateById(templateId);
        if (mdcFormTemplate == null) {
            throw new BusinessException(String.format(ErrorCodeEnum.MDC10021037.msg(),templateId));
        }
        mdcFormDataDto.setTemplateId(mdcFormTemplate.getId());
        mdcFormDataDto.setSchemaId(mdcFormTemplate.getSchemaId());
        // 回显设备列表项
        Example example = new Example(AnMdcFormTemplateItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("templateId", mdcFormTemplate.getId());
        criteria.andEqualTo("type", "device");
        example.setOrderByClause("`sort` ASC");
        List<AnMdcFormTemplateItem> devices = mdcFormTemplateItemMapper.selectByExample(example);
        if (devices != null) {
            List<DeviceDesc> mdcDeviceDescs = new ArrayList<>();
            for (AnMdcFormTemplateItem mdcFormTemplateItem : devices) {
                DeviceDesc mdcDeviceDesc = new DeviceDesc();
                mdcDeviceDesc.setDevice(mdcFormTemplateItem.getContent());
                mdcDeviceDescs.add(mdcDeviceDesc);
            }
            mdcFormDataDto.setAssetList(mdcDeviceDescs);
        }
        // 回显巡检内容项列表
        Example example1 = new Example(AnMdcFormTemplateItem.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("templateId", mdcFormTemplate.getId());
        criteria1.andEqualTo("type", "inspcContent");
        example1.setOrderByClause("`sort` ASC");
        List<AnMdcFormTemplateItem> inspcs = mdcFormTemplateItemMapper.selectByExample(example1);
        if (inspcs != null) {
            List<InspcDetail> mdcInspcDetails = new ArrayList<>();
            for (AnMdcFormTemplateItem mdcFormTemplateItem : inspcs) {
                InspcDetail mdcInspcDetail = new InspcDetail();
                mdcInspcDetail.setItemContent(mdcFormTemplateItem.getContent());
                mdcInspcDetails.add(mdcInspcDetail);
            }
            mdcFormDataDto.setInspcDetailList(mdcInspcDetails);
        }
        return mdcFormDataDto;
    }

    @Override
    public Integer deleteById(Long templateId) {
        AnMdcFormTemplate mdcFormTemplate = anMdcFormTemplateService.selectAnMdcFormTemplateById(templateId);
        if (mdcFormTemplate == null) {
            throw new BusinessException(String.format(ErrorCodeEnum.MDC10021037.msg(),templateId));
        }
        // 先删除所有模板子项
        Example example = new Example(AnMdcFormTemplateItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("templateId", mdcFormTemplate.getId());
        mdcFormTemplateItemMapper.deleteByExample(example);
        // 再删除模板
        return anMdcFormTemplateService.deleteAnMdcFormTemplateById(mdcFormTemplate.getId());
    }
}
