package com.ananops.mdc.service.impl;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.core.text.Convert;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdc.domain.AnMdcSysDict;
import com.ananops.mdc.domain.AnMdcSysDictItem;
import com.ananops.mdc.dto.DictItemDto;
import com.ananops.mdc.dto.MdcAddDictItemDto;
import com.ananops.mdc.dto.SysDictItemsDto;
import com.ananops.mdc.enums.ErrorCodeEnum;
import com.ananops.mdc.enums.SysDictEnum;
import com.ananops.mdc.mapper.AnMdcSysDictItemMapper;
import com.ananops.mdc.mapper.AnMdcSysDictMapper;
import com.ananops.mdc.service.IAnMdcSysDictItemEnService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnMdcSysDictItemEnServiceImpl implements IAnMdcSysDictItemEnService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AnMdcSysDictItemServiceImpl AnMdcSysDictItemServiceImpl;

    @Autowired
    private AnMdcSysDictItemMapper anMdcSysDictItemMapper;

    @Autowired
    private AnMdcSysDictMapper anMdcSysDictMapper;

    @Override
    public AnMdcSysDictItem selectAnMdcSysDictItemById(Long id, LoginAuthDto loginAuthDto)
    {
        return AnMdcSysDictItemServiceImpl.selectAnMdcSysDictItemById(id);
    }

    @Override
    public List<AnMdcSysDictItem> selectAnMdcSysDictItemList(MdcAddDictItemDto mdcAddDictItemDto, LoginAuthDto loginAuthDto)
    {
        AnMdcSysDictItem anMdcSysDictItem=new AnMdcSysDictItem();
        BeanUtils.copyProperties(mdcAddDictItemDto,anMdcSysDictItem);
        logger.info("AnMdcSysDictItemEnServiceImpl.selectAnMdcSysDictItemList--{}",anMdcSysDictItem);
        return AnMdcSysDictItemServiceImpl.selectAnMdcSysDictItemList(anMdcSysDictItem);
    }

    @Override
    public MdcAddDictItemDto insertAnMdcSysDictItem(MdcAddDictItemDto mdcAddDictItemDto, LoginAuthDto loginAuthDto)
    {
        AnMdcSysDictItem anMdcSysDictItem=new AnMdcSysDictItem();
        // 校验填充的数据字段
        validateDictItem(mdcAddDictItemDto);
        BeanUtils.copyProperties(mdcAddDictItemDto,anMdcSysDictItem);
        UpdateInfoUtil.setInsertInfo(anMdcSysDictItem,loginAuthDto);
        Long dictId = anMdcSysDictItem.getDictId();
        if (dictId==null){
            throw new BusinessException(ErrorCodeEnum.MDC10021026.msg());
        }
        AnMdcSysDict dict =anMdcSysDictMapper.selectAnMdcSysDictById(dictId);
        if(dict==null){//如果没有此字典库
            throw new BusinessException(String.format(ErrorCodeEnum.MDC10021024.msg(),dictId));
        }
        int rows=AnMdcSysDictItemServiceImpl.insertAnMdcSysDictItem(anMdcSysDictItem);
        //判断返回码
        if(rows>0){
            BeanUtils.copyProperties(anMdcSysDictItem,mdcAddDictItemDto);
        }
        else{
            throw new BusinessException(ErrorCodeEnum.RDC100000000.msg());
        }
        return mdcAddDictItemDto;
    }
    private void validateDictItem(MdcAddDictItemDto addDictItemDto) {
        Preconditions.checkArgument(!StringUtils.isEmpty(addDictItemDto.getName()), ErrorCodeEnum.MDC10021034.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(addDictItemDto.getCode()), ErrorCodeEnum.MDC10021035.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(addDictItemDto.getSort()), ErrorCodeEnum.MDC10021036.msg());
    }

    @Override
    public MdcAddDictItemDto updateAnMdcSysDictItem(MdcAddDictItemDto mdcAddDictItemDto, LoginAuthDto loginAuthDto)
    {
        AnMdcSysDictItem anMdcSysDictItem=new AnMdcSysDictItem();
        // 校验填充的数据字段
        validateDictItem(mdcAddDictItemDto);
        BeanUtils.copyProperties(mdcAddDictItemDto,anMdcSysDictItem);
        UpdateInfoUtil.setInsertInfo(anMdcSysDictItem,loginAuthDto);
        Long itemId=anMdcSysDictItem.getId();
        AnMdcSysDictItem dictItem =anMdcSysDictItemMapper.selectAnMdcSysDictItemById(itemId);
        if(null==dictItem){//如果没有此字典项
            throw new BusinessException(String.format(ErrorCodeEnum.MDC10021025.msg(),itemId));
        }
        int rows=AnMdcSysDictItemServiceImpl.updateAnMdcSysDictItem(anMdcSysDictItem);
        //判断返回码
        if(rows>0){
            BeanUtils.copyProperties(anMdcSysDictItem,mdcAddDictItemDto);
        }
        else{
            throw new BusinessException(ErrorCodeEnum.RDC100000001.msg());
        }
        return mdcAddDictItemDto;
    }

    @Override
    public int deleteAnMdcSysDictItemByIds(String ids, LoginAuthDto loginAuthDto)
    {
        int rows=0;
        for(String id :Convert.toStrArray(ids)){
            if(id==null){
                throw new BusinessException(ErrorCodeEnum.MDC10021027.msg());
            }
            AnMdcSysDictItem dictItem=anMdcSysDictItemMapper.selectAnMdcSysDictItemById(Long.parseLong(id));
            if (dictItem==null){
                throw new BusinessException(String.format(ErrorCodeEnum.MDC10021025.msg(),id));
            }
            UpdateInfoUtil.setInsertInfo(dictItem,loginAuthDto);
            dictItem.setDr(String.valueOf(1));
            rows=rows+AnMdcSysDictItemServiceImpl.updateAnMdcSysDictItem(dictItem);
        }
        return rows;
    }

    @Override
    public SysDictItemsDto getSysDictItems(LoginAuthDto loginAuthDto) {

        SysDictItemsDto sysDictItemsDto = new SysDictItemsDto();
        // 该用户所属的组织ID
        Long deptId = loginAuthDto.getDeptId();

        // 装入故障类型数据子项
        Long troubleTypeDictId = SysDictEnum.TROUBLE_TYPE.getId();
        List<DictItemDto> troubleTypeDictItems = getDictItemList(troubleTypeDictId, deptId);
        if (!troubleTypeDictItems.isEmpty()) {
            sysDictItemsDto.setTroubleTypeList(troubleTypeDictItems);
        }
        // 装入故障位置数据子项
        Long troubleAddressDictId = SysDictEnum.TROUBLE_ADDRESS.getId();
        List<DictItemDto> troubleAddressDictItems = getDictItemList(troubleAddressDictId, deptId);
        if (!troubleAddressDictItems.isEmpty()) {
            sysDictItemsDto.setTroubleAddressList(troubleAddressDictItems);
        }
        // 装入设备类型数据子项
        Long deviceTypeDictId = SysDictEnum.DEVICE_TYPE.getId();
        List<DictItemDto> deviceTypeDictItems = getDictItemList(deviceTypeDictId, deptId);
        if (!deviceTypeDictItems.isEmpty()) {
            sysDictItemsDto.setDeviceTypeList(deviceTypeDictItems);
        }
        // 装入故障等级数据子项
        Long troubleLevelDictId = SysDictEnum.TROUBLE_LEVEL.getId();
        List<DictItemDto> troubleLevelDictItems = getDictItemList(troubleLevelDictId, deptId);
        if (!troubleLevelDictItems.isEmpty()) {
            sysDictItemsDto.setTroubleLevelList(troubleLevelDictItems);
        }
        // 装入紧急程度数据子项
        Long emergencyLevelDictId = SysDictEnum.EMERGENCY_LEVEL.getId();
        List<DictItemDto> emergencyLevelDictItems = getDictItemList(emergencyLevelDictId, deptId);
        if (!emergencyLevelDictItems.isEmpty()) {
            sysDictItemsDto.setEmergencyLevelList(emergencyLevelDictItems);
        }

        return sysDictItemsDto;
    }

    // 装入数据字典项
    private List<DictItemDto> getDictItemList(Long dictId, Long deptId) {
        List<AnMdcSysDictItem> troubleTypeDictItems = getDictItemListByDictIdAndGroupId(dictId, deptId);
        List<DictItemDto> dictItemDtos = new ArrayList<>();
        if (troubleTypeDictItems != null) {
            for (AnMdcSysDictItem mdcSysDictItem : troubleTypeDictItems) {
                DictItemDto dictItemDto = new DictItemDto();
                try {
                    BeanUtils.copyProperties(mdcSysDictItem, dictItemDto);
                } catch (Exception e) {
                    throw new BusinessException("字典项Dto与字典项属性拷贝异常");
                }
                dictItemDtos.add(dictItemDto);
            }
        }
        return dictItemDtos;
    }

    private List<AnMdcSysDictItem> getDictItemListByDictIdAndGroupId(Long dictId, Long deptId) {

        List<AnMdcSysDictItem> res = new ArrayList<>();

        if (dictId==null){
            throw new BusinessException(ErrorCodeEnum.MDC10021026.msg());
        }

        // 查询默认的
        AnMdcSysDictItem anMdcSysDictItem=new AnMdcSysDictItem();
        anMdcSysDictItem.setDictId(dictId);
        anMdcSysDictItem.setDeptId(-1L);
        List<AnMdcSysDictItem> sysDictItems = anMdcSysDictItemMapper.selectAnMdcSysDictItemList(anMdcSysDictItem);
        if (sysDictItems != null) {
            res.addAll(sysDictItems);
        }
        // 只有当用户的公司组织Id不为空时，查询用户自有的字典项
        if (deptId != null) {
            anMdcSysDictItem.setDeptId(deptId);
            List<AnMdcSysDictItem> userDictItems = anMdcSysDictItemMapper.selectAnMdcSysDictItemList(anMdcSysDictItem);
            res.addAll(userDictItems);
        }
        return res;
    }

}
