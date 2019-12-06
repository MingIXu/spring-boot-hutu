package com.hutu.log.aspect;

import com.hutu.log.entity.ApiLog;
import com.hutu.log.event.ApiLogEvent;
import com.hutu.security.utils.SpringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;

/**
 * 系统日志，切面处理类
 * @author hutu
 * @date 2018/6/10 15:12
 */
@Slf4j
@Aspect
public class ApiLogAspect {

	@SneakyThrows
	@Around("@annotation(apiLog)")
	public Object around(ProceedingJoinPoint point, com.hutu.log.annotation.ApiLog apiLog) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTime = System.currentTimeMillis();
		long spendTime = endTime - startTime;
		ApiLog log = new ApiLog();
		log.setCreateTime(new Date()).setMethod(strMethodName).setTime(Long.toString(spendTime));
		SpringUtil.publishEvent(new ApiLogEvent(log));
		return obj;
	}

}
