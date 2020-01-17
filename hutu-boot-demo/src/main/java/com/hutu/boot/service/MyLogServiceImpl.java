//package com.hutu.boot.service;
//
//import com.hutu.log.service.ApiLogService;
//import com.hutu.security.annotation.Logical;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * 测试自定义鉴权逻辑
// * @author hutu
// * @date 2019-12-06 18:46
// */
//@Slf4j
//@Service
//public class MyLogServiceImpl implements ApiLogService {
//
//    public boolean doAuth(Logical logical, String[] role) {
//        log.info("测试自定义鉴权逻辑 逻辑logical: {} 所需角色role: {}",logical,role);
//        return false;
//    }
//
//    @Override
//    public void saveLog(Object apiLog) {
//        log.info("日志： {}",apiLog);
//    }
//}
