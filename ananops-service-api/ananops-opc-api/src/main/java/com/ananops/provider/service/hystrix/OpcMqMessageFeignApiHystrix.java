/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OpcMqMessageFeignApiHystrix.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.service.hystrix;


import com.ananops.base.dto.MessageQueryDto;
import com.ananops.base.dto.MqMessageVo;
import com.ananops.provider.service.OpcMqMessageFeignApi;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The class Opc mq message feign api hystrix.
 *
 * @author ananops.com @gmail.com
 */
@Component
public class OpcMqMessageFeignApiHystrix implements OpcMqMessageFeignApi {

	@Override
	public Wrapper<List<String>> queryMessageKeyList(final List<String> messageKeyList) {
		return null;
	}

	@Override
	public Wrapper<PageInfo<MqMessageVo>> queryMessageListWithPage(final MessageQueryDto messageQueryDto) {
		return null;
	}
}
