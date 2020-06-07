package com.ananops.mdc.service.impl;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.text.Convert;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdc.domain.AnMdcSysDict;
import com.ananops.mdc.domain.AnMdcSysDictItem;
import com.ananops.mdc.dto.MdcAddDictDto;
import com.ananops.mdc.enums.ErrorCodeEnum;
import com.ananops.mdc.mapper.AnMdcSysDictItemMapper;
import com.ananops.mdc.mapper.AnMdcSysDictMapper;
import com.ananops.mdc.service.IAnMdcSysDictEnService;
import com.ananops.mdc.service.IAnMdcSysDictService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class AnMdcSysDictEnServiceImpl implements IAnMdcSysDictEnService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAnMdcSysDictService anMdcSysDictService;

    @Autowired
    private AnMdcSysDictMapper anMdcSysDictMapper;

    @Autowired
    private AnMdcSysDictItemMapper anMdcSysDictItemMapper;

    @Override
    public AnMdcSysDict selectAnMdcSysDictById(Long id, LoginAuthDto loginAuthDto)
    {
        logger.info("AnMdcSysDictEnServiceImpl.selectAnMdcSysDictById");
        return anMdcSysDictService.selectAnMdcSysDictById(id);
    }

    @Override
    public List<AnMdcSysDict> selectAnMdcSysDictList(MdcAddDictDto mdcAddDictDto, LoginAuthDto loginAuthDto)
    {
        AnMdcSysDict dict = new AnMdcSysDict();
        copyPropertiesWithIgnoreNullProperties(mdcAddDictDto,dict);
        List<AnMdcSysDict> res = new ArrayList<>();
        dict.setDr(String.valueOf(0));
        dict.setDeptId(-1L);
        List<AnMdcSysDict> dictList=anMdcSysDictService.selectAnMdcSysDictList(dict);
        if (dictList.size()>0){
            res.addAll(dictList);
        }
        if(mdcAddDictDto.getDeptId()!=null){
            dict.setDeptId(mdcAddDictDto.getDeptId());
            List<AnMdcSysDict> dictList1=anMdcSysDictService.selectAnMdcSysDictList(dict);
            if(dictList1.size()>0){
                res.addAll(dictList1);
            }
        }
        return res;
    }

    @Override
    public MdcAddDictDto insertAnMdcSysDict(MdcAddDictDto mdcAddDictDto, LoginAuthDto loginAuthDto)
    {
        AnMdcSysDict dict = new AnMdcSysDict();
        copyPropertiesWithIgnoreNullProperties(mdcAddDictDto,dict);
        //UpdateInfoUtil.setInsertInfo(dict,loginAuthDto);
        Preconditions.checkArgument(!StringUtils.isEmpty(mdcAddDictDto.getName()), ErrorCodeEnum.MDC10021033.msg());
        if(mdcAddDictDto.getId()==null){
            anMdcSysDictService.insertAnMdcSysDict(dict);
        }
        else {
            AnMdcSysDict t =anMdcSysDictMapper.selectAnMdcSysDictById(mdcAddDictDto.getId());
            if (t == null) {
                throw new BusinessException(String.format(ErrorCodeEnum.MDC10021024.msg(),mdcAddDictDto.getId()));
            }
            anMdcSysDictService.updateAnMdcSysDict(dict);
        }
        BeanUtils.copyProperties(dict,mdcAddDictDto);
        return mdcAddDictDto;
    }
    private void copyPropertiesWithIgnoreNullProperties(Object source, Object target){
        String[] ignore = getNullPropertyNames(source);
        BeanUtils.copyProperties(source, target, ignore);
    }
    private String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Override
    public int deleteAnMdcSysDictByIds(String ids, LoginAuthDto loginAuthDto)
    {
        int rows=0;
        for(String id :Convert.toStrArray(ids)){
            if(id==null){
                throw new BusinessException(ErrorCodeEnum.MDC10021026.msg());
            }
            AnMdcSysDict dict=anMdcSysDictMapper.selectAnMdcSysDictById(Long.parseLong(id));
            if (dict==null){
                throw new BusinessException(String.format(ErrorCodeEnum.MDC10021024.msg(),id));
            }
            UpdateInfoUtil.setInsertInfo(dict,loginAuthDto);
            dict.setDr(String.valueOf(1));
            rows=rows+anMdcSysDictService.updateAnMdcSysDict(dict);
            AnMdcSysDictItem anMdcSysDictItem=new AnMdcSysDictItem();
            anMdcSysDictItem.setDictId(Long.parseLong(id));
            List<AnMdcSysDictItem> dictItemList=anMdcSysDictItemMapper.selectAnMdcSysDictItemList(anMdcSysDictItem);
            if (dictItemList.size()>0){
                for (AnMdcSysDictItem dictItem:dictItemList){
                    UpdateInfoUtil.setInsertInfo(dictItem,loginAuthDto);
                    dictItem.setDr(String.valueOf(1));
                    anMdcSysDictItemMapper.updateAnMdcSysDictItem(dictItem);
                }
            }
        }
        return rows;
    }
}
