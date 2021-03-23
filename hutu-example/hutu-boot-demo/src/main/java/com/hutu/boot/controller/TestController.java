package com.hutu.boot.controller;

import com.hutu.security.annotation.SkipAuth;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @SkipAuth
    @PostMapping("/{beanName}/{methodName}")
    public void route(@PathVariable("beanName")String beanName, @PathVariable("methodName")String methodName, @RequestBody(required = false) Object[] args){

        System.out.println();
    }
}
