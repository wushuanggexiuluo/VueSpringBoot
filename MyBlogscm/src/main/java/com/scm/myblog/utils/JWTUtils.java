package com.scm.myblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）,默认：{"alg": "HS512","typ": "JWT"}
 * payload的格式 设置：（用户信息、创建时间、生成时间）
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */

@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtils {

    //定义token返回头部
    public static String header;

    //token前缀
    public static String tokenPrefix;

    //签名密钥
    public static String secret;

    //有效期
    public static long expireTime;

    //存进客户端的token的key名
    public static final String USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public void setHeader(String header) {
        JWTUtils.header = header;
    }

    public void setTokenPrefix(String tokenPrefix) {
        JWTUtils.tokenPrefix = tokenPrefix;
    }

    public void setSecret(String secret) {
        JWTUtils.secret = secret;
    }

    public void setExpireTime(int expireTimeInt) {
        JWTUtils.expireTime = expireTimeInt * 1000L * 60;
    }

    /**
     * 创建TOKEN
     */
    public static String createToken(String sub) {
        return tokenPrefix + JWT.create()
                //设置主题
                .withSubject(sub)
                //设置过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                //设置签名算法
                .sign(Algorithm.HMAC512(secret));
    }


    /**
     * 验证token
     */
    public static String validateToken(String token) {
        try {
            //这个解密算法要和加密算法一样才可以
            return JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getSubject();
        } catch (TokenExpiredException e) {
            System.out.println("token已经过期");
        } catch (Exception e) {
            System.out.println("token验证失败");
        }
        return token;
    }

    /**
     * 检查token是否需要更新
     */
    public static boolean isNeedUpdate(String token) {
        //获取token过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e) {
            return true;
        } catch (Exception e) {
            System.out.println("token验证失败");
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        assert expiresAt != null;
        return (expiresAt.getTime() - System.currentTimeMillis()) < (expireTime >> 1);
    }
}

