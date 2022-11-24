--create table products (
--    id varchar2(64) default SYS_GUID() not null primary key ,
--    created_at timestamp default systimestamp not null ,
--    status_record varchar2(255) not null,
--    updated_at timestamp not null,
--    created_by varchar2(255),
--    updated_by varchar2(255),
--    description varchar2(255) not null,
--    name varchar2(100) not null,
--    price number(19,2) not null,
--    quantity number(10) not null
--);



create table products (
    id number(19) not null primary key,
    created_at timestamp default systimestamp not null ,
    status_record varchar2(255) not null,
    updated_at timestamp not null,
    created_by varchar2(255),
    updated_by varchar2(255),
    description varchar2(255) not null,
    name varchar2(100) not null,
    price number(19,2) not null,
    quantity number(10) not null
);

