package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.*;
import com.javaweb.shopping.mapper.*;
import com.javaweb.shopping.service.ShopService;
import com.javaweb.shopping.utils.IDUtils;
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
    public ResultVO listShopByUserId(String userId){
        try{
            Example example = new Example(Shop.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("shopKeeperID",userId);//状态为1表示上架商品
            List <Shop> shops = shopMapper.selectByExample(example);
            return new ResultVO(ResStatus.OK,"success",shops);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultVO(ResStatus.NO,"数据库层获取失败！",null);
        }
    }
    @Override
    public ResultVO selectProductFromShopID(String shopID) {
        try{
            List <ProductVO> shopProduct=shopMapper.selectProductFromShopID(shopID);
            for(ProductVO productVO: shopProduct){

                Example example = new Example(ProductSku.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("productId",productVO.getProductId());//状态为1表示上架商品
                List<ProductSku> productSkus =  productSkuMapper.selectByExample(example);

                Example example1 = new Example(ProductImg.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("itemId",productVO.getProductId());//状态为1表示上架商品
                List<ProductImg> productImgs =  productImgMapper.selectByExample(example1);

                productVO.setSkus(productSkus);
                productVO.setImgs(productImgs);
            }
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
    public ResultVO listAllCheckingShop() {
        try{
            List <Shop> shops=shopMapper.listAllCheckingShop();
            return new ResultVO(ResStatus.OK,"success",shops);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层获取失败！",null);
        }
    }

    @Override
    public ResultVO addProduct(ProductVO productVO) {
        try{
            productVO.setProductStatus(0);
            if(productVO.getProductId()==null){
                productVO.setProductId(String.valueOf(IDUtils.getId()));
            }
            Product product = new Product(productVO.getProductId(),productVO.getProductName(),productVO.getCategoryId(),productVO.getRootCategoryId(),productVO.getSoldNum(),productVO.getProductStatus(),productVO.getContent(),productVO.getShopID());
            shopMapper.addProduct(product);
            if(productVO.getSkus()!=null){
                for(ProductSku productSku: productVO.getSkus()){
                    if(productSku.getSkuId()==null){
                        productSku.setSkuId(IDUtils.getId());
                    }
                    productSku.setProductId(productVO.getProductId());
                    productSkuMapper.insert(productSku);
                }
            }
            if(productVO.getImgs()!=null){
                for(ProductImg productImg: productVO.getImgs()){
                    if(productImg.getId()==null){
                        productImg.setId(IDUtils.getId());
                    }
                    productImg.setItemId(productVO.getProductId());
                    productImgMapper.insert(productImg);
                }
            }
            return new ResultVO(ResStatus.OK,"success",productVO.getProductId());
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
        }
    }

    @Override
    public ResultVO addProductImg(ProductImg productImg){
        try{
            productImgMapper.insert(productImg);
            return new ResultVO(ResStatus.OK,productImg.getUrl(),null);
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
        }
    }


    @Override
    public ResultVO updateProduct(ProductVO productVO) {
        try{
            Product product = new Product(productVO.getProductId(),productVO.getProductName(),productVO.getCategoryId(),productVO.getRootCategoryId(),productVO.getSoldNum(),productVO.getProductStatus(),productVO.getContent(),productVO.getShopID());
            Example example = new Example(Product.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("productId",product.getProductId());//状态为1表示上架商品
            productMapper.updateByExampleSelective(product,example);

            if(productVO.getSkus()!=null){
                Example example1 = new Example(ProductSku.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("productId",product.getProductId());//状态为1表示上架商品
                productSkuMapper.deleteByExample(example);
                for(ProductSku productSku: productVO.getSkus()){
                    if(productSku.getSkuId()==null){
                        productSku.setSkuId(IDUtils.getId());
                    }
                    productSku.setProductId(productVO.getProductId());
                    productSkuMapper.insert(productSku);

                }
            }
            if(productVO.getImgs()!=null){
                Example example2 = new Example(ProductImg.class);
                Example.Criteria criteria2 = example2.createCriteria();
                criteria2.andEqualTo("productId",product.getProductId());//状态为1表示上架商品
                productImgMapper.deleteByExample(example);
                for(ProductImg productImg: productVO.getImgs()){
                    if(productImg.getId()==null){
                        productImg.setId(IDUtils.getId());
                    }
                    productImg.setItemId(productVO.getProductId());
                    productImgMapper.insert(productImg);
                }
            }
            return new ResultVO(ResStatus.OK,"success",productVO.getProductId());
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }
    }

    @Override
    public ResultVO addProductParams(ProductParams productParams) {
        try{
            if(productParams.getParamId()==null){
                productParams.setParamId(String.valueOf(IDUtils.getId()));
            }
            productParamsMapper.insert(productParams);
            return new ResultVO(ResStatus.OK,"success",productParams.getParamId());
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层插入失败！",null);
        }
    }

    @Override
    public ResultVO updateProductParams(ProductParams productParams) {
        try{
            Example example = new Example(ProductParams.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("productId",productParams.getProductId());//状态为1表示上架商品
            productParamsMapper.updateByExample(productParams,example);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            System.out.println(e);
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }
    }

    @Override
    public String[] getShopIdByUserID(int userID) {
        return shopMapper.getShopIdByUserID(userID);
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





    @Transactional
    @Override
    public ResultVO addShop(Shop shop,String userId) {
        synchronized (this){
            try{
                if(shop.getShopID()==null){
                    shop.setShopID(String.valueOf(IDUtils.getId()));
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

    @Transactional
    //店铺申请审核通过
    @Override
    public ResultVO updateUserToShopKeeper(int ID) {
        try{
            shopMapper.updateUserToShopKeeper(ID);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }

    }

    @Transactional
    @Override
    public ResultVO updateShopKeeperToUser(int ID) {
        try{
            shopMapper.updateShopKeeperToUser(ID);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }

    }
    //修改店铺状态通过
    @Transactional
    @Override
    public ResultVO pass(int ID) {//ID为商店的ID，非店主ID
        try{
            shopMapper.pass(ID);
            return new ResultVO(ResStatus.OK,"success",null);
        }catch (Exception e){
            return new ResultVO(ResStatus.NO,"数据库层更新失败！",null);
        }
    }


    @Override
    public ResultVO deleteShop(String ID,String userId) {
        synchronized (this){
            try{
                //先删除该店铺所有的商品shop_name
                List<ProductVO> products = shopMapper.selectProductFromShopID(String.valueOf(ID));
                for(ProductVO product:products){
                    String id=product.getProductId();
                    deleteProduct(id);
                }
                shopMapper.deleteShop(ID,userId);
                shopMapper.updateShopKeeperToUser(Integer.parseInt(userId));
                return new ResultVO(ResStatus.OK,"success",null);
            }catch (Exception e){
                return new ResultVO(ResStatus.NO,"数据库层删除失败！",null);
            }
        }

    }
}
