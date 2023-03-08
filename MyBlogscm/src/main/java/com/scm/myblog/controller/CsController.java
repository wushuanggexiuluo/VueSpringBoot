package com.scm.myblog.controller;

import com.scm.myblog.entity.BO.LoginUser;
import com.scm.myblog.entity.DTO.LoginDto;
import com.scm.myblog.entity.User;
import com.scm.myblog.entity.VO.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@Api(tags = "Cs控制器")
@RestController
@RequestMapping("/cs")
public class CsController {

    @GetMapping("/get2")
    public String userNginx(){
        return "hello,world!";
    }

    @GetMapping("/get")
    @ApiOperation("获取标记")
    public Timestamp getTime(){
        return new Timestamp(new Date().getTime());
    }

    @ApiOperation("用户数据校验测试")
    @PostMapping("/csuser")
    public Result userTest(@RequestBody @Valid LoginDto u, BindingResult result){
        if (result.hasErrors()){
            System.out.println(1);
        }
        else {
            System.out.println(2);
        }
        return null;
    }
}
