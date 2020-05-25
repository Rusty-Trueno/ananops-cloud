package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcTroubleaddressGroupMapper;
import com.ananops.mdmc.domain.AnMdmcTroubleaddressGroup;
import com.ananops.mdmc.service.IAnMdmcTroubleaddressGroupService;
import com.ananops.common.core.text.Convert;

/**
 * 关联故障地址与组织Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcTroubleaddressGroupServiceImpl implements IAnMdmcTroubleaddressGroupService
{
    @Autowired
    private AnMdmcTroubleaddressGroupMapper anMdmcTroubleaddressGroupMapper;

    /**
     * 查询关联故障地址与组织
     *
     * @param id 关联故障地址与组织ID
     * @return 关联故障地址与组织
     */
    @Override
    public AnMdmcTroubleaddressGroup selectAnMdmcTroubleaddressGroupById(Long id)
    {
        return anMdmcTroubleaddressGroupMapper.selectAnMdmcTroubleaddressGroupById(id);
    }

    /**
     * 查询关联故障地址与组织列表
     *
     * @param anMdmcTroubleaddressGroup 关联故障地址与组织
     * @return 关联故障地址与组织
     */
    @Override
    public List<AnMdmcTroubleaddressGroup> selectAnMdmcTroubleaddressGroupList(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup)
    {
        return anMdmcTroubleaddressGroupMapper.selectAnMdmcTroubleaddressGroupList(anMdmcTroubleaddressGroup);
    }

    /**
     * 新增关联故障地址与组织
     *
     * @param anMdmcTroubleaddressGroup 关联故障地址与组织
     * @return 结果
     */
    @Override
    public int insertAnMdmcTroubleaddressGroup(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup)
    {
        return anMdmcTroubleaddressGroupMapper.insert(anMdmcTroubleaddressGroup);
    }

    /**
     * 修改关联故障地址与组织
     *
     * @param anMdmcTroubleaddressGroup 关联故障地址与组织
     * @return 结果
     */
    @Override
    public int updateAnMdmcTroubleaddressGroup(AnMdmcTroubleaddressGroup anMdmcTroubleaddressGroup)
    {
        return anMdmcTroubleaddressGroupMapper.updateAnMdmcTroubleaddressGroup(anMdmcTroubleaddressGroup);
    }

    /**
     * 删除关联故障地址与组织对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcTroubleaddressGroupByIds(String ids)
    {
        return anMdmcTroubleaddressGroupMapper.deleteAnMdmcTroubleaddressGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除关联故障地址与组织信息
     *
     * @param id 关联故障地址与组织ID
     * @return 结果
     */
    public int deleteAnMdmcTroubleaddressGroupById(Long id)
    {
        return anMdmcTroubleaddressGroupMapper.deleteAnMdmcTroubleaddressGroupById(id);
    }
}
