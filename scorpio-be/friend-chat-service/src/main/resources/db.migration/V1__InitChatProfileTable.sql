Create table Chat_Profile(
                             user_id varchar(64) primary key,
                             friends_request_code varchar(64) not null unique,
);