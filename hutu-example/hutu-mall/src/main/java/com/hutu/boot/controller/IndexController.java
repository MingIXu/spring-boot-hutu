package com.hutu.boot.controller;

import com.hutu.common.entity.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


@Slf4j
@Api(tags = "首页")
@RestController
@RequestMapping("index")
public class IndexController {

    @GetMapping("type/{type}")
    public R getType1List(@PathVariable Integer type){
        return R.ok();
    }
    @GetMapping("type/{type}")
    public R getType2List(@PathVariable Integer type){
        return R.ok();
    }
    @GetMapping("type/{type}")
    public R getType3List(@PathVariable Integer type){
        return R.ok();
    }
    @GetMapping("type/{type}")
    public R getType4List(@PathVariable Integer type){
        return R.ok();
    }
    @GetMapping("type/{type}")
    public R getType5List(@PathVariable Integer type){
        return R.ok();
    }
}
