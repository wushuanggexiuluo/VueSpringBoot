create table user
(
    user_id       int auto_increment
        primary key,
    user_name     varchar(255) null,
    user_password varchar(255) null,
    user_role     int          null comment '用户角色'
)
    row_format = DYNAMIC;

INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (2, 'aaaaaa', 'e10adc3949ba59abbe56e057f20f883e', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (3, 'aaaaaaq', 'fcea920f7412b5da7be0cf42b8c93759', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (4, 'admin1', '96e79218965eb72c92a549dd5a330112', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (5, 'qqqqqq', 'e10adc3949ba59abbe56e057f20f883e', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (6, 'abcdef', 'e10adc3949ba59abbe56e057f20f883e', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (7, 'abcdefa', 'e10adc3949ba59abbe56e057f20f883e', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (8, 'scm123456', '$2a$10$SAzHRSgSGMebHf.jKYBM7eXXVUGg2a2UnRFNTxyw0gcEUxiN/5Yia', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (9, 'admin', '$2a$10$QuzUDni4PMAO7hUR6IhSp.EFJia8me6yG.HFeLySh2cBrby8KWTiq', 1);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (10, 'admsin', 'abc1s23456', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (11, 'admwwsin', '$2a$10$hZY0cKpV/mp3n5877.pR4e.PEODrwAdRIUjd.75c.NVasFwGqWxu.', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (13, 'qwqwq', '$2a$10$Yh3DKISK0DNk3Uqho8SiYuFVCkYb5He42lSV/t6z46I9bEUGiSUzO', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (14, 'qweqwe3', '$2a$10$mNiqQmL1LHXC0Au02ItSROu2KdGNHsnGI3UhwbjRC0V4lwLWVFAk.', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (15, 'admins', '$2a$10$1hlUt5SrRls6lMS6ToZBc.0k329cBay3kmrEIeuhu1JX3sbbCjZAS', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (16, 'lovezgt', '$2a$10$MafJaXl21Of0FGNhpjF2TunKZdemSLisiWVaHA1dBw7E4HgE5eV2u', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (17, 'lovezgtz', '$2a$10$flnaXNJ62TG6z3ho.TmKhORd3PZ3IoqTZcHbtW6IVGYcYH.7WdX82', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (18, 'wderth', '$2a$10$1GRvy2JUB8p4.0hu8XNvOuZbo6eij5BDMQ8HZNfB4amMrfs0lf1M6', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (19, 'lhigyufdtrsew', '$2a$10$0NK1NYo4pcodAhUpq1Ye3uRUB/czCTljcjJNoRKSVh77l0jANKOym', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (20, 'admin11', '$2a$10$/w6pZLp8Di/XzY/aeLNcDO72UppIU94cGmfS4HhthgM.AxU39nz1O', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (21, 'acb', '$2a$10$i5yPPEMc8ljTA9VAZqWOkOA6w2JqToQzDlLXAwzOmrk0vFWFpzMmy', 2);
INSERT INTO yyy.user (user_id, user_name, user_password, user_role) VALUES (22, 'dsc', '$2a$10$6xVNgSJxFOEOQRKSs8sKAeLlTecWvh/ijiclW5uSltgTN2ntiI69a', 2);
