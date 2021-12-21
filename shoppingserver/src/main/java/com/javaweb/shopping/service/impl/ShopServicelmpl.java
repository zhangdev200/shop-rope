package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.*;
import com.javaweb.shopping.mapper.*;
import com.javaweb.shopping.service.ShopService;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ShopServicelmpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCommentsMapper productCommentsMapper;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductParamsMapper productParamsMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;


    @Override
    public ResultVO selectProductFromShopID(String shopID) {
        try{
            List <ProductVO> shopProduct=shopMapper.selectProductFromShopID(shopID);
            return new ResultVO(ResStatus.OK,"success",shopProduct);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层获取失败！",null);
        }

    }

    @Override
    public ResultVO listAllShop() {
        try{
            List <Shop> shops=shopMapper.listAllShop();
            return new ResultVO(ResStatus.OK,"success",shops);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层获取失败！",null);
        }
    }


    @Override
    public ResultVO addProduct(ProductVO productVO) {
//        try{
            productVO.setProductStatus(0);
            Product product = new Product(productVO.getProductId(),productVO.getProductName(),productVO.getCategoryId(),productVO.getRootCategoryId(),productVO.getSoldNum(),productVO.getProductStatus(),productVO.getContent(),productVO.getShopID());
            shopMapper.addProduct(product);
            if(productVO.getSkus()!=null){
                for(ProductSku productSku: productVO.getSkus()){
                    productSkuMapper.insert(productSku);
                }
            }
            if(productVO.getImgs()!=null){
                for(ProductImg productImg: productVO.getImgs()){
                    productImgMapper.insert(productImg);
                }
            }
            return new ResultVO(ResStatus.OK,"success",productVO.getProductId());
//        }catch (Exception e){
//            System.out.println(e);
//            return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
//        }
    }

    @Override
    public ResultVO addProductParams(ProductParams productParams) {
        try{
            if(productParams.getParamId()==null){
                productParams.setParamId(String.valueOf(System.currentTimeMillis()));
            }
            productParamsMapper.insert(productParams);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
        }


    }

    @Override
    public ResultVO deleteProduct(String ID) {
        try{

            //Product表
            shopMapper.deleteProduct(Integer.parseInt(ID));
            //评论表
            productCommentsMapper.deleteProductComment(ID);
            //product_params表
            Example example1 = new Example(ProductParams.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("productId",ID);//状态为1表示上架商品
            productParamsMapper.deleteByExample(example1);
            //product_sku表
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId",ID);//状态为1表示上架商品
            productSkuMapper.deleteByExample(example2);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层删除失败！",null);
        }

    }

    @Override
    public ResultVO updateUserToShopKeeper(int ID) {
        try{
            shopMapper.updateUserToShopKeeper(1);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }

    }

    @Transactional
    @Override
    public ResultVO addShop(Shop shop,String userId) {
        synchronized (this){
            try{
                if(shop.getShopID()==null){
                    shop.setShopID(String.valueOf(System.currentTimeMillis()%1000000000));
                }
                //状态设为0表示管理员还未审核同意
                shop.setStatus(0);
                shop.setShopKeeperID(userId);
                shopMapper.addShop(shop);
                shopMapper.updateUserToShopKeeper(Integer.parseInt(userId));
                return new ResultVO(ResStatus.OK,"success",null);
            }catch (Exception e){
                System.out.println(e);
                return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
            }
        }

    }

    @Override
    public ResultVO updateShopKeeperToUser(int ID) {
        try{
            shopMapper.updateShopKeeperToUser(ID);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }

    }

    @Override
    public ResultVO deleteShop(String ID,String userId) {
        synchronized (this){
            try{
                //先删除该店铺所有的商品
                List<ProductVO> products = shopMapper.selectProductFromShopID(String.valueOf(ID));
                for(ProductVO product:products){
                    String id=product.getProductId();
                    deleteProduct(id);
                }
                shopMapper.deleteShop(ID);
                shopMapper.updateShopKeeperToUser(Integer.parseInt(userId));
                return new ResultVO(ResStatus.OK,"success",null);
            }catch (Exception e){
                return new ResultVO(ResStatus.NO,"数据库层删除失败！",null);
            }
        }

    }


}
