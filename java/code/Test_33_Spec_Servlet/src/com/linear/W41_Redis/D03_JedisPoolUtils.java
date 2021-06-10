package com.linear.W41_Redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  41.3、jedis连接池工具类
 *
 */
class JedisPoolUtils {


    private static JedisPool jedisPool;

    static{
        // 读取配置文件，加载配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader()
                .getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建config对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        // 根据所得参数，初始化jedisPool
        jedisPool = new JedisPool(
                config,
                pro.getProperty("host"),
                Integer.parseInt(pro.getProperty("port"))
        );
    }

    // 从连接池中获取连接
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}

public class D03_JedisPoolUtils{

    @Test
    public void test(){
        Jedis jedis = JedisPoolUtils.getJedis();

        jedis.set("hello","world");

        jedis.close();
    }
}
