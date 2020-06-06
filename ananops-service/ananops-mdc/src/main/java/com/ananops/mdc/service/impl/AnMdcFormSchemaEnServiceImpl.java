package com.ananops.mdc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcFormSchema;
import com.ananops.mdc.dto.MdcFormSchemaDto;
import com.ananops.mdc.service.IAnMdcFormSchemaEnService;
import com.ananops.mdc.service.IAnMdcFormSchemaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class AnMdcFormSchemaEnServiceImpl implements IAnMdcFormSchemaEnService {

    @Autowired
    private IAnMdcFormSchemaService anMdcFormSchemaService;

    @Override
    public List<MdcFormSchemaDto> getInspcFormSchema(LoginAuthDto loginAuthDto) {
        // 这里默认只能管理员登录，管理员账号直接挂在公司组织下的，所以用户组织就是公司组织
        Long deptId = loginAuthDto.getDeptId();
        List<AnMdcFormSchema> res = new ArrayList<>();
        AnMdcFormSchema anMdcFormSchema=new AnMdcFormSchema();
        anMdcFormSchema.setDeptId(-1L);
        anMdcFormSchema.setDr(String.valueOf(0));
        List<AnMdcFormSchema> formSchemasList= anMdcFormSchemaService.selectAnMdcFormSchemaList(anMdcFormSchema);
        if (formSchemasList.size()>0){
            res.addAll(formSchemasList);
        }
        if (deptId!=null){
            anMdcFormSchema.setDeptId(deptId);
            List<AnMdcFormSchema> formSchemasList1 = anMdcFormSchemaService.selectAnMdcFormSchemaList(anMdcFormSchema);
            if(formSchemasList1.size()>0){
                res.addAll(formSchemasList1);
            }
        }
        return translateJSON(res);
    }

    // 将存在数据库中的JSON字符串转换为JSON对象
    @SuppressWarnings("unchecked")
    private List<MdcFormSchemaDto> translateJSON(List<AnMdcFormSchema> mdcFormSchemas) {
        List<MdcFormSchemaDto> res = new ArrayList<>();
        if (mdcFormSchemas != null) {
            for (AnMdcFormSchema mdcFormSchema : mdcFormSchemas) {
                MdcFormSchemaDto mdcFormSchemaDto = new MdcFormSchemaDto();
                BeanUtils.copyProperties(mdcFormSchema, mdcFormSchemaDto);
                // 这里为了保证序列化之后的JSON顺序与字符串字段的存储顺序一致
                LinkedHashMap<String, Object> propsSchema = JSON.parseObject(mdcFormSchema.getPropsSchema(),LinkedHashMap.class, Feature.OrderedField);
                JSONObject propsSchemaJsonObject = new JSONObject(true);
                propsSchemaJsonObject.putAll(propsSchema);
                mdcFormSchemaDto.setPropsSchema(propsSchemaJsonObject);
                LinkedHashMap<String, Object> uiSchema = JSON.parseObject(mdcFormSchema.getUiSchema(),LinkedHashMap.class, Feature.OrderedField);
                JSONObject uiSchemaJsonObject=new JSONObject(true);
                uiSchemaJsonObject.putAll(uiSchema);
                mdcFormSchemaDto.setUiSchema(uiSchemaJsonObject);
                res.add(mdcFormSchemaDto);
            }
        }
        return res;
    }
}
