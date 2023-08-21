 create table roles (
    id number(19) not null primary key,
    created_at timestamp default systimestamp not null ,
    status_record varchar2(255) not null,
    updated_at timestamp not null,
    name varchar2(20) not null
);

--CREATE SEQUENCE  ROLES_ID_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

alter table roles
add constraint roles_name_unique unique (name);
    
    
    
    
    
