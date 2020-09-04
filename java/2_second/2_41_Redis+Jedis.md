# Vol.41 Redis

## 41.1 Redis

Redis，高性能，内存型，非关系型，NoSQL数据库。  
Redis的默认端口号是6379。   

- 数据之间没有关系
- 无耦合
- 易扩展


> Tips:		
> Redis 一般用来缓存一些不会经常变化的数据。		
> 		
> 如果一个数据经常变化，其实不适合放到redis，		
> 因为数据变化时，redis里面的数据要更新，mysql里面的数据也要更新，		
> 这并没有发挥出redis的效率。		

——————————————————————————————————————————      
主流的NOSQL产品
-	键值(Key-Value)存储数据库
	- 相关产品： Tokyo Cabinet/Tyrant、Redis、Voldemort、Berkeley DB
	- 典型应用： 内容缓存，主要用于处理大量数据的高访问负载。 
	- 数据模型： 一系列键值对
	- 优势： 快速查询
	- 劣势： 存储的数据缺少结构化

-	列存储数据库
	- 相关产品：Cassandra, HBase, Riak
	- 典型应用：分布式的文件系统
	- 数据模型：以列簇式存储，将同一列数据存在一起
	- 优势：查找速度快，可扩展性强，更容易进行分布式扩展
	- 劣势：功能相对局限

-	文档型数据库
	- 相关产品：CouchDB、MongoDB
	- 典型应用：Web应用（与Key-Value类似，Value是结构化的）
	- 数据模型： 一系列键值对
	- 优势：数据结构要求不严格
	- 劣势： 查询性能不高，而且缺乏统一的查询语法

-	图形(Graph)数据库
	- 相关数据库：Neo4J、InfoGrid、Infinite Graph
	- 典型应用：社交网络
	- 数据模型：图结构
	- 优势：利用图结构相关算法。
	- 劣势：需要对整个图做计算才能得出结果，不容易做分布式的集群方案。

——————————————————————————————————————————      

## 41.2 Redis应用场景

- 缓存（数据查询、短连接、新闻内容、商品内容等等）

- 聊天室的在线好友列表

- 任务队列。（秒杀、抢购、12306等等）

- 应用排行榜

- 网站访问统计

- 数据过期处理（可以精确到毫秒

- 分布式集群架构中的session分离

——————————————————————————————————————————      

## 41.3 Redis支持的键值数据结构类型

Redis 的数据，都是 key-value型。        

key全都是字符串，   
value则有五种类型的数据。     
——————————————————————————————————————————      
- 1) 字符串类型 string

```sql
-- 1、存储一组key value： set key value 
set username zhangsan

-- 2、获取，传入一个key
get username

-- 3、删除，传入一个key
del username
```
——————————————————————————————————————————      

- 2) 哈希类型 hash

```sql

-- 1、存储一组hash：hset key field value
--      有两层深度，一层是field，一层是value
hset myhash username lisi
hset myhash password 123


-- 2、获取
-- 返回所有的field和value
hget myhash     
-- 获取某一个field 下面的value
hget myhash username

-- 3、删除某一个 field 或者 key
hdel myhash username
hdel myhash
```

——————————————————————————————————————————      

- 3) 列表类型 list，其实就是双向队列

```sql
/*  1、添加：分两种，list是一个双向队列
    第一种是左插入，一种是右插入
 */
lpush mylist a
lpush mylist b
rpush mylist c

/*  2、获取，从一个范围获取
        lrange key start end
        如果end是-1，则到末尾
 */ 
lrange mylist 0 -1


/*  3、删除，删除也分左删除和右删除
 */
lpop mylist
rpop mylist
```

——————————————————————————————————————————      

- 4) 集合类型 set，不允许重复的队列，无序

```sql
-- 1、新增一条 set
sadd myset a
sadd myset b

-- 2、获取myset中的所有元素
smembers myset

-- 3、删除set集合中的某一个元素
srem myset a
```

——————————————————————————————————————————      

- 5) 有序集合类型 sortedset，不允许重复的队列，且有序。

```sql
/*  1、存储，sortedset是一个有序的队列，
    所以在插入数据的时候，需要指定其用于排序的score
    zadd key score value
 */
zadd mysort 60 zhangsan

/*  2、获取，
    获取的结果会按序排列

    如果在末尾带上withscores时，获取的就是包括score在内的数据
    zrange key start end [withscores]
 */
127.0.0.1:6379> zrange mysort 0 -1
1) "lisi"
2) "zhangsan"
3) "wangwu"

127.0.0.1:6379> zrange mysort 0 -1 withscores
1) "zhangsan"
2) "60"
3) "wangwu"
4) "80"
5) "lisi"
6) "500"

/*  3、删除某一条记录
    zrem key value
 */
zrem mysort lisi
```

——————————————————————————————————————————      

- Redis通用命令，这些命令对所有的数据结构都可用
```sql
-- 1、查询所有的键
keys *

-- 2、获取键对应的value的类型
type mylist

-- 3、删除指定的key value
del key
```
——————————————————————————————————————————      

Redis文件
* redis.windows.conf ：配置文件
* redis-cli.exe ：redis的客户端
* redis-server.exe ：redis服务器端

——————————————————————————————————————————      

## 41.4 Redis的持久化

Redis 是内存数据库，redis服务重启时，数据就会丢失。     
因此，可以设置将redis在内存中的数据保存到硬盘，进行持久化。     

- redis持久化机制：
	- 1. RDB：默认方式，不需要进行配置，默认就使用这种机制
		- 在一定的间隔时间中，检测key的变化情况，然后持久化数据
			- 1. 编辑redis.windwos.conf文件，配置保存机制
				- # after 900 sec (15 min) if at least 1 key changed
                ```sql
					save 900 1 -- 900秒内，至少有1条数据被改，持久化一次
				```
                - #   after 300 sec (5 min) if at least 10 keys changed
				```sql
                    save 300 10 -- 300s内，至少10条数据被改，持久化一次
                ```
				- #   after 60 sec if at least 10000 keys changed
				```sql
                    save 60 10000 -- 60s内，有10000条数据被改，持久化一次
				```

			- 2. 重新启动redis服务器，并指定配置文件名称
				- D:\redis\windows-64\redis-2.8.9> redis-server.exe redis.windows.conf	
				
	- 2. AOF：日志记录的方式，可以记录每一条命令的操作。可以每一次命令操作后，持久化数据
		- 1. 编辑redis.windwos.conf文件
			- appendonly no（关闭aof） --> appendonly yes （开启aof）

			- appendfsync always ： 每一次操作都进行持久化
			- appendfsync everysec ： 每隔一秒进行一次持久化
			- appendfsync no	 ： 不进行持久化

——————————————————————————————————————————      

## 41.5 Jedis

Jedis，用java操作redis数据库。		
需要导入jedis的jar包。		

```java
/* 1、如果Jedis里面啥都不传，默认是localhost:6379 */
Jedis jedis = new Jedis("localhost",6379);

/* 2、操作redis，操作完成后关闭连接。 */
jedis.set("username","lisi");
jedis.close();
```
——————————————————————————————————————————      

Jedis操作几种数据结构

- string : set get

```java
Jedis jedis = new Jedis();

// 存储
jedis.set("username","zhangsan");

// 获取
String username = jedis.get("username");

/* username设置过期时间，20s后自动删除该键值对 */
jedis.setex("username",20,"lisi")

jedis.close();
```
——————————————————————————————————————————      

- hash : hset hget hgetAll

```java
Jedis jedis = new Jedis();

jedis.hset("user","name","wangwu");
jedis.hset("user","age","100");

String name = jedis.hget("user","name");
String age = jedis.hget("user","age");

// 获取hash user中所有数据
Map<String,String> user = jedis.hgetAll("user");
Set<String> keySet = user.keySet(); // map转set
for(String key : keySet){
	String value = user.get(key);
	// key value
}

jedis.close();
```
——————————————————————————————————————————      

- list : linkedList，支持重复元素
	- lpush
	- rpush
	- lpop
	- rpop
	- lrange start end

```java
Jedis jedis = new Jedis();

jedis.lpush("mylist","a","b","c");
jedis.rpush("mylist","d","e","f");

// 从左往右，获取list
List<String> mylist = jedis.lrange("mylist",0,-1);

// 从左边弹出队列中的第一个元素
String ele = jedis.lpop("mylist");

// 从右边弹出队列中的第一个元素
String ele2 = jedis.rpop("mylist");

jedis.close();
```
——————————————————————————————————————————      

- set : sadd smembers ，不允许重复，元素无序

```java
Jedis jedis = new Jedis();

// 添加abc三个元素
jedis.sadd("myset","a","b","c");

// 获取一个元素
Set<String> myset = jedis.smembers("myset");

jedis.close();
```
——————————————————————————————————————————      

- sortedset : zadd zrange ，不允许重复元素，且元素有序

```java
Jedis jedis = new Jedis();

// 存储，存的时候需要带权值
jedis.zadd("mySortedSet",1,"a");
jedis.zadd("mySortedSet",31,"b");
jedis.zadd("mySortedSet",44,"c");
jedis.zadd("mySortedSet",20,"d");

Set<String> mySortedSet = jedis.zrange("mySortedSet",0,-1);

jedis.close();
```
——————————————————————————————————————————      

## 41.6 Jedis连接池

JedisPool，和JDBC差不多。

```java
/**
	设置一个jedis.properties文件，用来写jedis的配置
 */
// jedis.properties
host=127.0.0.1
port=6379
maxTotal=50
maxIdle=10

// JedisPoolUtils.java
public class JedisPoolUtils{
	private static JedisPool jedisPool;

	static{
		/* 1、加载配置文件 */
		InputStream is = JedisPoolUtils.class
			.getClassLoader()
			.getResourceAsStream("jedis.properties");
		
		/* 2、以参数形式读取配置文件 */
		Properties pro = new Properties;
		try{
			pro.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}

		/* 3、将参数写入到jedis pool config */
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(
			Integer.parseInt(pro.getProperties("maxTotal"))
		);
		config.setMaxIdle(
			Integer.parseInt(pro.getProperties("maxIdle"))
		);

		/* 4、创建jedis 连接池对象 */
		jedisPool = new JedisPool(
			config,
			config.getProperties("host"),
			Integer.parseInt(config.getProperties("port"))
		)
	}

	public static Jedis getJedis(){
		return jedisPool.getResource();
	}
}

// xxx.java使用连接池
public class JedisDemo{
	public void test(){
		Jedis jedis = JedisPoolUtils.getJedis();

		jedis.set("hello","world");

		// 此时并非关闭连接，而是归还连接。
		jedis.close();
	}
}
```



