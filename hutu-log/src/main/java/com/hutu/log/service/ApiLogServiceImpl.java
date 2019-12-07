package com.hutu.log.service;

import com.hutu.log.entity.LogApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiLogServiceImpl implements ApiLogService {
    @Override
    public void saveLog(Object apiLog) {
        // TODO 保存日志到数据库
        log.info("保存日志：{}",(LogApi)apiLog);
    }
}
