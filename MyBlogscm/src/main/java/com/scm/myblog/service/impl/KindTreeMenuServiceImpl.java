package com.scm.myblog.service.impl;


import com.alibaba.fastjson.JSON;
import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.DOMAIN.KindTreeMenu;
import com.scm.myblog.mapper.DictionaryMapper;
import com.scm.myblog.mapper.KindTreeMenuMapper;
import com.scm.myblog.service.KindTreeMenuService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 孙超孟
* @description 针对表【kind_tree_menu(分类树状菜单)】的数据库操作Service实现
* @createDate 2023-04-27 14:28:28
*/
@Service
public class KindTreeMenuServiceImpl implements KindTreeMenuService {

    @Autowired
    private KindTreeMenuMapper kindTreeMenuMapper;

    @Autowired
    private DictionaryMapper dm;
    @Autowired
    private DbUtils dbUtils;

    @Override
    public Result2<String> getClassifyTree() {
        List<KindTreeMenu> treeMenus = kindTreeMenuMapper.selectList(null);
        List<KindTreeMenu> menus = dbUtils.listWithTree(treeMenus,true);
        System.out.println(menus);
        String s = JSON.toJSONString(menus);
        return Result2.ok(s.replace("[]", "undefined"));
    }

    public Result2<List<KindTreeMenu>> findTreeById(Integer id){
        List<KindTreeMenu> treeMenus = kindTreeMenuMapper.findById(id);
        // 递归将数据变立体
        List<KindTreeMenu> menus = dbUtils.listWithTree(treeMenus,true);
        System.out.println(menus);
        return Result2.ok(menus);
    }
}




