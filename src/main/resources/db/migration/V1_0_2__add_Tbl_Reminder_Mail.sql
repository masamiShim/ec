create table if not exists ec_db.Reminder_Mail (
  id int auto_increment,
  user_id int not null,
  url varchar (255) not null,
  code char (8) not null,
  expired timestamp not null ,
  created timestamp not null,
  created_By int not null,
  modified timestamp not null,
  modified_By int not null,
  deleted timestamp,
  primary key (id)
);
