create table role
(
    name     varchar(255) null,
    role_key varchar(255) null,
    id       int auto_increment
        primary key,
    status   int          null comment '0表示启用，1表示禁用'
);

INSERT INTO yyy.role (name, role_key, id, status) VALUES ('管理员', 'admin', 1, 0);
INSERT INTO yyy.role (name, role_key, id, status) VALUES ('普通用户', 'user', 2, 0);
INSERT INTO yyy.role (name, role_key, id, status) VALUES ('游客', 'tourist', 3, 0);
