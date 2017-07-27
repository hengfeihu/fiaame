package com.assetman.fiaame.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * Created by hengfeihu on 2017/7/24.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSecurityInterceptor())
                .excludePathPatterns("/error")
                .excludePathPatterns("/login")
                .excludePathPatterns("/")
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public HandlerInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("login拦截=================================================");
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null)
                return true;

            // 跳转登录
            String url = "/";
            response.sendRedirect(url);
            return false;
        }
    }
}
