package com.hutu.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置参考 https://doc.xiaominfo.com/guide/useful.html
 *
 * @author hutu
 * @date 2020-01-15 15:24
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(false)
                .apiInfo(apiInfo())
                .select()
                // 需要扫描接口的包
                .apis(RequestHandlerSelectors.basePackage("com.hutu.boot"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统api文档")
                .description("系统api在线文档,你想要的都在这里")
                .contact(new Contact("湖图","https://github.com/MingIXu","806001926@qq.com"))
                .version("1.0")
                .build();
    }
}
