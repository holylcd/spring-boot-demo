package org.holy.spring.boot.quick.component.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description //TODO
 * @Author holyl
 * @Date 2019/9/2 15:08
 * @Version 1.0.0
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
