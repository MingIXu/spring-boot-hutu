package com.hutu.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.hutu.common.constant.CommonConstant;
import com.hutu.common.constant.ProfilesConstant;
import com.hutu.mybatis.plugins.SqlLogInterceptor;
import com.hutu.mybatis.properties.MybatisPlusAutoFillProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.hutu.mybatis.properties.MybatisPlusAutoFillProperties.AUTO_FILL_PREFIX;

/**
 * mybatis统一配置
 *
 * @author hutu
 * @date 2020/5/25 3:13 下午
 */
@Configuration(proxyBeanMethods = false)
@MapperScan(CommonConstant.MAPPER_PACKAGES)
public class MybatisAutoConfiguration {

    private final MybatisPlusAutoFillProperties autoFillProperties;

    public MybatisAutoConfiguration(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * sql 日志
     *
     * @return SqlLogInterceptor
     */
    @Bean
    @Profile({ProfilesConstant.DEV, ProfilesConstant.TEST})
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }

    /**
     * 自动填充字段
     *
     * @return 填充处理类
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = AUTO_FILL_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new FillMetaObjectHandler(autoFillProperties);
    }
}
