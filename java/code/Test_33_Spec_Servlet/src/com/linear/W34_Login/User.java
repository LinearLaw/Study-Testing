package com.linear.W34_Login;


import com.fasterxml.jackson.annotation.JsonIgnore;


/*
    转成JSON时，可以通过注解来控制属性值的变化规则
        - @JsonIgnore 排除属性
        - @JsonFormat 属性值的格式化
 */
public class User {
    @JsonIgnore
    private Integer id;
    private String username;

    @JsonIgnore
    private String password;

    private Integer create_time;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
