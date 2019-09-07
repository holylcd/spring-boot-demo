package org.holy.spring.boot.quick.component.token;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * JWT 主体
 * @author holy
 * @version 1.0.0
 * @date 2019/9/5 12:01
 */
@Data
@Accessors(chain = true)
public class JwtPrincipal implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 设备号
     */
    private String deviceNo;

}
