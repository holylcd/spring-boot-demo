package org.holy.spring.boot.quick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.holy.spring.boot.quick.bean.domain.RoleDO;
import org.holy.spring.boot.quick.component.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {
}