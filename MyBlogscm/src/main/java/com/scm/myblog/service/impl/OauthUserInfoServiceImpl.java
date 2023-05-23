package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scm.myblog.entity.DOMAIN.OauthUserInfo;
import com.scm.myblog.service.OauthUserInfoService;
import com.scm.myblog.mapper.OauthUserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 孙超孟
* @description 针对表【oauth_user_info(用户第三方登陆信息表)】的数据库操作Service实现
* @createDate 2023-04-24 10:59:53
*/
@Service
public class OauthUserInfoServiceImpl extends ServiceImpl<OauthUserInfoMapper, OauthUserInfo>
    implements OauthUserInfoService{

}




