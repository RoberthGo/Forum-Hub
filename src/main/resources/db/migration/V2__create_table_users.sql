CREATE TABLE users(
    id bigint not null auto_increment,
    username varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    profile varchar(25) not null ,
    primary key(id)
);