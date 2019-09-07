package org.holy.spring.boot.quick.service;

import org.holy.spring.boot.quick.domain.UserInfo;
import org.holy.spring.boot.quick.model.UserVo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserService {

    /**
     * 模拟
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 模拟
     * @param example
     * @return
     */
    List<UserInfo> findAll1(Example example);

    /**
     * 模拟
     * @param id
     * @return
     */
    UserVo selectById(Long id);

    /**
     * 模拟
     * @param id
     * @return
     */
    UserVo selectById2(Long id);
}
