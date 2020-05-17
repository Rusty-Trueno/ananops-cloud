package com.ananops.activiti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ananops.activiti.mapper.HistoryMapper;
import com.ananops.activiti.service.IHistoryInfoService;
import com.ananops.activiti.vo.HiProcInsVo;

/**
 * 
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
@Service
public class HistoryInfoServiceImpl implements IHistoryInfoService
{
    @Autowired
    private HistoryMapper historyMapper;

    /* (non-Javadoc)
     * @see com.ananops.activiti.service.IHistoryInfoService#getHiProcInsListDone(com.ananops.activiti.vo.HiProcInsVo)
     */
    @Override
    public List<HiProcInsVo> getHiProcInsListDone(HiProcInsVo hiProcInsVo)
    {
        return historyMapper.getHiProcInsListDone(hiProcInsVo);
    }
}
