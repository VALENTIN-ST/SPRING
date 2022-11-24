--insert into roles (name, created_at, status_record, updated_at)
--values ('ADMIN', current_timestamp, 'ACTIVE', current_timestamp),
--        ('USER', current_timestamp, 'ACTIVE', current_timestamp);
--        
--        
--        
        


insert into roles (id,name, created_at, status_record, updated_at) values (1,'ADMIN', systimestamp, 'ACTIVE', systimestamp);
insert into roles (id,name, created_at, status_record, updated_at) values (2,'USER', systimestamp, 'ACTIVE', systimestamp);