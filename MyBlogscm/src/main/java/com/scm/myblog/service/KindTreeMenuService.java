package com.scm.myblog.service;

import com.scm.myblog.entity.CORE.Result2;

/**
 * @author 孙超孟
 * @description 针对表【kind_tree_menu(分类树状菜单)】的数据库操作Service
 * @createDate 2023-04-27 14:28:28
 */
public interface KindTreeMenuService {

    Result2<String> getClassifyTree();
}
