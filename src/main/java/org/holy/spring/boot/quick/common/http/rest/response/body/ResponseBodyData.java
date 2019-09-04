package org.holy.spring.boot.quick.common.http.rest.response.body;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 业务正常时的响应体，带 data
 * @author holy
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 * @version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ResponseBodyData<T> implements Serializable {

	private static final long serialVersionUID = 7688867097199205758L;

	private Integer code;

	private String msg;

	private T data;

	public static <T> ResponseBodyData<T> ok(T data) {
		return new ResponseBodyData<>(
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase(),
				data
		);
	}
}