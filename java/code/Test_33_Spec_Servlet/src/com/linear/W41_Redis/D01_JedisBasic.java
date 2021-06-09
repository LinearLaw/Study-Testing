package com.linear.W41_Redis;

import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;


/*
    41.1、Jedis的基本操作

 */
public class D01_JedisBasic {

    private static Jedis jedis;
    @BeforeClass
    public static void loadJedis(){
        jedis = new Jedis("localhost",6379);
    }

    /*
        1、string
     */
    @Test
    public void stringOperate(){
        jedis.set("username","Trump");

        String name = jedis.get("username");
        System.out.println("username : " + name);

        // 使用 setex() 方法，可以设置过期时间，单位 秒，到期了会自动删除
        jedis.setex("activecode",20,"hhh");

        // jedis.del("username");
        jedis.close();
    }

    /**
     * 2、hash
     */
    @Test
    public void hashOperate(){
        jedis.hset("user","name","Abama");
        jedis.hset("user","age","88");
        jedis.hset("user","gender","male");

    }


}
