package com.javaweb.shopping.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import static com.sun.javafx.tk.Toolkit.tk;

public interface GeneralDao<T> extends Mapper<T>,MySqlMapper<T> {
}
