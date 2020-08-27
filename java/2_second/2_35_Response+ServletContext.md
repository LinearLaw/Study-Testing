# Vol.35 

## 35.1 HTTP协议 -> 不详写

请求消息
- 请求行
- 请求头
- 请求空行
- 请求体

响应
- 响应行
- 响应头
- 响应空行
- 响应体

——————————————————————————————————————————      

## 35.2 Response

```java
@WebServlet("/response")
public class ResponseDemo extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        /*  1、重定向
            动态获取虚拟目录
         */
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/response");

        /* 2、设置编码，输出字符流 */
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write("<a>你好</a>");

        /* 3、输出字节流 */
        ServletOutputStream sos = response.getOutputStream();
        sos.write("你好".getBytes("utf-8"));
    }
}
```
——————————————————————————————————————————      

## 35.3 路径

- 相对路径

- 绝对路径：主要依据 —— 判断定义的路径是给谁用的？判断请求将来从哪儿发出
	* 给客户端浏览器使用：需要加虚拟目录(项目的访问路径)
	    - 建议虚拟目录动态获取：request.getContextPath()
		- \<a> , \<form> 重定向...

	* 给服务器使用：不需要加虚拟目录
		* 转发路径

——————————————————————————————————————————      

## 35.4 输出一个验证码 -> 联系一下

```java
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/getCharCode")
public class CheckCode extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        int width = 100;
        int height = 50;

        /* 1、创建一个图片对象 */
        BufferedImage image = new BufferedImage(
            width,  
            height,
            BufferedImage.TYPE_INT_RGB  // 图片的类型，RGB类型的图片
        );

        /* 2、获取画笔对象 */
        Graphics g = image.getGraphics();
        /* 2.1、先给画笔设置颜色，然后填充一个矩形 */
        g.setColor(Color.PINK);     
        g.fillRect(0,0,width,height);   

        /* 2.2、绘制一个边框 */
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width-1,height-1);

        /* 2.3、验证码可能出现的字符字典 */
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";

        /* 3、生成随机数，随机找一个字符，绘制到图像中 */
        Random ran = new Random();
        for(int i = 1;i<=4;i++){
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            g.drawString(ch + "", width/5*i, height/2);
        }

        /* 4、绘制干扰线 */
        g.setColor(Color.GREEN);
        for(int i = 0;i<10;i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);

            g.drawLine(x1,y1,x2,y2);
        }

        /* 5、将绘制的图片输出到response */
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
```
——————————————————————————————————————————      

## 35.5 ServletContext

使用ServletContext，可以和web容器进行通信
- 获取MIME类型

- 共享数据

- 获取文件真实路径

```java
@WebServlet("/servletContext")
public class ServletContextDemo extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        /*  1、获取ServletContext，
            有两种方式，这两种方式获取到的对象是同一个对象。
         */
        ServletContext ctx1 = request.getServletContext();
        ServletContext ctx2 = this.getServletContext();

        /* 2、获取某一个资源的MIME类型 */
        ServletContext context = this.getServletContext();
        String filename = "a.jpg";
        String mimeType = context.getMimeType(filename);

        /* 3、可以给context设置数据，进行共享
            需要注意，ServletContext setAttribute之后，
            设置的值是全局使用的，所以谨慎使用，波及范围很广。
         */
        context.setAttribute("msg","haaa");

        /* set的attribute可以在另外一个Servlet中获取到 */
        Servlet contextN = this.getServletContext();
        Object msg = contextN.getAttribute("msg");

        /* 4、获取文件的真实路径
            返回的是带盘符的完整路径。eg-> c://aaabbb/dd
         */
        String b = context.getRealPath("/b.txt");
    }
}
```

——————————————————————————————————————————      

## 35.6 练习：下载文件 -> 试一下

如何解决中文文件名的问题？
```java
String filename = request.getParameter("filename");
String agent = request.getHeader("user-agent");

/* 根据不同的浏览器，重新设置filename的值 */
if(agent.contains("MSIE")){
    /* IE浏览器 */
    filename = URLEncoder.encode(filename,"utf-8");
    filename = filename.replace("+"," ");
}else if(agent.contains("Firefox")){
    /* 火狐浏览器 */
    BASE64Encode base64Encoder = new BASE64Encoder();
    filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
}else{
    /* 其他浏览器 */
    filename = URLEncoder.encode(filename, "utf-8");
}

/* 给header的content-disposition设置对应的值 */
response.setHeader("content-disposition","attachment;filename="+filename);
```

——————————————————————————————————————————      