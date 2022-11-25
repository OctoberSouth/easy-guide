package com.lp.easy.web.exception;

import com.lp.easy.exception.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException {

    private Integer code;

    private String message;

    public MyException(BizCodeEnum bizCode) {
        this.code = bizCode.getCode();
        this.message = bizCode.getMessage();
    }

    public MyException(String message) {
        this.code = 500;
        this.message = message;
    }

    public static MyException error(BizCodeEnum bizCode) {
        return new MyException(bizCode);
    }

    public static MyException error(String message) {
        return new MyException(message);
    }

}
