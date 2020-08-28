package cn.web.servlet;

import sun.plugin.util.UIUtil;

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
 * 返回一个验证码
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        /* 1、创建一个图片对象 */
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        /* 2、获取画笔对象 */
        Graphics g = image.getGraphics();

        /* 3、给图像绘制一个底色 */
        g.setColor(Color.ORANGE); // 画笔设置颜色
        g.fillRect(0,0,width,height);   // 绘制矩形图层

        /* 4、给图像绘制一个矩形边框 */
        g.setColor(Color.RED);
        g.drawRect(0,0,width-1,height-1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";

        /* 5、生成一个随机数对象 */
        Random ran = new Random();

        /* 6、根据随机数，生成索引，通过索引，在str字典里找出这个字，绘制到图片对象上 */
        for (int i=1;i<=4;i++){
           int index = ran.nextInt(str.length());

           char ch = str.charAt(index);
           g.drawString(ch + "",width/5*i, height/2);
        }

        /* 7、接着换一种颜色，在图片上绘制几根线，用来做干扰 */
        g.setColor(Color.BLUE);
        for (int i =0;i<10;i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);

            g.drawLine(x1,y1,x2,y2);
        }

        /* 8、绘制结束，将图片响应给客户端 */
        ImageIO.write(image,"jpg",res.getOutputStream());
    }
}
