package org.holy.spring.boot.quick.common.http.rest.request.body;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页基础参数
 * @author holy
 * @date 2019/9/4 9:38
 * @version 1.0.0
 */
@Data
public class CommonPage implements Serializable {

    private static final long serialVersionUID = -8081743138283953970L;

    @Min(value = 1,message = "页码不能小于1")
    @NotNull(message = "页码不能为空")
    private Integer page;


    @Min(value = 1,message = "页行数不能小于1")
    @Max(value = 100,message = "页行数不能大于100")
    @NotNull(message = "页行数不能为空")
    private Integer prePage;

}
