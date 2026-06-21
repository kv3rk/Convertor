create table convertor_engtorus
(
    id           UUID                     not null,
    time         timestamp with time zone not null,
    wrong_string text                     not null,
    right_string text                     not null,
    constraint pk_convertor_engtorus primary key (id)
);