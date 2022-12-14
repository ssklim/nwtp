DROP USER IF EXISTS  network@localhost;
create user network@localhost identified WITH mysql_native_password  by '1234';
DROP DATABASE IF EXISTS  network;
create database network;
grant all privileges on network.* to network@localhost with grant option;
#commit;
 
USE network;

create table user(
ip varchar(9999),
port int,
id varchar(20),
pw varchar(100),
name varchar(20),
nickName varchar(20),
email varchar(50),
birth varchar(20),
phone varchar(20),
address varchar(100),
todayM varchar(50),
state varchar(50),
outTime varchar(50),
count int,

primary key(id)
);

create table message(
sender varchar(20),
roomId int,
content varchar(100),
year int,
month int,
day int,
hour int,
minute int,
second int,

foreign key(sender) references user(id)
);

create table participant (
partId varchar(20),

foreign key(partId) references user(id)
);

create table friend(
fromId varchar(20),
toId varchar(20),

primary key(fromId, toId)
);

