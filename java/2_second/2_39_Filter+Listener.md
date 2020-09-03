# Vol.39 

## 39.1 Filter 过滤器
 
Web三大组件
- JSP
- Filter
- Listener

——————————————————————————————————————————      

Web过滤器， 
当访问服务器的资源时，过滤器可以将请求拦截下来，完成一些特殊的功能。        

一般用于完成通用的操作。    
例如：登录验证、统一编码处理、敏感字符过滤。    

```java
/**
    定义一个类，去implements Filter接口
 */
@WebFilter("/*")
public class FilterDemo implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        // 当过滤器启动时触发init，在这里可以做一些资源加载工作，
        // init仅执行一次
    }

    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain
    )throws IOException,ServletException{
        // 每次访问 /* 也就是所有Servlet路由时，都会触发doFilter

        // 允许通过，传递到下一个匹配路由
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy(){
        // 当服务器正常关闭时，触发destroy
    }
}
```
——————————————————————————————————————————      

## 39.2 过滤器可以有两种配置方法，

- 第一种是用注解
```java
@WebFilter("/*")
```

——————————————————————————————————————————      

- 第二种是web.xml配置
```xml
<filter>
    <filter-name>demo1</filter-name>
    <filter-class>cn.web.filter.FilterDemo</filter-class>
</filter>
<filter-mapping>
    <filter-name>demo1</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

## 39.3 过滤器执行

- 执行流程
    - 执行filter
    - 执行到filterChain.doFilter，转去其他资源处理
    - 其他资源处理完成，继续执行filterChain.doFilter后面的代码

- 生命周期
    - init
    - doFilter
    - destroy

——————————————————————————————————————————      

- 过滤器配置
    - 拦截路径
        - 具体的路径 →  /index.jsp
        - 某一个目录下的所有 →  /user/*
        - 某一类型的文件 →  *.jsp
        - 拦截所有资源 → /*

    - 拦截方式：dispatcherTypes
        - dispatcherTypes
            - 1、 REQUEST：默认值。浏览器直接请求资源，此时会触发过滤器
			- 2、 FORWARD：转发访问资源时触发
			- 3、 INCLUDE：包含访问资源时触发
			- 4、 ERROR：错误跳转资源时触发
			- 5、 ASYNC：异步访问资源时触发

        - 配置方式
            - 注解：@WebFilter(dispatcherTypes = DispatcherType.FORWARD)
            - web.xml：<dispatcher></dispatcher>

> Tips:     
> dispatcherTypes可以配置多个，不一定只能配置一个值，可以接收一个数组。    
 
——————————————————————————————————————————      

- 多个过滤器时
    - 执行顺序：
        - 对于注解配置，按类名的字符串比较规则，值小的先执行。
            - AFilter 和 BFilter，AFilter先执行。
        - 对于web.xml配置，<filter-mapping>谁定义在上边，谁先执行

——————————————————————————————————————————      

## 39.4 设计模式 - 代理模式

在获取到响应之后，对响应的参数进行过滤，

可以考虑在servlet上加一个代理，代理中对request进行增强。

```java
/* 1、定义一个接口 */
public interface SaleComputer{
    public String sale(double money);
    public void show();
}

/* 2、定义类，实现SaleComputer接口 */
public class Lenovo implements SaleComputer{
    @Override
    public String sale(double money){

        System.out.println("花费："+money);
        return "电脑";
    }

    @Override
    public void show(){
        System.out.println("show computer");
    }
}

/* 3、利用代理，对lenovo的sale方法进行增强 */
public class ProxyTest{
    public static void main(String[] args){
        Lenovo lenovo = new Lenovo();

        SaleComputer proxyLenovo = (SaleComputer) Proxy.newProxyInstance(
            lenovo.getClass().getClassLoader(),
            lenovo.getClass().getInterfaces(),
            /* 参数三，传入一个InvocationHandler的实例
                实例重写了invoke方法
             */
            new InvocationHandler(){
                /**
                 * 代理对象调用的所有方法，都会触发这里的invoke
                 *      proxy 代理对象
                 *      method 代理对象调用的方法
                 *      args 代理对象调用方法时，传入的参数
                 */ 
                @Override
                public Object invoke(
                    Object proxy,
                    Method method, 
                    Object[] args
                )throws Throwable{
                    if(method.getName().equals("sale")){
                        /* 对sale方法进行增强，在代理中，克扣了部分钱 */
                        double money = (double) args[0];
                        money = money * 0.85;

                        /* 给sale方法增加额外的功能 */
                        System.out.println("专车接送...");
                        String obj = (String) method.invoke(lenovo,money);
                        System.out.println("送货上门...");

                        return obj + "_鼠标垫";
                    }else{
                        Object obj = method.invoke(lenovo,args);
                        return obj;
                    }
                }
            }
        );

        // 手动触发调用show方法，进行测试
        proxyLenovo.show();
    }
}
```
——————————————————————————————————————————      

**那么，如何增强request对象？**     

在Servlet前，增加一个过滤器。
```java
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter{
    public void doFilter(
        ServletRequest req,
        ServletResponse res,
        FilterChain filterChain
    ) throws ServletException, IOException{
        
        /* 1、设置一个代理对象，里面传入req */
        ServletRequest proxyReq = (ServletRequest) Proxy.newProxyInstance(
            req.getClass().getClassLoader(),
            req.getClass().getInterfaces();
            new InvocationHandler(){
                /* 设置代理的具体函数 */
                @Override
                public Object invoke(
                    Object proxy,
                    Method method,
                    Object[] args
                )throws Throwable{
                    /* 对getParameter函数进行增强，过滤敏感字符 */
                    if(method.getName().equals("getParameter")){
                        String value = (String) method.invoke(req,args);

                        if(value != null){
                            for(String str:list){
                                if(value.contains(str)){
                                    value = value.replaceAll(str,"***");
                                }
                            }
                        }
                        return value
                    }
                    
                    return method.invoke(req,args);
                }
            }
        )

        /* 2、过滤完成，将增强后的req传入到下一个路由 */
        filterChain.doFilter(proxyReq,res);
    }

    /* init方法里面，用来加载一些资源，例如敏感词汇 */
    private List<String> list = new ArrayList<String>();//敏感词汇集合
    public void init(FilterConfig config) throws ServletException {
        try{
            //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");

            //2.读取文件
            BufferedReader br = new BufferedReader(
                new FileReader(realPath)
            );

            //3.将文件的每一行数据添加到list中
            String line = null;
            while((line = br.readLine())!=null){
                list.add(line);
            }

            br.close();
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void destroy() {}
}
```
——————————————————————————————————————————      

## 39.5 Listener 监听器

监听器，用于监听某一种事件的发生。

> Tips:     
> 监听器在开发中用的比较少，主要是一些框架的源码里面会用        


- 事件监听机制
	- 事件	：一件事情
	- 事件源 ：事件发生的地方
	- 监听器 ：一个对象
	- 注册监听：将事件、事件源、监听器绑定在一起。 
        - 当事件源上发生某个事件后，执行监听器代码

ServletContextListener，用于监听ServletContext对象的创建和销毁。

```java
@WebListener
public class ContextLoaderListener implements ServletContextListener{

    /**
     * @desc 监听 ServletContext对象的创建，
     *      ServletContext会在服务器启动的时候自动创建
     *      所以这个监听器会在服务器启动的时候触发
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        // 1、获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();

        // 2、加载资源文件
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        // 3、获取真实路径
        String realPath = servletContext.getRealPath(contextConfigLocation);

        // 4、将资源加载到内存
        try{
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        }catch(Exception e){
            e.printStackTrace();
        }

        // 在这里就监听到了 ServletContext 被创建了。
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent){
        // 服务器关闭时，ServletContext对象被销毁，
        // 此时会触发 contextDestroyed 函数
    }
}
```

——————————————————————————————————————————      

配置Listener
- 注解：@WebListener
- web.xml
```xml
<listener>
    <listener-class>cn.web.listener.ContextLoaderListener</listener-class>
    
    <!-- 并且可以指定初始化的参数 -->
    <context-param></context-param>
</listener>
```