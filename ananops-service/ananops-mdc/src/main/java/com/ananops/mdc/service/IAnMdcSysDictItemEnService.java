package com.ananops.mdc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcSysDictItem;
import com.ananops.mdc.dto.MdcAddDictItemDto;
import com.ananops.mdc.dto.SysDictItemsDto;

import java.util.List;

public interface IAnMdcSysDictItemEnService {
    AnMdcSysDictItem selectAnMdcSysDictItemById(Long id, LoginAuthDto loginAuthDto);

    MdcAddDictItemDto insertAnMdcSysDictItem(MdcAddDictItemDto mdcAddDictItemDto, LoginAuthDto loginAuthDto);

    MdcAddDictItemDto updateAnMdcSysDictItem(MdcAddDictItemDto mdcAddDictItemDto, LoginAuthDto loginAuthDto);

    List<AnMdcSysDictItem> selectAnMdcSysDictItemList(MdcAddDictItemDto mdcAddDictItemDto, LoginAuthDto loginAuthDto);

    int deleteAnMdcSysDictItemByIds(String ids, LoginAuthDto loginAuthDto);

    /**
     * 为维修工单页面提供准备数据
     *
     * @param userId 用户Id
     *
     * @return
     */
    SysDictItemsDto getSysDictItems(LoginAuthDto loginAuthDto);
}
