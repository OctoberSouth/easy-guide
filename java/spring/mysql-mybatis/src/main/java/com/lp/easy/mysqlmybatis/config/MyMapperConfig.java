package com.lp.easy.mysqlmybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 事务
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.lp.easy.mysqlmybatis.dao")
public class MyMapperConfig {
}
