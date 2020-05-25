package com.ananops.mdmc.service.impl;

import java.util.List;
import com.ananops.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.mdmc.mapper.AnMdmcFileTaskStatusMapper;
import com.ananops.mdmc.domain.AnMdmcFileTaskStatus;
import com.ananops.mdmc.service.IAnMdmcFileTaskStatusService;
import com.ananops.common.core.text.Convert;

/**
 * 不同状态工单上传文件Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
public class AnMdmcFileTaskStatusServiceImpl implements IAnMdmcFileTaskStatusService
{
    @Autowired
    private AnMdmcFileTaskStatusMapper anMdmcFileTaskStatusMapper;

    /**
     * 查询不同状态工单上传文件
     *
     * @param id 不同状态工单上传文件ID
     * @return 不同状态工单上传文件
     */
    @Override
    public AnMdmcFileTaskStatus selectAnMdmcFileTaskStatusById(Long id)
    {
        return anMdmcFileTaskStatusMapper.selectAnMdmcFileTaskStatusById(id);
    }

    /**
     * 查询不同状态工单上传文件列表
     *
     * @param anMdmcFileTaskStatus 不同状态工单上传文件
     * @return 不同状态工单上传文件
     */
    @Override
    public List<AnMdmcFileTaskStatus> selectAnMdmcFileTaskStatusList(AnMdmcFileTaskStatus anMdmcFileTaskStatus)
    {
        return anMdmcFileTaskStatusMapper.selectAnMdmcFileTaskStatusList(anMdmcFileTaskStatus);
    }

    /**
     * 新增不同状态工单上传文件
     *
     * @param anMdmcFileTaskStatus 不同状态工单上传文件
     * @return 结果
     */
    @Override
    public int insertAnMdmcFileTaskStatus(AnMdmcFileTaskStatus anMdmcFileTaskStatus)
    {
        return anMdmcFileTaskStatusMapper.insert(anMdmcFileTaskStatus);
    }

    /**
     * 修改不同状态工单上传文件
     *
     * @param anMdmcFileTaskStatus 不同状态工单上传文件
     * @return 结果
     */
    @Override
    public int updateAnMdmcFileTaskStatus(AnMdmcFileTaskStatus anMdmcFileTaskStatus)
    {
        return anMdmcFileTaskStatusMapper.updateAnMdmcFileTaskStatus(anMdmcFileTaskStatus);
    }

    /**
     * 删除不同状态工单上传文件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnMdmcFileTaskStatusByIds(String ids)
    {
        return anMdmcFileTaskStatusMapper.deleteAnMdmcFileTaskStatusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除不同状态工单上传文件信息
     *
     * @param id 不同状态工单上传文件ID
     * @return 结果
     */
    public int deleteAnMdmcFileTaskStatusById(Long id)
    {
        return anMdmcFileTaskStatusMapper.deleteAnMdmcFileTaskStatusById(id);
    }
}
