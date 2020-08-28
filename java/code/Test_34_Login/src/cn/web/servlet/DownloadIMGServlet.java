package cn.web.servlet;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/download")
public class DownloadIMGServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        /* 1、获取文件的名称 */
        String filename = req.getParameter("filename");

        /* 2、通过ServletContext，获取文件的真实路径 */
        ServletContext servletCtx = this.getServletContext();
        String realPath = servletCtx.getRealPath("/img/"+filename);

        /* 3、创建一个读取文件流
         *      如果读取文件失败，需要捕获异常，返回提示语。
         */
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(realPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件读取出错...");
            res.setHeader("content-type","text/html;charset=utf-8");
            res.getWriter().write("没有找到指定文件");
            return;
        }

        /* 4、设置返回的数据类型 */
        String mimeType = servletCtx.getMimeType(filename);
        res.setHeader("content-type",mimeType);

        /* 5、如果这是一个中文文件名。会出现问题，这里需要判断不同浏览器类型
         */
        String agent = req.getHeader("user-agent");
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");

        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";

        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }

        /* 6、判断好了不同浏览器类型，确定好了filename，将其写入到响应头中 */
        res.setHeader("content-disposition","attachment;filename="+filename);

        /* 7、从response对象中，获取输出流 */
        ServletOutputStream sos = res.getOutputStream();

        /* 8、读取文件，往输出流中输出文件数据 */
        byte[] buff = new byte[1024*8];
        int len = 0;
        while((len = fis.read(buff))!= -1){
            sos.write(buff,0,len);
        }

        /* 9、关闭文件流 */
        fis.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doPost(req,res);
    }
}
