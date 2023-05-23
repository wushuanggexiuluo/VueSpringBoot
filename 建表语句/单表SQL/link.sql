create table link
(
    link_id             int unsigned auto_increment comment '链接ID'
        primary key,
    link_url            varchar(255)             null comment 'URL',
    link_name           varchar(255)             null comment '姓名',
    link_image          varchar(255)             null comment '头像',
    link_description    text charset utf8mb4     null comment '描述',
    link_owner_nickname varchar(40)              not null comment '所属人名称',
    link_owner_contact  varchar(255)             null comment '联系方式',
    link_update_time    datetime                 null comment '更新时间',
    link_create_time    datetime                 null comment '创建时间',
    link_status         int unsigned default '1' null comment '状态',
    constraint link_name
        unique (link_name)
)
    engine = MyISAM
    charset = utf8mb3
    row_format = DYNAMIC;

INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (10, 'www.baidu.com', '百度', null, '百度（Baidu）是拥有强大互联网基础的领先AI公司。', '李彦宏', '1766363627@qq.com', '2022-12-15 02:14:02', '2022-11-29 11:52:58', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (11, 'www.google.com', '谷歌', null, '谷歌公司（Google Inc.）成立于1998年9月4日，由拉里·佩奇和谢尔盖·布林共同创建，被公认为全球最大的搜索引擎公司。', '埃里克·施密特', '2746464657@qq.com', '2022-11-02 11:52:55', '2022-10-19 11:52:58', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (12, 'https://hao.360.cn', '360浏览器', null, '360安全浏览器（360 Security Browser）是360安全中心推出的一款基于Internet Explorer和Chromium双核的浏览器，是世界之窗开发者凤凰工作室和360安全中心合作的产品', '北京奇虎科技有限公司', '2028524188@qq.com', '2022-12-13 06:09:20', '2022-12-07 19:29:05', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (18, 'com.lenovo.browser', '联想浏览器', 'http://cdn.forevergtt.love/mysystem/medicine/2514310c-cf3d-4066-8f6d-181da61caf35', '联想浏览器（原绿茶浏览器）是联想官方提供的安全、易用的手机浏览器。', '联想（北京）有限公司', '2872392768@qq.com', '2022-12-13 06:09:16', '2022-12-01 19:29:12', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (17, 'http://ie.sogou.com', '搜狗浏览器', 'http://cdn.forevergtt.love/mysystem/medicine/9362c94a-9add-47b4-aee8-3710b5d5d781', '搜狗高速浏览器 [1]  由搜狗公司开发，基于谷歌chromium内核，力求为用户提供跨终端无缝使用体验，让上网更简单、网页阅读更流畅的浏览器。', '搜狗', '1273635364@qq.com', '2022-12-13 06:09:08', '2022-12-02 19:29:33', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (13, 'http://mb.qq.com ', 'QQ浏览器', null, 'QQ浏览器 [1]  是腾讯科技（深圳）有限公司开发的一款浏览器，其前身为TT浏览器。QQ浏览器秉承TT浏览器1-4系列方便易用的特点，但技术架构不同；交互和视觉表现也重新设计。采用Chromium内核+IE双内核，让浏览快速稳定，拒绝卡顿，完 美支持HTML5和各种新的Web标准。它同时可以安装众多Chrome的拓展，支持QQ快捷登录，登录浏览器后即可自动登录腾讯系网页 [。', '深圳市腾讯计算机系统有限公司', '1625354625@qq.com', '2022-12-14 20:26:40', '2022-12-12 20:26:43', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (14, 'http://ie.2345.com', '2345加速浏览器', null, '2345手机浏览器是一款极速上网手机浏览器，小巧稳定且省流量，安装包只有52.51M！四重广告智能拦截模式，安全省流。支持主流各大搜索引擎，内置海量新闻头条、免费小说影视、网址导航，满足你90%的手机上网需求！', '上海二三四五网络科技有限公司', '23531245321@qq.com', '2022-12-14 20:29:04', '2022-12-13 20:29:07', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (15, ':http://www.liebao.cn/ ', '猎豹浏览器', null, '猎豹安全浏览器，是由猎豹移动公司（原金山网络）推出的一款浏览器，主打安全与极速特性，采用Trident和WebKit双渲染引擎，并整合金山自家的BIPS进行安全防护。猎豹安全浏览器对Chrome的Webkit内核进行了超过100项的技术优化，访问网页速度更快。其具有首创的智能切换引擎，动态选择内核匹配不同网页，并且支持HTML5新国际网页标准，极速浏览的同时也保证兼容性。', '北京金山办公软件股份有限公司', '17363526453@qq.com', '2022-12-14 20:34:11', '2022-12-13 20:34:14', 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (16, 'http://www.firefox.com.cn/', '火狐浏览器', null, 'Mozilla Firefox，中文俗称“火狐”（正式缩写为Fx或fx [1]  ），是一个由Mozilla开发的自由及开放源代码的网页浏览器。', 'Mozilla', '2645636182@qq.com', '2022-12-14 20:38:31', null, 1);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (19, 'www.aa.com', '1', 'http://cdn.forevergtt.love/mysystem/medicine/d1f7923b-802d-43cb-87e5-9dafb853f13b', '1', '1', '293547382q5@qq.com', null, '2022-12-15 02:59:30', 0);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (20, 'www.aa.com', 'sdf', null, '胡一天人', 'sert', '2872392768@qq.com', null, '2023-01-09 02:16:49', 0);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (21, 'www.awd.com', 'ertf', null, '深V', 'i啊我靠', '2872392768@qq.com', null, '2023-01-09 02:38:04', 0);
INSERT INTO yyy.link (link_id, link_url, link_name, link_image, link_description, link_owner_nickname, link_owner_contact, link_update_time, link_create_time, link_status) VALUES (22, 'www.ad.com', 'aesdf', null, 'adf', 'ac', '2872392768@qq.com', null, '2023-01-09 02:45:11', 0);
