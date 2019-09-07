package org.holy.spring.boot.quick.component.token;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author holyl
 * @version 1.0.0
 * @date 2019/9/6 10:16
 */
@Component
@Getter
public class JwtConfig {
    /**
     * JWT 密钥
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * JWT 过期时间
     */
    @Value("${jwt.ttl-millis}")
    private long ttlMillis;

    /**
     * token 前缀
     */
    @Value("${jwt.prefix}")
    private String tokenPrefix;

    /**
     * token 请求头名称
     */
    @Value("${jwt.header-name}")
    private String headerName;

    /**
     * 签名算法
     */
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
}
