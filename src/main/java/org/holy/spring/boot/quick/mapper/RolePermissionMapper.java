package org.holy.spring.boot.quick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.holy.spring.boot.quick.bean.domain.RolePermissionDO;
import org.holy.spring.boot.quick.component.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermissionDO> {

    @Select("SELECT" +
            "  sys_permission.`name` AS `name` " +
            "FROM " +
            "  sys_role_permission " +
            "JOIN sys_permission ON sys_permission.enabled = 1 " +
            "AND sys_permission.permission_code = sys_role_permission.permission_code " +
            "JOIN sys_user_role ON sys_role_permission.role_code = sys_user_role.role_code " +
            "WHERE" +
            "  sys_role_permission.role_code = #{userCode}")
    Set<String> listPermissionByUserCode(@Param("userCode") String userCode);

}