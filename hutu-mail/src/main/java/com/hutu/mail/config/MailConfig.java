package com.hutu.mail.config;

import com.hutu.mail.service.MailService;
import com.hutu.mail.service.impl.MailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * mybatis-plus默认配置
 *
 * @author hutu
 * @date 2019/6/21 9:23
 */
@Configuration
@AutoConfigureAfter(MailSenderAutoConfiguration.class)
public class MailConfig {

    @Bean
    public MailService mailService(){
        return new MailServiceImpl();
    }
}