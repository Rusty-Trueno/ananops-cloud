package com.ananops.mdc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.mdc.domain.AnMdcFormTemplate;
import com.ananops.mdc.dto.FormDataDto;
import com.ananops.mdc.service.IAnMdcFormTemplateEnService;
import com.ananops.mdc.service.IAnMdcFormTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 表单模板 提供者
 * 
 * @author ananops
 * @date 2020-06-04
 */
@RestController
@RequestMapping("formTemplate")
@Api("表单模板")
public class AnMdcFormTemplateController extends BaseController
{

	@Autowired
	private IAnMdcFormTemplateEnService anMdcFormTemplateEnService;
	
	/**
	 * 查询表单模板
	 */
	@ApiOperation(value = "查询表单模板详情")
	@GetMapping("get/{templateId}")
	public R get(@PathVariable("templateId") Long templateId)
	{
		return R.data(anMdcFormTemplateEnService.queryById(templateId));
		
	}

	@GetMapping(value = "/queryDetailsById/{templateId}")
	@ApiOperation(httpMethod = "GET",value = "查询表单模板详情（包括内容项）")
	public R queryDetailsById(@PathVariable Long templateId) {
		return R.data(anMdcFormTemplateEnService.queryDetailsById(templateId));
	}
	
	/**
	 * 查询表单模板列表
	 */
	@ApiOperation(value = "查询表单模板列表")
	@GetMapping("list")
	public R list()
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		startPage();
        return result(anMdcFormTemplateEnService.getFormTemplateList(loginAuthDto));
	}
	
	
	/**
	 * 新增保存表单模板
	 */
	@ApiOperation(value = "新增保存表单模板")
	@PostMapping("save")
	public R addSave(@ApiParam(name = "mdcFormDataDto",value = "添加或编辑动态表单模板")@RequestBody JSONObject jsonObject)
	{
		FormDataDto mdcFormDataDto = JSON.parseObject(jsonObject.toJSONString(), FormDataDto.class);
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		return R.data(anMdcFormTemplateEnService.saveFormTemplate(mdcFormDataDto, loginAuthDto));
	}

	/**
	 * 修改保存表单模板
	 */
	@ApiOperation(value = "修改保存表单模板")
	@PostMapping("update")
	public R editSave(@ApiParam(name = "mdcFormTemplate",value = "添加或编辑动态表单模板单项")@RequestBody AnMdcFormTemplate mdcFormTemplate)
	{
		LoginAuthDto loginAuthDto =getLoginAuthDto();
		return R.data(anMdcFormTemplateEnService.updateFormTemplate(mdcFormTemplate, loginAuthDto));
	}

	/**
	 * 根据表单模板Id删除动态表单模板
	 *
	 * @return 返回
	 */
	@PostMapping(value = "/deleteFormTemplateById/{templateId}")
	@ApiOperation(httpMethod = "GET",value = "根据表单模板Id删除动态表单模板")
	public R deleteById(@PathVariable Long templateId) {
		return R.data(anMdcFormTemplateEnService.deleteById(templateId));
	}
	
}
