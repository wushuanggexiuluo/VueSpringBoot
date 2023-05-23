create table user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id)
);

INSERT INTO yyy.user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO yyy.user_role (user_id, role_id) VALUES (9, 1);
INSERT INTO yyy.user_role (user_id, role_id) VALUES (9, 2);
