package com.javaweb.shopping.service;

import com.javaweb.shopping.vo.ResultVO;

public interface CategoryService {

    public ResultVO listCategories();

    public ResultVO listFirstLevelCategories();

}
