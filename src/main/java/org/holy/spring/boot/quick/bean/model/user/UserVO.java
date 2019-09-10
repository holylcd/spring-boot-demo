package org.holy.spring.boot.quick.bean.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户VO
 * @author holy
 * @version 1.0.0
 * @date 2019/9/5 11:03
 */
@Data
@Accessors(chain = true)
public class UserVO implements Serializable {
    /**
     * 用户 id
     */
    private Long id;
    /**
     * 用户 id
     */
    private String userCode;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;
    /**
     * 用户状态
     */
    private Boolean state;
}
