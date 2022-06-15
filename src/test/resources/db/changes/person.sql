--liquibase formatted sql

--changeset kuery:create-person
create table person
(
   id int auto_increment primary key,
   name varchar(255) not null
);