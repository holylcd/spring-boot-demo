package org.holy.spring.boot.quick.common.exception;

import lombok.Getter;
import org.holy.spring.boot.quick.constants.biz.CommonBizStatus;
import org.springframework.http.HttpStatus;

/**
 * http security 异常
 * @author holy
 * @version 1.0.0
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 */
@Getter
public class SecurityException extends BaseException {

    /**
     * 默认 HTTP CODE 状态
     */
    static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.FORBIDDEN;

    /**
     * 默认业务状态
     */
    static final CommonBizStatus DEFAULT_BIZ_STATUS = CommonBizStatus.FORBIDDEN;

    private CommonBizStatus bizStatus;

    public SecurityException() {
        this(DEFAULT_HTTP_STATUS, DEFAULT_BIZ_STATUS);
    }

    public SecurityException(HttpStatus httpStatus, CommonBizStatus bizStatus) {
        super(httpStatus);
        this.bizStatus = bizStatus;
    }

    public SecurityException(HttpStatus httpStatus, CommonBizStatus bizStatus, Throwable cause) {
        this(httpStatus, bizStatus, cause, false, false);
    }

    public SecurityException(HttpStatus httpStatus, CommonBizStatus bizStatus, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(httpStatus, cause, enableSuppression, writableStackTrace);
        this.bizStatus = bizStatus;
    }

}
