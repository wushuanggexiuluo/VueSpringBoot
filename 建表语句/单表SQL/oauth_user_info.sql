create table oauth_user_info
(
    id                int auto_increment
        primary key,
    uid               varchar(20)  null comment 'OAuth用户唯一的id',
    username          varchar(30)  null comment 'OAuth用户名',
    avatar            varchar(120) null comment 'OAuth平台的头像url',
    oauth_token       varchar(50)  null comment '给的token',
    oauth_expireIn    int          null comment 'oauth的过期时间',
    oauth_platform_id int          null comment '平台id',
    is_delete         int          null,
    status            int          null comment '状态'
)
    comment '用户第三方登陆信息表';

