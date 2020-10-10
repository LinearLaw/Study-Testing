# Vol.56 Mybatis

## 56.1 什么是Mybatis

Mybatis是一个持久层的框架，**Mybatis解决的是持久层的问题**。

啥是持久层，持久层就是跟数据相关的。    

以往，要连接数据库，创建连接池，操作细节很多，      
这些细节其实并不是我们需要关心的，我们最需要关心的其实是sql操作。   

——————————————————————————————————————————      

最开始，我们通过jdbc连接数据库：

```java
public class JDBC{
    public static void main(String[] args){
        // 1、导入mysql jdbc驱动包
        Class.forName("com.mysql.jdbc.Driver");

        // 2、连接数据库，获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql///dbname");

        // 3、利用连接对象，创建一个状态机
        Statement stmt = conn.createStatement();

        // 4、定义一段sql语句，执行sql
        String sql = "select * from user";
        List<User> users = stmt.executeUpdate(sql);

        // 5、sql执行完毕之后，关闭状态机，关闭数据库连接
        stmt.close();
        conn.close();
    }
}
```
——————————————————————————————————————————      

然后，我们想到了封装一个JDBCUtils，还用上了连接池。

```java
/**
 * JDBCUtils，将数据库的连接操作，数据库连接池封装起来，
 *      对外暴露方法
 */
public class JDBCUtils{
    // 1、声明一个数据源
    private static DataSource ds;

    // 2、读取配置信息，创建连接池对象，并实例化数据源
    static{
        try{
            Properties pro = new Properties();
            pro.load(
                JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties")
            );

            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 3、定义获取数据源方法
    public static DataSource getDataSource(){
        return ds;
    }

    // 4、定义获取连接对象的方法
    public static Connection getConnection()throws SQLException{
        return ds.getConnection();
    }

    // 5、关闭资源
    public static void close(Connection conn, Statement stmt,ResultSet rs){
        if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
    }

    public static void close(Connection conn , Statement stmt){
        close(sonn,stmt,null);
    }
}

/**
 *  对于一个DAO而言，使用JDBCUtils获取数据库连接，来操作sql。
 */
public class UserDaoImpl{
    // 使用 JdbcTemplate 是为了防止sql注入
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User findUserById(int uid){
        User user = null;
        try {
            String sql = "select * from tab_user where uid = ?";

            user = template.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    uid
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }
    // ...other sql operator
}
```
——————————————————————————————————————————      

但是，这个JDBCUtils，还是配置太麻烦了，     
所以就有了Mybatis，全用配置的方式来实现sql。
- 1、在pom.xml中，dependency加入mybatis，groupId是org.mybatis，artifactId是mybatis。
- 2、/main/resources 新建SqlMapperConfig.xml

```java
/**
 * 
 */



```
