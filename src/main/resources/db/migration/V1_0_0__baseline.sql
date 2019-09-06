/*
create database ec_db;
create user if not exists 'ec_user'@'%' IDENTIFIED BY 'ec_user_01';
grant all ON ec_db.* TO ec_user@'%';
FLUSH PRIVILEGES ;
*/

create table if not exists ec_db.Exhibitor (
  id int auto_increment,
  name varchar(255) not null,
  email varchar(255) not null,
  password varchar(255) not null,
  verified bit not null,
  created timestamp not null,
  createdBy int not null,
  modified timestamp not null,
  modifiedBy int not null,
  primary key (id)
)

create table if not exists ec_db.Customer (
  id int auto_increment,
  name varchar(255) not null,
  email varchar(255) not null,
  password varchar(255) not null,
  verified bit not null,
  created timestamp not null,
  createdBy int not null,
  modified timestamp not null,
  modifiedBy int not null,
  primary key (id)
)

create table if not exists ec_db.Item (
  id int auto_increment,
  exhibitor_id int not null,
  name varchar(255) not null,
  code varchar(255) not null,
  price int not null,
  quantity int not null,
  created timestamp not null,
  createdBy int not null,
  modified timestamp not null,
  modifiedBy int not null,
  primary key (id)
)

create table if not exists ec_db.Item_Image (
  id int auto_increment,
  item_id int not null,
  image mediumblob not null,
  is_primary bit not null,
  created timestamp not null,
  createdBy int not null,
  modified timestamp not null,
  modifiedBy int not null,
  primary key (id)
)

create table if not exists ec_db.Chat_Room (
  id int auto_increment,
  item_id int not null,
  customer_id int not null,
  created timestamp not null,
  createdBy int not null,
  modified timestamp not null,
  modifiedBy int not null,
  primary key (id)
)

create table if not exists ec_db.Chat (
  id int auto_increment,
  chat_room_id int not null,
  side bit not null,
  comment varchar(511) not null,
  created timestamp not null,
  createdBy int not null,
  primary key (id)
)
