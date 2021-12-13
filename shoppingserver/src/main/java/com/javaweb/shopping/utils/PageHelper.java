package com.javaweb.shopping.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class PageHelper<T> {

    //总记录数
    private int count;

    //总页数
    private int pageCount;

    //分页数据
    private List<T> list;

    public PageHelper() {
    }

    public PageHelper(int count, int pageCount, List<T> list) {
        this.count = count;
        this.pageCount = pageCount;
        this.list = list;
    }
}
