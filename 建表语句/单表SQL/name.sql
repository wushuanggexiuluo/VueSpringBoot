create table name
(
    name_id       int auto_increment
        primary key,
    name_nickname varchar(255) null comment '称号',
    name_link_id  int          null comment '友人id'
)
    row_format = DYNAMIC;

INSERT INTO yyy.name (name_id, name_nickname, name_link_id) VALUES (1, '管理员', 1);
INSERT INTO yyy.name (name_id, name_nickname, name_link_id) VALUES (2, '资源大使', 2);
INSERT INTO yyy.name (name_id, name_nickname, name_link_id) VALUES (3, '程序员', 3);
INSERT INTO yyy.name (name_id, name_nickname, name_link_id) VALUES (4, '', null);
