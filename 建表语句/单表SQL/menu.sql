create table menu
(
    menu_name varchar(255) null comment '菜单名',
    perm_key  varchar(255) null comment '具体权限',
    id        int auto_increment
        primary key,
    status    int          null comment '0表示正常，1表示停用',
    del_flag  int          null comment '0表示未删除，1表示已删除'
);

INSERT INTO yyy.menu (menu_name, perm_key, id, status, del_flag) VALUES ('新增文章', 'blog:add:article', 1, 0, 0);
INSERT INTO yyy.menu (menu_name, perm_key, id, status, del_flag) VALUES ('删除文章', 'blog:delete:article', 2, 0, 0);
