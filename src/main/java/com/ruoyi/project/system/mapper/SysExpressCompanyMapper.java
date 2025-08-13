package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysExpressCompany;

/**
 * 快递公司信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-06
 */
public interface SysExpressCompanyMapper 
{
    /**
     * 查询快递公司信息
     * 
     * @param companyId 快递公司信息主键
     * @return 快递公司信息
     */
    public SysExpressCompany selectSysExpressCompanyByCompanyId(Long companyId);

    /**
     * 查询快递公司信息列表
     * 
     * @param sysExpressCompany 快递公司信息
     * @return 快递公司信息集合
     */
    public List<SysExpressCompany> selectSysExpressCompanyList(SysExpressCompany sysExpressCompany);

    /**
     * 新增快递公司信息
     * 
     * @param sysExpressCompany 快递公司信息
     * @return 结果
     */
    public int insertSysExpressCompany(SysExpressCompany sysExpressCompany);

    /**
     * 修改快递公司信息
     * 
     * @param sysExpressCompany 快递公司信息
     * @return 结果
     */
    public int updateSysExpressCompany(SysExpressCompany sysExpressCompany);

    /**
     * 删除快递公司信息
     * 
     * @param companyId 快递公司信息主键
     * @return 结果
     */
    public int deleteSysExpressCompanyByCompanyId(Long companyId);

    /**
     * 批量删除快递公司信息
     * 
     * @param companyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysExpressCompanyByCompanyIds(Long[] companyIds);
}
