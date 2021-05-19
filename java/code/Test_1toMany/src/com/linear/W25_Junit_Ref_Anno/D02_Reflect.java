package com.linear.W25_Junit_Ref_Anno;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/*
    25.2、反射 -> 映射
        用来加载类、访问变量、执行类方法

        - 加载配置文件
        - 读取配置文件中的参数：类名、方法名
        - 根据类名，加载类
        - 根据方法名，invoke执行方法
 */
public class D02_Reflect {

    public static void main(String[] args){

        // 1、从当前类，获取class loader
        ClassLoader classLoader = D02_Reflect.class.getClassLoader();

        /* 2、利用class loader的getResoucrceAsStream方法，获取配置文件信息
        *   这里的路径有讲究，
        *   以 src 作为 sources root，当前这个文件就位于src的直接目录下。 -> 这一点很关键
        * */
        InputStream is = classLoader.getResourceAsStream("D02.properties");

        // 3、Properties对象，load 读取配置
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4、然后就可以获取配置中的各个参数了。
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        try {
            // 5、根据class名称，获取class对象
            Class cls = Class.forName(className);

            // 6、利用类对象，创建一个实例
            Object obj = cls.newInstance();

            // 7、利用类对象，获取类对象的一个方法
            Method mtd = cls.getMethod(methodName);

            // 8、调用invoke方法，执行obj中的mtd方法。
            mtd.invoke(obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}
