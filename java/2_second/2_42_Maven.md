# Vol.42 Maven

## 42.1 Maven

作用
- 管理jar包
- 依赖管理
- 构建工程，一键构建，自动部署
- 单元测试，打包

——————————————————————————————————————————      

官网：maven.apache.org

在安装maven之前，需要机器中配置好了JDK。

——————————————————————————————————————————      

Maven 目录
- /bin  : 存放了maven的命令，比如我们前面用到的mvn tomcat:

- /runboot : 存放了一些maven本身的引导程序，如类加载器等

- /conf : 存放了maven的一些配置文件，如setting.xml文件
    - setting.xml  可以设置本地仓库的路径。
        - <localRepository>D:/Repository</localRepository>

- /lib : 存放了maven本身运行所需的一些jar包

此外，  
需要配置MAVEN_HOME到环境变量，值是maven的安装路径。     
接着在path中加入 %MAVEN_HOME%/lib。    

至此我们的maven软件就可以使用了，前提是你的电脑上之前已经安装并配置好了JDK。

——————————————————————————————————————————      


## 42.2 依赖管理

在Maven构建的项目中，   
jar包放入到统一的jar包仓库中，而项目仅记录jar包所在的坐标，     
这个坐标写入到项目中的pom.xml中。   

于是这里有三种jar包仓库
- 本地仓库：自己电脑上面的jar包仓库
- 远程仓库：也叫私服，一般是局域网服务器上面的jar包仓库。
- 中央仓库：maven的中央仓库。

——————————————————————————————————————————      

## 42.3 一键构建

构建过程：清理、编译、测试、报告、打包、部署。

启动tomcat服务，应注意，这里用的tomcat是maven自带的tomcat插件。
```cmd
mvn tomacat:run
```
——————————————————————————————————————————      

## 42.4 Maven工程

创建的一个Maven工程，
- /.settings
- /src  : 源代码
    - /main 
        - java : 项目源代码
        - resource : 放入配置文件
        - webapp : jsp，html，js，css都放这里

    - /test : 主要是一些测试类
        - java
        - resource

- /target : 编译后的代码
- pom.xml

启动：mvn tomcat:run，
端口号默认8080，虚拟路径可能需要加上项目文件夹名。

——————————————————————————————————————————      

## 42.5 Maven 指令

- mvn compile : 将/src/main/java中的代码编译，放入target文件夹

- mvn clean : 将/target文件夹删掉，里面都是编译好的class文件

- mvn test : 将/src/test/java中的代码编译，放入target文件夹

- mvn package : 将java工程打成jar包

- mvn install : 将maven项目打成jar包，并发布到本地仓库

——————————————————————————————————————————      

## 42.6 Maven 指令的生命周期

maven对项目构建过程分为三套相互独立的生命周期。

- Clean Lifecycle 在进行真正的构建之前进行一些清理工作。    

- Default Lifecycle 构建的核心部分，编译，测试，打包，部署等等。    

- Site Lifecycle 生成项目报告，站点，发布站点。 

——————————————————————————————————————————      

## 42.7 Maven 概念模型

- 项目对象模型 POM，Project Object Modal
    - 主要是pom.xml文件。
    - 通过pom.xml文件定义项目的坐标、项目依赖、项目信息、插件目标等。

- 依赖管理系统(Dependency Management System)
    - 通过maven的依赖管理对项目所依赖的jar 包进行统一管理

- 项目生命周期(Project Lifecycle)
    - 使用maven完成项目的构建，项目构建包括：清理、编译、测试、部署等过程
    
    - compile → test → package → install → deploy
        - 每一个指令执行，都会把在它之前的所有指令都执行一遍。

- 标准
    - 例如标准的目录结构，标准的生命周期阶段，标准的坐标定义

- 插件(plugin)目标(goal)
    - maven 管理项目生命周期过程都是基于插件完成的

——————————————————————————————————————————      

## 42.8 Maven 的idea配置

File → Settings → Build Execution,Deployment → Build Tools → Maven

需要配置maven的安装目录，setting路径，本地仓库的位置。

创建Maven工程项目时，可以选择使用模板或不使用模板。

创建了一个java 类文件时，需要给它加上依赖，在pom.xml上面加。

```xml
<dependencies>
    <dependency>
        <!--项目名称，定义为组织名+项目名，类似包名-->
        <groupId>com.itheima</groupId>

        <!--模块名称-->
        <artifactId>hello_maven</artifactId>

        <!--当前项目版本号，snapshot为快照版本即非正式版本，release为正式发布版本-->
        <version>0.0.1-SNAPSHOT</version>

        <!-- 打包类型
            jar package会打成jar包
            war package打成war包
        -->
        <packaging >jar</packaging>

        <!-- 依赖范围
            compile 默认范围 可以不写，编译、测试、运行都有效
            provided 编译、测试均有效，但运行无效，
                例如 servlet-api，jsp-api

            runtime 测试、运行 有效
                例如 jdbc驱动的jar包
            
            test 仅在测试时有效
                例如junit
         -->
        <scope>provided</scope>
    </dependency>
</dependencies>
```

