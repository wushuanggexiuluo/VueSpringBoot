create table kind_tree_menu
(
    id        int auto_increment
        primary key,
    menu_name varchar(50)  null comment '当前菜单名字',
    parent_id int          null comment '父菜单的id',
    menu_path varchar(100) null comment '菜单资源路径',
    status    int          null comment '状态1为启用0为禁用',
    is_delete int          null,
    order_by  int          null comment '排序'
)
    comment '分类树状菜单';

INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (0, '根节点', -1, '根节点', 1, 0, null);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (1, '前端', 0, '1', 1, 0, 1);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (2, 'js框架', 1, '21', 1, 0, 1);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (3, 'vue', 2, '132', 1, 0, 2);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (4, 'reacte', 2, '243re', 1, 0, 4);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (5, 'UI框架', 1, 'BootStrap', 1, 0, 5);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (6, '后端', 0, '后端', 1, 0, 1);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (7, 'java', 6, 'JVAAa', 1, 0, 3);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (8, '运维', 0, '运维', 1, 0, null);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (9, '网络安全', 0, '网络安全', 1, 0, null);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (10, '移动开发', 0, '移动开发', 1, 0, null);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (11, '嵌入式', 0, '嵌入式', 1, 0, null);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (12, '人工智能', 0, '人工智能', 1, 0, null);
INSERT INTO yyy.kind_tree_menu (id, menu_name, parent_id, menu_path, status, is_delete, order_by) VALUES (13, '算法', 0, '算法', 1, 0, null);
