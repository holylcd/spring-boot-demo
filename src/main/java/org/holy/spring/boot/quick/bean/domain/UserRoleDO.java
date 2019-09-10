package org.holy.spring.boot.quick.bean.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "sys_user_role")
public class UserRoleDO implements Serializable {
    @Id
    private Integer id;

    /**
     * 用户code
     */
    @Column(name = "user_code")
    private Integer userCode;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    private static final long serialVersionUID = 1L;
}