create table dictionary
(
    id                       int auto_increment
        primary key,
    kind_code                int         null comment '大致的种类代码',
    kind_name                varchar(30) null comment '当前大致类别的名字',
    key_value_map            text        null comment '键值对',
    create_time              datetime    null,
    update_time              datetime    null,
    status                   int         null comment '1表示开启0表示停用',
    is_use_current_key_value int         null comment '是否使用当前的key,value,1使用，0不使用'
)
    comment '数据字典';

INSERT INTO yyy.dictionary (id, kind_code, kind_name, key_value_map, create_time, update_time, status, is_use_current_key_value) VALUES (1, 100, '性别', '[{"key":"1001","val":"男"},{"key":"1002","val":"女"},{"key":"1003","val":"百变怪"},{"key":"1004","val":"外星异种"},{"key":"1005","val":"阴阳人"},{"key":"1006","val":"石头人"},{"key":"1007","val":"超越生死者"}]', '2023-04-26 10:56:31', null, 1, null);
INSERT INTO yyy.dictionary (id, kind_code, kind_name, key_value_map, create_time, update_time, status, is_use_current_key_value) VALUES (2, 200, '文章状态', '[{"key":"2001","val":"草稿"},{"key":"2002","val":"未审核"},{"key":"2003","val":"审核通过"},{"key":"2004","val":"审核未通过"},{"key":"2005","val":"已删除"},{"key":"2006","val":"已公开"},{"key":"2007","val":"仅自己可见"}]', '2023-04-27 11:06:51', '2023-04-27 11:06:57', 1, null);
