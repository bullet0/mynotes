package com.example.jwt.config;

import com.example.jwt.intercept.TokenHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: com.example.jwt.config.xxx
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-04-02 17:18
 */
@Configuration
public class InterceptConfig extends WebMvcConfigurationSupport {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TokenHandlerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
