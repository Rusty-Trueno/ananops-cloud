package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcTroubletypeGroup;
import java.util.List;

/**
 * 关联故障类型和组织Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface AnMdmcTroubletypeGroupMapper extends BaseMapper<AnMdmcTroubletypeGroup>
{
    /**
     * 查询关联故障类型和组织
     *
     * @param id 关联故障类型和组织ID
     * @return 关联故障类型和组织
     */
    public AnMdmcTroubletypeGroup selectAnMdmcTroubletypeGroupById(Long id);

    /**
     * 查询关联故障类型和组织列表
     *
     * @param anMdmcTroubletypeGroup 关联故障类型和组织
     * @return 关联故障类型和组织集合
     */
    public List<AnMdmcTroubletypeGroup> selectAnMdmcTroubletypeGroupList(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup);

    /**
     * 新增关联故障类型和组织
     *
     * @param anMdmcTroubletypeGroup 关联故障类型和组织
     * @return 结果
     */
    public int insertAnMdmcTroubletypeGroup(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup);

    /**
     * 修改关联故障类型和组织
     *
     * @param anMdmcTroubletypeGroup 关联故障类型和组织
     * @return 结果
     */
    public int updateAnMdmcTroubletypeGroup(AnMdmcTroubletypeGroup anMdmcTroubletypeGroup);

    /**
     * 删除关联故障类型和组织
     *
     * @param id 关联故障类型和组织ID
     * @return 结果
     */
    public int deleteAnMdmcTroubletypeGroupById(Long id);

    /**
     * 批量删除关联故障类型和组织
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTroubletypeGroupByIds(String[] ids);
}
