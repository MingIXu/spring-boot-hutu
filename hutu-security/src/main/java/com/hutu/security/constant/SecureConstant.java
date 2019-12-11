
package com.hutu.security.constant;

/**
 * 授权校验常量
 *
 * @author Chill
 */
public interface SecureConstant {

    /**
     * 认证请求头
     */
    String BASIC_HEADER_KEY = "Authorization";

    /**
     * 请求白名单
     */
    String[] WHITE_WORDS = {".css", ".js", ".html", ".map", ".woff", ".woff2", ".jpg", ".png", ".gif", ".jpeg", ".bmp",
            "ttf", "mp4", "m3u8", "flv", "pdf", "docx", "doc", "xlsx", "xls", ".txt", "/druid", "/error", "/configuration/ui",
            "/swagger-resources", "/v2/", "/services"};

}
