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
@Table(name = "sys_permission")
public class PermissionDO implements Serializable {
    @Id
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    private static final long serialVersionUID = 1L;
}