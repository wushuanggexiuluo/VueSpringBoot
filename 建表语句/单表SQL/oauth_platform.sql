create table oauth_platform
(
    id          int auto_increment
        primary key,
    name        varchar(30)  null comment '平台名称',
    description varchar(100) null,
    is_delete   int          null,
    status      int          null
)
    comment '第三方认证平台信息表';

INSERT INTO yyy.oauth_platform (id, name, description, is_delete, status) VALUES (1, 'Gitee', 'Gitee中国人使用最多的代码托管平台', 1, 1);
