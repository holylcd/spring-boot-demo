package org.holy.spring.boot.quick.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author holy
 * @version 1.0.0
 * @date 2019/9/5 11:03
 */
@Data
@Accessors(chain = true)
public class TokenVo implements Serializable {
    /**
     * 发放给用户的系统凭证
     */
    private String token;
    /**
     * 凭证过期时间
     */
    private Date expireDate;
    /**
     * refresh token
     */
    private String refreshToken;
}
