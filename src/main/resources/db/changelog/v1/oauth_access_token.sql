CREATE TABLE oauth_access_token(
    authentication_id varchar(256) primary key NOT NULL,
    token_id varchar(256),
    token bytea,
    user_name varchar(256),
    client_id varchar(256),
    authentication bytea,
    refresh_token varchar(256)
)

