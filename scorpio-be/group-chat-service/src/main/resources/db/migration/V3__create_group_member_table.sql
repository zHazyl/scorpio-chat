create table group_member
(
    id           bigint     not null identity(0, 1) primary key,
    group_id bigint not null,
    member_id    varchar(64) not null,
    is_admin bit not null default 0,
    foreign key (group_id) references group_chat(id),
    foreign key (member_id) references chat_profile (user_id),
    unique (group_id, member_id)
);