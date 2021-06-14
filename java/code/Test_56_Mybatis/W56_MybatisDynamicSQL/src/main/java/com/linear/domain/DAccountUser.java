package com.linear.domain;

import java.io.Serializable;

/**
 * 56.9 封装额外的对象，用来同时映射account和user
 *
 * 实现Serializable的主要目的是为了让类支持序列化
 */
public class DAccountUser extends DAccount implements Serializable {

    // 因为继承已经获得了account的属性，此时只需要将部分user属性加入即可。
    private String userName;
    private String address;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return super.toString() + " DAccountUser{" + "userName='" + userName + '\'' + ", address='" + address + '\'' +'}';
    }
}
