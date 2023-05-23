package com.scm.myblog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.DOMAIN.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 孙超孟
* @description 针对表【user_info(用户基本信息表)】的数据库操作Mapper
* @createDate 2023-04-26 11:39:58
* @Entity com.scm.myblog.entity.DOMAIN.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




