# Vol.18 

## 18.1、 System对象

System对象，里面的方法，属性都是静态的。
- out ：  标准输出，输出到控制台

- in  ：  标准输入，从键盘录入

- gc() ：   运行垃圾回收器。

- properties/getProperties();

可以用map方法取出集合中的元素。
Properties都是字符串。

```java
// 给系统设定信息
System.setProperty("key","value");
// 获取系统设定的属性
String val = System.getProperty("key");
```
有啥用？
启动虚拟机的时候可以设定property的值，
```cmd
java -D hhh=dd JavaDemo
```
——————————————————————————————————————————      

## 18.2、Runtime

每一个java程序都有一个Runtime类实例。
使用单例设计模式，向系统提供这个唯一的Runtime实例。

```java
// 获取这个Runtime实例
Runtime a = Runtime.getRuntime();

// 1、Process类没有子类。
// 2、本句可能会抛异常，所以需要throws Exception。
// 3、exec用于执行一条cmd命令
Process p = r.exec("java aaa"); 

Thread.sleep(4000);

p.destroy();
```
——————————————————————————————————————————      

## 18.3、Date

```java
import java.util.*;
import java.text.*;

Date d = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String d = sdf.format(d);
```

——————————————————————————————————————————      

## 18.4、Calendar

```java
// 获取一个日历
Calendar c = Calendar.getInstance();

// 获取年份
int year = Calendar.get(Calendar.YEAR) - 1900;

int day = c.get(Calendar.DAY_OF_MONTH);
int month = c.get(Calendar.MONTH);

c.set(年,月,日);

// 参数一是type，参数二是偏移量
c.add(Calendar.DAY_OF_MONTH,-1);
```
> 练习：    
> 传入任意年份，获取二月的天数。    
> 获取昨天的现在时刻    

——————————————————————————————————————————      

## 18.5、Math

```java
import java.util.*;

// 向上取整
Math.ceil(16.34);
// 向下取整
Math.floor(12.3);

// 四舍五入
Math.round(12.56);

// 获取2的3次方
Math.pow(2,3);

// 获取 0 到 1 的随机数，左闭右开
Math.random();

Random r = new Random();
int d = r.nextInt(10)+1;
int e = r.nextDouble(10);
```
——————————————————————————————————————————      

## 18.6、IO流

用来处理数据传输
- 字节流：
    - InputStream
    - OutputStream
    
- 字符流
    - Reader
    - Writer

> Tips：    
> 从字节流和字符流派生出的子类，都以他们为尾名称    

- 输入流
- 输出流

```java
// 在硬盘上，创建一个文件，并写入一些文字数据。
FileWriter fw = new FileWriter("demo.txt");

// write写入之后，硬盘中的文件不一定就写入了，此时只是写入到了内存。
fw.write("abafdf");

// 将改动更新到硬盘中的文件。
fw.flush();

// 关闭流，close方法会先flush，再关闭流。
fw.close();
```
> Tips:     
> 使用文件的FileWrite，函数上要声明throws IOException，     
> 或者try catch。       

——————————————————————————————————————————      

## 18.7、如何处理IO异常
```java

FileWriter fw = null;
try{
    // 创建demo.txt有可能会失败，此时会IOException
    fw = new FileWriter("demo.txt");
    fw.write("abacdfs");
}catch(IOException e){

}finally{
    try{
        // 如果fw已经成功创建，在出错时，需要将写入流资源关闭
        if(fw != null){
            fw.close();
        }
    }catch(IOException e){
        System.out.println(e.toString());
    }
}
```
——————————————————————————————————————————      

## 18.8、数据读写

FileWriter
- write()

FileReader
- read()

——————————————————————————————————————————      

1、写
```java
// 第二参数传入true，则默认写入文件末尾，不传true则为覆盖。
FileWriter fw = new FileWriter("demo.txt",true);
fw.write("123");
fw.close();
```
——————————————————————————————————————————      

2、读
```java
FileReader fr = new FileReader("demo.txt");
int ch = 0;

// fr.read()，一次读一个字符，自动往下一个读，
// 返回的是数字 0-65535，-1则为文件末尾
while((ch = fr.read())!=-1){

}
```
——————————————————————————————————————————      
```java
char[] buf = new char[1024];

FileReader fr = new FileReader("demo.txt");

int num = 0;

// read方法传入一个char数组，将字符读入到数组，返回读到的字符个数
while((num=fr.read(buf))!=-1){
    
}
fw.close();
```