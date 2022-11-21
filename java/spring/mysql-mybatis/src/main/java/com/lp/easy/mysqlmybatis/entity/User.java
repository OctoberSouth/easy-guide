package com.lp.easy.mysqlmybatis.entity;

import io.mybatis.provider.Entity;
import lombok.Data;

@Data
@Entity.Table("user")
public class User {

    @Entity.Column(id = true)
    private Long id;
    @Entity.Column("user_name")
    private String userName;
    @Entity.Column
    private Integer sex;

}