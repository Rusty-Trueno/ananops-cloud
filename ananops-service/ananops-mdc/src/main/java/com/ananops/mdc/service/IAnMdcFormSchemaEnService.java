package com.ananops.mdc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.dto.MdcFormSchemaDto;

import java.util.List;

public interface IAnMdcFormSchemaEnService {
    List<MdcFormSchemaDto> getInspcFormSchema(LoginAuthDto loginAuthDto);
}
