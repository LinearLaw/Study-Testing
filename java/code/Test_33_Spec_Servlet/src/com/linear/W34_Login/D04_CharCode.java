package com.linear.W34_Login;


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

/*
    34、输出一个验证码
        使用的是java子带的图形库

 */
@WebServlet("/getCharCode")
public class D04_CharCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        // 1、创建一张画布
        int width = 100;
        int height = 40;
        BufferedImage image = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB // rgb类型的图片
        );

        // 2、画笔
        Graphics g = image.getGraphics();

        // 3、给画笔设置颜色，画上背景色
        g.setColor(Color.BLUE);
        g.fillRect(0,0,width,height);

        // 4、画上一个框
        g.setColor(Color.PINK);
        g.fillRect(0,0,width-1,height-1);

        // 5、生成随机数
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random ran = new Random();

        StringBuilder ranStr = new StringBuilder();
        g.setColor(Color.RED);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        for (int i = 0 ; i <= 4; i++){

            char ch = str.charAt(ran.nextInt(str.length()));
            ranStr.append(ch);
            g.drawString(ch+"",width/5*i+5,height/2+5);
        }

        // 6、设置session，用来检验
        req.getSession().setAttribute("CS",ranStr.toString());

        // 7、绘制干扰线
        g.setColor(Color.GREEN);
        for(int i = 0;i<10;i++){
            g.drawLine(ran.nextInt(width),ran.nextInt(width),ran.nextInt(height),ran.nextInt(height));
        }

        // 8、将绘制的图片输出到response
        ImageIO.write(image,"jpg",resp.getOutputStream());

    }
}
