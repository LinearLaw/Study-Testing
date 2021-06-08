package com.linear.W40_JSON;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.linear.W34_Login.JdbcUtils;
import com.linear.W34_Login.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/*
    40.1、Obejct to JSON
        收到请求，查询数据库得到列表，将列表封装成json格式返回

    Object -> JSON
        用ObjectMapper.writeValueAsString(obj)
        注意结合Map进行使用

    Object <- JSON  用ObjectMapper.readValue(json, obj.class)

 */
@WebServlet("/getUserList")
public class D01_ReturnJSON extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        try {
            // 查询数据库，将数据对象存入List
            String sql = "select * from user";
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            List<User> list = qr.query(sql,new BeanListHandler<>(User.class));

            // 将list映射到json字符串中
            ObjectMapper mp = new ObjectMapper();
            String json = mp.writeValueAsString(list);

            System.out.println(json);

            // 封装数据信息
            Map<String,Object> resMsg = new HashMap<>();
            resMsg.put("status",1);
            resMsg.put("data",json);

            // 返回数据
            resp.setContentType("text/json;charset=utf8");
            resp.getWriter().print(mp.writeValueAsString(resMsg));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
