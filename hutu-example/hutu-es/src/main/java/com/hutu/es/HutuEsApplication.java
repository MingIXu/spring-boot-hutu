package com.hutu.es;

import com.hutu.es.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.CustomEsRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class HutuEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HutuEsApplication.class, args);
	}

	@RestController
	public class Test{
		@Autowired
		CustomEsRestTemplate elasticsearchRestTemplate;
		@GetMapping("getbook")
		public String get(){
			log.info("coming Test ...");
			return elasticsearchRestTemplate.get("5", Book.class).toString();
		}
	}
}
