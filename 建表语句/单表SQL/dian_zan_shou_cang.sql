create table dian_zan_shou_cang
(
    id          int auto_increment
        primary key,
    is_dianzan  int null comment '是否点赞',
    is_shoucang int null,
    uid         int null comment '用户id',
    article_id  int null comment '文章id'
)
    comment '点赞收藏表';

