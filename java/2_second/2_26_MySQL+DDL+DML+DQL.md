# Vol.12 MySQL

## 12.1、数据库DB

- 1. 持久化存储数据的。其实数据库就是一个文件系统
- 2. 方便存储和管理数据
- 3. 使用了统一的方式操作数据库 -- SQL

——————————————————————————————————————————      

## 12.2、安装MySQL

如果安装失败，需要删除C:/ProgramData/MySQL文件夹，再卸载MySQL，     
然后重新安装。      

——————————————————————————————————————————      

## 12.3、MySQL服务

- 启动服务
```cmd
# 第一种方法，打开服务界面，启动服务
services.msc

# 第二种方法，
net start mysql

net stop mysql
```
——————————————————————————————————————————      

- 登录MySQL
```cmd
# 1、输入命令，回车，然后会让你输密码
mysql -uroot -p


# 2、直接输密码
mysql -uroot -p密码


# 3、远程登录
mysql -hip -uroot -p密码
mysql --host=ip --user=root --password=密码

```
——————————————————————————————————————————      

- 退出
```
# 1、
exit

# 2、
quit

```

——————————————————————————————————————————      

## 12.4、MySQL目录

- 安装目录
    - 配置文件 ：my.ini

- 数据目录
    - my.ini配置文件中，datadir指向的路径即为数据路径

——————————————————————————————————————————      

## 12.5、SQL Structured Query Language

SQL，结构化查询语言，   
定义了操作所有关系型数据库的规则，不同数据库会有一些各自特定的命令。

SQL语句，可以单行，可以多行，以；结尾，     
不区分大小写，不过建议关键字用大写。        

```sql
-- 使用 "-- 注释的内容" 可以加单行注释
# 使用 "# 注释的内容" 也可以写单行注释
/* 多行注释 */

show database; -- 注释
```
——————————————————————————————————————————      

## 12.6、SQL总共有四类

- 1、DDL(Data Definition Language)数据定义语言
	- 用来定义数据库对象：数据库，表，列等。
    - 关键字：create, drop,alter 等

- 2、DML(Data Manipulation Language)数据操作语言
	- 用来对数据库中表的数据进行增删改。
    - 关键字：insert, delete, update 等

- 3、DQL(Data Query Language)数据查询语言
	- 用来查询数据库中表的记录(数据)。
    - 关键字：select, where 等

- 4、DCL(Data Control Language)数据控制语言(了解)
	- 用来定义数据库的访问权限和安全级别，及创建用户。
    - 关键字：GRANT， REVOKE 等

——————————————————————————————————————————      

## 12.7、DDL

### 12.7.1、DDL - 操作数据库

- 创建
```sql
/* 创建名为dbname的数据库 */
create database dbname;

/* 如果不存在则创建名为dbname的数据库 */
create database if not exists dbname; 

/* 指定字符编码utf-8 */
create database dbname character set utf8;
```
——————————————————————————————————————————      

- 查询
    - MySQL的默认数据库，有四个
        - information_schema    事务数据库
        - mysql     默认数据库
        - performance_schema    提升性能的数据库
        - test      测试用的，不过如果要测试也不会用它

```sql
show database;

/* 显示所有创建的数据库 */
show create database;
```
——————————————————————————————————————————      

- 修改
```sql
alter database 数据库名称 character set 字符集名称;
```
——————————————————————————————————————————      

- 删除
```sql
drop database 数据库名称;

drop database if exists 数据库名称;
```
——————————————————————————————————————————      

- 使用
```sql
/* 查询当前正在使用的数据库名称 */
select database();

/* 使用某一个数据库 */
use 数据库名称;
```
——————————————————————————————————————————      

### 12.7.2、DDL - 操作表

- 创建表
    - 数据类型
        - int : 整数类型

        - double : 小数类型

        - date : 日期，只包含年月日，yyyy-MM-dd

        - datetime : 日期，包含年月日时分秒	 yyyy-MM-dd HH:mm:ss

		- timestamp : 时间错类型，包含年月日时分秒，yyyy-MM-dd HH:mm:ss	
			- 如果将来不给这个字段赋值，或赋值为null，则默认使用当前的系统时间，来自动赋值

		- varchar：字符串
			- eg: name varchar(20) : 表明name字段，最大20个字符
			- eg: zhangsan 8个字符  张三 2个字符

```sql
create table student(
	id int,
	name varchar(32),
	age int ,
	score double(4,1),
	birthday date,
	insert_time timestamp
);
```
——————————————————————————————————————————      

- 复制表
```sql
create table 表名 like 被复制的表名;
```
——————————————————————————————————————————      

- 查询表
```sql
/* 查询某个数据库中所有的表名称 */
show tables;

/* 查询某个表的结构 */
desc 表名;
```
——————————————————————————————————————————      

- 删除表
```sql
drop table 表名;

drop table  if exists 表名;
```
——————————————————————————————————————————      

- 修改表
```sql
/* 1. 修改表名 */
alter table 表名 rename to 新的表名;

/* 2. 修改表的字符集 */
alter table 表名 character set 字符集名称;

/* 3. 添加一列 */
alter table 表名 add 列名 数据类型;

/* 4. 修改列名称 类型 */
alter table 表名 change 列名 新列别 新数据类型;

alter table 表名 modify 列名 新数据类型;

/* 5. 删除列 */
alter table 表名 drop 列名;
```
——————————————————————————————————————————      

SQLYog：MySQL的图形化工具。

——————————————————————————————————————————      

## 12.8、DML - 操作数据 → 用的最多

- 添加
```sql
insert into 表名(列名1,列名2,...列名n) values(值1,值2,...值n);

/* 注意：如果表名后，不定义列名，则默认给所有列添加值  */
insert into 表名 values(值1,值2,...值n);
```

> Tips：    
> 列名和值需要一一对应。    
> 除了数字类型，其他类型都要用引号括起。    

——————————————————————————————————————————      

- 删除数据
```sql
delete from 表名 where 条件;

delete from db1 where id=1;

/*
    如果要删除某一个表
*/
delete from db1;    /* 不建议使用这种写法 */

truncate table 表名;  /* 建议使用这种，它会先删除表，再重新创建 */
```

> Tips:     
> 如果没加where里面的条件，会删除表里面的所有记录，注意。       

——————————————————————————————————————————      

- 修改数据
```sql
update 表名 set 列名1 = 值1, 列名2 = 值2,... [where 条件];

update table1 set name="哇哇哇",age=299 where id=1;
```

> Tips：    
> 如果不加任何条件，则会将表中所有记录全部修改。    

——————————————————————————————————————————      

