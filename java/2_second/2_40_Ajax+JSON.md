# Vol.40 

## 40.1 AJAX
## 40.2 jQuery

没啥好讲的。    
这不就是我大前端的基本功么。

注意一下原生实现ajax，容易忘。  

——————————————————————————————————————————      

## 40.3 JSON

JavaScript Object Notation，JavaScript对象表示法。

* json现在多用于存储和交换文本信息的语法
* 进行数据的传输
* JSON 比 XML 更小、更快，更易解析。

——————————————————————————————————————————      

JSON常见的解析器

- Jsonlib ：官方提供

- Gson ：谷歌

- fastjson ：阿里，据说有安全性问题

- jackson ：springMVC内置的解析器。

——————————————————————————————————————————      

- Java对象 转 JSON ，使用ObjectMapper
    - 注解
        - @JsonIgnore : 排除属性。
        - @JsonFormat : 属性值的格式化
```java
public class Person {

    private String name;
    private int age ;

    /**
     * 如果有一些属性，不希望转成json对象呈递出去，
     * 可以加一个注解 @JsonIgnore
     */
    @JsonIgnore
    private String gender;

    /**
     * 如果想要对Date对象进行序列化，
     * 可以使用注解 @JsonFormat
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Date getBirthday() {return birthday;}
    public void setBirthday(Date birthday) { this.birthday = birthday;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}

```
```java
Person p = new Person();
p.setName("张三");
p.setAge(33);
p.setGender("男");

/* 1、使用ObjectMapper来完成转换 */
ObjectMapper mapper = new ObjectMapper();

/* 2、使用 writeValueAsStrging 即可得到json字符串 */
String json = mapper.writeValueAsStrging(p);

/* 
    3、writeValue方法，
    可以将对象转json字符串，同时传入一个可以输出的流中。
    参数一：
        - File
        - Writer
        - OutputStream
    参数二：一个java对象
 */
mapper.writeValue(new FileWriter("./a.txt"),p);

/**
 * 另外，List和Map，
 *      如果是一个List集合，转成json，会变成js数组
 *      如果是一个Map集合，转成json，会变成js对象
 */ 
```
——————————————————————————————————————————      

- JSON 转 Java对象

```java
/* 1、初始化JSON字符串 */
String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":23}";

/* 2、创建ObjectMapper对象 */
ObjectMapper mapper = new ObjectMapper();

/* 3、转换为Java对象 Person对象
        使用readValue方法
 */
Person person = mapper.readValue(json, Person.class);
```
——————————————————————————————————————————      

```java
/* 如果 Servlet 的 response 想要返回一个json */

// 1、先设置响应的 header
response.setContentType("application/json;charset=utf-8");

// 2、创建一个Map对象，存入要返回的那些值
Map<String,Object> map = new HashMap<String,Object>();
map.put("userExist",false);
map.put("msg","not exist");

// 3、利用writeValue，将结果返回
ObjectMapper mapper = new ObjectMapper();
mapper.writeValue(response.getWriter(), map);
```
——————————————————————————————————————————      