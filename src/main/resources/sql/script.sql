create table films
(
    id          int auto_increment
        primary key,
    name        varchar(50)                        not null,
    picture     varchar(255)                       null,
    directedBy  varchar(255)                       null,
    description varchar(1024)                      null,
    duration    int                                not null,
    reg_date    datetime default CURRENT_TIMESTAMP null
);

create table seance
(
    id              int auto_increment
        primary key,
    number_of_seats int unsigned default '18' null,
    date_seance     date                      not null,
    time_seance     time                      not null,
    price_seance    double                    not null,
    film_id         int                       null,
    constraint seance_ibfk_1
        foreign key (film_id) references films (id)
            on update cascade on delete cascade
);

create index film_id
    on seance (film_id);

create table users
(
    id            int auto_increment
        primary key,
    firstName     varchar(20)                                                 not null,
    lastName      varchar(20)                                                 not null,
    login         varchar(16)                                                 not null,
    password      varchar(100)                                                not null,
    contact       varchar(20)                                                 null,
    role          enum ('ADMIN', 'USER', 'UNKNOWN') default 'USER'            null,
    email         varchar(254)                                                null,
    reg_date_user datetime                          default CURRENT_TIMESTAMP null,
    constraint email
        unique (email),
    constraint login
        unique (login)
);

create table tickets
(
    id          int auto_increment
        primary key,
    user_id     int not null,
    seance_id   int not null,
    number_seat int null,
    constraint seance_id
        unique (seance_id, number_seat),
    constraint tickets_ibfk_1
        foreign key (user_id) references users (id)
            on update cascade on delete cascade,
    constraint tickets_ibfk_2
        foreign key (seance_id) references seance (id)
            on update cascade on delete cascade
);

create index user_id
    on tickets (user_id);

