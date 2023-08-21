vcreate table user_role (
    id_user number(19) not null,
    id_role number(19) not null
);

alter table user_role
    add constraint user_role_pkey primary key (id_user, id_role);

alter table user_role
    add constraint fk_user_role_id_role foreign key (id_role) references roles(id);

alter table user_role
    add constraint fk_user_role_id_user foreign key (id_user) references users(id);
