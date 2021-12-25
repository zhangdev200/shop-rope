package com.javaweb.shopping.controller;
import com.javaweb.shopping.entity.IndexImg;
import com.javaweb.shopping.entity.ProductImg;
import com.javaweb.shopping.mapper.ProductImgMapper;
import com.javaweb.shopping.service.IndexImgService;
import com.javaweb.shopping.service.ProductService;
import com.javaweb.shopping.service.ShopService;
import com.javaweb.shopping.service.UserService;
import com.javaweb.shopping.utils.IDUtils;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiashubing
 * @since 2019/6/25
 */
@RestController
@RequestMapping("file")
public class UploadFileController {
    @Autowired
    ShopService shopService;
    @Autowired
    IndexImgService indexImgService;
    @Autowired
    UserService userService;
    String filepath="img";
    @RequestMapping(value = "/productimg", method = RequestMethod.POST)
    public ResultVO uploadProductImg(@RequestParam("file") MultipartFile file,@RequestParam String productId) {
        String fileName = file.getOriginalFilename();//获取文件名
        fileName = getFileName(fileName);
        String localfilepath = getUploadPath();
        if (!file.isEmpty()) {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(localfilepath + File.separator + fileName)))) {
                out.write(file.getBytes());
                out.flush();
                String path=filepath + File.separator + fileName;

                return shopService.addProductImg(new ProductImg(productId,path));
            } catch (FileNotFoundException e) {
                return new ResultVO(ResStatus.NO, "上传文件失败 FileNotFoundException：" + e.getMessage(),null);
            } catch (IOException e) {
                return new ResultVO(ResStatus.NO, "上传文件失败 IOException：" + e.getMessage(),null);
            }
        } else {
            return new ResultVO(ResStatus.NO, "上传文件失败，文件为空",null);
        }
    }

    //上传轮播图
    @RequestMapping(value = "/indexImg", method = RequestMethod.POST)
    public ResultVO uploadIndexImg(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取文件名
        fileName = getFileName(fileName);
        String localfilepath = getUploadPath();
        if (!file.isEmpty()) {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(localfilepath + File.separator + fileName)))) {
                out.write(file.getBytes());
                out.flush();
                String imgUrl=filepath + File.separator + fileName;
                return indexImgService.addIndexPic(imgUrl);
            } catch (FileNotFoundException e) {
                return new ResultVO(ResStatus.NO, "上传文件失败 FileNotFoundException：" + e.getMessage(),null);
            } catch (IOException e) {
                return new ResultVO(ResStatus.NO, "上传文件失败 IOException：" + e.getMessage(),null);
            }
        } else {
            return new ResultVO(ResStatus.NO, "上传文件失败，文件为空",null);
        }
    }

    @RequestMapping(value = "/userimg", method = RequestMethod.POST)
    public ResultVO uploadUserImg(@RequestParam("file") MultipartFile file,@RequestParam int userId) {
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);

        String localfilepath = getUploadPath();
        if (!file.isEmpty()) {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(localfilepath + File.separator + fileName)))) {
                out.write(file.getBytes());
                out.flush();
                String path=filepath + File.separator + fileName;
                return userService.updateUserImg(userId,path);
            } catch (FileNotFoundException e) {
                return new ResultVO(ResStatus.NO, "上传文件失败 FileNotFoundException：" + e.getMessage(),null);
            } catch (IOException e) {
                return new ResultVO(ResStatus.NO, "上传文件失败 IOException：" + e.getMessage(),null);
            }
        } else {
            return new ResultVO(ResStatus.NO, "上传文件失败，文件为空",null);
        }
    }




    /**
     * 文件名后缀前添加一个时间戳
     */
    private String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyyMMddHHmmss");  //设置时间格式
        String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + IDUtils.getId() +fileName.substring(index);
        return fileName;
    }

    /**
     * 获取当前系统路径
     */
    private String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "static/img/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }
}