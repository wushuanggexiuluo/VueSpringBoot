create table user_article_controller
(
    id               int auto_increment
        primary key,
    uid              int  null,
    article_sort     int  null,
    jinbing_sort     int  null,
    tag_sort         int  null,
    article_sort_id  text null,
    jing_hua_sort_id int  null,
    gui_dang_sort_id int  null
);

INSERT INTO yyy.user_article_controller (id, uid, article_sort, jinbing_sort, tag_sort, article_sort_id, jing_hua_sort_id, gui_dang_sort_id) VALUES (1, 9, 1, null, null, '1354-1353-1352', 1, 1);
