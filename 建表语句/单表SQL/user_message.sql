create table user_message
(
    id              int auto_increment
        primary key,
    sender_uid      int          null comment '发送者id',
    get_uid         int          null comment '接受者id',
    status          int          null,
    message_content varchar(200) null comment '私信内容',
    create_time     datetime     null,
    update_time     datetime     null
)
    comment '用户私信表';

