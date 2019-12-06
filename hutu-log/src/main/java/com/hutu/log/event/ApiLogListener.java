package com.hutu.log.event;

import com.hutu.log.entity.ApiLog;
import com.hutu.log.service.ApiLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class ApiLogListener {

    private final ApiLogService apiLogService;

    //	@Async("自定义线程池")
    @Async
    @Order
    @EventListener(ApiLogEvent.class)
    public void saveSysLog(ApiLogEvent event) {
        ApiLog apiLog = (ApiLog) event.getSource();
        log.info(apiLog.toString());
        apiLogService.create(apiLog);
    }
}
