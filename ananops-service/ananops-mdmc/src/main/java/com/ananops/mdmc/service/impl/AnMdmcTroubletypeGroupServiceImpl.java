package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTroubletypeGroupMapper;
import com.ananops.mdmc.domain.AnMdmcTroubletypeGroup;
import com.ananops.mdmc.service.IAnMdmcTroubletypeGroupService;
import com.ananops.common.core.text.Convert;

/**
 * 关联故障类型和组织Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTroubletypeGroupServiceImpl implements IAnMdmcTroubletypeGroupService
{
    @Autowired
    private AnMdmcTroubletypeGroupMapper anMdmcTroubletypeGroupMapper;

    /**
     * 查询关联故障类型和组织
     *
     * @param id 关联故障类型和组织ID
     * @return 关联故障类型和组织
     */
    @Override
    public AnMdmcTroubletypeGroup selectAnMdmcTroubletypeGroupById(Long id)
    {
        return anMdmcTroubletypeGroupMapper.selectAnMdmcTroubletypeGroupById(id);
    }

    /**
     * 查询关联故障类型和组织列表
     *
     * @param anMdmcTroubletypeGroup 关联故障类型和组织
     * @return 关联故障类型和组织
     */
    @Override
    public List<AnMdmcTroubletypeGroup> selectAnMdmcTroubletypeGroupList(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup)
    {
        return anMdmcTroubletypeGroupMapper.selectAnMdmcTroubletypeGroupList(anMdmcTroubletypeGroup);
    }

    /**
     * 新增关联故障类型和组织
     *
     * @param anMdmcTroubletypeGroup 关联故障类型和组织
     * @return 结果
     */
    @Override
    public int insertAnMdmcTroubletypeGroup(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup)
    {
        return anMdmcTroubletypeGroupMapper.insert(anMdmcTroubletypeGroup);
    }

    /**
     * 修改关联故障类型和组织
     *
     * @param anMdmcTroubletypeGroup 关联故障类型和组织
     * @return 结果
     */
    @Override
    public int updateAnMdmcTroubletypeGroup(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup)
    {
        return anMdmcTroubletypeGroupMapper.updateAnMdmcTroubletypeGroup(anMdmcTroubletypeGroup);
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
     * 删除关联故障类型和组织信息
     *
     * @param id 关联故障类型和组织ID
     * @return 结果
     */
    public int deleteAnMdmcTroubletypeGroupById(Long id)
    {
        return anMdmcTroubletypeGroupMapper.deleteAnMdmcTroubletypeGroupById(id);
    }
}
