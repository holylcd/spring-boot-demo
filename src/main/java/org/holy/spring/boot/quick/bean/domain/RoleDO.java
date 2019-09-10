package org.holy.spring.boot.quick.bean.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Table(name = "sys_role")
public class RoleDO implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 角色名
     */
    private String role;

    /**
     * 描述
     */
    private String description;

    /**
     * 资源编号列表
     */
    @Column(name = "resource_ids")
    private String resourceIds;

    /**
     * 是否有效
     */
    private Boolean available;

    private static final long serialVersionUID = 1L;
}