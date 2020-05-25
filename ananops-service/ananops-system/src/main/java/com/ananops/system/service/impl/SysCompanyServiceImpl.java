package com.ananops.system.service.impl;

import java.util.List;

import com.ananops.common.core.dto.LoginAuthDto;
import com.ananops.common.utils.DateUtils;
import com.ananops.common.utils.bean.UpdateInfoUtil;
import com.ananops.system.domain.SysDept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ananops.system.mapper.SysCompanyMapper;
import com.ananops.system.domain.SysCompany;
import com.ananops.system.service.ISysCompanyService;
import com.ananops.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 企业管理 Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
@Slf4j
public class SysCompanyServiceImpl implements ISysCompanyService {
    @Resource
    private SysCompanyMapper sysCompanyMapper;

    /**
     * 查询企业
     *
     * @param id 企业ID
     * @return 企业
     */
    @Override
    public SysCompany selectSysCompanyById(Long id) {
        return sysCompanyMapper.selectSysCompanyById(id);
    }

    /**
     * 查询企业列表
     *
     * @param sysCompany 企业
     * @return 企业
     */
    @Override
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany) {
        return sysCompanyMapper.selectSysCompanyList(sysCompany);
    }

    /**
     * 新增企业
     *
     * @param sysCompany 企业
     * @return 结果
     */
    @Override
    public int insertSysCompany(SysCompany sysCompany, LoginAuthDto user) {
        UpdateInfoUtil.setInsertInfo(sysCompany, user);
        sysCompany.setDeptId(user.getDeptId());
        return sysCompanyMapper.insert(sysCompany);
    }

    /**
     * 修改企业
     *
     * @param sysCompany 企业
     * @return 结果
     */
    @Override
    public int updateSysCompany(SysCompany sysCompany, LoginAuthDto user) {
        UpdateInfoUtil.setModifyInfo(sysCompany, user);
        return sysCompanyMapper.updateSysCompany(sysCompany);
    }

    /**
     * 删除企业对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByIds(String ids) {
        return sysCompanyMapper.deleteSysCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除企业信息
     *
     * @param id 企业ID
     * @return 结果
     */
    public int deleteSysCompanyById(Long id) {
        return sysCompanyMapper.deleteSysCompanyById(id);
    }

    @Override
    public SysCompany selectSysCompanyByDeptId(SysDept sysDept) {
        String ancestors = sysDept.getAncestors();
        Long deptId = sysDept.getDeptId();
        if (ancestors != null && !ancestors.equals("0")) {
            String[] ancestor = ancestors.split(",");
            deptId = Long.valueOf(ancestor[1]);
        }
        log.info("deptId: " + String.valueOf(deptId));
        return sysCompanyMapper.selectSysCompanyByDeptId(deptId);
    }

}
