
# account表

create table account(
  id int primary key auto_increment,
  name varchar(40),
  money float
)character set utf8 collate utf8_general_ci;

insert into account(name,money) values("aaa",123);
insert into account(name,money) values("bbb",456);
insert into account(name,money) values("ccc",789);