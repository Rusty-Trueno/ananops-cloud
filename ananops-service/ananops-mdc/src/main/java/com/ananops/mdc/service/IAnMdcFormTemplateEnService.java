package com.ananops.mdc.service;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcFormTemplate;
import com.ananops.mdc.dto.FormDataDto;

import java.util.List;

public interface IAnMdcFormTemplateEnService {
    Integer saveFormTemplate(FormDataDto mdcFormDataDto, LoginAuthDto loginAuthDto);

    Integer updateFormTemplate(AnMdcFormTemplate mdcFormTemplate, LoginAuthDto loginAuthDto);

    List<AnMdcFormTemplate> getFormTemplateList(LoginAuthDto loginAuthDto);

    AnMdcFormTemplate queryById(Long templateId);

    FormDataDto queryDetailsById(Long templateId);

    Integer deleteById(Long templateId);
}
