package com.hutu.log;

import com.hutu.log.annotation.ApiLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @ApiLog("日志apo测试")
    @RequestMapping("test")
    public String test() {
        return "test";
    }
}

