package com.hutu.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "org.springframework.data.elasticsearch.repository"
)
public class EsConfig {

}
