package com.javaweb.shopping.service;

import com.javaweb.shopping.entity.IndexImg;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface IndexImgService {

    public ResultVO listIndexImgs();

    public ResultVO addIndexImg(String imgId, String imgUrl, String imgBgColor, String prodId, String categoryId, Integer indexType, Integer seq, Integer status, Timestamp createTime,Timestamp updateTime);

    //删除轮播图
    @Transactional
    public ResultVO deleteIndexImg(String imgId);

    ResultVO addIndexPic(String imgUrl);

}
