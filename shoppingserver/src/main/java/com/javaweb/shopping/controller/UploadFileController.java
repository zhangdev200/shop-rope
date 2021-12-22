package com.javaweb.shopping.controller;

import com.javaweb.shopping.vo.ResStatus;
import com.javaweb.shopping.vo.ResultVO;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/file")
public class UploadFileController {


    @PostMapping("/img")
    public ResultVO singleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResultVO(ResStatus.NO,"请选择上传文件",null);
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/resources/static/img/"+file.getOriginalFilename());
            Files.write(path, bytes);
            return new ResultVO(ResStatus.OK,"上传成功",null);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultVO(ResStatus.NO,"上传失败",null);
        }
    }

}