package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.DOMAIN.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<String> getAuthorityParams(Integer userId);

}
