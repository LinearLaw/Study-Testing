
import java.io.*;
import java.net.*;

/**
 * @desc 客户端连接
 */
class TransClient{

    public static void main(String[] args)throws Exception
    {
        // 1、创建Socket对象，建立连接，端口号10005
        Socket s = new Socket("localhost",10005);

        // 2、创建一个流对象，用于读取键盘数据
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        // 3、将数据写入到socket输出流，发送给服务端，创建一个PrintWriter对象
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        // 4、创建一个读取流，用于读取服务端返回的信息
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));


        // 5、读取键盘输入的内容，用out进行发送出去
        String line = null;
        while((line=bufr.readLine())!==null){
            if("over".equals(line)){
                break;
            }

            // out是一个PrintWrite对象，println方法可以将数据发送给服务器
            out.println(line);

            // bufIn读取服务器返回的数据，打印到控制台
            String str = bufIn.readLine();
            System.out.println("Server:" + str);
        }

        // 6、关闭读取流，关闭Socket
        bufr.close();
        s.close();
    }
}

/**
 * @desc 服务端
 */
class TransServer{
    public static void main(String[] args)
    {
        // 1、创建一个ServerSocket对象，监听端口10006，
        ServerSocket ss = new ServerSocket(10006);

        // 2、用accept方法，获取收到的信息
        Socket s = ss.accept();
        // 获取收到信息的ip地址
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip + "...connected");

        // 3、创建一个读取流对象，用于读取获取的信息
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // 4、创建PrintWriter对象，用于发送信息
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        // 5、读取获取到的信息，将其转换成大写，发出去
        String line = null;
        while((line=bufIn.readLine())!=null){
            System.out.println(line);

            // println发出了收到字符的大写字符。
            out.println(line.toUpperCase());
        }

        // 6、关闭流对象，关闭ServerSocket
        s.close();
        ss.close();

    }
}