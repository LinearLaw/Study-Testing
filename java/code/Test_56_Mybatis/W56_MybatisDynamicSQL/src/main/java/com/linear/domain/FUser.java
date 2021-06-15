package com.linear.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表的数据结构
 */

public class FUser implements Serializable {

    private int uid;
    private String userName;
    private Date birthday;
    private String sex;
    private String address;

    // 主表保存从表集合的引用
    private List<FAccount>  alist;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FAccount> getAlist() {
        return alist;
    }

    public void setAlist(List<FAccount> alist) {
        this.alist = alist;
    }

    @Override
    public String toString() {
        return "DUser{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
