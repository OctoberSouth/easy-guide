package com.lp.easy.redis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {

    private Long longType;

    private Integer integerType;

    private Double doubleType;

    private String stringType;

    private Float floatType;

    private LocalDateTime localDateTimeType;

    private LocalDate localDateType;

}
