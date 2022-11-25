package com.lp.easy.redis;

import cn.hutool.json.JSONUtil;
import com.lp.easy.redis.entity.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class RedisApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        TestEntity test = new TestEntity(1231L, 1, 1.0d, "String", 1.4F, LocalDateTime.now(), LocalDate.now());

        System.out.println(JSONUtil.toJsonStr(test));
        this.stringRedisTemplate.opsForValue().set(UUID.randomUUID().toString(), JSONUtil.toJsonStr(test));
    }

}
