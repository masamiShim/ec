/*
create database ec_db;
create user if not exists 'ec_user'@'%' IDENTIFIED BY 'ec_user_01';
grant all ON ec_db.* TO ec_user@'%';
FLUSH PRIVILEGES ;
*/


create table if not exists ec_db.User (
  id int auto_increment,
  nickname varchar(255) not null,
  email varchar(255) not null,
  password varchar(255) not null,
  enable bit not null,
  expired bit not null default false,
  credential bit not null default false,
  locked bit not null,
  failure tinyint not null default false,
  remind bit not null default false,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);

create table if not exists ec_db.Rel_User_Exhibitor (
  user_id int not null,
  exhibitor_id int not null,
  primary key (user_id, exhibitor_id)
  );


create table if not exists ec_db.Exhibitor (
  id int auto_increment,
  verified bit not null,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);

create table if not exists ec_db.Customer (
  id int auto_increment,
  verified bit not null,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);

create table if not exists ec_db.Rel_User_Customer (
  user_id int not null,
  customer_id int not null,
  primary key (user_id, customer_id)
);

create table if not exists ec_db.Item (
  id int auto_increment,
  exhibitor_id int not null,
  name varchar(255) not null,
  code varchar(255) not null,
  price int not null,
  quantity int not null,
  comment varchar(511),
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);

create table if not exists ec_db.Item_Image (
  id int auto_increment,
  item_id int not null,
  image mediumblob not null,
  is_primary bit not null,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);

create table if not exists ec_db.Chat_Room (
  id int auto_increment,
  item_id int not null,
  customer_id int not null,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);

create table if not exists ec_db.Chat (
  id int auto_increment,
  chat_room_id int not null,
  side bit not null,
  comment varchar(511) not null,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);
