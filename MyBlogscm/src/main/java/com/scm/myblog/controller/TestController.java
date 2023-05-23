package com.scm.myblog.controller;

import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.DOMAIN.KindTreeMenu;
import com.scm.myblog.entity.DTO.K_V_DictionaryDto;
import com.scm.myblog.entity.VO.DictionaryVo;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.service.impl.DictionaryServiceImpl;
import com.scm.myblog.service.impl.KindTreeMenuServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Test")
@Api(value = "测试控制器")
public class TestController {

    @Autowired
    private KindTreeMenuServiceImpl ktms;

    @Autowired
    private DictionaryServiceImpl ds;


    /**
     * 获取分类树
     *
     * @return {@link Result2}<{@link String}>
     */
    @GetMapping("/getClassifyTree")
    public Result2<String> getClassifyTree(){
        return ktms.getClassifyTree();
    }

    /**
     * 获取数字字典
     *
     * @return {@link Result2}<{@link PageData}<{@link DictionaryVo}>>
     */
    @GetMapping("/getDictionary")
    public Result2<PageData<DictionaryVo>> getDictionary(){
        return ds.getDictionary();
    }

    @GetMapping("/getAllKeyValue")
    public Result2<Map<String,String>> getKeyValue(){
       return ds.getAllKeyValueOnDictionary();
    }


    @GetMapping("/getAllKeyValue/{id}")
    public Result2<Map<String,String>> getKeyValueById(@PathVariable("id") Integer id){
        return ds.getAllKeyValueOnDictionaryById(id);
    }
    /**
     * 添加通过id 新增k_v
     *
     * @param dto dto
     * @return {@link Result2}<{@link String}>
     */
    @PostMapping("/addKeyValById")
    public Result2<String> addKeyValById(@RequestBody @Validated K_V_DictionaryDto dto){
        return ds.addKeyValById(dto);
    }

    //搜索树状菜单
    @GetMapping("/findTreeById/{id}")
    public Result2<List<KindTreeMenu>> findTreeById(@PathVariable("id") Integer id){
        return ktms.findTreeById(id);
    }
}
