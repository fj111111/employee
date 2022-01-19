package com.xxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//全面扩展springmvc,如果我们要扩展springmvc，官方建议我们这样做
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("main.html").setViewName("dashboard");
    }
    // 自定义的配置国际化组件就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                //addPathPatterns("/**")：拦截所有请求
                //excludePathPatterns：排除掉的请求 index,首页，登录页，静态资源
                .addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/static/*");
    }
}
