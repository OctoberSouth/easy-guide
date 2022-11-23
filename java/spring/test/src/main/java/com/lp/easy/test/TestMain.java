package com.lp.easy.test;

import cn.hutool.json.JSONUtil;
import com.lp.easy.exception.BizCodeEnum;
import com.lp.easy.utils.ResponseVO;

public class TestMain {

    public static void main(String[] args) {
        ResponseVO responseVO = new ResponseVO<String>();
        responseVO.setT(1);
        System.out.println(JSONUtil.toJsonStr(ResponseVO.error(BizCodeEnum.UNKONW_EXCEPTION, responseVO)));
    }
}
