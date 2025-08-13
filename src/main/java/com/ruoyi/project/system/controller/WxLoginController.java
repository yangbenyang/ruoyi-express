package com.ruoyi.project.system.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/wx")
public class WxLoginController {

    @Value("${wx.mini.appid}")
    private String appid;

    @Value("${wx.mini.secret}")
    private String secret;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysPermissionService permissionService;

    /**
     * 微信小程序登录，code换openid和token
     */
    @Anonymous
    @PostMapping("/auth")
    @SuppressWarnings("unchecked")
    public AjaxResult wxAuth(@RequestBody Map<String, String> param) throws Exception {
        String code = param.get("code");
        if (code == null) {
            return AjaxResult.error("code不能为空");
        }
        // 请求微信服务器换openid
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + code
                + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String wxRespStr = restTemplate.getForObject(url, String.class);
        Map<String, Object> wxResp = new ObjectMapper().readValue(wxRespStr, Map.class);

        if (wxResp == null || wxResp.get("openid") == null) {
            return AjaxResult.error("微信登录失败");
        }
        String openid = wxResp.get("openid").toString();

        // 查找用户
        SysUser user = userService.selectUserByOpenid(openid);
        if (user == null) {
            // 没有则注册新用户
            user = new SysUser();
            user.setUserName("wx_" + openid);
            user.setNickName("微信用户");
            user.setOpenid(openid);
            user.setPassword(SecurityUtils.encryptPassword(openid)); // 随机密码
            user.setStatus("0");
            user.setRoleIds(new Long[]{2L}); // 自动分配普通用户角色
            userService.insertUser(user);
        }

        // 生成token
        LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        String token = tokenService.createToken(loginUser);

        Map<String, Object> result = new HashMap<>();
        result.put("openid", openid);
        result.put("token", token);

        return AjaxResult.success(result);
    }
} 