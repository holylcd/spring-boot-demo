package org.holy.spring.boot.quick.common.exception;

import lombok.Getter;
import org.holy.spring.boot.quick.constants.biz.CommonBizStatus;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义鉴权异常
 * @author holy
 * @version 1.0.0
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 */
@Getter
public class CustomAuthenticationException extends AuthenticationException {

    /**
     * 默认 HTTP CODE 状态
     */
    static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.FORBIDDEN;

    /**
     * 默认业务状态
     */
    static final CommonBizStatus DEFAULT_BIZ_STATUS = CommonBizStatus.FORBIDDEN;

    private HttpStatus httpStatus;

    private CommonBizStatus bizStatus;

    public CustomAuthenticationException() {
        this(DEFAULT_HTTP_STATUS, DEFAULT_BIZ_STATUS);
    }

    public CustomAuthenticationException(HttpStatus httpStatus, CommonBizStatus bizStatus) {
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
        this.bizStatus = bizStatus;
    }

    public CustomAuthenticationException(HttpStatus httpStatus, CommonBizStatus bizStatus, Throwable cause) {
        super(httpStatus.getReasonPhrase(), cause);
        this.httpStatus = httpStatus;
        this.bizStatus = bizStatus;
    }


}
