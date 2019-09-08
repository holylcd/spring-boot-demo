package org.holy.spring.boot.quick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.holy.spring.boot.quick.component.magger.BaseMapper;
import org.holy.spring.boot.quick.domain.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @Description //TODO
 * @Author holyl
 * @Date 2019/9/2 15:28
 * @Version 1.0.0
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<UserInfo> {
}
