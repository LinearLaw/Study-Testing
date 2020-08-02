# Vol.13 String

## 13.1、String

```java
// str 是内存中的一个对象
String str = "abc"; 

// str1 是内存中的另一个对象
String str1 = new String("abc");

// 本式子返回false
str == str1;   
```

String 类由final修饰，里面的内部方法不可以复写。
字符串一旦初始化，就不能改变。

——————————————————————————————————————————      
```java
// equals 比较的是两个String 是否相同，而不是比较地址。
s1.equals(s2); 
```
——————————————————————————————————————————      

## 13.2、字符串方法
——————————————————————————————————————————      
```java
String s1 = "abc";
String s2 = new String("abc");
String s3 = "abc";

s1==s2; // false
s1==s3; // true

// "abc"存放到了常量池，所以s1==s3。
```
——————————————————————————————————————————      

String 是描述字符串事物，提供了多个方法对字符串操作

——————————————————————————————————————————      

### 1、获取
- str.length();     

- str.charAt(index);    获取索引处的字符

- str.indexOf(str1);    返回str1在str中的索引，如果没有返回-1
    - 也可以传一个ASCII码值，返回字符在str中第一次出现的位置

- str.indexOf(int ch, int fromIndex);

- str.lastIndexOf(int ch);  反向索引，从后往前找。
    - 注意，查找是从后往前，但是返回的是正向索引。

```java
// 比较常用的判断条件
str.indexOf(str1) != -1;
```
——————————————————————————————————————————      

### 2、判断

- boolean startsWith(str)   是否以str开头

- boolean endsWith(str)     是否以str结尾

- boolean isEmpty()         是否为空

- boolean contains(str)     判断str是否包含str1这个字符串

- boolean equalsIgnoreCase()    忽略大小写判断相同

——————————————————————————————————————————      

### 3、转换

**字符数组转字符串**

```java
// 可以在构造的时候传入
new String(char[] strArr, offset, count)
```

静态方法
- String.copyValueOf(char[] strArr)

- String.copyValueOf(char[] strArr, offset, count)

- String.valueOf(char[] strArr)

——————————————————————————————————————————      

字符串转字符数组
- str.toCharArray()   返回一个字符数组

字节数组转字符串
- new String(byte[] bArr)
- new String(byte[] bArr, offset, count)

字符串转字节数组
- str.getBytes()      返回一个字节数组

将某种基本类型转字符串
- String.valueOf(int a)     返回字符串
- String.valueOf(double d)  返回字符串

——————————————————————————————————————————      
### 4、切割，替换

——————————————————————————————————————————      
```java
// replace 替换某一段字符串
String replace(oldchar, newchar)
String s = s.replace("java","");

// split，将字符串按指定的字符进行切割
s.split(regex)
String[] arr = s.split(",");
```
——————————————————————————————————————————      
```java
// 获取str内的某一段。
String a = str.subString(start,end);

// 转成大写
String b = str.toUpperCase();

// 转成小写
String c = str.toLowerCase();

// 比较，返回str和str2的unicode的差值，0=两个字符串相等
int n = str.compareTo(str2);

// 去除前后空格
String d = str.trim();
```
——————————————————————————————————————————      

## 13.3、StringBuffer

字符串缓冲区，可以修改里面的内容，是一个容器。

- 长度可变
- 可以按字节操作多个数据类型
- 最终可以通过toString方法变成字符串

```java
StringBuffer s1 = new StringBuffer();

// 将int类型的数据加入到StringBuffer
s1.append(123);

// 从索引index处插入数据data
s1.insert(index,data);

s1.delete(start,end);
s1.deleteCharAt(index);

// 返回指定索引处的字符
s1.charAt(index);   

// 返回str在s1处的索引
s1.indexOf(String str);

s1.lastIndexOf(String str);

s1.length();

// 获取字符串的某一段，
s1.subString(start,end);

s1.replace(start, end, string);

// 设置指定索引处的字符
s1.setCharAt(int index, char ch);
```

两种类
- StringBuilder → 线程不同步，效率高
- StringBuffer → 线程统统不

StringBuilder 的方法和 StringBuffer 一样

——————————————————————————————————————————      

## 13.4、基本数据类型的对象包装类

- byte → Byte

- short → Short

- int → Integer

- long → Long

- boolean → Boolean

- float → Float

- double → Double

- char → Character

——————————————————————————————————————————      

```java
// 获取最大值
Integer.MAX_VALUE
```
——————————————————————————————————————————      

- 基本数据类型 + "" → 就会转成字符串
- 基本数据类型.toString(data)
    - Integer.toString(123) → "123"

——————————————————————————————————————————      
```java
int a = Integer.parseInt("123");

double b = Double.parseDouble("123.444");

boolean b = Boolean.parseBoolean("true");

// 字符串转int对象
Integer i = new Integer("123");
// int对象转int值
int num = i.intValue();
```
——————————————————————————————————————————      

十进制转其他进制
```java
Integer.toBinaryString(123);    // 二进制字符串

Integer.toHexString(444);   //十六进制字符串

Integer.toOctalString(555); // 八进制字符串
```

——————————————————————————————————————————      

其他进制转十进制
```java
Integer.parseInt(string, raidix);

// 下面两个式子等价
Integer x = 4;
Integer x = new Integer(4);

// 下面两个式子等价
x = x+1;
x = x.intValue() + 1;
```
——————————————————————————————————————————      