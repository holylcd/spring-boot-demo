package org.holy.spring.boot.quick.service;

import org.holy.spring.boot.quick.bean.model.user.TokenVO;
import org.holy.spring.boot.quick.bean.model.user.UserInfoVO;
import org.holy.spring.boot.quick.bean.model.user.UserVO;
import org.holy.spring.boot.quick.component.security.UserPrincipal;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserService extends BaseService<UserVO> {

    TokenVO login(UserVO userVO);

    void logout(UserPrincipal userPrincipal);

    UserInfoVO info(UserPrincipal userPrincipal);

    UserVO selectById(Long id);

    List<UserVO> findAll();

    List<UserVO> findAll1(Example example);

}
