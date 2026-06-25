create table users
(
    user_id   UUID not null,
    nickname  text not null unique,
    password  text not null,
    role      UUID,
    role_name text not null,
    constraint pk_users primary key (user_id),
    constraint fk_users_to_roles foreign key (role) references roles (id)
);