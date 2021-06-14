package com.linear.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 */

public class EUser implements Serializable {

    private int uid;
    private String userName;
    private Date birthday;
    private String sex;
    private String address;

    // user类中加上role集合的引用
    private List<ERole> rlist;

    public List<ERole> getRlist() {
        return rlist;
    }

    public void setRlist(List<ERole> rlist) {
        this.rlist = rlist;
    }

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

    @Override
    public String toString() {
        return "EUser{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
