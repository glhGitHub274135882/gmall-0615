package com.atguigu.gmall.order.config;

import com.atguigu.gmall.order.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shkstart
 * @create 2019-11-13 21:26
 */
@Configuration
public class CartWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/order/pay/success");
    }
}
