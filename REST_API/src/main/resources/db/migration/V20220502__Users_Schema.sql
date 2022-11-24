---- TABLE USER
--create table users (
--    id bigserial not null primary key,
--    created_at timestamp without time zone not null default now(),
--    status_record character varying(255) not null,
--    updated_at timestamp without time zone not null,
--    user_active boolean not null,
--    email character varying(40),
--    first_name character varying(40) not null,
--    last_name character varying(40) not null,
--    username character varying(15) not null
--);
--
--alter table users
--    add constraint users_email_unique unique (email);
--
--alter table users
--    add constraint users_username_unique unique (username);
--
---- TABLE USER PASSWORD
--
--create table user_password(
--    id_user bigserial,
--    password character varying(255),
--    primary key (id_user),
--    foreign key (id_user) references users(id)
--);
--
---- TABLE RESET PASSWORD
--create table reset_password(
--    id bigserial,
--    generated timestamp not null,
--    expired timestamp not null,
--    id_user bigserial not null,
--    unique_code character varying(50) not null,
--    primary key (id),
--    foreign key (id_user) references users(id),
--    unique (unique_code)
--);







-- TABLE USER
-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table users (
    id number(19) not null primary key,
    created_at timestamp default systimestamp not null ,
    status_record varchar2(255) not null,
    updated_at timestamp not null,
    user_active char(1) not null,
    email varchar2(40),
    first_name varchar2(40) not null,
    last_name varchar2(40) not null,
    username varchar2(15) not null
);

--CREATE SEQUENCE  USER_ID_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

alter table users
    add constraint users_email_unique unique (email);

alter table users
    add constraint users_username_unique unique (username);

-- TABLE USER PASSWORD

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table user_password(
    id_user number(19),
    password varchar2(255),
    primary key (id_user),
    foreign key (id_user) references users(id)
);



-- SQLINES DEMO *** ORD
-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table reset_password(
    id number(19),
    generated timestamp not null,
    expired timestamp not null,
    id_user number(19) not null,
    unique_code varchar2(50) not null,
    primary key (id),
    foreign key (id_user) references users(id),
    unique (unique_code)
);

--CREATE SEQUENCE  RESET_PASSWORD_ID_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;