create database ec_db;
create user if not exists 'ec_user'@'%' IDENTIFIED BY 'ec_user_01';
grant all ON ec_db.* TO ec_user@'%';
FLUSH PRIVILEGES ;
