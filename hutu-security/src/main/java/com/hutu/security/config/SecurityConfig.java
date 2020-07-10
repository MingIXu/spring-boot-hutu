package com.hutu.security.config;

import com.hutu.security.aspect.AuthAspect;
import com.hutu.security.constant.SecurityConstant;
import com.hutu.security.interceptor.AuthorizationInterceptor;
import com.hutu.security.properties.SecurityProperties;
import com.hutu.security.service.DefaultSecurityServiceImpl;
import com.hutu.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 安全配置类
 *
 * @author hutu
 * @date 2019/7/16 14:56
 */
@Order
@Configuration
@AllArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private SecurityProperties securityProperties;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(securityProperties))
                .excludePathPatterns(SecurityConstant.DEFAULT_EXCLUDE_PATTERNS);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityService permissionService() {
        return new DefaultSecurityServiceImpl();
    }

    /**
     * 注入鉴权aop
     *
     * @return AuthAspect
     */
    @Bean
    public AuthAspect authAspect() {
        return new AuthAspect();
    }

}
