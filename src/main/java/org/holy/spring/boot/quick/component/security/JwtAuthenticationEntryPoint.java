package org.holy.spring.boot.quick.component.security;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未认证身份异常处理
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {
		String message = e.getMessage();
		if (StringUtils.isBlank(message)) {
			message = "登录信息异常";
		}

		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

	    PrintWriter out = response.getWriter();
		JSONObject body = new JSONObject();
		body.put("code", 403);
		body.put("msg", message);

		out.print(body);

	    log.error(ExceptionUtils.getStackTrace(e));

    }
}
