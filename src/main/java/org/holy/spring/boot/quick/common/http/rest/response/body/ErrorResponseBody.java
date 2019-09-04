package org.holy.spring.boot.quick.common.http.rest.response.body;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.holy.spring.boot.quick.constants.biz.IBizStatus;

import java.io.Serializable;

/**
 * 业务异常时的响应体
 * @author holy
 * @
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 * @version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponseBody implements Serializable {

	private static final long serialVersionUID = -3510814042352393019L;

	/**
	 * 业务状态码
	 */
	private Integer code;

	/**
	 * 业务消息
	 */
	private String msg;

	public static ErrorResponseBody err(IBizStatus bizStatus) {
		return new ErrorResponseBody(bizStatus.getCode(), bizStatus.getMsg());
	}

	public static ErrorResponseBody err(Integer code, String msg) {
		return new ErrorResponseBody(code, msg);
	}

}