package com.scm.myblog.controller;

import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.Tips;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.exception.DefinitionException;
import com.scm.myblog.utils.QiniuCloudUtil;
import com.scm.myblog.utils.UploadFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Api(tags = "上传控制器")
@CrossOrigin(origins = "*")
@RestController
public class UploadController {
    @ApiOperation("上传文件")
    @PostMapping("/Upload")
    public Result UploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String url="";
        try {
            url=QiniuCloudUtil.upload(new File(UploadFileUtils.UploadTemp(file)));
        } catch (IOException e) {
            throw new DefinitionException(Code.UPLOAD_ERR, Tips.UPLOAD_ERR);
        }
        return new Result(url,Code.UPLOAD_OK,Tips.UPLOAD_OK);
    }

}
