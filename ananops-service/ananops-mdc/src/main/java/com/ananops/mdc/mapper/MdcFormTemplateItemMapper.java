package com.ananops.mdc.mapper;

import com.ananops.common.core.mybatis.MyMapper;
import com.ananops.mdc.domain.AnMdcFormTemplateItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 表单模板项
 *
 * @author Bingyue Duan
 *
 * @version 1.0
 *
 * @date 2020/4/19 下午5:14
 */
@Mapper
@Component
public interface MdcFormTemplateItemMapper extends MyMapper<AnMdcFormTemplateItem> {
}