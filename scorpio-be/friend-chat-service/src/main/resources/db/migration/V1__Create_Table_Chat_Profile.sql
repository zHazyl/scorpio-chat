CREATE TABLE chat_profile(
    user_id				    VARCHAR(64)     NOT NULL PRIMARY KEY,
    friends_request_code	VARCHAR(64)     NOT NULL UNIQUE
)