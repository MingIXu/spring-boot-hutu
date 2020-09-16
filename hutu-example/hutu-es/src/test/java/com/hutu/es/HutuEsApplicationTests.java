package com.hutu.es;

import cn.hutool.json.JSONUtil;
import com.hutu.es.dao.BookRepository;
import com.hutu.es.entity.Book;
import com.hutu.es.util.EsTemplate;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HutuEsApplicationTests {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void contextLoads() {
        ArrayList list = new ArrayList<Book>() {{
            add(new Book().setId("1").setName("小米").setPrice(88).setSummary("人很好").setAge("18,12,23"));
            add(new Book().setId("2").setName("小明").setPrice(44).setSummary("人很好").setAge("十二岁，十五"));
            add(new Book().setId("3").setName("小花和小明").setPrice(55).setSummary("人很一般").setAge("十一岁，十二岁"));
            add(new Book().setId("4").setName("小裘").setPrice(868).setSummary("人很还行"));
            add(new Book().setId("5").setName("小新").setPrice(878).setSummary("人很好"));
            add(new Book().setId("6").setName("小毛").setPrice(888).setSummary("人很好"));
        }};

        bookRepository.saveAll(list);
        Page<Book> books = bookRepository.findByName("小", PageRequest.of(0, 3));
        System.out.println(books.getContent());
    }

    @Test
    public void get() {
        Book book = elasticsearchRestTemplate.get("1", Book.class);
        System.out.println(book);
    }

    @Autowired
    EsTemplate esTemplate;

    @Test
    public void searchBook() {
        WildcardQueryBuilder age = QueryBuilders.wildcardQuery("age", "*12*");
        System.out.println(JSONUtil.toJsonPrettyStr(esTemplate.list("name", "花明", Book.class)));
    }
}