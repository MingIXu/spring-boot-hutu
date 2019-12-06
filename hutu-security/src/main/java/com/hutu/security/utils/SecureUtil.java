/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hutu.security.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hutu.common.enums.ResultCode;
import com.hutu.common.utils.IdGenerator;
import com.hutu.security.constant.SecureConstant;
import com.hutu.security.exception.GlobalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Secure工具类
 *
 * @author Chill
 */
@Slf4j
public class SecureUtil {
    /**
     * 签名key
     */
    private final static String SECRET_KEY = "bWluZyBodWEgcWlhbmcgTE9WRSB4dSB0YWkgbGlhbiAh";

    /**
     * 快过期一个小时内可以刷新令牌
     */
    private final static long REFRESH_TIME = 1000 * 60 * 60;

    /**
     * 生成 jwt token
     */
    public String createToken(Object sourceToken) {
        String subject = JSONUtil.toJsonStr(sourceToken);
        //发布时间
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(getExpire());

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .setId(IdGenerator.getIdStr())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 解析jwt token
     */
    public Claims parseToken(String sourceToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(sourceToken)
                    .getBody();
        } catch (Exception e) {
            log.info("解析token失败");
            throw new GlobalException("解析token失败", e);
        }
    }

    /**
     * 刷新Token
     */
    public String refreshToken() {

        String token = WebUtil.getRequestParameter(SecureConstant.BASIC_HEADER_KEY);

        if (StrUtil.isEmpty(token)) {
            log.info("请求中无token认证信息");
            throw new GlobalException(ResultCode.UNAUTHORIZED);
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            long issuedAt = claims.getIssuedAt().getTime();
            long difference = System.currentTimeMillis() - issuedAt;
            if (difference > REFRESH_TIME) {
                //发布时间
                Date nowDate = new Date();
                //过期时间
                Date expireDate = new Date(getExpire());
                return Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setClaims(claims)
                        .setIssuedAt(nowDate)
                        .setExpiration(expireDate)
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .compact();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    /**
     * 次日凌晨2点
     *
     * @return expire
     */
    public static long getExpire() {
        // 说明: https://blog.csdn.net/u014044812/article/details/79231738
        return LocalDate.now().plusDays(1).atTime(2, 0, 0).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取用户id 此系统以用户id做subject
     *
     * @return subject
     */
    public LoginUser getCallerInfo() {
        String token = WebUtil.getRequestParameter(SecureConstant.BASIC_HEADER_KEY);
        if (StrUtil.isNotEmpty(token)) {
            Claims claim = parseToken(token);
            if (claim != null) {
                return JSONUtil.toBean(claim.getSubject(), LoginUser.class);
            }
        } else {
            log.info("请求中无token认证信息");
            throw new GlobalException(ResultCode.UNAUTHORIZED);
        }
        return null;
    }

    public Integer getUserId() {
        try {
            return getCallerInfo().getUserId();
        } catch (Exception e) {
            log.info("getUserId 失败");
            return 0;
        }
    }

    public String getAccount() {
        try {
            return getCallerInfo().getAccount();
        } catch (Exception e) {
            log.info("getAccount 失败");
            return "";
        }
    }

    public String getUserName() {
        try {
            return getCallerInfo().getUserName();
        } catch (Exception e) {
            log.info("getUserName 失败");
            return "";
        }
    }

    public String getTenantId() {
        try {
            return getCallerInfo().getTenantId();
        } catch (Exception e) {
            log.error("getTenantId 失败");
            throw e;
        }
    }
}
