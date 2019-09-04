package org.holy.spring.boot.quick.service.impl;

import org.holy.spring.boot.quick.domain.UserInfo;
import org.holy.spring.boot.quick.mapper.UserMapper;
import org.holy.spring.boot.quick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserInfo> findAll1(Example example) {
        return userMapper.selectByExample(example);
    }

}
