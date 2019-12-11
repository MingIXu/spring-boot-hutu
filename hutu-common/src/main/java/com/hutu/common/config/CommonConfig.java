package com.hutu.common.config;

import com.hutu.common.utils.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * 注入通用工具类
 * @author hutu
 * @date 2019-12-11 16:17
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CommonConfig {
    /**
     * Spring上下文缓存
     *
     * @return SpringUtil
     */
    @Bean
    public SpringUtil springUtils() {
        return new SpringUtil();
    }
}
