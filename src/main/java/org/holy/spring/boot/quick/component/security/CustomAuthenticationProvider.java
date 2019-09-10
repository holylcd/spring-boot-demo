package org.holy.spring.boot.quick.component.security;

import org.holy.spring.boot.quick.bean.model.user.UserVO;
import org.holy.spring.boot.quick.common.exception.CustomAuthenticationException;
import org.holy.spring.boot.quick.component.token.JwtPrincipal;
import org.holy.spring.boot.quick.component.token.JwtProvider;
import org.holy.spring.boot.quick.constants.biz.CommonBizStatus;
import org.holy.spring.boot.quick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义身份认证
 * @author holy
 * @version 1.0.0
 * @date 2019/9/5 23:09
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获得凭证 token
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String credential = jwtProvider.getTokenFromRequest(request);
        if (null == credential || "".equals(credential.trim())) {
            throw new CustomAuthenticationException(HttpStatus.UNAUTHORIZED, CommonBizStatus.UNAUTHORIZED);
        }

        // 解析凭证
        JwtPrincipal principal = jwtProvider.parseJwt(credential);

        // 判断是否存在 redis，这样做使得 token 可控
        // Boolean exist = redisTemplate.hasKey("xxxxx:xxxx:xxx" + userId);
        Boolean exist = true;
        if (!exist) {
            throw new CustomAuthenticationException(HttpStatus.UNAUTHORIZED, CommonBizStatus.UNAUTHORIZED);
        }

        // 更新状态信息
        Long userId = principal.getUserId();
        UserVO userVo = userService.selectById(userId);
        // 模拟角色，权限
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority("PERMISSION_SELECT");
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(simpleGrantedAuthority);
        grantedAuthority.add(simpleGrantedAuthority1);

        UserPrincipal userPrincipal = new UserPrincipal(
                grantedAuthority,
                userVo.getId(),
                userVo.getUserCode(),
                null,
                userVo.getName(),
                true,
                true,
                true,
                true
        );
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                userPrincipal,
                authentication.getCredentials(),
                // SecurityExpressionRoot 使用改权限集合
                grantedAuthority
        );

        newAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return newAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
