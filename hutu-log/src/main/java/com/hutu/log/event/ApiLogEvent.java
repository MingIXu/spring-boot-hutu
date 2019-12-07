package com.hutu.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 */
public class ApiLogEvent extends ApplicationEvent {
    public ApiLogEvent(Object source) {
        super(source);
    }
}
