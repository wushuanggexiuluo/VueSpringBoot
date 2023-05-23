package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scm.myblog.entity.DOMAIN.OauthPlatform;
import com.scm.myblog.service.OauthPlatformService;
import com.scm.myblog.mapper.OauthPlatformMapper;
import org.springframework.stereotype.Service;

/**
* @author 孙超孟
* @description 针对表【oauth_platform(第三方认证平台信息表)】的数据库操作Service实现
* @createDate 2023-04-24 10:59:53
*/
@Service
public class OauthPlatformServiceImpl extends ServiceImpl<OauthPlatformMapper, OauthPlatform>
    implements OauthPlatformService{

}




