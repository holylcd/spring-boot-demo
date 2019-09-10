package org.holy.spring.boot.quick.service;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 基础 service
 * @author holy
 * @version 1.0.0
 * @date 2019/9/9 11:03
 */
interface BaseService<T> {

    /**
     * 新增一个对象
     * @param t
     * @return
     */
    T insertObject(T t);

    /**
     * 更新一个对象
     * @param t
     * @return
     */
    Integer updateObject(T t);

    /**
     * 获取一个对象
     * @return
     */
    T getObject(T t);

    /**
     * 获取对象集合
     * @param example
     * @return
     */
    List<T> listObjects(Example example);

    /**
     * 获取对象总数
     * @param example
     * @return
     */
    Integer count(Example example);

    /**
     * 删除一个对象
     * @param id
     * @return
     */
    Integer deleteObject(Long id);
}
