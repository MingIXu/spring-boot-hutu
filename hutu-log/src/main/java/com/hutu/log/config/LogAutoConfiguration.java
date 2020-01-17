
package com.hutu.log.config;

import com.hutu.log.aspect.ApiLogAspect;
import com.hutu.log.event.ApiLogListener;
import com.hutu.log.mapper.LogMapper;
import com.hutu.log.service.ApiLogService;
import com.hutu.log.service.ApiLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

/**
 * 日志工具自动配置
 * @author hutu
 */
@Order
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

//    @Autowired(required = false)
//    private ApiLogService apiLogService;

    @Bean
    @ConditionalOnMissingBean(ApiLogService.class)
    public ApiLogService apiLogService() {
        return new ApiLogServiceImpl();
    }

    @Bean
    public ApiLogAspect apiLogAspect() {
        return new ApiLogAspect();
    }

    @Bean
    public ApiLogListener sysLogListener(ApiLogService apiLogService) {
        return new ApiLogListener(apiLogService);
    }
}
