package com.javaweb.shopping.controller;
import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiashubing
 * @since 2019/6/25
 */
@RestController
@RequestMapping("file")
public class UploadFileController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultVO uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取文件名
        fileName = getFileName(fileName);
        String filepath = getUploadPath();
        if (!file.isEmpty()) {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filepath + File.separator + fileName)))) {
                out.write(file.getBytes());
                out.flush();
                return new ResultVO(ResStatus.OK, fileName,null);
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
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
        String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
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
        if (!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(), "static/img/");
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }
}