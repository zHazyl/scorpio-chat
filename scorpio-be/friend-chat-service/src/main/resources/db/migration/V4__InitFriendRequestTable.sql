create table friend_request
(
    id                        bigint      identity(0,1) primary key,
    sender_chat_profile_id    varchar(64)  not null,
    recipient_chat_profile_id varchar(64)  not null,
    sent_time                 datetime not null,
    is_accepted               bit     not null default 0,
    foreign key (sender_chat_profile_id) references Chat_Profile (user_id),
    foreign key (recipient_chat_profile_id) references Chat_Profile (user_id),
    unique (sender_chat_profile_id, recipient_chat_profile_id)
)