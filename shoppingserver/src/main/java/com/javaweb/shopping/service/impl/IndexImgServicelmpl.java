package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.IndexImg;
import com.javaweb.shopping.mapper.IndexImgMapper;
import com.javaweb.shopping.service.IndexImgService;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class IndexImgServicelmpl implements IndexImgService {

    @Autowired
    private IndexImgMapper indexImgMapper;

    @Override
    public ResultVO listIndexImgs() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        if(indexImgs.size()==0){
            return new ResultVO(ResStatus.NO,"fail",null);
        }else{
            return new ResultVO(ResStatus.OK,"success",indexImgs);
        }
    }

//    @Override
//    public ResultVO addIndexImg(IndexImg img) {
//        try{
//            Date date = new Date();
//            Timestamp time = new Timestamp(date.getTime());
//            img.setCreateTime(time);
//            img.setUpdateTime(time);
//            img.setImgId("10");
//            img.setImgUrl("123.jpg");
//            img.setIndexType(1);
//            img.setStatus(1);
//            img.setSeq(2);
//            indexImgMapper.addIndexImg(img);
//            return new ResultVO(ResStatus.OK,"success",null);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResultVO(ResStatus.NO,"数据库层操作失败",null);
//        }
//    }

    //增加轮播图
    @Transactional
    @Override
    public ResultVO addIndexImg(String imgId, String imgUrl, String imgBgColor, String prodId, String categoryId, Integer indexType, Integer seq, Integer status, Timestamp createTime, Timestamp updateTime){
                try{
                indexImgMapper.addIndexImg(imgId, imgUrl, imgBgColor, prodId,categoryId,indexType,seq,status,createTime,updateTime);
                return new ResultVO(ResStatus.OK,"success",null);
            }catch (Exception e){
                e.printStackTrace();
                return new ResultVO(ResStatus.NO,"数据库层操作失败",null);
            }
    }


    //删除轮播图
    @Transactional
    @Override
    public ResultVO deleteIndexImg(String imgId){
        try{
            indexImgMapper.deleteIndexImg(imgId);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultVO(ResStatus.NO,"数据库层操作失败",null);
        }
    }

    //上传轮播图
    @Override
    public ResultVO addIndexPic(String imgUrl){
        try{
            return new ResultVO(ResStatus.OK,imgUrl,null);
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
        }
    }

}
