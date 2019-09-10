package org.holy.spring.boot.quick.component.security;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.holy.spring.boot.quick.common.exception.CustomAuthenticationException;
import org.holy.spring.boot.quick.common.http.rest.response.body.ErrorResponseBody;
import org.holy.spring.boot.quick.constants.biz.CommonBizStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义鉴权异常统一处理
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {
		// 尝试获取自定义异常
		CustomAuthenticationException exception = null;
    	if (e instanceof CustomAuthenticationException) {
    		exception = (CustomAuthenticationException) e;
		}

		Integer code;
		String message;
		HttpStatus httpStatus;
    	if (null == exception) {
    		message = e.getMessage();
			httpStatus = HttpStatus.FORBIDDEN;
			code = httpStatus.value();
		}else {
			code = exception.getBizStatus().getCode();
			message = exception.getBizStatus().getMsg();
			httpStatus = exception.getHttpStatus();
		}

		if (StringUtils.isBlank(message)) {
			message = CommonBizStatus.FORBIDDEN.getMsg();
		}

		ErrorResponseBody errBody = ErrorResponseBody.err(code, message);
		String jsonBody = JSONObject.toJSONString(errBody);

		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(httpStatus.value());
	    PrintWriter out = response.getWriter();
		out.print(jsonBody);

		log.warn(ExceptionUtils.getStackTrace(e));
    }
}
