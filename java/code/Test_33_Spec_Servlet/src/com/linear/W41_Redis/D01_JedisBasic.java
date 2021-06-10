package com.linear.W41_Redis;

import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;


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
     *      hset
     *      hget
     *      hgetAll
     */
    @Test
    public void hashOperate(){
        jedis.hset("user","name","Abama");
        jedis.hset("user","age","88");
        jedis.hset("user","gender","male");

        // 获取 key.field 的value
        String name = jedis.hget("user","name");
        System.out.println(name);

        System.out.println("-------------");

        // 获取 key 下的所有field和value
        Map<String,String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for (String key : keySet){
            System.out.println(key + " : " + user.get(key));
        }

        jedis.close();
    }

    /**
     * 3、list
     *      注意这个操作特殊一点，不是get和set
     *      添加 lpush   rpush
     *      获取 lrange(key, start, end)
     *      删除 lpop   rpop
     */
    @Test
    public void listOperate(){
        jedis.lpush("mlist","a","b","c"); // 左插入
        jedis.rpush("mlist","1","2");   // 右插入

        List<String> mlist = jedis.lrange("mlist",0,-1);
        System.out.println("[before lrange] " + mlist);

        // 左弹出
        String ele1 = jedis.lpop("mlist");
        System.out.println("lpop: " + ele1);

        // 右弹出
        String ele2 = jedis.rpop("mlist");
        System.out.println("rpop: " + ele2);

        // 注意，只存在lrange，没有rrange
        List<String> mlist2 = jedis.lrange("mlist",0,-1);
        System.out.println("[after lrange]" + mlist2);

        jedis.close();
    }

    /**
     *  4、set
     *      注意这个的操作会特殊一点，
     *      添加 sadd
     *      获取 smember
     */
    @Test
    public void setOperate(){
        jedis.sadd("mset","java","php","c++");

        Set<String> mset = jedis.smembers("mset");
        System.out.println(mset);

        jedis.close();
    }

    /**
     * 5、sortedset
     *      zadd(key, score, member)  score是用来排序的权重
     *      zrange(key, start, end)
     */
    @Test
    public void sortedSetOperate(){
        jedis.zadd("msortedset",40,"math");
        jedis.zadd("msortedset",50,"english");
        jedis.zadd("msortedset",33,"chinese");

        Set<String> msortedset = jedis.zrange("msortedset",0,-1);
        System.out.println(msortedset);
        jedis.close();
    }


}
