package com.lp.easy.mysqlmybatis.dao;

import io.mybatis.mapper.Mapper;

import java.io.Serializable;

public interface MyMapper<T, I extends Serializable> extends Mapper {
}
