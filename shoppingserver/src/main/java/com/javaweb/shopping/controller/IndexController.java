package com.javaweb.shopping.controller;


import com.javaweb.shopping.entity.IndexImg;
import com.javaweb.shopping.service.CategoryService;
import com.javaweb.shopping.service.IndexImgService;
import com.javaweb.shopping.service.ProductService;
import com.javaweb.shopping.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@CrossOrigin
@RequestMapping("/index")
@Api(value = "提供首页数据显示所需的接口",tags = "首页管理")
public class IndexController {

    @Autowired
    private IndexImgService indexImgService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/indeximg")
    @ApiOperation("首页轮播图接口")
    public ResultVO listIndexImgs(){
        return indexImgService.listIndexImgs();
    }

    @PostMapping("/addIndexImg")
    @ApiOperation("增加首页轮播图")
    public ResultVO addIndexImg(String imgId, String imgUrl, String imgBgColor, String prodId, String categoryId, Integer indexType, Integer seq, Integer status, Timestamp createTime, Timestamp updateTime){
        return indexImgService.addIndexImg(imgId, imgUrl, imgBgColor, prodId,categoryId,indexType,seq,status,createTime,updateTime);
    }

    @PostMapping("/deleteIndexImg")
    @ApiOperation("删除首页轮播图")
    public ResultVO deleteIndexImg(String imgId){
        return indexImgService.deleteIndexImg(imgId);
    }

    @GetMapping("/category-list")
    @ApiOperation("商品分类查询接口")
    public ResultVO listCatetory(){
        return categoryService.listCategories();
    }


    @GetMapping("/list-recommends")
    @ApiOperation("新品推荐接口")
    public ResultVO listRecommendProducts() {
        return productService.listRecommendProducts();
    }

    @GetMapping("/category-recommends")
    @ApiOperation("分类推荐接口")
    public ResultVO listRecommendProductsByCategory(){
        return categoryService.listFirstLevelCategories();
    }

}
