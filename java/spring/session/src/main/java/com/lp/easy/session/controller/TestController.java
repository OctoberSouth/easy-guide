package com.lp.easy.session.controller;

import com.lp.easy.session.entity.TestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 刘攀
 * @version 1.0
 * @time 2019年3月8日下午2:41:05
 * @describe
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping
    public void test(HttpSession session) {
        TestEntity test = new TestEntity(1231L, 1, 1.0d, "String", 1.4F, LocalDateTime.now(), LocalDate.now());

        session.setAttribute("test", test);
        Object test1 = session.getAttribute("test");
        System.out.println(test1);
    }
}
