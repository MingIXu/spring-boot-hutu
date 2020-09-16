package com.hutu.boot.service;

import com.hutu.log.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 测试自定义鉴权逻辑
 * @author hutu
 * @date 2019-12-06 18:46
 */
@Slf4j
@Service("apiLogService")
@Order(Integer.MIN_VALUE)
public class MyLogServiceImpl implements ApiLogService {

    @Override
    public void saveLog(Object apiLog) {
        log.info("日志： {}",apiLog);
    }
}
