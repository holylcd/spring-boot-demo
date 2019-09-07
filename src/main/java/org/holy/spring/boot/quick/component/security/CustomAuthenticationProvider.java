package org.holy.spring.boot.quick.component.security;

import org.holy.spring.boot.quick.model.UserVo;
import org.holy.spring.boot.quick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Long userId = principal.getId();

        UserVo userVo = userService.selectById(userId);

        // 模拟角色，权限
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority("PERMISSION_SELECT");
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(simpleGrantedAuthority);
        grantedAuthority.add(simpleGrantedAuthority1);

        UserPrincipal userPrincipal = new UserPrincipal(
                grantedAuthority,
                userVo.getId(),
                userVo.getPassword(),
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

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        newAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return newAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
