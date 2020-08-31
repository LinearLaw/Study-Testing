# Vol.37

## 37.1 JSP 指令

- 作用：用于配置JSP页面，导入资源文件
- 格式：
    - <%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>

```jsp
<!-- 1、page指令 -->
<%@ page 
    contentType="text/html;charset=utf-8"  
    errorPage="500.jsp"     /* 出错时跳转到500.jsp */
    pageEncoding="utf-8"    /* 规定编码 */
    isErrorPage="false"     /* 是否是错误页面 */
    import="java.util.List" /* 引入包 */
 %>

<!-- 1.1、一般的，导入java包会单独写一行
    引入java包之后，在jsp的java代码里面才可以用这些类
 -->
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<!-- 2、include，用来引入一个jsp文件 -->
<%@ include file="top.jsp" %>


<!-- 3、taglib，用来引入标签库，主要用于完成html显示逻辑 -->
<%@ taglib 

    /* 标签前缀 */
    prefix="c"    

    /* 标签包的路径 */
    uri="http://java.sun.com/jsp/jstl/core" 
 %>

```
——————————————————————————————————————————      

## 37.2 JSP内置对象

在jsp页面中不需要创建，直接使用的对象。     
一共有9个：
```
	变量名					真实类型					作用
* pageContext			PageContext				当前页面共享数据，
                                                还可以获取其他八个内置对象

* request				HttpServletRequest		一次请求访问的多个资源(转发)

* session				HttpSession				一次会话的多个请求间

* application			ServletContext			所有用户间共享数据

* response				HttpServletResponse		响应对象

* page					Object					当前页面Servlet对象，相当于this

* out					JspWriter				输出对象，数据会输出到页面上

* config				ServletConfig			Servlet的配置对象

* exception				Throwable				异常对象
```
——————————————————————————————————————————      

## 37.3 MVC

- 1. jsp演变
	- 1. 早期只有servlet，只能使用response输出标签数据 → 非常麻烦

	- 2. 后来有了jsp，简化了Servlet的开发，
        - 但是，如果过度使用jsp，在jsp中即写大量的java代码，有写html表，
        - 会造成难于维护，难于分工协作
	- 3. 再后来，java的web开发，借鉴mvc开发模式，使得程序的设计更加合理性

- 2. MVC：
	- 1. M：Model，模型。JavaBean
		* 完成具体的业务操作，如：查询数据库，封装对象
	- 2. V：View，视图。JSP
		* 展示数据
	- 3. C：Controller，控制器。Servlet
		* 获取用户的输入
		* 调用模型
		* 将数据交给视图进行展示

——————————————————————————————————————————      

* 优缺点：
	1. 优点：
		1. 耦合性低，方便维护，可以利于分工协作
		2. 重用性高

	2. 缺点：
		1. 使得项目架构变得复杂，对开发人员要求高

——————————————————————————————————————————      

## 37.4 EL表达式

Expression Language 表达式语言。    

作用是替换和简化jsp页面中java代码的编写。   
```java
${java表达式}
```

jsp是默认支持EL表达式的，   
如何关闭EL表达式的使用？
```jsp
<!-- 方式一 -->
<%@ page 
    isELIgnored="true"  /* 忽略el表达式 */
 %>

<!-- 方式二，加一个反斜杠 -->
\${表达式}
```
——————————————————————————————————————————      

### 1、逻辑运算符

```jsp
<!-- 1、表达式输出的结果是true或false
    empty可以判断字符串、集合、数组对象是否为null或者长度为0
 -->
${empty str} 
${not empty str}

<!--  以下两种写法效果相同  -->
${3 div 4}
${3 / 4}

${3 mod 4}
${3 % 4 }

${3 and 4}
${3 or 4}

${not true}
${!true}

${a>1 and b\<2}
${a>1 && b\<2}
```
——————————————————————————————————————————      

### 2、域
有四个域对象
- pageScope --> pageContext

- requestScope  --> request

- sessionScope --> session

- applicationScope --> application（ServletContext）

```jsp
<!-- 1、直接用 域对象.成员名 -->
${ requestScope.name }


<!-- 2、省略域对象，直接用成员名
    此时，会依次从最小的域中查找是否有该键对应的值。
 -->
${ name }


<!-- 3、使用Object对象、List对象、Map对象 -->
<!-- 3.1、EL表达式访问某一个对象中的某一个成员 -->
<%
    User user = new User();
    user.setName("张三");
    user.setAge(23);
    user.setBirthday(new Date());

    request.setAttribute("u",user);
 %>
<span>${requestScope.u.name}</span>

<!-- Tips：u.age，其实是u.getAge()的返回值
    因此，如果需要u.birStr，
    就需要在User类中，写一个getBirStr方法。
 -->
<span>${u.age}</span>
<span>${u.birthday}</span>
<span>${u.birthday.month}</span>


<!-- 3.2、List -->
<% 
    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add(user);

    request.setAttribute("list",list);
 %>
<span>${list}</span>
<span>${list[0]}</span>
<span>${list[1]}</span>
<span>${list[10]}</span>
<span>${list[2].name}</span>


<!-- 3.3、Map -->
<%
    Map map = new HashMap();
    map.put("sname","李四");
    map.put("gender","男");
    map.put("user",user);

    request.setAttribute("map",map);
 %>
<span>${map.gender}</span>
<span>${map["gender"]}</span>
<span>${map.user.name}</span>

<!-- 4、常用的一个方法，在jsp中获取虚拟目录 -->
<span>${pageContext.request.contextPath}</span>

```
——————————————————————————————————————————      

## 37.5 JSTL

JavaServer Pages Tag Library  JSP标准标签库。
是由Apache组织提供的开源的免费的jsp标签。

作用：用于简化和替换jsp页面上的java代码。		


- 1. 导入jstl相关jar包
- 2. 引入标签库：taglib指令：  <%@ taglib %>
- 3. 使用标签

```jsp

<!-- 0、引入JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 1、c:if
    test里面写判断条件，条件为空则c:if内的标签会显示出来。

    c:if没有else的情况，要达成c:else，可以再写一个c:if
 -->
<c:if test="${not empty list}">
    <h1>我是真...</h1>
</c:if>


<!-- 2、c:choose c:when
    其实就是switch case
 -->
<c:choose>
    <c:when test="${number == 1}">星期一</c:when>
    <c:when test="${number == 2}">星期二</c:when>
    
    <c:otherwise>数字输入有误</c:otherwise>
</c:choose>


<!-- 3、c:forEach，
    有两种写法
 -->
<!-- 3.1、普通的for循环
    for(int i = 0; i < 10; i ++){

    } 
    begin ：开始值
    end ：结束值
    var ：临时变量，相当于当前遍历的元素
    step ：步长，每次循环，i=i+step
    varStatus ：循环状态对象
        index ：容器中元素的索引，从0开始
        count ：循环次数，从1开始
-->
<c:forEach begin="1" end="10" var="i" step="2" varStatus="s">
    ${i} <h3>${s.index}<h3> <h4> ${s.count} </h4><br>
</c:forEach>

<!-- 3.2、对象的for循环
    List<User> list;
    for(User user : list){

    }
    var就是临时遍历的元素，相当于user
    items就是要遍历的对象，也就是list
    varStatus跟普通for循环一样。
 -->
<%  List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");

    request.setAttribute("list",list);
%>
<c:forEach items="${list}" var="str" varStatus="s">
    <p>
        ${s.index} ${s.count} ${str}
    </p>
</c:forEach>
```
——————————————————————————————————————————      


## 37.6 三层架构：软件设计架构

- 1. 界面层(表示层)：用户看的得界面。
    - 用户可以通过界面上的组件和服务器进行交互

- 2. 业务逻辑层：处理业务逻辑的。

- 3. 数据访问层：操作数据存储文件。