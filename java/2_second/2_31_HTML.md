# Vol.31 HTML

## 30.1 HTML

HTML这个我熟啊！    
不写了。

## 30.2 CSS

没啥好写的。

## 30.3 JS

JavaScript = ECMAScript + JavaScript自己特有的东西（eg:BOM、DOM）

- DOM : Document Object Model 文档对象模型
    - 核心对象：
        - Document：文档对象
	    - Element：元素对象
	    - Attribute：属性对象
	    - Text：文本对象
	    - Comment:注释对象

    - XML DOM - 针对 XML 文档的标准模型
    - HTML DOM - 针对 HTML 文档的标准模型

——————————————————————————————————————————      

- BOM : Browser Object Model 浏览器对象模型
    - Window：窗口对象
	- Navigator：浏览器对象
	- Screen：显示器屏幕对象
	- History：历史记录对象
	- Location：地址栏对象

——————————————————————————————————————————      

## 30.4 XML

XML，Extensible Markup Language 可扩展标记语言

- xml与html的区别
	- 1、 xml标签都是自定义的，html标签是预定义。
	- 2、 xml的语法严格，html语法松散
	- 3、 xml是存储数据的，html是展示数据

——————————————————————————————————————————      

- 解析：操作xml文档，将文档中的数据读取到内存中   

	* 操作xml文档
		1. 解析(读取)：将文档中的数据读取到内存中
		2. 写入：将内存中的数据保存到xml文档中。持久化的存储

	* 解析xml的方式：
		1. DOM：将标记语言文档一次性加载进内存，在内存中形成一颗dom树
			* 优点：操作方便，可以对文档进行CRUD的所有操作
			* 缺点：占内存

		2. SAX：逐行读取，基于事件驱动的。
			* 优点：不占内存。
			* 缺点：只能读取，不能增删改

- xml常见的解析器：
	- 1、 JAXP：sun公司提供的解析器，支持dom和sax两种思想

	- 2、 DOM4J：一款非常优秀的解析器

	- 3、 Jsoup：jsoup 是一款Java 的HTML解析器，    
        可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，    
        可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。  

	- 4、 PULL：Android操作系统内置的解析器，sax方式的。

——————————————————————————————————————————      

* Jsoup：jsoup 是一款Java 的HTML解析器，    
    可直接解析某个URL地址、HTML文本内容。   
    它提供了一套非常省力的API，     
    可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。  
    
	* 快速入门：
		* 步骤：
			1. 导入jar包
			2. 获取Document对象
			3. 获取对应的标签Element对象
			4. 获取数据

	* 代码：
```java
// 2.1获取student.xml的path
String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();

// 2.2解析xml文档，加载文档进内存，获取dom树--->Document
Document document = Jsoup.parse(new File(path), "utf-8");

// 3.获取元素对象 Element
Elements elements = document.getElementsByTag("name");
System.out.println(elements.size());

// 3.1获取第一个name的Element对象
Element element = elements.get(0);

// 3.2获取数据
String name = element.text();
System.out.println(name);
```
