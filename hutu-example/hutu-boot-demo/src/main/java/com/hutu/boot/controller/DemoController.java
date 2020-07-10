package com.hutu.boot.controller;

import com.hutu.boot.vo.TestVO;
import com.hutu.common.entity.R;
import com.hutu.log.annotation.ApiLog;
import com.hutu.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试相关功能
 * @author hutu
 * @date 2020-01-15 17:09
 */
@Slf4j
@Api(tags = "swagger测试")
@RestController
public class DemoController {

    @Autowired
    private MailService mailService;

    @ApiOperation("权限aop测试")
    @ApiLog("权限aop测试")
    @GetMapping("permission")
    public R permission() {
        return R.ok();
    }

    @ApiOperation("日志aop测试")
    @ApiLog("日志aop测试")
    @GetMapping("test")
    public R<TestVO> test() {
        log.info("--------------------------");
        return R.ok(new TestVO().setName("小米").setAge(20));
    }

    @ApiLog("发邮件测试")
    @PostMapping("sendMail")
    public R sendMail() {
        mailService.sendSimpleMail("XXXXXXXXX@qq.com", "这是一封简单邮件", "这是一封普通的SpringBoot测试邮件");
        return R.ok("邮件发送成功");
    }
}
