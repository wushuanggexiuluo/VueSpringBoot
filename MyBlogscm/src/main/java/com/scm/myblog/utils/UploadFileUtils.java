package com.scm.myblog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;

public class UploadFileUtils {
    public static String UploadTemp(MultipartFile file) {
        String filename = file.getOriginalFilename();
        System.out.println("源文件名:"+filename);
        //指定上传文件本地存储目录，不存在需要提前创建
        String dirPath= null;
        boolean isWindows=false;
        if (SystemUtils.isWindows()){
            dirPath = "C://temp";
            System.out.println("是win系统");
            isWindows = true;
        }
        else {
            dirPath="/home/www/temp";
            System.out.println("不是win系统");
        }
        File filePath=new File(dirPath);
        //指定上传文件本地存储目录，不存在需要提前创建
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        System.out.println("dirPath："+dirPath);
        try{
            assert filename != null;
            file.transferTo(new File(dirPath,filename));
        } catch (IOException e) {
            System.out.println("文件转化失败！请检查文件！");
            throw new RuntimeException(e);
        }
        return isWindows?(dirPath+"\\"+filename):(dirPath+"/"+filename);
    }
}