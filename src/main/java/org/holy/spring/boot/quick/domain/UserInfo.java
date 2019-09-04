package org.holy.spring.boot.quick.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户信息
 * @author holy
 * @date 2019/9/2 15:33
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
//@NameStyle(Style.camelhump)
//@Table(name = "user_info")
public class UserInfo implements Serializable {
    @Id
    private Long id;
    private String account;
    private String realname;
    private String password;
    private String status;
    private Boolean disabled;
}
