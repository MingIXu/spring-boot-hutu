package com.hutu.log.service;

import com.hutu.log.entity.LogApi;
import lombok.extern.slf4j.Slf4j;

/**
 * 保存日志默认实现
 * @author hutu
 * @date 2019-12-07 20:21
 */
@Slf4j
public class ApiLogServiceImpl implements ApiLogService {
    @Override
    public void saveLog(Object apiLog) {
        // TODO 保存日志到数据库
        log.info("保存日志：{}", apiLog);
    }
}
