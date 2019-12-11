package com.hutu.boot;

import com.hutu.security.annotation.Logical;
import com.hutu.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 默认鉴权实现
 * @author hutu
 * @date 2019-12-06 18:46
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean doAuth(Logical logical, String[] role) {
        log.info("测试自定义鉴权逻辑");
        return false;
    }
}
