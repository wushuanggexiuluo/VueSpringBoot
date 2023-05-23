create table message
(
    message_id            int unsigned auto_increment comment '留言ID'
        primary key,
    message_author_name   varchar(50) charset utf8mb3  null comment '留言人名称',
    message_author_email  varchar(50) charset utf8mb3  null comment '留言人邮箱',
    message_author_avatar varchar(100) charset utf8mb3 null comment '留言人头像',
    message_content       varchar(1000)                null comment '留言内容',
    message_ip            varchar(50) charset utf8mb3  null comment 'IP',
    message_create_time   datetime                     null comment '留言时间',
    message_role          int                          null comment '角色，是否管理员/是否是友人'
)
    row_format = DYNAMIC;

INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (43, '小花', '2872392768@qq.com', null, '<p>你好，你的文章对我帮助很大。</p>', '182.112.59.70', '2022-11-23 20:07:03', 1);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (44, '在希望的田野上', '2754378642@qq.com', null, '你好', '219.229.255.255
', '2022-11-23 20:07:03', 1);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (45, '不能吃的馒头', '13453245563@qq.com', null, '我有一些问题想和你探讨。', '61.169.255.255
', '2022-12-07 11:17:39', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (46, '碧海蓝天', '2746453423@qq.com', null, '我很赞同。', '117.74.255.255', '2022-12-07 11:19:38', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (47, '开心', '26454736351@qq.com', null, '你的一些观点很有见解。', '125.215.63.255
', '2022-12-07 11:34:58', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (48, 'Real', '1625344756@qq.com', null, '希望能和你成为朋友', '114.95.255.255', '2022-12-07 11:41:55', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (49, '海的那边', '17263526354@qq.com', null, '好好好很好！', '1.192.217.131	', '2022-12-14 20:41:09', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (50, '蓝精灵', '2317634271@qq.com', null, '我喜欢你的文章哦5', '1.193.115.0', '2022-12-14 20:42:08', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (51, '小红帽', '1627462547@qq.com', null, '我已经关注你啦', '61.52.8.243	', '2022-12-14 20:44:14', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (52, '羊咩咩', '27364523354@qq.com', null, '踩一踩', '42.236.14.160', '2022-12-14 20:47:14', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (53, 'Lucky', '17364837365@qq.com', null, '好有内涵啊', '1.199.161.0', '2022-12-14 20:48:17', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (54, '咕噜', '26356345267@qq.com', null, '很实用！', '58.56.12.0', '2022-12-14 20:49:31', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (55, '1', '1', null, '1', '122.0.255.255
', '2022-12-13 05:57:22', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (56, '隔壁的小黄', '1625353625@qq.com', null, '好好好~', '27.148.160.027', '2022-12-14 20:50:18', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (57, '吃饱没咩', '2635524144@qq.com', null, '给你点赞', '210.34.130.210', '2022-12-14 20:51:33', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (58, '夏天', '2746363562@qq.com', null, '作者希望你出更多的好作品。', '36.149.19.255', '2022-12-14 20:53:04', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (59, '骑士', '2533223455@qq.com', null, '狠狠的赞同。', '43.248.107.255', '2022-12-14 20:56:51', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (60, '最后的晚餐', '1333324244@qq.com', null, '有些方面我不是很同意。', '58.223.15.0', '2022-12-14 20:58:01', null);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (61, '你爹', '287239s2768@qq.com', null, '垃圾', '0:0:0:0:0:0:0:1', '2022-12-15 03:00:01', 0);
INSERT INTO yyy.message (message_id, message_author_name, message_author_email, message_author_avatar, message_content, message_ip, message_create_time, message_role) VALUES (62, 'www', '287239s2768@qq.com', null, '???ds ', '0:0:0:0:0:0:0:1', '2022-12-19 01:40:14', 0);
