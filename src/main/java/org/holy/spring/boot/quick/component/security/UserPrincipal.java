package org.holy.spring.boot.quick.component.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * spring security 用户主体
 * @author holy
 * @version 1.0.0
 * @date 2019/9/5 12:01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class UserPrincipal implements UserDetails, CredentialsContainer {

    /**
     * 用户权限
     */
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 账户id
     */
    private Long userId;
    /**
     * 账户code
     */
    private String userCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 账户是否有效
     */
    private boolean accountNonExpired;
    /**
     * 账户是否解锁
     */
    private boolean accountNonLocked;
    /**
     * 凭证是否有效
     */
    private boolean credentialsNonExpired;
    /**
     * 账户是否启动
     */
    private boolean enabled;

    /**
     * 清理敏感信息
     */
    @Override
    public void eraseCredentials() {
        password = null;
    }
}
