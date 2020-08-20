# Vol.30 

## 30.1 数据库连接池

操作一个sql
- 申请连接到数据库
- 执行sql
- 关闭连接

每一个sql操作都要这么做，频繁连接、关闭，造成了资源的浪费。

所以就有了数据库连接池。

——————————————————————————————————————————      

数据库连接池，其实就是一个容器(集合)，存放数据库连接的容器。    

当系统初始化好后，容器被创建，容器中会申请一些连接对象，    
当用户来访问数据库时，从容器中获取连接对象，    
用户访问完之后，会将连接对象归还给容器。    

好处：节约资源、运行高效。

——————————————————————————————————————————      

实现：
javax.sql包下的 DataSource 接口。
- getConnection() : 获取一个连接
- Connection.close()  :  归还连接，归还连接后，连接不会关闭。

> Tips:     
> 数据库连接池的 DataSource 接口，一般不由我们自己实现，        
> 由数据库厂商实现。        

——————————————————————————————————————————      

目前主流的数据库连接池
- C3P0 : 比较冷门
- Druid : 新兴的连接池，阿里巴巴开发的。

——————————————————————————————————————————      

## 30.2 C3P0

- 1、引入jar包，引入数据库驱动包，将其Add Library到项目中
    - c3p0-0.9.5.2.jar 
    - mchange-commons-java-0.2.12.jar

- 2、定义配置文件
    - 名称固定，二选一
        - c3p0.properties 
        - c3p0-config.xml

- 3、创建核心对象 ComboPooledDataSource
- 4、获取连接 getConnection

——————————————————————————————————————————      

> Tips：  
> 数据库连接池，    
> 会有一个默认连接数和最大连接数，写在配置文件里，
>     
> 一开始，假如默认连接数是n，此时会建立n个连接，     
> 当n个连接都被占用时，     
> 如果此时又有新的连接请求进来，就会创建新连接，    
> 
> 当连接数到达了最大连接数，就不会再创建新连接，    
> 当原有的连接被归还的时候，后面的连接请求才会被响应。  

——————————————————————————————————————————      

```java
package cn.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo{
    public static void main(String[] args)throws SQLException{
        /* 1、创建数据库连接池对象 */
        DataSource ds = new ComboPooledDataSource();

        /* 2、获取连接对象 */
        Connection conn = ds.getConnection();

        System.out.println(conn);
    }
}
```

```java
public class C3P0Demo2{
    public static void main(String[] args) throws SQLException{
        DataSource ds = new ComboPooledDataSource();

        for(int i = 1;i<=10;i++){
            /*  如果当前连接数已经到了最大连接数，且没有归还，
                此时再次调用getConnection方法，会抛出一个异常 
             */
            Connection conn = ds.getConnection();

            if(i == 5){
                /* 调用close方法，连接将会归还到连接池中 */
                conn.close();   
            }
        }
    }
}
```

## 30.3 Druid

Druid是目前最好的数据库连接池组件。

其配置文件可以设置为任意名称，在导入DataSource的时候传入配置文件的路径。    

提供了一个工厂类，通过该工厂类可以获取到连接池对象。    

```java
/* 1、加载配置文件 */
Properties pro = new Properties();
InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
pro.load(is);

/* 2、获取连接池对象 */
DataSource ds = DruidDataSourceFactory.createDataSource(pro);

/* 3、获取连接 */
Connection conn = ds.getConnection();
```
——————————————————————————————————————————      

一般的，可以定义一个工具类，来封装一层，    
里面写加载配置、获取连接池对象等方法。  

```java
public class JDBCUtils {
	
    //1.定义成员变量 DataSource
    private static DataSource ds ;

    static{
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
			
	/* 获取连接 */
	public static Connection getConnection() throws SQLException {
	    return ds.getConnection();
	}
	/* 释放资源 */
	public static void close(Statement stmt,Connection conn){ 
        close(null,stmt,conn);
	}	
	public static void close(ResultSet rs , Statement stmt, Connection conn){
	    if(rs != null){
	        try {
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    if(stmt != null){
	        try {
	            stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    if(conn != null){
	        try {
	            conn.close();//归还连接
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
			
	/* 获取连接池方法 */
	public static DataSource getDataSource(){ return  ds; }
}
```
——————————————————————————————————————————      

## 30.4 Sprint JDBC

Spring框架对JDBC的简单封装。    
提供了一个JDBCTemplate对象简化JDBC的开发。    

- 1. 导入jar包

- 2. 创建JdbcTemplate对象。依赖于数据源DataSource
	* JdbcTemplate template = new JdbcTemplate(ds);

- 3. 调用JdbcTemplate的方法来完成CRUD的操作

	* update():执行DML语句。增、删、改语句

	* queryForMap():查询结果将结果集封装为map集合，     
        * 将列名作为key，将值作为value 将这条记录封装为一个map集合
		* 注意：这个方法查询的结果集长度只能是1

	* queryForList():查询结果将结果集封装为list集合
		* 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中

	* query():查询结果，将结果封装为JavaBean对象
		* query的参数：RowMapper
			* 一般我们使用BeanPropertyRowMapper实现类。     
                可以完成数据到JavaBean的自动封装
			* new BeanPropertyRowMapper<类型>(类型.class)

	* queryForObject：查询结果，将结果封装为对象
		* 一般用于聚合函数的查询

```java
public class JDBCTemplateDemo{
    public static void main(String[] args) {
        /* 1、在 JdbcTemplate 中传入DataSource对象 */
        JdbcTemplate tpl = new JdbcTemplate(JDBCUtils.getDataSource());

        /* 2、直接调用update，
            第二参数传入sql字符串中占位符 ? 的具体值
         */
        String sql = "update account set balance=5000 where id=?";
        int count = tpl.update(sql,3);

        /* 有多个 ? 的情况下，枚举传入具体值即可 */
        String sql = "select * from emp where id = ? or id = ?";
        Map<String, Object> map = tpl.queryForMap(sql, 1001,1002);
        System.out.println(map);
    }
}
```


