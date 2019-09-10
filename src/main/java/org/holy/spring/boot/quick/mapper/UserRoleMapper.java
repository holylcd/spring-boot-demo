package org.holy.spring.boot.quick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.holy.spring.boot.quick.bean.domain.RoleDO;
import org.holy.spring.boot.quick.bean.domain.UserRoleDO;
import org.holy.spring.boot.quick.component.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    @Select("SELECT" +
            "  sys_role.`name` AS `name` " +
            "FROM" +
            "  sys_user_role " +
            "JOIN sys_role ON sys_role.enabled =1 AND sys_role.role_code = sys_user_role.role_code " +
            "WHERE" +
            "  sys_user_role.user_code = #{userId}")
    Set<String> listRoleByUserId(@Param("userId") Long userId);

}