package org.holy.spring.boot.quick.constants.biz;

/**
 * 通用业务状态
 * @author holy
 * @date 2019/9/3 17:02
 * @version 1.0.0
 */
public enum CommonBizStatus implements IBizStatus {

    /**
     * 200 成功
     */
    OK(200, "OK"),
    /**
     * 500 系统异常
     */
    INTERNAL_SERVER_ERROR(500, "系统异常");

    private Integer code;

    private String msg;

    CommonBizStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
