create table group_chat
(
    id           bigint     not null identity(0, 1) primary key,
    group_name    varchar(100) not null,
    created_date datetime not null
);