package com.ananops.mdmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.exception.BusinessException;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.mdmc.domain.AnMdmcTroubleaddressGroup;
import com.ananops.mdmc.dto.MdmcAddTroubleInfoDto;
import com.ananops.mdmc.dto.MdmcTroubleAddressDto;
import com.ananops.mdmc.dto.MdmcTroubleTypeDto;
import com.ananops.mdmc.mapper.AnMdmcTroubleaddressGroupMapper;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTroubletypeGroupMapper;
import com.ananops.mdmc.domain.AnMdmcTroubletypeGroup;
import com.ananops.mdmc.service.IAnMdmcTroubleInfoService;
import com.ananops.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 关联故障类型和组织Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTroubleInfoServiceImpl implements IAnMdmcTroubleInfoService
{
    @Resource
    private AnMdmcTroubletypeGroupMapper anMdmcTroubletypeGroupMapper;

    @Resource
    private AnMdmcTroubleaddressGroupMapper anMdmcTroubleaddressGroupMapper;

    /**
     * 根据用户id返回故障类型列表和故障位置列表
     *
     * @param id 用户id
     * @return 关联故障类型和组织
     */
    @Override
    public MdmcAddTroubleInfoDto selectAnMdmcTroubleInfoById(Long id)
    {
        if(id==null){
            throw new BusinessException("用户id为空");
        }
        Long groupId=0L;
        //todo 调用uac获取组织id
        MdmcAddTroubleInfoDto mdmcAddTroubleInfoDto=new MdmcAddTroubleInfoDto();
        mdmcAddTroubleInfoDto.setUserId(id);
        Example example=new Example(AnMdmcTroubleaddressGroup.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("groupId",groupId);
        List<MdmcTroubleAddressDto> troubleAddressDtoList=new ArrayList<>();
        List<AnMdmcTroubleaddressGroup> troubleAddressList=anMdmcTroubleaddressGroupMapper.selectByExample(example);
        if (troubleAddressList!=null){
            for (AnMdmcTroubleaddressGroup troubleAddress:troubleAddressList){
                MdmcTroubleAddressDto troubleAddressDto=new MdmcTroubleAddressDto();
                org.springframework.beans.BeanUtils.copyProperties(troubleAddress,troubleAddressDto);
                troubleAddressDtoList.add(troubleAddressDto);
            }
        }
        else {
            List<AnMdmcTroubleaddressGroup> troubleAddressList1=anMdmcTroubleaddressGroupMapper.selectAll();
            for (int i=0;i<5;i++){
                MdmcTroubleAddressDto troubleAddressDto1=new MdmcTroubleAddressDto();
                if(troubleAddressList1!=null){
                    AnMdmcTroubleaddressGroup troubleAddress1=troubleAddressList1.get(i);
                    org.springframework.beans.BeanUtils.copyProperties(troubleAddress1,troubleAddressDto1);
                    troubleAddressDtoList.add(troubleAddressDto1);
                }
            }
        }

        mdmcAddTroubleInfoDto.setTroubleAddressList(troubleAddressDtoList);
        Example example1=new Example(AnMdmcTroubletypeGroup.class);
        Example.Criteria criteria1=example1.createCriteria();
        criteria1.andEqualTo("groupId",groupId);
        List<MdmcTroubleTypeDto> troubleTypeList=new ArrayList<>();
        List<AnMdmcTroubletypeGroup> mdmcTroubleTypeList=anMdmcTroubletypeGroupMapper.selectByExample(example1);
        if (mdmcTroubleTypeList!=null){
            for (AnMdmcTroubletypeGroup troubleType:mdmcTroubleTypeList){
                MdmcTroubleTypeDto typeDto=new MdmcTroubleTypeDto();
                BeanUtils.copyProperties(troubleType,typeDto);
                troubleTypeList.add(typeDto);
            }
        }
        else{
            List<AnMdmcTroubletypeGroup> troubleTypes=anMdmcTroubletypeGroupMapper.selectAll();
            for (int i=0;i<3;i++){
                if (troubleTypes!=null){
                    MdmcTroubleTypeDto typeDto=new MdmcTroubleTypeDto();
                    AnMdmcTroubletypeGroup troubleType=troubleTypes.get(i);
                    BeanUtils.copyProperties(troubleType,typeDto);
                    troubleTypeList.add(typeDto);
                }
            }
        }
        mdmcAddTroubleInfoDto.setTroubleTypeList(troubleTypeList);
        return mdmcAddTroubleInfoDto;
    }

    /**
     * 组织录入故障类型和位置
     *
     * @param troubleInfoDto 关联故障类型和组织
     * @return 结果
     */
    @Override
    public MdmcAddTroubleInfoDto insertAnMdmcTroubletypeGroup(MdmcAddTroubleInfoDto troubleInfoDto, LoginAuthDto loginAuthDto)
    {
        Long userId=troubleInfoDto.getUserId();
        if (userId==null){
            throw new BusinessException("用户id不能是空");
        }
        //todo 调用uac获取组织id
        List<MdmcTroubleTypeDto> troubleTypeList=troubleInfoDto.getTroubleTypeList();
        List<MdmcTroubleAddressDto> troubleAddrssList=troubleInfoDto.getTroubleAddressList();
        if (troubleAddrssList.isEmpty() && troubleTypeList.isEmpty()){
            throw  new BusinessException("故障类型和故障位置不能是空");
        }

        if (troubleTypeList.size()>0){
            for (MdmcTroubleTypeDto troubleType:troubleTypeList){
                if(troubleType.getTroubleType().length()>0){
                    AnMdmcTroubletypeGroup mdmcTroubleType=new AnMdmcTroubletypeGroup();
                    //todo set组织id
                    mdmcTroubleType.setTroubleType(troubleType.getTroubleType());
                    UpdateInfoUtil.setInsertInfo(mdmcTroubleType,loginAuthDto);
                    anMdmcTroubletypeGroupMapper.insert(mdmcTroubleType);
                    Long typeId=mdmcTroubleType.getId();
                    troubleType.setId(typeId);
                }
            }
        }
        if (troubleAddrssList.size()>0){
            for (MdmcTroubleAddressDto troubleAddressDto:troubleAddrssList){
                if (troubleAddressDto!=null && (troubleAddressDto.getTroubleLatitude()!=null
                        ||troubleAddressDto.getTroubleAddress()!=null)){
                    AnMdmcTroubleaddressGroup mdmcTroubleAddress=new AnMdmcTroubleaddressGroup();
                    //todo set组织id
                    BeanUtils.copyProperties(troubleAddressDto,mdmcTroubleAddress);
                    UpdateInfoUtil.setInsertInfo(mdmcTroubleAddress,loginAuthDto);
                    anMdmcTroubleaddressGroupMapper.insert(mdmcTroubleAddress);
                    Long addressId=mdmcTroubleAddress.getId();
                    troubleAddressDto.setId(addressId);
                }

            }
        }

        return troubleInfoDto;
    }


    /**
     * 删除关联故障类型和组织对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTroubletypeGroupByIds(String ids)
    {
        return anMdmcTroubletypeGroupMapper.deleteAnMdmcTroubletypeGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除故障类型
     *
     * @param id 故障类型ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTroubletypeById(Long id) {
        return anMdmcTroubletypeGroupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除故障位置
     *
     * @param id 故障位置ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTroubleaddressById(Long id) {
        return anMdmcTroubleaddressGroupMapper.deleteByPrimaryKey(id);
    }

}
