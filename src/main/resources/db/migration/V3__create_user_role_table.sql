create table roles
(
    id          UUID not null,
    role        text not null unique,
    description text not null,
    created_at  timestamp,
    updated_at  timestamp,
    constraint pk_convertor_roles primary key (id)
);