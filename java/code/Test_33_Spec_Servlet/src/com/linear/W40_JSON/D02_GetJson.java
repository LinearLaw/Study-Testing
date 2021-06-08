package com.linear.W40_JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linear.W34_Login.User;
import org.apache.commons.beanutils.BeanUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    40.2、 JSON to Object
        获取到了一个json字符串，将其映射到java对象

    Object -> JSON

    Object <- JSON
        用ObjectMapper.readValue(json, User.class)
        注意结合Map进行使用

 */
@WebServlet("/submitUserInfo")
public class D02_GetJson extends HttpServlet {

    private String ArrayStringToString(String[] as){
        StringBuilder sb = new StringBuilder();
        for(String i : as){
            sb.append(i);
        }
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // 1、获取请求参数
        String json = req.getParameter("info");
        // System.out.println(json);

        // 2、解析json，映射到user类中
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(json,User.class);

        // System.out.println(user);

        // 3、响应信息
        Map<String,Object> msg = new HashMap<>();
        msg.put("msg","提交成功");
        msg.put("code",1);

        resp.setContentType("text/json;charset=utf8");
        resp.getWriter().print(om.writeValueAsString(msg));

    }
}
