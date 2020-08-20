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

——————————————————————————————————————————      

> 练习：  
> 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。     
> 		1. 定义Emp类    
> 		2. 定义方法 public List<Emp> findAll(){}    
> 		3. 实现方法 select * from emp;  

```java
/*  Emp.java
    定义一个类，里面的成员变量，和emp表中的列明保持一致
 */
package cn.domain;
import java.util.Date;

public class Emp{
    private int id;
    private String ename;
    private int job_id;
    private int mgr;
    private Date joindate;
    private double salary;
    private double bonus;
    private int dept_id;
    /*  然后，idea 右键 - Generate - Getter And Setter
        全选所有属性，点确认，就可以生成set get这些成员变量的方法；
     */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }
    public int getJob_id() { return job_id; }
    public void setJob_id(int job_id) { this.job_id = job_id; }
    public int getMgr() { return mgr; }
    public void setMgr(int mgr) { this.mgr = mgr; }
    public Date getJoindate() { return joindate; }
    public void setJoindate(Date joindate) { this.joindate = joindate; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public int getDept_id() { return dept_id; }
    public void setDept_id(int dept_id) { this.dept_id = dept_id; }
    public double getBonus() { return bonus;  }
    public void setBonus(double bonus) { this.bonus = bonus; }
    
    /* idea 右键，Generate - toString - 全选所有属性 - 确认 */
    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", job_id=" + job_id +
                ", mgr=" + mgr +
                ", joindate=" + joindate +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", dept_id=" + dept_id +
                '}';
    }
}
```

```java
package cn.itcast.jdbc;

import cn.itcast.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo{
    public static void main(String[] args){
        List<Emp> list = new JDBCDemo().findAll();

        System.out.println(list);
        System.out.println(list.size());
    }

    /* 查询emp表中的所有对象 */
    public List<Emp> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // List用来装一条一条的数据
        List<Emp> list = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///db3","root","root");
            stmt = conn.createStatement();
            
            String sql = "select * from emp";
            rs = stmt.executeQuery(sql);

            Emp emp = null;
            list = new ArrayList<Emp>();

            while(rs.next()){
                // 获取数据
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                // 创建emp对象,并赋值
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                list.add(emp);
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            /* 关闭资源 */
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

——————————————————————————————————————————      

## 29.8 PreparedStatement

原本的Statement，传入的sql是一个拼接的字符串。  
有可能会造成sql注入。   

所以就有了PreparedStatement，使用预编译的sql，参数用 ? 占位。

```java
Connection conn = null;
PreparedStatement pstmt = null;

String sql = "update account set balance = balance - ? where id = ?";
pstmt = conn.prepareStatement(sql);

// 用这种 setXxx 的方式来给占位的 ? 赋值
pstmt.setDouble(1,500);
pstmt.setInt(2,1);

// 执行sql
pstmt.executeUpdate();
```
> Tips:     
> 以后都会用 PreparedStatement，取代之前的Statement，       
> 可以防止sql注入，而且效率更高。       

——————————————————————————————————————————      

## 29.9 自己封装一个JDBC工具类

——————————————————————————————————————————      

## 29.10 JDBC控制事务

- 开启事务 ：setAutoCommit(false)
- 提交 ：commit()
- 回滚 ：roollback()

```java

package cn.itcast.jdbc;

import cn.itcast.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo{
    public static void main(String[] args){
        List<Emp> list = new JDBCDemo().findAll();

        System.out.println(list);
        System.out.println(list.size());
    }

    /* 查询emp表中的所有对象 */
    public List<Emp> findAll(){
        Connection conn = null;
        Statement stmt = null;

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///db3","root","root");

            // 1、开启事务，false就是关闭自动提交，变为手动提交
            conn.setAutoCommit(false);
            
            // 2、sql语句
            // 张三 - 500
            String sql1 = "update account set balance = balance - ? where id = ?";
            // 李四 + 500
            String sql2 = "update account set balance = balance + ? where id = ?";
            
            // 3、获取预编译sql执行对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);

            // 4、设置sql的参数
            pstmt1.setDouble(1,500);
            pstmt1.setInt(2,1);

            pstmt2.setDouble(1,500);
            pstmt2.setInt(2,2);

            // 5、执行sql
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            // 6、提交事务
            conn.commit();
        } catch (Exception e) {
            try {
                // 7、如果发现了异常，进行事务回滚
                if(conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            /* 在这里关闭资源 */
        }
    }
}
```

