package com.linear.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 58.2.3、自定义转换类，从String -> Date
 *      分两步：类实现Converter接口，将xml配置指向这个类
 */
public class String2Date implements Converter<String,Date> {

    public Date convert(String source){
        if(source == null){ throw new RuntimeException("数据为空");}
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        System.out.println("2222");
        try {
            return df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("转换时出现错误");
        }

    }
}
