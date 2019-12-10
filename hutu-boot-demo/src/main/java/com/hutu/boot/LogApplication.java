package com.hutu.boot;

import com.hutu.log.annotation.ApiLog;
import com.hutu.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }

    @Autowired
    private MailService mailService;

    @ApiLog("日志apo测试，发邮件测试")
    @RequestMapping("test")
    public String test() {
        mailService.sendSimpleMail("806001926@qq.com", "这是一封简单邮件", "这是一封普通的SpringBoot测试邮件");
        return "test";
    }
}

