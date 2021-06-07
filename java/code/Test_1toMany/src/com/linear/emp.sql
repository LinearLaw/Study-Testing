-- 创建员工表
create table emp (
	id int primary key auto_increment,
	name varchar(10),
	gender char(1), -- 性别
	salary double, -- 工资
	join_date date, -- 入职日期
	dept_id int,
	foreign key (dept_id) references dept(id) -- 外键，关联部门表(部门表的主键)
)
insert into emp(name,gender,salary,join_date,dept_id) values('孙悟空','男',7200,'2013-02-24',1);
insert into emp(name,gender,salary,join_date,dept_id) values('猪八戒','男',3600,'2010-12-02',2);
insert into emp(name,gender,salary,join_date,dept_id) values('唐僧','男',9000,'2008-08-08',2);
insert into emp(name,gender,salary,join_date,dept_id) values('白骨精','女',5000,'2015-10-07',3);
insert into emp(name,gender,salary,join_date,dept_id) values('蜘蛛精','女',4500,'2011-03-14',1);


alter table emp change name ename;

