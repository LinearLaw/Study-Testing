-- 创建部门表
create table dept(
	id int primary key auto_increment,
	name varchar(20)
)
insert into dept (name) values ('开发部'),('市场部'),('财务部');



-- 1、直接查询，得到的是两个表的笛卡尔积
SELECT * FROM emp,dept;

-- 2、使用内连接
-- 2.1、隐式内连接
SELECT emp.name , emp.gender, dept.name FROM emp,dept WHERE emp.`dept_id`=dept.`id`;

-- 2.2、显式内连接
SELECT emp.name,emp.gender,dept.name dn FROM emp INNER JOIN dept ON emp.`dept_id`=dept.`id`;

-- 3、外连接
-- 3.1、左外连接
SELECT * FROM dept d LEFT JOIN emp e ON d.`id`=e.`dept_id`;