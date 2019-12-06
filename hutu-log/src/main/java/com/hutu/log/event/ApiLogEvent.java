package com.hutu.log.event;

import com.hutu.log.entity.ApiLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author lengleng
 * 系统日志事件
 */
public class ApiLogEvent extends ApplicationEvent {
    public ApiLogEvent(ApiLog source) {
        super(source);
    }
}
