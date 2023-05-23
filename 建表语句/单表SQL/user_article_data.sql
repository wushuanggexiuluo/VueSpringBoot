create table user_article_data
(
    id           int auto_increment
        primary key,
    uid          int      null,
    is_shou_cang int      null,
    is_dian_zan  int      null,
    is_ping_lun  int      null,
    article_id   int      null,
    update_time  datetime null,
    create_time  datetime null
);

INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (14, 22, 1, null, null, 1314, '2023-04-17 06:46:48', '2023-04-16 14:23:30');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (15, 22, 1, null, null, 1315, '2023-04-17 01:36:54', '2023-04-17 01:36:53');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (16, 22, 1, null, null, 1331, '2023-04-17 01:37:03', '2023-04-17 01:36:58');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (17, 9, 1, null, null, 1334, '2023-04-18 02:53:58', '2023-04-18 02:53:49');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (18, 9, 1, null, null, 1314, null, '2023-04-18 02:54:06');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (19, 9, 1, null, null, 1353, null, '2023-04-22 02:11:34');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (20, 9, 1, null, null, 1352, null, '2023-04-22 02:11:37');
INSERT INTO yyy.user_article_data (id, uid, is_shou_cang, is_dian_zan, is_ping_lun, article_id, update_time, create_time) VALUES (21, 9, 1, null, null, 1316, null, '2023-04-22 02:14:29');
