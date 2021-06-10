package com.linear.W41_Redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
    41.2、jedis 连接池

 */
public class D02_JedisDatabase {
    @Test
    public void dbBasic(){
        // 配置连接池config
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10); // 等待时间

        // 创建连接池
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379 );

        // 从连接池中取连接
        Jedis jedis = jedisPool.getResource();
        jedis.set("setdd","heheheh");

        // 关闭连接，归还到连接池
        jedis.close();
    }
}
