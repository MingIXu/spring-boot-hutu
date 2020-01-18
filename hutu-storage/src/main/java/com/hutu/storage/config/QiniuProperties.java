package com.hutu.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七牛云配置
 *
 * @author hutu
 * @date 2020-01-17 17:36
 */
@Data
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String prefix;
}
