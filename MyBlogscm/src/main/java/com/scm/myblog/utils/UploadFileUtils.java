package com.scm.myblog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;

@Slf4j
public class UploadFileUtils {
    public static String UploadTemp(MultipartFile file) {
        String filename = file.getOriginalFilename();
        log.info("源文件名:"+filename);
        //指定上传文件本地存储目录，不存在需要提前创建
        String dirPath= null;
        boolean isWindows=false;
        if (SystemUtils.isWindows()){
            dirPath = "C://temp";
            log.info("是win系统");
            isWindows = true;
        }
        else {
            dirPath="/home/www/temp";
            log.info("不是win系统");
        }
        File filePath=new File(dirPath);
        //指定上传文件本地存储目录，不存在需要提前创建
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        log.info("dirPath："+dirPath);
        try{
            assert filename != null;
            file.transferTo(new File(dirPath,filename));
        } catch (IOException e) {
            log.info("文件转化失败！请检查文件！");
            throw new RuntimeException(e);
        }
        return isWindows?(dirPath+"\\"+filename):(dirPath+"/"+filename);
    }
}