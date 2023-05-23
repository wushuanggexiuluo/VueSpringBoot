create table img_name_tb
(
    file_name varchar(255) null,
    file_url  varchar(255) null,
    is_dead   int          null,
    id        int          not null
        primary key
)
    row_format = DYNAMIC;

