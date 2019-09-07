package org.holy.spring.boot.quick.component.token;

import io.jsonwebtoken.*;
import org.holy.spring.boot.quick.model.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

/**
 * jwt Provider
 */
@Component
public class JwtProvider {

    @Autowired
    private JwtConfig jwtConfig;

    /**
     * 创建 JWT
     * @param principal JWT 生成依据
     * @return
     */
    public TokenVo createJwt(JwtPrincipal principal) {
        if (Objects.isNull(principal)) {
            throw new IllegalArgumentException("Jwt principal can not be empty");
        }
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = jwtConfig.getSignatureAlgorithm();

        // 过期时间
        long expirationMillis = System.currentTimeMillis() + jwtConfig.getTtlMillis();
        Date expirationDate = new Date(expirationMillis);

        // 密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfig.getJwtSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // token
        JwtBuilder builder = Jwts.builder()
                // token 唯一标识
                .setId(String.valueOf(principal.getUserId()))
                // 接收方
                .setAudience(principal.getDeviceNo())
                // 过期时间
                .setExpiration(expirationDate)
                // 加密
                .signWith(signatureAlgorithm, signingKey)
                ;
        String token = addPrefix(builder.compact());

        // refresh token
        long refreshExpirationMillis = System.currentTimeMillis() + jwtConfig.getTtlMillis();
        Date refreshExpirationDate = new Date(refreshExpirationMillis);
        JwtBuilder refreshBuilder = Jwts.builder()
                // token 唯一标识
                .setId(String.valueOf(principal.getUserId()))
                // 接收方
                .setAudience(principal.getDeviceNo())
                // 过期时间
                .setExpiration(refreshExpirationDate)
                // 加密
                .signWith(signatureAlgorithm, signingKey)
                ;
        String refreshToken = refreshBuilder.compact();

        TokenVo tokenVo = new TokenVo()
                .setToken(token)
                .setExpireDate(expirationDate)
                .setRefreshToken(refreshToken);
        return tokenVo;
    }

    /**
     * 解析 JWT
     * @param jwt
     * @return
     */
    public JwtPrincipal parseJwt(String jwt) {
        jwt = deletePrefix(jwt);

        // 密钥
        byte[] key;
        try {
            key = DatatypeConverter.parseBase64Binary(jwtConfig.getJwtSecret());
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("jwt secret error", e);
        }


        // 解析
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("非法的登录信息", e);
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("登录信息异常", e);
        } catch (SignatureException e) {
            throw new SignatureException("签名异常", e);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "登录状态已过期");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("负载异常", e);
        }

        String userId = claims.getId();
        String deviceNo = claims.getAudience();

        return new JwtPrincipal()
                .setUserId(Long.valueOf(userId))
                .setDeviceNo(deviceNo);
    }

    /**
     * 增加前缀
     * @param jwt
     * @return
     */
    private String addPrefix(String jwt) {
        return String.format("%s %s", jwtConfig.getTokenPrefix(), jwt);
    }

    /**
     * 删除前缀
     * @param jwt
     * @return
     */
    private String deletePrefix(String jwt) {
        return jwt.substring(jwtConfig.getTokenPrefix().length() + 1);
    }

}
