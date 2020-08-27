# Vol.34 

## 34.1 Servlet

- Servlet : 接口

- GenericServlet : 抽象类，除了service，其他方法都进行了空实现

- HttpServlet : 抽象类，对http进行了封装。
    - 定义一个类，extends HttpServlet
    - 复写 doGet / doPost 方法

## 34.2 GenericServlet

```java
/*
    1、本来我们写Servlet，需要将5个方法全都实现

    但是事实上，我们可能只需要用service方法，其他方法都是空函数，
    所以就有了GnenricServlet
 */

@WebServlet("/demo1")
public class ServletDemo1 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException { }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("demo1....");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() { }
}
```
```java
/*
    2、使用GenericServlet之后，只需要重写service方法即可。
 */
@WebServlet("/demo2")
public class ServletDemo2 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("demo2.....");
    }
}
```
——————————————————————————————————————————      

## 34.3 HttpServlet

```java
/* 
    定义一个类，extends HttpServlet，
    重写其doGet和doPost
 */
@WebServlet("/demo3")
public class ServletDemo3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet....");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
    }
}
```
```java
/* name是类名，urlPattern是对应的虚拟路径 */
@WebServlet(name="abc",urlPattern="/demo3")

/* 也可以指定多个路由执行同一个Servlet */
@WebServlet({"/demo1","/demo2","/demo3"})
```
——————————————————————————————————————————      

## 34.4 HTTP协议

Hyper Text Transfer Protocol : 超文本传输协议

- 特点：
	1. 基于TCP/IP的高级协议
	2. 默认端口号:80
	3. 基于请求/响应模型的:一次请求对应一次响应
	4. 无状态的：每次请求之间相互独立，不能交互数据

- 历史版本：
	* 1.0：每一次请求响应都会建立新的连接
	* 1.1：复用连接

请求消息
- 请求行
- 请求头
    - User-Agent: 浏览器版本信息
    - Referer: 请求来源地址
        - 可以防盗链、可以做统计工作

- 请求空行
- 请求体

——————————————————————————————————————————      

## 34.5 Request

request & response
- 1、 request和response对象是由服务器创建的。我们来使用它们
- 2、 request对象用来获取请求消息，response对象用来设置响应消息

——————————————————————————————————————————      

继承体系

> ServletRequest		--	接口        
> 	|	继承        
> HttpServletRequest	-- 接口     
> 	|	实现        
> org.apache.catalina.connector.RequestFacade 类(tomcat)        

——————————————————————————————————————————      

request功能
- 获取请求行
```java
@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 1、获取请求方式 */
        String method = request.getMethod(); // GET

        /* 2、获取虚拟目录 */
        String contextPath = request.getContextPath(); // 项目的目录

        /* 3、获取Servlet路径 */
        String servletPath = request.getServletPath(); // return /requestDemo1

        /* 4、获取get方式请求参数 */
        String queryString = request.getQueryString(); // xxx=aaa

        /* 5、获取请求的URI */
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();

        /* 6、获取协议及版本 */
        String protocol = request.getProtocol();

        /* 7、获取客户机IP地址 */
        String remoteAddr = request.getRemoteAddr();
    }
}
```
——————————————————————————————————————————      

- 获取请求头
```java
@WebServlet("/reqDemo2")
public class RequestDemo2 extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        /* 1、获取所有的请求头名称 */
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();

            String value = request.getHeader(name);
        }

        /* 2、根据agent判断浏览器版本 */
        String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            // 谷歌
        }else if(agent.contains("Firefox")){
            // 火狐
        }

        /* 3、利用Referer判断请求来源 */
        String referer = request.getHeader("referer");
        if(referer != null){
            if(referer.contains("/demo")){
                // 判断路径里是否包含某些字符，确定是否是正确的来源
            }else{
                // 发现不是正确的来源，说明是盗链    
            }
        }
    }
}
```
——————————————————————————————————————————      

- 获取请求体：get请求的请求体是null，所以一般只用于post
```java
@WebServlet("/requestDemo5")
public class RequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 1、获取reader对象，用来读取请求体 */
        BufferedReader br = request.getReader();

        String line = null;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }

        /* 2、获取参数 */
        String username = request.getParameter("username");

        String[] hobbies = request.getParameterValues("hobby");

        /* 3、用枚举的方式获取所有参数 */
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();

            String value = request.getParameter(name);
        }

        /* 4、用map的方式获取所有参数 */
        Map<String,String[]> parameterMap = request.getParameterMap();
        Set<String> keyset = parameterMap.keySet();
        for(String name:keySet){
            String[] values = parameterMap.get(name);
            
            for(String value:values){
                System.out.println(value);
            }
        }

        /* 5、设置流的编码格式
            以utf-8的编码，来获取参数值，避免中文乱码

            get请求中文乱码，tomcat 8已经解决，
            post请求的中文乱码，就需要setCharacterEncoding
         */
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
}
```


——————————————————————————————————————————      

> Tips:     
> idea快速创建Servlet，     
> 选中某一个Package，右键new → Servlet → 输入类名 → ok。        
>       
> 此时会自动生成Servlet代码，并加注解       

——————————————————————————————————————————      

## 34.6 请求转发

```java
/* 将当前的请求，转发到其他的Servlet */
request.getRequestDispatcher("/requestDemo").forward(request,response);
```
```java
/* 在转发之前，可以设置一些自定义的数据 */
request.setAttribute("msg","hello");
request.getRequestDispatcher("/requestDemo").forward(request,response);

/*  在转到的其他Servlet中，可以获取这些自定义数据
    这种方式可以让两个Servlet共享数据
 */
Object msg = request.getAttribute("msg");
```

## PS

1、快速添加try catch
```
1、选中要try的一段代码

2、两种方式
    - 顶部菜单，Code -> Surround with...
    - 快捷键 Alt + Shift + Z

3、在弹出的菜单中选try...catch...
```
——————————————————————————————————————————      

2、ClassNotFoundException 和 NoClassDefFoundError
```
给一个类写测试类，使用了junit，运行时发现弹出了NoClassDefFoundError，

File -> Project Structure -> Libraries
    然后将IDEA安装目录下，/lib文件夹内的jar包都Add，就不报错了。
```
——————————————————————————————————————————      

