package com.linear.D02_CRUD.domain;

/**
 * account类 对应了 account表
 */
public class Account {

    private String name;
    private Float money;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{ name='" + name + '\'' +
                ", money=" + money + ", id=" + id + '}';
    }
}
