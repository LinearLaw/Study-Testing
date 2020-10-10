package com.linear.study.mybatis.utils;

import com.linear.study.mybatis.config.Configuration;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XMLConfigBuilder {

    /**
     * 解析mybatis xml主配置文件 → 主要的操作都在这里
     *      dom4j + xpath
     *
     * @param config
     * @return
     */
    public static Configuration loadConfiguration(InputStream config){
        Configuration cfg = new Configuration();

        SAXReader reader = new SAXReader();

        Document document = reader.read(config);


    }
}
