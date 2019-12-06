package com.hutu.security.config;

import com.hutu.security.aspect.PermissionsAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 引入鉴权aop
 *
 * @author hutu
 * @date 2019/7/16 14:56
 */
@Configuration
public class PermissionHandlerConfig {

    @Bean
    public PermissionsAspect authAspect() {
        return new PermissionsAspect();
    }
}
