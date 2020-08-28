package cn.web.servlet;

import cn.dao.UserDao;
import cn.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/dologin",name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * 收到了来自客户端的login post请求，处理数据，进行响应
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("post sth...");

        /* 1、设置编码格式 */
        req.setCharacterEncoding("utf-8");

        /* 2.1、方式一，直接用UserDao来进行 */
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        if(user == null){
            // 登录失败，转发到失败的servlet
            req.getRequestDispatcher("/FailServlet").forward(req,res);
        }else{
            // 登录成功，存储当前查到的用户数据，并转发到成功的servlet
            req.setAttribute("user",user);
            req.getRequestDispatcher("/SuccessServlet").forward(req,res);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get sth...");
    }
}
