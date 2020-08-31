package cn.web;

import cn.dao.UserDaoImpl;
import cn.domain.User;
import cn.service.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String correctCode =(String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        request.removeAttribute("login_msg");

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
        if(correctCode != null && !correctCode.equalsIgnoreCase(verifyCode)){
            request.setAttribute("login_msg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        /* 1、使用Map装载请求的所有参数 */
        Map<String,String[]> map = request.getParameterMap();
        User user = new User();
        try {
            /* BeanUtils提供了一套工具，用来完成对象的封装 */
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /* 2、调用service，查找当前用户账户信息 */
        UserServiceImpl service = new UserServiceImpl();
        User loginUser = service.login(user);
        if(loginUser != null){
            session.setAttribute("user",loginUser);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else{
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
