package com.inoriii.hello.spring.web.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.inoriii.hello.spring.api.enums.ResponseCode;
import com.inoriii.hello.spring.common.exception.BusinessException;
import com.inoriii.hello.spring.model.constant.JWTConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JwtUtil {

    /**
     * 获取jwt签名
     */
    public static <T> String getUserJwt(T t) {
        return getUserJwt(t, JWTConstant.TIMEOUT_MINUTES);
    }


    /**
     * 解析jwt签名
     */
    public static <T> T getUserJwt(String token, Class<T> t) {
        JWT jwt = checkJwt(token);
        try {
            return jwt.getPayload().getClaimsJson().toBean(t);
        } catch (Exception e) {
            log.error("jwt转换异常", e);
            throw new BusinessException(ResponseCode.JWT_FAIL);
        }
    }

    /**
     * jwt续期
     */
    public static String refreshToken(String token) {
        return refreshToken(token, JWTConstant.TIMEOUT_MINUTES);
    }

    public static String refreshToken(String token, long timeoutMinutes) {
        JWT jwt = checkJwt(token);
        JWTPayload payload = jwt.getPayload();
        if (payload == null) {
            throw new BusinessException(ResponseCode.JWT_FAIL);
        }
        LocalDateTime expires = LocalDateTime.ofInstant(Instant.ofEpochSecond((Integer) payload.getClaim(JWTPayload.EXPIRES_AT)), ZoneId.systemDefault());
        if (expires == null) {
            throw new BusinessException(ResponseCode.JWT_FAIL);
        }
        payload.setPayload(JWTPayload.EXPIRES_AT, expires.plusMinutes(timeoutMinutes));
        return jwt.sign();
    }

    public static JWT checkJwt(String token) {
        if (!StringUtils.hasText(token)) return null;
        JWT jwt = null;
        try {
            jwt = JWT.of(token);
        } catch (Exception e) {
            log.error("jwt,of(token)校验失败", e);
            throw new BusinessException(ResponseCode.JWT_FAIL);
        }
        try {
            if (!jwt.setKey(JWTConstant.KEY).verify()) {
                throw new BusinessException(ResponseCode.JWT_VALID_FAIL);
            }
        } catch (Exception e) {
            log.error("jwt签名校验失败", e);
            throw new BusinessException(ResponseCode.JWT_FAIL);
        }
        try {
            if (!jwt.validate(0)) {
                throw new BusinessException(ResponseCode.JWT_TIMEOUT_FAIL);
            }
        } catch (Exception e) {
            log.error("jwt未过期校验失败", e);
            throw new BusinessException(ResponseCode.JWT_FAIL);
        }
        return jwt;
    }

    public static <T> String getUserJwt(T t, long timeoutMinutes) {
        Map<String, Object> payload = new HashMap<>();
        if (t != null) payload.putAll(BeanUtil.beanToMap(t));
        return getUserJwt(payload, timeoutMinutes);
    }

    public static String getUserJwt(Map<String, Object> payload, long timeoutMinutes) {
        return getUserJwt(JWTConstant.HEADERS, payload, JWTConstant.KEY, timeoutMinutes);
    }

    public static String getUserJwt(Map<String, Object> headers, Map<String, Object> payload, byte[] key, long timeoutMinutes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeOut = now.plusMinutes(timeoutMinutes);
        payload = MapUtil.isEmpty(payload) ? new HashMap<>() : payload;
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, timeOut);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //wt的唯一身份标识
        payload.put(JWTPayload.JWT_ID, UUID.randomUUID().toString());
        return JWTUtil.createToken(headers, payload, key);
    }
}