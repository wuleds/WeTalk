create database WeTalk;

use WeTalk;

#用户信息表，存储用户名、Id和密码
create table user
(
    userId  char(20) primary key,
    userName char(20) not null,
    password char(20) not null
);

#用户登录信息日志表，存储用户上线时间和下线时间
create table user_login_log
(
    id int auto_increment primary key ,
    userId char(20) not null,
    userName char(20) not null,
    loginDate char(20) not null,
    lineDate char(20)
);

#私聊信息日志表，存储用户间的私聊信息
create table private_chat_message_log
(
    id int auto_increment primary key ,
    formId char(20) not null,
    toId char(20) not null,
    message text not null,
    sendDate char(20) not null,
    code char  not null
);

#公共聊天室信息日志表，存储用户在公共聊天室发的信息
create table public_room_message_log
(
    id int auto_increment primary key ,
    formId char(20) not null,
    message text not null,
    sendDate char(20) not null,
    code char not null
);

#系统消息日志表，存储系统消息
create table system_message_log
(
    id int auto_increment primary key,
    message text not null,
    sendDate char(20) not null
);