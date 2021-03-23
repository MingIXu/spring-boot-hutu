package com.hutu.es;

import com.hutu.es.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.CustomEsRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class HutuEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutuEsApplication.class, args);
    }

    @RestController
    public class Test {
        @Autowired
        ElasticsearchOperations elasticsearchRestTemplate;

        @GetMapping("getbook")
        public String get() {
            log.info("coming Test ...");
            SearchHits<Book> result = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.termQuery("name", "小米")).build(), Book.class);
            System.out.println(result.getSearchHits());
            return "success";
        }
    }
}
