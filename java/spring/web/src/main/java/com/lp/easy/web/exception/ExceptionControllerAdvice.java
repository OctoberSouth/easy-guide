package com.lp.easy.web.exception;

import com.lp.easy.exception.BizCodeEnum;
import com.lp.easy.utils.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseVO handleValidException(MethodArgumentNotValidException exception) {
        log.error("数据校验出现问题{}，具体异常是{}", exception.getMessage(), exception.getClass());
        Map<String, Object> map = new HashMap<>();
        exception.getFieldErrors().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));
        return ResponseVO.error(BizCodeEnum.VAILD_EXECPTION, map);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseVO handleException(Throwable throwable) {
        log.error(String.valueOf(throwable));
        log.error("未知异常{}", throwable);
        return ResponseVO.error(BizCodeEnum.UNKONW_EXCEPTION);
    }
}
