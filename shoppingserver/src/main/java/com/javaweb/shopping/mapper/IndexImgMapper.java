package com.javaweb.shopping.mapper;

import com.javaweb.shopping.entity.IndexImg;
import com.javaweb.shopping.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IndexImgMapper extends GeneralDAO<IndexImg> {

    //1.查询轮播图信息： 查询status=1 且 按照seq进行排序
    public List<IndexImg> listIndexImgs();

    //2.增加轮播图信息
    public void addIndexImg(String imgId, String imgUrl, String prodId);
    //删除轮播图
    public void deleteIndexImg(String imgId);



}
