package com.ananops.mdc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcSysDict;
import com.ananops.mdc.dto.MdcAddDictDto;

import java.util.List;

public interface IAnMdcSysDictEnService {
    AnMdcSysDict selectAnMdcSysDictById(Long id, LoginAuthDto loginAuthDto);

    MdcAddDictDto insertAnMdcSysDict(MdcAddDictDto mdcAddDictDto, LoginAuthDto loginAuthDto);

    List<AnMdcSysDict> selectAnMdcSysDictList(MdcAddDictDto mdcAddDictDto, LoginAuthDto loginAuthDto);

    int deleteAnMdcSysDictByIds(String ids, LoginAuthDto loginAuthDto);
}
