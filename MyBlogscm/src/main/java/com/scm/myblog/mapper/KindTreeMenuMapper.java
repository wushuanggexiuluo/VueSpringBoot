package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.DOMAIN.KindTreeMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 孙超孟
* @description 针对表【kind_tree_menu(分类树状菜单)】的数据库操作Mapper
* @createDate 2023-04-27 14:28:27
* @Entity generator.domain.KindTreeMenu
*/
@Mapper
@Repository
public interface KindTreeMenuMapper extends BaseMapper<KindTreeMenu> {

    List<KindTreeMenu> findById(Integer id);

}




