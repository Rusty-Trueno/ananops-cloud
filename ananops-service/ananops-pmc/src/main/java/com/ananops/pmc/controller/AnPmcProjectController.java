package com.ananops.pmc.controller;

import com.ananops.common.core.dto.BaseQuery;
import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.bean.BeanUtils;
import com.ananops.pmc.domain.AnPmcProjectUser;
import com.ananops.pmc.dto.PmcProReqQueryDto;
import com.ananops.pmc.dto.PmcProjectDto;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.ananops.common.core.domain.R;
import com.ananops.common.core.controller.BaseController;
import com.ananops.pmc.domain.AnPmcProject;
import com.ananops.pmc.service.IAnPmcProjectService;

import java.util.List;

/**
 * 项目管理 提供者
 * 
 * @author ananops
 * @date 2020-05-28
 */
@RestController
@RequestMapping("project")
@Api("项目管理")
public class AnPmcProjectController extends BaseController
{
	
	@Autowired
	private IAnPmcProjectService anPmcProjectService;
	
	/**
	 * 查询
	 */
	@ApiOperation(value = "查询项目")
	@GetMapping("get/{id}")
	public AnPmcProject get(@PathVariable("id") Long id)
	{
		return anPmcProjectService.selectAnPmcProjectById(id);
		
	}
	
	/**
	 * 查询【请填写功能名称】列表
	 */
	@ApiOperation(value = "查询项目列表")
	@GetMapping("list")
	public R list(AnPmcProject anPmcProject)
	{
		startPage();
        return result(anPmcProjectService.selectAnPmcProjectList(anPmcProject));
	}
	
	
	/**
	 * 新增保存【请填写功能名称】
	 */
	@ApiOperation(value = "新增保存项目")
	@PostMapping("save")
	public R addSave(@RequestBody PmcProjectDto pmcProjectDto)
	{
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		AnPmcProject anPmcProject = new AnPmcProject();
		BeanUtils.copyProperties(pmcProjectDto,anPmcProject);
		return toAjax(anPmcProjectService.insertAnPmcProject(anPmcProject,loginAuthDto));
	}

	/**
	 * 修改保存【请填写功能名称】
	 */
	@ApiOperation(value = "修改保存项目")
	@PostMapping("update")
	public R editSave(@RequestBody AnPmcProject anPmcProject)
	{		
		return toAjax(anPmcProjectService.updateAnPmcProject(anPmcProject));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除项目")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anPmcProjectService.deleteAnPmcProjectByIds(ids));
	}


	@GetMapping("getProjectListByCompanyId/{companyId}")
	@ApiOperation(value = "获取某个公司或部门的项目列表")
	public R getProjectListByCompanyId(@PathVariable Long companyId){
		List<AnPmcProject> anPmcProjectList = anPmcProjectService.getProjectListByCompanyId(companyId);
		return R.data(anPmcProjectList);
	}

	@GetMapping("getProjectList")
	@ApiOperation(value = "获取某个组织某种类型的项目列表")
	public R getProjectList(@ApiParam(value = "分页排序参数") @RequestBody PmcProReqQueryDto pmcProReqQueryDto){
		logger.info("获取某个组织某种类型的项目列表: pmcProReqQueryDto={}", pmcProReqQueryDto);
		List<AnPmcProject> anPmcProjectList = anPmcProjectService.getProjectList(pmcProReqQueryDto);
		return R.data(anPmcProjectList);
	}

	@GetMapping("getProjectListWithPage")
	@ApiOperation(value = "分页获取所有项目列表")
	public R getProjectListWithPage(@ApiParam(value = "分页排序参数") @RequestBody BaseQuery baseQuery) {
		PageInfo pageInfo = anPmcProjectService.getProjectListWithPage(baseQuery);
		return R.data(pageInfo);
	}


	@GetMapping("getProjectByUserId/{userId}")
	@ApiOperation(value = "根据用户id获取项目信息")
	public R getProjectByUserId(@PathVariable Long userId){
		logger.info("查询项目信息,userId={}",userId);
		List<AnPmcProject> anPmcProjectList  = anPmcProjectService.getProjectByUserId(userId);
		return R.data(anPmcProjectList);
	}

	@GetMapping("getProjectByContractId/{contractId}")
	@ApiOperation(value = "根据合同Id获取项目列表")
	public R getProjectByContractId(@PathVariable Long contractId){
		logger.info("根据合同Id获取项目列表,contractId={}",contractId);
		List<AnPmcProject> anPmcProjectList  = anPmcProjectService.getProjectByContractId(contractId);
		return R.data(anPmcProjectList);
	}

	@PostMapping("addProUser")
	@ApiOperation(value = "添加项目用户关联信息")
	public R addProUser(@RequestBody AnPmcProjectUser anPmcProjectUser){
		return toAjax(anPmcProjectService.addProUser(anPmcProjectUser)) ;
	}

	@GetMapping("queryProUserByProjectId/{projectId}")
	@ApiOperation(value = "查看项目用户关联信息")
	public R queryProUserByProjectId(@PathVariable Long projectId){
		return R.data(anPmcProjectService.queryProUserByProjectId(projectId));
	}

	@PostMapping("deleteProUser")
	@ApiOperation(value = "删除项目用户关联信息")
	public R deleteProUser(@RequestBody AnPmcProjectUser anPmcProjectUser){
		return toAjax(anPmcProjectService.deleteProUser2(anPmcProjectUser));

	}

	@GetMapping("getProjectCount/{companyId}")
	@ApiOperation(value = "获取项目总数")
	public R getProjectCount(@ApiParam(value = "组织id") @PathVariable Long companyId) {
		logger.info("获取项目总数");
		return toAjax(anPmcProjectService.getProjectCount(companyId));

	}
	
}
