create table topics(
    id bigint not null auto_increment,
    author varchar(100) not null,
    message varchar(400) not null unique,
    course varchar(200) not null,
    title varchar(200) not null unique,
    status varchar(15) not null,
    creation_date datetime not null,
    primary key(id)
);