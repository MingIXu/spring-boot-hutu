package com.hutu.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单
 *
 * @author hutu
 */
@Data
@Component
@ConfigurationProperties(SecurityProperties.PREFIX)
public class SecurityProperties {
    /**
     * Prefix of {@link SecurityProperties}.
     */
    public static final String PREFIX = "ehealth.security";
    /**
     * 放行API集合
     */
    private final List<String> skipUrl = new ArrayList<>();

}
