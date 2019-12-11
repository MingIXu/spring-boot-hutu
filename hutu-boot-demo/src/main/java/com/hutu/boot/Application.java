package com.hutu.boot;

import com.hutu.common.entity.R;
import com.hutu.log.annotation.ApiLog;
import com.hutu.mail.service.MailService;
import com.hutu.security.annotation.PreAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 例子项目
 *
 * @author hutu
 * @date 2019-12-10 15:24
 */
@SpringBootApplication
@EnableAsync
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PreAuth("测试权限aop")
    @RequestMapping("permission")
    public R permission() {
        return R.ok();
    }

    @ApiLog("日志apo测试")
    @RequestMapping("test")
    public R test() {
        return R.ok();
    }

    @Autowired
    private MailService mailService;

    @ApiLog("发邮件测试")
    @RequestMapping("sendMail")
    public R sendMail() {
        mailService.sendSimpleMail("806001926@qq.com", "这是一封简单邮件", "这是一封普通的SpringBoot测试邮件");
        return R.ok("邮件发送成功");
    }
}

