package org.holy.spring.boot.quick.constants.type;

import lombok.Getter;

/**
 * 性别枚举
 * @author holy
 * @version 1.0.0
 * @date 2019/9/4 23:20
 */
@Getter
public enum GenderType {

    /**
     * 男
     */
    MALE(0, "male"),
    /**
     * 女
     */
    FEMALE(1, "female");

    private Integer code;

    private String value;

    GenderType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static GenderType valueOf(Integer code) {
        for (GenderType gender : values()) {
            if (gender.code.equals(code)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("非法的性别 code");
    }
}
