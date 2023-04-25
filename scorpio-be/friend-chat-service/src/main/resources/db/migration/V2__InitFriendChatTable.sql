CREATE TABLE friend_chat (
                            id BIGINT IDENTITY(0,1) PRIMARY KEY,
                            chat_with_id BIGINT,
                            sender_id varchar(64) NOT NULL,
                            recipient_id varchar(64) NOT NULL,
                            s_nickname varchar(64),
                            r_nickname varchar(64),
                            FOREIGN KEY(chat_with_id) REFERENCES friend_chat(id),
                            FOREIGN KEY(sender_id) REFERENCES Chat_Profile(user_id),
                            FOREIGN KEY(recipient_id) REFERENCES Chat_Profile(user_id),
                            UNIQUE (chat_with_id,sender_id,recipient_id)
);