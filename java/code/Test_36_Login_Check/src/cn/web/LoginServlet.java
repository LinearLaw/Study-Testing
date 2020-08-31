package cn.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String correctCode =(String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");

        if(username == null){
            request.setAttribute("login_msg","用户名不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if(password == null){
            request.setAttribute("login_msg","密码不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if(verifyCode == null){
            request.setAttribute("login_msg","验证码不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if(!correctCode.equalsIgnoreCase(verifyCode)){
            request.setAttribute("login_msg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }


        


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
