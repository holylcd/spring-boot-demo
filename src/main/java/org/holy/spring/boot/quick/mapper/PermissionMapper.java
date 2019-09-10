package org.holy.spring.boot.quick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.holy.spring.boot.quick.bean.domain.PermissionDO;
import org.holy.spring.boot.quick.component.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<PermissionDO> {
}