CREATE TABLE friend_request(
    id						    BIGINT		NOT NULL IDENTITY PRIMARY KEY,
    sender_chat_profile_id	    VARCHAR(64)	NOT NULL,
    recipient_chat_profile_id	VARCHAR(64)	NOT NULL,
    sent_time				    DATETIME	NOT NULL,
    is_accepted				    BIT			NOT NULL DEFAULT 0,
    UNIQUE (sender_chat_profile_id,recipient_chat_profile_id)
)