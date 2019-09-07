package org.holy.spring.boot.quick.common.http.rest.response.body;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 业务正常时的响应体，带 type
 * @author holy
 * @email holylcd@foxmail.com
 * @date 2019/4/12
 * @version 1.0.0
 */
@Getter
public class ResponseBodyPage<T> extends PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 7688867097199205758L;

	private Integer code;

	private String msg;

	private ResponseBodyPage() {
		super();
	}

	private ResponseBodyPage(List<T> list, Integer code, String msg) {
		super(list);
		this.code = code;
		this.msg = msg;
	}

	private ResponseBodyPage(List<T> list, int navigatePages, Integer code, String msg) {
		super(list, navigatePages);
		this.code = code;
		this.msg = msg;
	}

	public static <T> ResponseBodyPage<T> ok(List<T> list) {
		// 参数验证
		Objects.requireNonNull(list, "查询异常");

		return new ResponseBodyPage<>(
				list,
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase()
				);
	}

	public static <T> ResponseBodyPage<T> ok(List<T> list, int navigatePages) {
		// 参数验证
		Objects.requireNonNull(list, "查询异常");
		if (0 >= navigatePages) {
			throw new IllegalArgumentException("页行数不能小于等于0");
		}

		return new ResponseBodyPage<>(
				list,
				navigatePages,
				HttpStatus.OK.value(),
				HttpStatus.OK.getReasonPhrase()
		);
	}
}