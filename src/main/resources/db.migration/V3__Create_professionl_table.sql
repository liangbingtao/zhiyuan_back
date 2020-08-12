create table profession
(
    description longtext     null,
    subject     varchar(150) null,
    major       varchar(50)  null,
    pid         int          not null
        primary key,
    proname     varchar(300) null,
    timelength  varchar(10)  null,
    classes     varchar(500) null,
    job         varchar(500) null,
    graduate    varchar(500) null,
    code        varchar(50)  null,
    sort        varchar(10)  null
);

