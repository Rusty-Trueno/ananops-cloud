package com.ananops.activiti.mapper;

import java.util.List;

import com.ananops.activiti.vo.HiProcInsVo;

public interface HistoryMapper
{
    List<HiProcInsVo> getHiProcInsListDone(HiProcInsVo hiProcInsVo);
}