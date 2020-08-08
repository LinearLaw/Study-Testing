# Vol.20

## 20.1、 File类

File类用来操作文件或文件夹。

Stream也可以操作文件，但是只能操作数据，不能修改文件属性，也不能操作文件夹。

```java

File f = new File("a.txt");

// 第一个参数是目录，第二个参数是文件，
// 组合起来就是 ./abc/b.txt
File f = new File("./abc","./b.txt");

// 也可以分开来写
File f1 = new File("./abc");
File f2 = new File(f1,"c.txt");

f.toString();   // 返回构造时传入的路径。

/*
    在windows和linux下，文件夹的分隔符是不一样的
    windows 是 \abc\dddd\sad
    linux 是 /abc/dddd/sad
    
    为了兼容分隔符，
    File提供了一个static的成员，叫separator
 */
File f4 = new File("c:" + File.separator + "abc");
```

——————————————————————————————————————————      

## 20.2、File类的常用方法

```java
File f = new File("a.txt");

// 1、创建
// 此时会在硬盘上创建真实的 文件
f.createNewFile();  

// 此时会在硬盘上创建真实的 文件夹
f.mkdir();  

// 此时可以创建一个 前缀名.后缀名 的临时文件
f.createTempFile("前缀名","后缀名");

//——————————————————————————————————

// 2、删除
f.delete();
// 在文件可删除的时候删除
f.deleteOnExit();

//——————————————————————————————————

// 3、判断
// 是否可执行
f.canExecute();
// 是否可写
f.canWrite();
// 两个文件比较大小
f1.compareTo(f2);
// 文件是否存在
f.exists();

// 对于一个文件或文件夹，先要判断是否存在，再判断是文件还是文件夹
f.isDirectory();
f.isFile();

// 是否是绝对路径，true=绝对
f.isAbsolute();

// 文件是否是隐藏文件
f.isHidden();
```
——————————————————————————————————————————      

```java
File dir = new File("fsdmfk\\werwe");
dir.mkdir();
```
——————————————————————————————————————————      

```java
File f = new File("a.txt");

// 创建了一个 a.txt 的文件
f.createNewFile();      

// 这里创建了一个 /a.txt 的文件夹
f.mkdir();
```
——————————————————————————————————————————      

```java
// 4、获取信息
f.getName();

// 返回构造时传入的路径
f.getPath();

// 获取父级文件夹，相对路径的时候会返回null
f.getParent();

// 获取一个File对象，File对象里面有绝对路径
f.getAbsolutePath();

// 获取最后一次修改的时间
long time = f.lastModified();

long len = f.length();

f1.removeTo(f2);

// ——————————————— 以下方法，f必须是目录，且必须存在 —————————————————
// 返回一个File数组，包括文件，文件夹。
f.listRoots();
// 返回文件对象数组，仅文件
f.listFiles();  

// 返回名称的数组。
f.list();

// 还可以传入一个过滤器
FilenameFilter fnf = new FilenameFilter(){
    public boolean accemt(File dir, String name){
        return name.endsWith(".bmp");
    }
}
f.list(fnf);
```

——————————————————————————————————————————      

## 20.3、删除带内容的目录

从里往外删。

```java
import java.io.*;
class RemoveDir{
    public static void main(String[] args){
        File dir = new File("d:\\testdir");
        removeDir(dir);
    }
    public static void removeDir(File dir){
        File[] files = dir.listFiles();

        for(int x = 0;x<files.length;x++){
            if(files[x].isDirectory()){
                retmoveDir(files[x]);
            }else{
                System.out.println(files[x].toString() + "--file--" +files[x].delete());
            }
        }

        System.out.println(dir + "--dir--" + dir.delete());
    }
}
```
——————————————————————————————————————————      

## 20.4、Property对象

Property是HashTable的子类，具备map集合的特点，  
是集合中，和io融合的集合容器。  

用于以键值对形式，配置文件 → .ini 文件

```java
Properties prop = new Properties();
FileInputStream fis = new FileInputStream("info.txt");

// 加载配置文件
prop.load(fis);
// 设置某一个配置键和值
prop.setProperty("xxx","100");

// 创建一个文件写入流
FileOutputStream fos = new FileOutputStream("info.txt");
// 保存当前的配置，第二个参数是当前保存的comment，
// 第二参数会作为当前配置的注释存在
prop.store(fos,"haha");

// 将属性列表打印到指定的输出流，
// System.out是一个控制台输出流，所以本句会在控制台打印配置信息
prop.list(System.out);

// 关闭写入流、读取流
fos.close();
fis.close();

// 获取当前的key值数组
Set<String> names = prop.stringPropertyNames();
for(String s:names){
    System.out.println(s+":"+prop.getProperty(s));
}
```

——————————————————————————————————————————      

## 20.5、打印流


- PrintStream ：字节打印
    - 构造函数可以接收
        - 1、File对象   File
        - 2、字符串路径 String
        - 3、字节输出流 OutputStream

- PrintWriter ：字符打印
    - 构造函数可以接收
        - 1、File对象 File
        - 2、字符串路径 String
        - 3、字节输出流 OutputStream
        - 4、字符输出流 Writer

——————————————————————————————————————————      

## 20.6、序列流

SequenceInputStream ，可以串联流，将多个流合并成一个流。

例如，三个文件合并到一个文件。
```java
import java.io.*;
import java.util.*;

class SequenceDemo{
    public static void main(String[] args)throws IOException{
        Vector<FileInputStream> v = new Vector<FileInputStream>();

        // 创建三个输入流
        v.add(new FileInputStream("c:\\1.txt"));
        v.add(new FileInputStream("c:\\2.txt"));
        v.add(new FileInputStream("c:\\3.txt"));

        // 枚举三个输入流
        Enumeration<FileInputStream> en = v.elements();

        // 将三个输入流传入到序列流中
        SequenceInputStream sis = new SequenceInputStream(en);

        // 输出流
        FileOutputStream fos = new FileOutputStream("c:\\4.txt");

        byte[] buf = new byte[1024];
        int len = 0;
        while((len = sis.read(buf)) != -1){
            fos.write(buf,0,len);
        }

        fos.close();
		sis.close();
    }
}
```
——————————————————————————————————————————      

## 20.7、切割文件

splitFile();