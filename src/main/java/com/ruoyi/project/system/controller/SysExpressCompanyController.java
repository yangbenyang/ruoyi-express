package com.ruoyi.project.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.SysExpressCompany;
import com.ruoyi.project.system.service.ISysExpressCompanyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 快递公司信息Controller
 * 
 * @author ruoyi
 * @date 2025-08-06
 */
@RestController
@RequestMapping("/system/company")
public class SysExpressCompanyController extends BaseController
{
    @Autowired
    private ISysExpressCompanyService sysExpressCompanyService;

    /**
     * 查询快递公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExpressCompany sysExpressCompany)
    {
        startPage();
        List<SysExpressCompany> list = sysExpressCompanyService.selectSysExpressCompanyList(sysExpressCompany);
        return getDataTable(list);
    }

    /**
     * 导出快递公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:company:export')")
    @Log(title = "快递公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysExpressCompany sysExpressCompany)
    {
        List<SysExpressCompany> list = sysExpressCompanyService.selectSysExpressCompanyList(sysExpressCompany);
        ExcelUtil<SysExpressCompany> util = new ExcelUtil<SysExpressCompany>(SysExpressCompany.class);
        util.exportExcel(response, list, "快递公司信息数据");
    }

    /**
     * 获取快递公司信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:company:query')")
    @GetMapping(value = "/{companyId}")
    public AjaxResult getInfo(@PathVariable("companyId") Long companyId)
    {
        return success(sysExpressCompanyService.selectSysExpressCompanyByCompanyId(companyId));
    }

    /**
     * 新增快递公司信息
     */
    @PreAuthorize("@ss.hasPermi('system:company:add')")
    @Log(title = "快递公司信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExpressCompany sysExpressCompany)
    {
        return toAjax(sysExpressCompanyService.insertSysExpressCompany(sysExpressCompany));
    }

    /**
     * 修改快递公司信息
     */
    @PreAuthorize("@ss.hasPermi('system:company:edit')")
    @Log(title = "快递公司信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExpressCompany sysExpressCompany)
    {
        return toAjax(sysExpressCompanyService.updateSysExpressCompany(sysExpressCompany));
    }

    /**
     * 删除快递公司信息
     */
    @PreAuthorize("@ss.hasPermi('system:company:remove')")
    @Log(title = "快递公司信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyIds}")
    public AjaxResult remove(@PathVariable Long[] companyIds)
    {
        return toAjax(sysExpressCompanyService.deleteSysExpressCompanyByCompanyIds(companyIds));
    }
}
