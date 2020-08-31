package cn.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @desc 获取验证码图片
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);

        String checkCode = getCheckCode();
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("黑体",Font.BOLD,24));
        g.drawString(checkCode,15,25);

        ImageIO.write(image,"PNG",response.getOutputStream());
    }

    private String getCheckCode(){
        /* 1、字典 */
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();

        int CODE_COUNT = 4;     // 验证码字符个数

        /* 2、生成随机字符 */
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i=1;i<=CODE_COUNT;i++){
            int index = r.nextInt(size);
            char c = base.charAt(index);

            sb.append(c);
        }

        /* 3、把生成的字符返回 */
        return sb.toString();
    }
}
