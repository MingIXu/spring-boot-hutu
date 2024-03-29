package com.hutu.es;

import com.hutu.es.dao.BookRepository;
import com.hutu.es.entity.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class HutuEsApplicationTests {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ElasticsearchOperations elasticsearchRestTemplate;

    @Test
    public void contextLoads() {
        ArrayList list = new ArrayList<Book>() {{
            add(new Book().setId("1").setName("小米").setPrice(88).setSummary("人很好").setAge("18,12,23"));
            add(new Book().setId("2").setName("小明").setPrice(44).setSummary("人很好").setAge("十二岁，十五"));
            add(new Book().setId("3").setName("小花和小明").setPrice(55).setSummary("人很一般").setAge("十一岁，十二岁"));
            add(new Book().setId("4").setName("小裘").setPrice(868).setSummary("人很还行"));
            add(new Book().setId("5").setName("小新").setPrice(878).setSummary("人很好").setNums(Arrays.asList(new Integer(2), new Integer(3))));
            add(new Book().setId("6").setName("小毛").setPrice(888).setSummary("人很好").setNums(Arrays.asList(new Integer(1), new Integer(2))));
        }};

        elasticsearchRestTemplate.search(Query.findAll(),Book.class);
//        System.out.println(elasticsearchRestTemplate.get("1", Book.class));
//        bookRepository.saveAll(list);
//        Page<Book> books = bookRepository.findByName("小米", PageRequest.of(0, 3));
//        System.out.println(books.getContent());
    }

    @Test
    public void get() {
        Page<Book> books = bookRepository.findByName("小米", PageRequest.of(0, 3));
//        System.out.println(JSONUtil.toJsonPrettyStr(elasticsearchRestTemplate.list("", "", Book.class)));
    }

}