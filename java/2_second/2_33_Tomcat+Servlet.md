# Vol.33 

## 33.1 web相关

- C/S架构
- B/S架构

- 动态资源
- 静态资源

## 33.2 网络通信三要素

- IP
- 端口
- 协议：UDP、TCP

## 33.3 web服务器软件

web服务器：接收req，处理req，进行response；

常见的java相关web服务器
- webLogic：oracle公司，大型的JavaEE服务器，
    - 支持所有的JavaEE规范，收费的。

- webSphere：IBM公司，大型的JavaEE服务器，
    - 支持所有的JavaEE规范，收费的。

- JBOSS：JBOSS公司的，大型的JavaEE服务器，
    - 支持所有的JavaEE规范，收费的。

- Tomcat：Apache基金组织，中小型的JavaEE服务器，
    - 仅仅支持少量的JavaEE规范servlet/jsp。开源的，免费的。

——————————————————————————————————————————      

## 33.4 Tomcat

Tomcat 8

- bin → 里面都是可执行文件，startup可以启动tomcat服务

- conf → 配置文件 

- lib → 依赖的jar包

- logs → 日志文件

- webapps → 存放web项目

- work  → 存放运行时的数据

——————————————————————————————————————————      

启动tomcat，双击/bin/startup.bat。

> Tips:     
> 1、如果黑窗口一闪而过，说明环境变量JAVA_HOME错了；        
>     配置JAVA_HOME的路径为jdk目录      
> 2、如果报错，一般是端口号被占用导致出错，修改配置文件分配其他的端口号即可。     

——————————————————————————————————————————      

## 33.5 部署项目

- 1、直接放到webapps文件夹下即可。
    - 更简化的方法：将项目打成一个war包，
    - 如何将项目打成war包？
        - 要么直接添加到zip压缩文件，改后缀为war
        - 要么使用专门的war包打包工具。
    
- 2、修改 /conf/serve.xml 文件
    - 在<Host>标签体中写 <Context docBase="D:\hello" path="/hehe" />
		- docBase:项目存放的路径
		- path：虚拟目录，是url访问时的路径

- 3、在conf\Catalina\localhost创建任意名称的xml文件。
    - 在文件中编写 <Context docBase="D:\hello" />
		- docBase:项目路径 
        - path虚拟目录：默认是当前xml文件的名称，是url访问的时候的路径


## 33.6 两种项目

- 静态项目
- 动态项目
    - java项目 → 
        - 配置文件：/WEB-INF
            - /web.xml → web项目的核心配置
            - /classes → 类文件
            - /lib → 依赖的jar包

## 33.7 如何将Tomcat集成到idea中

## 