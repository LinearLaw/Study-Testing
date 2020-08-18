# Vol.29 JDBC

## 29.1 JDBC

Java DataBase Connectivity  Java 数据库连接。

本质：
官方定义的一套操作关系型数据库的规则。统一的一套方法。

- JDBC接口
- 数据库驱动：用来对接不同的数据库
    - MySQL
    - DB2
    - SQLite
    - Oracle
    - ...

## 29.2 如何使用JDBC

```java
package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo{
    public static void main(String[] args) throws Exception{
        /* 1、导入驱动jar包
            这里注意，jdbc.Driver里面有静态代码块，
            这段代码会注册真实的数据库驱动。

            所以只需要导入了这个包，数据库驱动就自动注册了。
         */
        Class.forName("com.mysql.jdbc.Driver");

        /* 2、连接数据库，获取数据库连接对象 */
        Connection conn = DriverManager.getConnection("jdbc:mysql:///db3");

        /* 3、利用连接对象，创建一个状态机 */
        Statement stmt = conn.createStatement();

        /* 4、定义一段sql语句，执行这段sql
            返回的count，是指本句sql影响到的行的数量
         */
        String sql = "update account set balance=2000";
        int count = stmt.executeUpdate(sql);

        System.out.println(count);

        /* 5、关闭资源 */
        stmt.close();
        conn.close();
    }
}
```
——————————————————————————————————————————      

## 29.3 JDBC常用类

- DriverManager : 驱动管理对象
- Connection : 连接
- Statement : 用来执行sql
- ResultSet : 结果的集合
- PreparedStatement : 也是用来执行sql
    - 是Statement的子类。

——————————————————————————————————————————      

## 29.4 DriverManagers

驱动管理对象。  

```java
/* 加载类，此时驱动会被自动注册 */
Class.forName("com.mysql.jdbc.Driver");

/* 通过查看源码发现：在com.mysql.jdbc.Driver类中存在静态代码块 */
static {
       try {
           java.sql.DriverManager.registerDriver(new Driver());
       } catch (SQLException E) {
           throw new RuntimeException("Can't register driver!");
       }
}

/* 获取数据库连接
    jdbc:mysql 表示用的协议
    /// 等价于localhost:3306，是一个默认值
    db3 连接名为db3的数据库

    第二参数和第三参数是账号和密码
 */
Connection conn = DriverManager.getConnection(
    "jdbc:mysql:///db3",
    "root",
    "password"
);
```

> Tips：    
> mysql5之后的驱动jar包可以省略注册驱动的步骤。     

——————————————————————————————————————————      

## 29.5 Connection

数据库连接对象。

```java
/* 1、通过 DriverManager 获得数据库连接对象 Connection */
Connection conn = DriverManager.getConnection(
    "jdbc:mysql:///db3",
    "root",
    "password"
);

/* 2、通过 Connection 获取执行sql的对象 Statement */
Statement stmt = conn.createStatement();

PreparedStatement prStmt = conn.createPrepareStatement(sql); // sql是一个sql命令字符串

/* 3、Connection 也可以管理事务 */
// 设置事务自动提交，false=关闭自动提交事务，需要手动提交
conn.setAutoCommit(false);   

// 提交事务
conn.commit();

// 回滚事务
conn.rollback();
```
——————————————————————————————————————————      

## 29.6 Statement

执行sql的对象。

Statement主要是用来执行静态的sql语句。
可以用来执行查找（executeQuery）+ 增删改（executeUpdate）。

```java
/*  1. boolean execute(String sql) ：
        可以执行任意的sql，用得少， 了解 

	2. int executeUpdate(String sql) ：
        执行    DML（insert、update、delete）语句、
                DDL(create，alter、drop)语句
		* 返回值：影响的行数，
			可以通过这个影响的行数判断DML语句是否执行成功 
			返回值>0的则执行成功，反之，则失败。

	3. ResultSet executeQuery(String sql)  ：
        执行DQL（select)语句，很常用；
 */
package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo{
    public static void main(String[] args){
        Statement stmt = null;
        Connection conn = null;
        try{
            /* 1、注册驱动 */
            Class.forName("com.mysql.jdbc.Driver");

            /* 2、获取 Connection 对象 */
            conn = DriverManager.getConnection(
                "jdbc:mysql:///db3",
                "root",
                "root"
            );

            /* 3、获取操作sql的对象 Statement */
            stmt = conn.createStatement();

            /* 4、执行sql */
            String sql = "insert into account values(null,'王五',3000)";
            int count = stmt.executeUpdate(sql);

            // Tips: insert update delete都可以用executeUpdate

            System.out.println(count);
            if(count > 0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }

            /* 5、接着进行异常捕获 */
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            /* 6、发生错误时，需要将资源进行回收 */
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
```

——————————————————————————————————————————      

## 29.7 ResultSet

结果集对象，用来封装查询结果。

ResultSet有一个类似迭代器的功能，用next来控制迭代。

```java
package cn.jdbc;

import java.sql.*;

public class JDBCDemo{
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            /* 1、注册驱动 */
            Class.forName("com.mysql.jdbc.Driver");

            /* 2、获取数据库连接对象、操作SQL对象 */
            conn = DriverManager.getConnection("jdbc:mysql:///db3","root","root");
            stmt = conn.createStatement();

            /* 3、执行sql，获取结果，结果类型为ResultSet */
            String sql = "select * from account";
            rs = stmt.executeQuery(sql);

            /* 4、获取到的rs，位于数据的第0条，
                第一条开始，才是正常的一条数据。
             */
            while(rs.next()){
                /* 5、可以用 rs.getXxx 的方法获取当前行数据的字段值
                    方法内可以传入列索引，列索从1开始
                    也可以传入字段名称

                   getInt表示以int数据来取出，getString表示用字符串取出
                 */
                int id = rs.getInt(1);
                String name = rs.getString("name");
            }

        }catch(ClassNotFountException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            /* 6、老规矩，释放资源 */
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
```

> 练习：  
> 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。     
> 		1. 定义Emp类    
> 		2. 定义方法 public List<Emp> findAll(){}    
> 		3. 实现方法 select * from emp;  
——————————————————————————————————————————      

## 29.8 PreparedStatement


