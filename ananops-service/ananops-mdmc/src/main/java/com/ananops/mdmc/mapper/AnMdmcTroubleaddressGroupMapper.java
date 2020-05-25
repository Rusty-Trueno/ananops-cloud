package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcTroubleaddressGroup;
import java.util.List;

/**
 * 关联故障地址与组织Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface AnMdmcTroubleaddressGroupMapper extends BaseMapper<AnMdmcTroubleaddressGroup>
{
    /**
     * 查询关联故障地址与组织
     *
     * @param id 关联故障地址与组织ID
     * @return 关联故障地址与组织
     */
    public AnMdmcTroubleaddressGroup selectAnMdmcTroubleaddressGroupById(Long id);

    /**
     * 查询关联故障地址与组织列表
     *
     * @param anMdmcTroubleaddressGroup 关联故障地址与组织
     * @return 关联故障地址与组织集合
     */
    public List<AnMdmcTroubleaddressGroup> selectAnMdmcTroubleaddressGroupList(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup);

    /**
     * 新增关联故障地址与组织
     *
     * @param anMdmcTroubleaddressGroup 关联故障地址与组织
     * @return 结果
     */
    public int insertAnMdmcTroubleaddressGroup(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup);

    /**
     * 修改关联故障地址与组织
     *
     * @param anMdmcTroubleaddressGroup 关联故障地址与组织
     * @return 结果
     */
    public int updateAnMdmcTroubleaddressGroup(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup);

    /**
     * 删除关联故障地址与组织
     *
     * @param id 关联故障地址与组织ID
     * @return 结果
     */
    public int deleteAnMdmcTroubleaddressGroupById(Long id);

    /**
     * 批量删除关联故障地址与组织
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcTroubleaddressGroupByIds(String[] ids);
}
