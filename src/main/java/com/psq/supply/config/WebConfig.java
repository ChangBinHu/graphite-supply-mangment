package com.psq.supply.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author psq
 * @description
 * @create 2025-03-31 22:36
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 处理React路由的SPA重定向
        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // 允许所有域名访问‌:ml-citation{ref="3,5" data="citationList"}
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 包含预检请求方法‌:ml-citation{ref="1,6" data="citationList"}
                .allowedHeaders("*")
                .maxAge(3600);  // 预检请求缓存时间‌:ml-citation{ref="3,5" data="citationList"}
    }
}
