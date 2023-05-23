package com.scm.myblog.controller.blogcontrollers;

import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 精品控制器
 *
 * @author Lancer
 * @date 2022/12/08
 */
@Api(tags = "精品控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Blog")
public class BoutiqueController {
    @Autowired
    ArticleServiceImpl a;
    @ApiOperation("获取所有精品")
    @GetMapping("/getAllBoutique")
    public Result getAllBoutique(){
        return a.getAllBoutique();
    }
}
