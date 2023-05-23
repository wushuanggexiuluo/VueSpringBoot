create table role_menu
(
    role_id int not null,
    menu_id int not null,
    primary key (role_id, menu_id)
);

INSERT INTO yyy.role_menu (role_id, menu_id) VALUES (1, 1);
INSERT INTO yyy.role_menu (role_id, menu_id) VALUES (1, 2);
INSERT INTO yyy.role_menu (role_id, menu_id) VALUES (2, 1);
