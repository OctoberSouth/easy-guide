package com.lp.easy.utils;

import com.lp.easy.exception.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {

    /**
     * code
     */
    private Integer code = 200;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T t;

    public ResponseVO(T t) {
        this.t = t;
    }

    public ResponseVO(T t, Integer code) {
        this.t = t;
        this.code = code;

    }

    public ResponseVO(BizCodeEnum bizCodeEnum) {
        this.code = bizCodeEnum.getCode();
        this.message = bizCodeEnum.getMessage();
    }

    public ResponseVO(BizCodeEnum bizCodeEnum, T t) {
        this.code = bizCodeEnum.getCode();
        this.message = bizCodeEnum.getMessage();
        this.t = t;
    }

    public static <T> ResponseVO<T> success(T t) {
        return new ResponseVO(t);
    }


    public static <T> ResponseVO<T> error(BizCodeEnum bizCodeEnum) {
        return new ResponseVO(bizCodeEnum);
    }

    public static <T> ResponseVO<T> error(BizCodeEnum bizCodeEnum, T t) {
        return new ResponseVO(bizCodeEnum, t);
    }

}

