package com.linear.domain;

import java.io.Serializable;

/**
 * 用户账户信息
 */
public class FAccount implements Serializable {

    private Integer id;
    private Integer uid;
    private Double money;

    // 在从表中，预留一个主表的引用
    private FUser user;

    public FUser getUser() { return user; }
    public void setUser(FUser user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
