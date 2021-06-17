package com.linear.D01_Injection.service.impl;

import com.linear.D01_Injection.service.IAccountService;

import java.util.*;

/**
 * 57.1、业务层实现类 - set注入 - 注入集合
 */
public class AccountServiceImpl3 implements IAccountService {

    private String[] strArr;
    private List<String> strList;
    private Set<Integer> strSet;
    private Map<String,String> strMap;
    private Properties props;


    public void saveAccount(){
        System.out.println("账户保存了: ");
        System.out.println("strArr: "+Arrays.toString(strArr));
        System.out.println(strList);
        System.out.println(strSet);
        System.out.println(strMap);
        System.out.println(props);
    }

    public String[] getStrArr() {
        return strArr;
    }

    public void setStrArr(String[] strArr) {
        this.strArr = strArr;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public Set<Integer> getStrSet() {
        return strSet;
    }

    public void setStrSet(Set<Integer> strSet) {
        this.strSet = strSet;
    }

    public Map<String, String> getStrMap() {
        return strMap;
    }

    public void setStrMap(Map<String, String> strMap) {
        this.strMap = strMap;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}
