create table schoolscore
(
    scid     int default 0 not null,
    sort     int           not null,
    name     varchar(50)   not null,
    minScore int           not null,
    minRank  int           not null,
    batch    int           not null,
    year     int(10)       not null
);

