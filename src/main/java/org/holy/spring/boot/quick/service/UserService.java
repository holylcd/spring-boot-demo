package org.holy.spring.boot.quick.service;

import org.holy.spring.boot.quick.domain.UserInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserService {

    List<UserInfo> findAll();

    List<UserInfo> findAll1(Example example);

}
