package com.ananops.mdmc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdmc.domain.AnMdmcTroubletypeGroup;
import com.ananops.mdmc.dto.MdmcAddTroubleInfoDto;

import java.util.List;

/**
 * 关联故障类型和组织Service接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface IAnMdmcTroubleInfoService
{
    /**
     * 根据用户id返回故障类型列表和故障位置列表
     *
     * @param id 用户id
     * @return 关联故障类型和组织
     */
    public MdmcAddTroubleInfoDto selectAnMdmcTroubleInfoById(Long id);


    /**
     * 组织录入故障类型和位置
     *
     * @param troubleInfoDto 关联故障类型和组织
     * @return 结果
     */
    public MdmcAddTroubleInfoDto insertAnMdmcTroubletypeGroup(MdmcAddTroubleInfoDto troubleInfoDto, LoginAuthDto loginAuthDto);


    /**
     * 批量删除关联故障类型和组织
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTroubletypeGroupByIds(String ids);

    /**
     * 删除故障类型
     *
     * @param id 关联故障类型和组织ID
     * @return 结果
     */
    public int deleteAnMdmcTroubletypeById(Long id);

    /**
     * 删除故障类型
     *
     * @param id 关联故障类型和组织ID
     * @return 结果
     */
    public int deleteAnMdmcTroubleaddressById(Long id);
}
