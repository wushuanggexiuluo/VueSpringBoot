package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.DOMAIN.Dictionary;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 孙超孟
* @description 针对表【dictionary(数据字典)】的数据库操作Mapper
* @createDate 2023-04-28 15:52:42
* @Entity generator.domain.Dictionary
*/
@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

}




