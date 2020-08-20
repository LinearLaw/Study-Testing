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

