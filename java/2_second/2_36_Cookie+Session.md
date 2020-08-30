# Vol.36 

## 36.1 会话

- 会话：一次会话中包含多次请求和响应。
    - 一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止

在一次会话的范围内，多次请求之间，有一些数据需要共享。

常用方式：
- Cookie ：用在客户端
- Session ：用在服务端

——————————————————————————————————————————      

## 36.2 Cookie 

Cookie，客户端会话技术，将数据保存到客户端

```java
/* 1、创建Cookie对象 */
Cookie c = new Cookie("name","value");

/* 2、将Cookie发送到客户端 */
response.addCookie(c);

/* 3、获取cookie */
Cookie[] cs = request.getCookies();
if(cs != null){
    for(Cookies c:cs){
        String name = c.getName();
        String value = c.getValue();
    }
}
```

——————————————————————————————————————————      

原理：
- 发送响应response时，响应header上携带了set-cookie；
- 在客户端发送request时，会自动在header上携带cookies；

——————————————————————————————————————————      

```java
/* 1、可以往res中加入多个Cookie */
Cookie c1 = new Cookie("msg","a");
Cookie c2 = new Cookie("msg2","b");

/* 2、并且可以给Cookie设置失效时间
    单位是秒
 */
c1.setMaxAge(30);
c2.setMaxAge(0); // max age=0时，该cookie失效

res.addCookie(c1);
res.addCookie(c2);
```

> Tips:     
> 同一个tomcat服务器中，如果有多个项目，      
> 默认情况下，多个项目的Cookie是不共享的。      
> 但是如果c.setPath("/")，那么Cookie将变成共享的。      

——————————————————————————————————————————      

> Tips：    
> tomcat 8 之前，cookie不可以存中文。   

> Tips:     
> 如果是不同的tomcat服务器，要共享cookie，      
> 就需要用domain，      
```java
/* 此时，tieba.baidu.com，news.baidu.com共享同一个cookie */
c.setDomain(".baidu.com")
```
——————————————————————————————————————————      

Tips：
cookie单个一般限制4KB，同域下个数20个左右。

——————————————————————————————————————————      

## 36.3 JSP

Java Server Pages，本质上其实是一个Servlet类。  

一个jsp文件，在编译过后就是一个Servlet类。

- 1. <%  java代码 %>：定义的java代码，
    - 编译后该代码块在service方法中。
    - service方法中可以定义什么，该脚本中就可以定义什么。

- 2. <%! java代码 %>：在jsp转换后，会变成java类的成员，
    - 用来定义成员变量，但是线程不安全，因此很少用。

- 3. <%= java代码 %>：定义的java代码，
    - 代码的结果会输出到页面上。
    - 输出语句中可以定义什么，该脚本中就可以定义什么。

——————————————————————————————————————————      

## 36.4 JSP内置对象

jsp本质上是一个servlet类，  
但是jsp本身其实导入了一些对象包，这些就是jsp的内置对象。    

- request

- response

- out → 字符输出对象，可以将数据输出到页面上。
    - 类似 response.getWriter()
    - 输出顺序，先输出response.getWriter()数据，再输出out.write()。

——————————————————————————————————————————      

> Tips：        
> <%  java代码  %> 中间是可以截断的。       

```jsp
<%
    List list = new ArrayList();
    list.add("111");
    list.add("222");
    list.add("333");

    for(String li:list){
        System.out.println(li);
%>

    <h2>java代码中间截断也没有关系，但是一般不会这样做</h2>

<%   
        System.out.println(list);
    }
%>
```

——————————————————————————————————————————      

## 36.5 Session

Session，服务器端会话技术， 
在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。

```java
/* 1、ServletA 里面设置session */
HttpSession session = req.getSession();
session.setAttribute("msg","xxx");

/* 2、ServletB 可以获取到这个session */
HttpSession session = req.getSession();
Object msg = session.getAttribute("msg");
```

> Tips：    
> session是依赖cookie的     
> - 响应：响应头上面，set-cookie:JSESSIONID=XXX
>     - 有了set-cookie，浏览器会自动将其存到cookie中。
>     
> - 请求：客户端发出请求，会自动携带cookie，
>     - 请求头上面，cookie:JSESSIONID=xxx

——————————————————————————————————————————      

## 36.6 一些细节

- 1、 当客户端关闭后，服务器不关闭，两次获取session是否为同一个？
	* 默认情况下。不是。

	* 如果需要相同，则可以创建Cookie,键为JSESSIONID，设置最大存活时间maxAge，让cookie持久化保存。

    ```java
		 Cookie c = new Cookie("JSESSIONID",session.getId());
	     c.setMaxAge(60*60);
	        response.addCookie(c);
    ```

- 2、 客户端不关闭，服务器关闭后，两次获取的session是同一个吗？
	- 不是同一个，但是要确保数据不丢失。tomcat自动完成以下工作
		- session的钝化：
			- 在服务器正常关闭之前，将session对象系列化到硬盘上
            - tomcat安装目录，会创建一个文件：/work/Catalina/localhost/项目名/sessionid.ser

		- session的活化：其实就是取出关闭服务器时创建的session文件，读取
			- 在服务器启动后，将session文件转化为内存中的session对象即可。
			
- 3、 session什么时候被销毁？
	- 1、 服务器关闭
	- 2、 session对象调用invalidate() 。
	- 3、 session默认失效时间 30分钟，可以通过配置修改	
    ```xml
		<session-config>
	        <session-timeout>30</session-timeout>
	    </session-config>
    ```

## 36.7 session的特点

- 1、 session用于存储一次会话的多次请求的数据，存在服务器端
- 2、 session可以存储任意类型，任意大小的数据

	- session与Cookie的区别：
		- 1、 session存储数据在服务器端，Cookie在客户端
		- 2、 session没有数据大小限制，Cookie有
		- 3、 session数据安全，Cookie相对于不安全