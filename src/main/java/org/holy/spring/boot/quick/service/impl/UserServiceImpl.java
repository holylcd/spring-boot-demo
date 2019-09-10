package org.holy.spring.boot.quick.service.impl;

import org.holy.spring.boot.quick.bean.domain.RoleDO;
import org.holy.spring.boot.quick.bean.domain.UserDO;
import org.holy.spring.boot.quick.bean.model.user.TokenVO;
import org.holy.spring.boot.quick.bean.model.user.UserInfoVO;
import org.holy.spring.boot.quick.component.security.UserPrincipal;
import org.holy.spring.boot.quick.component.token.JwtPrincipal;
import org.holy.spring.boot.quick.component.token.JwtProvider;
import org.holy.spring.boot.quick.mapper.RolePermissionMapper;
import org.holy.spring.boot.quick.mapper.UserMapper;
import org.holy.spring.boot.quick.bean.model.user.UserVO;
import org.holy.spring.boot.quick.mapper.UserRoleMapper;
import org.holy.spring.boot.quick.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public TokenVO login(UserVO userVO) {
        // 验证用户

        // 签发凭证
        JwtPrincipal jwtPrincipal = new JwtPrincipal()
                .setUserId(1L)
                .setDeviceNo("android1234567890");
        TokenVO tokenVO = jwtProvider.createJwt(jwtPrincipal);

        return tokenVO;
    }

    @Override
    public void logout(UserPrincipal userPrincipal) {
        // 删除 redis value
        //String key = "xxx:xxx:userCode";
        //redisTemplate.delete(key);

        // session、cookie
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.invalidate();
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
        }

        // 清除 spring security context
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public UserInfoVO info(UserPrincipal userPrincipal) {
        UserInfoVO userInfoVO = new UserInfoVO();

        UserDO userDO = userMapper.selectByPrimaryKey(userPrincipal.getUserId());
        BeanUtils.copyProperties(userDO, userInfoVO);

        Set<String> roles = userRoleMapper.listRoleByUserId(userPrincipal.getUserId());
        Set<String> permissions = rolePermissionMapper.listPermissionByUserCode(userPrincipal.getUserCode());
        userInfoVO.setRoles(roles);
        userInfoVO.setPermissions(permissions);

        return userInfoVO;
    }

    @Override
    public List<UserVO> findAll() {
        List<UserDO> userDOS = userMapper.selectAll();
        List<UserVO> userVOS = new ArrayList<>();
        BeanUtils.copyProperties(userVOS, userDOS);
        return userVOS;
    }

    @Override
    public List<UserVO> findAll1(Example example) {
        List<UserDO> userDOS = userMapper.selectByExample(example);
        List<UserVO> userVOS = new ArrayList<>();
        BeanUtils.copyProperties(userVOS, userDOS);
        return userVOS;
    }

    @Override
    public UserVO selectById(Long id) {
        UserVO userVo = new UserVO()
                .setId(1L)
                .setName("test")
                .setPassword("123456");
        return userVo;
    }

    public UserVO selectById2(Long id) {
        UserVO userVo = new UserVO()
                .setId(1L)
                .setName("test")
                .setPassword("123456");
        return userVo;
    }

    @Override
    public UserVO insertObject(UserVO userVO) {
        return null;
    }

    @Override
    public Integer updateObject(UserVO userVO) {
        return null;
    }

    @Override
    public UserVO getObject(UserVO userVO) {
        return null;
    }

    @Override
    public List listObjects(Example example) {
        return null;
    }

    @Override
    public Integer count(Example example) {
        return null;
    }

    @Override
    public Integer deleteObject(Long id) {
        return null;
    }
}
