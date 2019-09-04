package org.holy.spring.boot.quick.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 基础异常
 * @author holy
 * @version 1.0.0
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 */
@Getter
abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = -4764276147992596434L;

    /**
     * 默认 http 状态
     */
    static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private HttpStatus httpStatus;

    public BaseException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
    }

    public BaseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BaseException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus.getReasonPhrase(), cause);
        this.httpStatus = httpStatus;
    }

    public BaseException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public BaseException(HttpStatus httpStatus, Throwable cause,
                         boolean enableSuppression,boolean writableStackTrace) {
        super(httpStatus.getReasonPhrase(), cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
    }

    public BaseException(HttpStatus httpStatus, Throwable cause, String message,
                         boolean enableSuppression,boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
    }
}
