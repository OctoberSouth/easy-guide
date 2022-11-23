package com.lp.easy.exception;


import lombok.Getter;

@Getter
public enum BizCodeEnum {

    UNKONW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXECPTION(10001, "参数格式校验异常");

    private Integer code;
    private String message;

    BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
