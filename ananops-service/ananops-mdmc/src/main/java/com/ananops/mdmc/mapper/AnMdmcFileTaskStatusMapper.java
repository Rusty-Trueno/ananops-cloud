package com.ananops.mdmc.mapper;

import com.ananops.common.core.dao.BaseMapper;
import com.ananops.mdmc.domain.AnMdmcFileTaskStatus;
import java.util.List;

/**
 * 不同状态工单上传文件Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface AnMdmcFileTaskStatusMapper extends BaseMapper<AnMdmcFileTaskStatus>
{
    /**
     * 查询不同状态工单上传文件
     *
     * @param id 不同状态工单上传文件ID
     * @return 不同状态工单上传文件
     */
    public AnMdmcFileTaskStatus selectAnMdmcFileTaskStatusById(Long id);

    /**
     * 查询不同状态工单上传文件列表
     *
     * @param anMdmcFileTaskStatus 不同状态工单上传文件
     * @return 不同状态工单上传文件集合
     */
    public List<AnMdmcFileTaskStatus> selectAnMdmcFileTaskStatusList(AnMdmcFileTaskStatus anMdmcFileTaskStatus);

    /**
     * 新增不同状态工单上传文件
     *
     * @param anMdmcFileTaskStatus 不同状态工单上传文件
     * @return 结果
     */
    public int insertAnMdmcFileTaskStatus(AnMdmcFileTaskStatus anMdmcFileTaskStatus);

    /**
     * 修改不同状态工单上传文件
     *
     * @param anMdmcFileTaskStatus 不同状态工单上传文件
     * @return 结果
     */
    public int updateAnMdmcFileTaskStatus(AnMdmcFileTaskStatus anMdmcFileTaskStatus);

    /**
     * 删除不同状态工单上传文件
     *
     * @param id 不同状态工单上传文件ID
     * @return 结果
     */
    public int deleteAnMdmcFileTaskStatusById(Long id);

    /**
     * 批量删除不同状态工单上传文件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnMdmcFileTaskStatusByIds(String[] ids);
}
