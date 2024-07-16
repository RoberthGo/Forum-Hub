CREATE TABLE topics
(
    id            bigint       not null auto_increment,
    author        bigint       not null,
    message       varchar(400) not null,
    course        bigint       not null,
    title         varchar(200) not null,
    status        varchar(15)  not null,
    creation_date datetime     not null,
    primary key (id),
    FOREIGN KEY (author) REFERENCES users (id),
    FOREIGN KEY (course) REFERENCES courses (id)
);