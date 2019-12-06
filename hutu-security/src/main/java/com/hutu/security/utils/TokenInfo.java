package com.hutu.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenInfo {

	/**
	 * 令牌值
	 */
	private String token;

	/**
	 * 过期秒数
	 */
	private int expire;

}
