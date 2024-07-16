CREATE TABLE courses
(
    id            bigint       not null auto_increment,
    name          varchar(100) not null,
    category      varchar(30)  not null,
    author_id     bigint,
    public_course boolean,
    primary key (id),
    foreign key (author_id) references users (id)
);