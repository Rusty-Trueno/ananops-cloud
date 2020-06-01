package com.ananops.pmc.controller;

import com.ananops.common.core.dto.BaseQuery;
import com.ananops.pmc.dto.PmcContractDto;
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
import com.ananops.pmc.domain.AnPmcContract;
import com.ananops.pmc.service.IAnPmcContractService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 合同管理 提供者
 * 
 * @author ananops
 * @date 2020-05-28
 */
@RestController
@RequestMapping("contract")
@Api("合同管理")
public class AnPmcContractController extends BaseController
{
	
	@Resource
	private IAnPmcContractService anPmcContractService;
	
	/**
	 * 查询
	 */
	@ApiOperation(value = "查询合同")
	@GetMapping("get/{id}")
	public AnPmcContract get(@PathVariable("id") Long id)
	{
		return anPmcContractService.selectAnPmcContractById(id);
		
	}
	
	/**
	 * 查询合同列表
	 */
	@ApiOperation(value = "查询合同列表")
	@GetMapping("list")
	public R list(AnPmcContract anPmcContract)
	{
		startPage();
        return result(anPmcContractService.selectAnPmcContractList(anPmcContract));
	}


//	@ApiOperation(value = "新增保存巡检任务子项")
//	@PostMapping("save")
//	public R addSave(@RequestBody ImcAddInspectionItemDto imcAddInspectionItemDto)
//	{
//		return R.data(anImcInspectionItemService.insertAnImcInspectionItem(imcAddInspectionItemDto,getLoginAuthDto()));
//	}


	/**
	 * 新增保存【请填写功能名称】
	 */
	@ApiOperation(value = "新增保存合同")
	@PostMapping("save")
	public R addSave(@RequestBody PmcContractDto pmcContractDto)
	{		
		return R.data(anPmcContractService.insertAnPmcContract(pmcContractDto,getLoginAuthDto()));
	}

	/**
	 * 修改保存【请填写功能名称】
	 */
	@ApiOperation(value = "修改保存合同")
	@PostMapping("update")
	public R editSave(@RequestBody AnPmcContract anPmcContract)
	{		
		return toAjax(anPmcContractService.updateAnPmcContract(anPmcContract,getLoginAuthDto()));
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除合同")
	@PostMapping("remove")
	public R remove(String ids)
	{		
		return toAjax(anPmcContractService.deleteAnPmcContractByIds(ids));
	}



	@ApiOperation(value = "获取某个部门或公司的合同列表")
	@GetMapping("getContractListByCompanyId/{companyId}")
	public R getContactListByCompanyId(@ApiParam(value = "公司id") @PathVariable Long companyId) {
		List<AnPmcContract> pmcContractList = anPmcContractService.getContactListByCompanyId(companyId);
		return R.data(pmcContractList);
	}

	@ApiOperation(value = "根据组织名模糊查询获取对应的合同列表")
	@GetMapping("getContractListByLikePartyAName/{partyAName}")
	public R getContractListByLikePartyAName(@ApiParam(value = "甲方组织名") @PathVariable String partyAName) {
		List<AnPmcContract> pmcContractList = anPmcContractService.getContractListByLikePartyAName(partyAName);
		return R.data(pmcContractList);
	}
	
	@ApiOperation(value = "根据组织名模糊查询获取对应的合同列表")
	@GetMapping("getContractListByLikePartyBName/{partyBName}")
	public R getContractListByLikePartyBName(@ApiParam(value = "甲方组织名") @PathVariable String partyBName) {
		List<AnPmcContract> pmcContractList = anPmcContractService.getContractListByLikePartyBName(partyBName);
		return R.data(pmcContractList);
	}

	@ApiOperation(value = "分页获取所有合同列表")
	@GetMapping("getContractListWithPage")
	public R getContractListWithPage(@ApiParam(value = "分页排序参数") @RequestBody BaseQuery baseQuery) {
		PageInfo pageInfo = anPmcContractService.getContractListWithPage(baseQuery);
		return R.data(pageInfo);
	}

	@ApiOperation(value = "根据合同id删除合同")
	@PostMapping("deleteContractById/{id}")
	public R deleteContractById(@PathVariable Long id) {
		return toAjax(anPmcContractService.deleteAnPmcContractById(id));

	}

	@ApiOperation(value = "获取甲乙双方签订的合同")
	@GetMapping("getContactByAB/{partyAId}/{partyBId}")
	public R getContactByAB(@ApiParam(value = "甲方id") @PathVariable Long partyAId, @ApiParam(value = "乙方id") @PathVariable Long partyBId) {
		List<AnPmcContract> pmcContractList = anPmcContractService.getContactByAB(partyAId,partyBId);
		return R.data(pmcContractList);
	}

	@ApiOperation(value = "获取合同总数")
	@GetMapping("getContractCount/{groupId}")
	public R getContractCount(@ApiParam(value = "组织id") @PathVariable Long groupId) {
		logger.info("获取合同总数");
		return toAjax(anPmcContractService.getContractCount(groupId));
	}
}
