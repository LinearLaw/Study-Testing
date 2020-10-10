package com.linear.study.mybatis.io;

import java.io.InputStream;

/**
 * @desc 类加载器，用来读取指定配置文件的类
 */
public class Resources {

    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
