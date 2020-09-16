package com.hutu.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
//        basePackages = "org.springframework.data.elasticsearch.repository"
//        basePackages = "com.hutu.es"
)
public class EsConfig {

}
