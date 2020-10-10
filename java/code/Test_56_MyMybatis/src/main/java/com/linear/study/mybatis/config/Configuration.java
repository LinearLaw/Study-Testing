package com.linear.study.mybatis.config;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc mymybatis的配置类
 */
public class Configuration {

    private String driver;  // 驱动
    private String url;     // 数据库连接的url
    private String username;    // 用户名
    private String password;    // 密码
    private Map<String, Mapper> mappers = new HashMap<String, Mapper>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers = mappers;
    }
}
