package org.holy.spring.boot.quick.common.http.rest.response.body;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 业务正常时的响应体
 * @author holy
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 * @version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ResponseBody implements Serializable {

	private static final long serialVersionUID = 7581339796446717946L;

	private Integer code;

	private String msg;

	public static ResponseBody ok() {
		return new ResponseBody(
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase()
		);
	}
}