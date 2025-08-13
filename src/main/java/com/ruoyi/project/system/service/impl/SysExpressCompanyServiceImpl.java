package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysExpressCompanyMapper;
import com.ruoyi.project.system.domain.SysExpressCompany;
import com.ruoyi.project.system.service.ISysExpressCompanyService;

/**
 * 快递公司信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-06
 */
@Service
public class SysExpressCompanyServiceImpl implements ISysExpressCompanyService 
{
    @Autowired
    private SysExpressCompanyMapper sysExpressCompanyMapper;

    /**
     * 查询快递公司信息
     * 
     * @param companyId 快递公司信息主键
     * @return 快递公司信息
     */
    @Override
    public SysExpressCompany selectSysExpressCompanyByCompanyId(Long companyId)
    {
        return sysExpressCompanyMapper.selectSysExpressCompanyByCompanyId(companyId);
    }

    /**
     * 查询快递公司信息列表
     * 
     * @param sysExpressCompany 快递公司信息
     * @return 快递公司信息
     */
    @Override
    public List<SysExpressCompany> selectSysExpressCompanyList(SysExpressCompany sysExpressCompany)
    {
        return sysExpressCompanyMapper.selectSysExpressCompanyList(sysExpressCompany);
    }

    /**
     * 新增快递公司信息
     * 
     * @param sysExpressCompany 快递公司信息
     * @return 结果
     */
    @Override
    public int insertSysExpressCompany(SysExpressCompany sysExpressCompany)
    {
        sysExpressCompany.setCreateTime(DateUtils.getNowDate());
        return sysExpressCompanyMapper.insertSysExpressCompany(sysExpressCompany);
    }

    /**
     * 修改快递公司信息
     * 
     * @param sysExpressCompany 快递公司信息
     * @return 结果
     */
    @Override
    public int updateSysExpressCompany(SysExpressCompany sysExpressCompany)
    {
        sysExpressCompany.setUpdateTime(DateUtils.getNowDate());
        return sysExpressCompanyMapper.updateSysExpressCompany(sysExpressCompany);
    }

    /**
     * 批量删除快递公司信息
     * 
     * @param companyIds 需要删除的快递公司信息主键
     * @return 结果
     */
    @Override
    public int deleteSysExpressCompanyByCompanyIds(Long[] companyIds)
    {
        return sysExpressCompanyMapper.deleteSysExpressCompanyByCompanyIds(companyIds);
    }

    /**
     * 删除快递公司信息信息
     * 
     * @param companyId 快递公司信息主键
     * @return 结果
     */
    @Override
    public int deleteSysExpressCompanyByCompanyId(Long companyId)
    {
        return sysExpressCompanyMapper.deleteSysExpressCompanyByCompanyId(companyId);
    }
}
