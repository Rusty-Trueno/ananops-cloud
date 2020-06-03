package com.ananops.provider.mapper;

import com.ananops.common.core.dao.MyMapper;
import com.ananops.provider.model.domain.TpcMqSubscribe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TpcMqSubscribeMapper extends MyMapper<TpcMqSubscribe> {
}