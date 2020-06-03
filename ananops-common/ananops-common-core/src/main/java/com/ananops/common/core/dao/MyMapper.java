/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MyMapper.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.ananops.common.core.dao;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


/**
 * The interface My mapper.
 *
 * @param <T> the type parameter @author ananops.net@gmail.com
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T>, InsertListMapper<T>, ConditionMapper<T> {
}
