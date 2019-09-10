package org.holy.spring.boot.quick.bean.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.holy.spring.boot.quick.handler.GenderTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "sys_user")
public class UserDO implements Serializable {
    @Id
    private Long id;

    /**
     * 用户code
     */
    @Column(name = "user_code")
    private String userCode;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    @ColumnType(typeHandler = GenderTypeHandler.class)
    private String gender;
    /**
     * 启用
     */
    private Boolean enabled;
}
