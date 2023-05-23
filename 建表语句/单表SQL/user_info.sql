create table user_info
(
    user_id             int auto_increment
        primary key,
    user_avatar_img_url varchar(200) null comment '用户头像链接',
    user_text           varchar(200) null comment '用户个性签名',
    user_sex_code       int          null comment '在数据字典表的性别',
    user_blog_url       varchar(150) null comment '用户博客地址'
)
    comment '用户基本信息表';

