package org.holy.spring.boot.quick.config.security;

import org.holy.spring.boot.quick.component.security.CustomAuthenticationEntryPoint;
import org.holy.spring.boot.quick.component.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * web security config
 * @author holy
 * @version 1.0.0
 * @date 2019/9/5 11:19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    public void configure(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // CORS 攻击
                .cors()
                .and()

                // 防止 CSRF 攻击
                .csrf()
                .disable()

                // 认证异常处理
                .exceptionHandling()
                // 鉴权异常统一处理
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()

                // 禁用 session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 请求放通
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user/login")
                .permitAll()

                //剩余资源需要认证
                .anyRequest()
                .authenticated();
    }
}
