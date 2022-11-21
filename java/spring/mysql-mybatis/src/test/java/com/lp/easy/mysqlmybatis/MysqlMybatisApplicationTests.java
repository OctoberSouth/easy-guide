package com.lp.easy.mysqlmybatis;

import cn.hutool.json.JSONUtil;
import com.lp.easy.mysqlmybatis.entity.User;
import com.lp.easy.mysqlmybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MysqlMybatisApplicationTests {


    @Resource
    private UserService userService;

    @Test
    void add() {
        User user = new User();
        user.setUserName("mingzi");
        user.setSex(12);
        this.userService.add(user);
    }

    @Test
    void page() {
        System.out.println(JSONUtil.toJsonStr(this.userService.page()));
    }

}
