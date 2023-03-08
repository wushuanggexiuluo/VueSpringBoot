package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getAllTagPage(
            @Param("categoryName") String categoryName,
            @Param("categoryDescription") String categoryDescription,
            @Param("currentPage")Integer currentPage,
            @Param("pageSize")Integer pageSize
    );
   Integer getAllTagCount(
           @Param("categoryName") String categoryName,
           @Param("categoryDescription") String categoryDescription
           );

   Long getIdByTagName( @Param("categoryName") String categoryName);
}
