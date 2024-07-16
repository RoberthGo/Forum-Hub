CREATE TABLE answers (
    id        bigint PRIMARY KEY,
    message       varchar(500),
    topic         bigint ,
    creation_date DATETIME,
    author        bigint,
    solved        BOOLEAN,
    FOREIGN KEY (topic) REFERENCES  topics (id),
    FOREIGN KEY (author) REFERENCES users (id)
);