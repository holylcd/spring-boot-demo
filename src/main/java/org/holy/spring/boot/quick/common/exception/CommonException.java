package org.holy.spring.boot.quick.common.exception;

import lombok.Getter;
import org.holy.spring.boot.quick.constants.biz.CommonBizStatus;
import org.springframework.http.HttpStatus;

/**
 * 通用异常
 * @author holy
 * @version 1.0.0
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 */
@Getter
public class CommonException extends BaseException {

    /**
     * 默认业务状态
     */
    static final CommonBizStatus DEFAULT_BIZ_STATUS = CommonBizStatus.INTERNAL_SERVER_ERROR;

    private CommonBizStatus bizStatus;

    public CommonException() {
        this(DEFAULT_HTTP_STATUS, DEFAULT_BIZ_STATUS);
    }

    public CommonException(HttpStatus httpStatus, CommonBizStatus bizStatus) {
        super(httpStatus);
        this.bizStatus = bizStatus;
    }

    public CommonException(HttpStatus httpStatus, CommonBizStatus bizStatus, Throwable cause) {
        this(httpStatus, bizStatus, cause, false, false);
    }

    public CommonException(HttpStatus httpStatus, CommonBizStatus bizStatus, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(httpStatus, cause, enableSuppression, writableStackTrace);
        this.bizStatus = bizStatus;
    }

}
