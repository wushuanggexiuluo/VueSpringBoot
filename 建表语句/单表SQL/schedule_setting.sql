create table schedule_setting
(
    job_id          int auto_increment comment '任务ID'
        primary key,
    bean_name       varchar(255) null comment 'bean名称',
    method_name     varchar(255) null comment '方法名称',
    method_params   varchar(255) null comment '方法参数',
    cron_expression varchar(255) null comment 'cron表达式',
    remark          varchar(255) null comment '备注',
    job_status      int          null comment '状态(1正常 0暂停)',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
);

