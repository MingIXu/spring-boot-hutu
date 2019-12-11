package com.hutu.log.service;

import com.hutu.common.utils.SpringUtil;
import com.hutu.log.entity.LogApi;
import com.hutu.log.mapper.LogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 保存日志默认实现
 *
 * @author hutu
 * @date 2019-12-07 20:21
 */
@Slf4j
public class ApiLogServiceImpl implements ApiLogService {
    @Override
    public void saveLog(Object apiLog) {
        SpringUtil.getBean(LogMapper.class).insert((LogApi) apiLog);
        log.info("保存日志：{}", apiLog);
    }
}
