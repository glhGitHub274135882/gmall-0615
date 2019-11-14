package com.atguigu.gmall.auth.controller;

import com.atguigu.core.bean.Resp;
import com.atguigu.core.utils.CookieUtils;
import com.atguigu.gmall.auth.config.JwtProperties;
import com.atguigu.gmall.auth.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shkstart
 * @create 2019-11-12 22:46
 */
@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("accredit")
    public Resp<Object> accredit(@RequestParam("username") String userName, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        String jwtToken = this.authService.accredit(userName, password);
        if (StringUtils.isEmpty(jwtToken)) {
            return Resp.fail("xxxxxxxxxxxx");
        }
        CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), jwtToken, this.jwtProperties.getExpire() * 60);
//        System.out.println(this.jwtProperties.getCookieName()+"-----------"+jwtToken);
        return Resp.ok(null);
    }
}
