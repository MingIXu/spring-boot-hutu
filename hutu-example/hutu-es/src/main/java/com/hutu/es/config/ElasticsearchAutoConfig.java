package com.hutu.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.CustomEsRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 *  此处 @EnableElasticsearchRepositories 注解开启 elasticsearch-jap 功能
 *  此处注入自己的template为了方便定制修改扩展封装，最重要的是为了打印出完整dsl
 *
 * @author hutu
 * @date 2020/12/3 5:17 下午
 */
@Configuration(proxyBeanMethods = false)
@EnableElasticsearchRepositories
public class ElasticsearchAutoConfig {

    @Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
    public CustomEsRestTemplate customEsRestTemplate(RestHighLevelClient client, ElasticsearchConverter elasticsearchConverter) {
        return new CustomEsRestTemplate(client, elasticsearchConverter);
    }
}
