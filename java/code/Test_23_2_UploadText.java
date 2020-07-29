
/**
 * @desc 客户端发送某一个文件，服务器将其存到某一个文件中。
 */

import java.io.*;
import java.net.*;
import java.nio.Buffer;

/**
 * @desc 客户端
 */
class TextClient {

    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket("localhost",10010);

        BufferedReader bufr = new BufferedReader(new FileReader("./Test_20_2_RemoveDir.java"));

        // 创建PrintWriter对象，用于发送文件信息给服务器
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        String line = null;
        while((line=bufr.readLine()) != null){
            out.println(line);
        }

        // 关闭客户端的输出流，这时候会给流加上一个末尾标志-1
        s.shutdownOutput();

        // 读取服务器返回的内容
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = bufIn.readLine();
        System.out.println(str);

        bufr.close();
        s.close();
    }
}

/**
 * @desc 服务端
 */
class TextServer{
    public static void main(String[] args) throws Exception
    {
        ServerSocket ss = new ServerSocket(10006);

        Socket s = ss.accept();
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip+"...connected");

        // 读取获取到的信息，创建一个PrintWriter，用于将获取到的信息写入到文件
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(new FileWriter("server.txt"),true);

        String line = null;
        while((line=bufIn.readLine())!=null){
            out.println(line);
        }

        // 创建一个PrintWriter对象，用于发送信息给客户端
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        pw.println("上传成功");

        out.close();
        s.close();
        ss.close();
    }
}