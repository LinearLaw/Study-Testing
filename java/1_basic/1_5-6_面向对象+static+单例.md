## Vol.05

### 1、面向对象概念
- 面向对象：强调将功能封装进对象   
- 面向过程：强调功能行为   

——————————————————————————————————————————      

### 2、类与对象
面向对象：封装、继承、多态；

- 类：是现实中事物的描述
- 对象：是事物、是实实在在的个体。

```java
// c1会在堆内存中创建出来
Car c1 = new Car();
```
——————————————————————————————————————————      

### 3、成员变量、局部变量

范围：
- 成员变量作用于整个类
- 局部变量作用于语句中。

```java
class Car
{
    String color = "红色";
    void run(){
        System.out.printIn(color);
    }
}
```

- 成员变量在堆内存中
- 局部变量在栈内存中

在本类中创建本类对象

——————————————————————————————————————————      

### 4、匿名对象
```java
new Car().num = 5
```
- 方法一：对象的方法仅需要调用一次时，可以使用匿名对象
- 方法二：将匿名对象作为实参传递；

- 强引用
- 弱引用
- 软引用

——————————————————————————————————————————      

### 5、封装 Encapsulation

为什么要封装？
- 隔离变化

- 便于使用

- 重用性

- 安全性

——————————————————————————————————————————      
两种常见权限
- public 公有，权限很大   

- private 私有   

```java
class Person{
    int age;
    void speak(){}
}

// 有问题，外部可以对内部的age随意改动，
// 但是实际上， 可能age的值，并不是随意设置的，所以就需要对age进行限制
class PersonDemo{
    public static void main(String[] args){
        Person p = new Person();
        p.age = -20;
    }
}

// 安全的写法，此时外部就访问不到age了
private int age;
```
——————————————————————————————————————————      

private 私有，仅在本类中有效。   

一个成员被private修饰后，在类的外部不能直接访问该成员。   
所以，需要对外提供访问该成员的方法，   
**在方法里，可以对访问做出一些限制**。   

私有 ≠ 封装   

——————————————————————————————————————————      

### 3.4、构造函数   

- 1、和类名同名，无需定义返回值类型。   
不可以return，   
- 2、作用：给对象进行初始化 → 只调用一次   

Tips：   
如果类中没有给定构造函数，系统会默认加一个空参数的构造函数。   

```java
class Person
{
    Person()
    {
        System.out.printIn("   ");
    }
}

Person p = new Person(); // 建立对象时，会调用构造函数。
```

——————————————————————————————————————————      

### 3、构造函数可以重载   

```java
Person(){}
Person(String name){}
Person(String name,int age){}

```
练习：构造函数重载；   

——————————————————————————————————————————      

### 4、什么时候定义构造函数？   
需要初始化对象的时候。   

——————————————————————————————————————————      

### 5、构造代码块   

```java
class Person
{
    // 构造代码块没有名字，所有的初始化都会先经过构造代码块。
    // 所以这是一段公共的函数
    {
        // 这里就是构造代码块
    }
    Person(){

    }
}
/**
区别：构造代码块对所有的对象都会执行，
也就是它是所有对象的共性，先于具名构造函数执行。

构造函数只对当前对象执行，根据重载的不同，调用不同的构造函数。
*/
```

——————————————————————————————————————————      

### 6、this关键字   
①、区分局部变量和成员变量   
```java
Person(String name){
    name = name;    // 我咋知道是哪个name？
    this.name = name;   // 所以，就用this来指代当前对象的name
}
```
* this指代哪个对象？   
代表所在函数所属对象的引用。   

谁调用，就指向谁。   

——————————————————————————————————————————      

### 7、多个重载构造函数不可以互相调用   

②、this可以用作重载的构造函数，用来对当前对象进行构造   
用于构造函数的互相调用，作用是初始化当前对象。   
```java
Person(String name){}
Person(String name,int age){
    this(name);     // 相当于调用了重载的Person(name)
    this.age = age;
}
/**
注意：this(name)只能放在第一行，放其他行会报错。
*/
```

——————————————————————————————————————————      

## Vol.06

### 1、static关键字    
多个对象中存在共同的数据。   
把共同的数据取出，进行共享。   

static是一个修饰符，用于修饰成员，成员变量、成员函数。   
```java
class Person
{
    // country不在堆内存中了，被单独提取出来了
    static String country = "CN";
}

Person p = new Person();
Person p1 = new Person();

// p p1共享同一个country
```
但成员被静态修饰后，就多了一种调用方式，    
除了可以被对象调用，还可以直接被类名调用。      

——————————————————————————————————————————      

调用：类名.静态成员
```java
class StaticDemo
{
    public static void main(){
        p.country;
        Person.country; // 可以直接用类名调用
    }
}
```
注意：static是所有实例对象共享的东西，只有一份。   

而非static的特有对象随对象存储，有多份。   

——————————————————————————————————————————      

### 2、方法区
内存中的一个区，方法区，也叫共享区、数据区。   
类中共享数据、共享方法都在里面。   

——————————————————————————————————————————      

### 3、静态特点
- 随着类的加载而加载，
    - Person这个类进内存的时候，static的成员就已经存在了，static成员也叫类变量、静态成员变量。   
    - 而非static成员，也叫成员变量、实例变量，只有在实例化对象的时候才创建。   
    - 随着类的消失而消失，生命周期最长。

- 优先于对象存在   
    - 静态是先存在的，实例对象是后存在的

- 被所有对象所共享   

- 可以直接被类名所调用   

——————————————————————————————————————————      

### 4、实例变量和类变量的区别
- 存放位置：
    - 类变量随着类的加载存在方法区中，随类的消失而消失。
    - 实例变量随着对象的建立存在于堆内存中

- 类变量
    - 类变量生命周期最长，随着类的消失而消失。
    - 实例变量的生命周期随着实例对象的消失而消失。

——————————————————————————————————————————      

### 5、静态使用注意事项
- 静态修饰了一个类中的方法成员，方法里面不能访问非静态的成员。
```java
class Person
{
    String name;
    static String country = "CN"
    public static void show(){
        System.out.printIn(name);   // 此句报错，因为name是非静态成员。
    }
    public void speak(){
        System.out.printIn(country); // 不报错，非静态方法可以用静态成员
    }
}
```
- 静态方法不能用this、super关键字
    - 因为静态成员先于对象存在，所以静态方法不能有this

——————————————————————————————————————————      

静态方法有利有弊   
- 优点：
    - 对对象的共享数据进行单独空间的存储，减少了内存使用，共享数据没有必要每个对象都存一份
    - 可以直接用类调用静态方法。

- 弊端：
    - 生命周期过长
    - 访问有局限性（静态方法只能访问静态成员）


> 注意：主函数就是静态的。   

——————————————————————————————————————————      

### 6、主函数main
主函数是一个特殊的函数，是程序的入口，可以被jvm调用。   
主函数的定义：
- public：代表函数访问的权限是最大的；   

- static：代表主函数在类加载的时候就已经存在了。 

- void：对主函数没有具体的返回值；   

- main：不是关键字，但是是一个特殊单词，这个main可以被jvm识别。    

- 函数的参数：String[] args，是存储字符串类型的数组。

- 主函数具有固定格式，jvm可以识别，只有一个args形参名可以改，其他都不能改哦。

```java
class MainDemo
{
    // main还可以重载，但是只能识别String的参数
    // args可以用其他名字。
    public static void main(String[] args)
    {

    }
}
```

——————————————————————————————————————————      

* 虚拟机调用main，传的是啥值？   
```java
class MainDemo
{
    public static void main(String[] args)
    {
        System.out.printIn(args); // 打印一个地址
        System.out.printIn(args.length); // 打印 0 
    }
}
```
jvm在调用主函数时，传入的是一个new String[0]，

```cmd
java MainDemo haha heihei 123

# 此时传输main的args为["haha","heihei","123"]
```

```java
// 不同的类之间的主函数可以互相调用。
// 实际开发不会这么写，注意。
class MainDemo
{
    public static void main(String[] args)
    {
        MainTest.main(args);
    }
}
class MainTest
{
    public static void main(String[] args)
    {
        for(int x=0;x<args.length;x++){
            System.out.printIn(args[x]);
        }
    }
}
```

——————————————————————————————————————————      

### 7、注释生成文档
```
set classpath=.;c/myclass
```

.class文件看不到里面的代码，里面都是二进制。   

注意，要生成文档，要求类是public修饰。   
并且，最好要static修饰。   

```java
/**
 * 类的说明
 * @author xxx
 * @version v1.1
 */
public class ArrayTool{
    private ArrayTool(){}

    /**
    这里写说明：求数组最大值
    @param arr 接收一个int类型数组
    @return 返回数组最大值
    */
    public static int getMax(int[] arr){

    }

    /**
    求数组最小值
    @param arr 接收一个int类型数组
    @return 返回数组最小值
    */
    public static int getMin(int[] arr){}
}
```

接着用工具对注释进行提取，javadoc.exe命令

```cmd
javadoc -d ./myhelp -author -version  ArrayTool.java
```

使用javadoc命令，会生成一堆网页，从index.html可以看到。   
public会被生成文档，但是private不会被提取。   

一个类中默认会有一个空参数的构造函数，   
这个默认的构造函数和所属类是一致，   
如果类是public，构造函数也是public，   
如果类是private，构造函数也是private。   

API帮助文档。   
java也自带文档。   

——————————————————————————————————————————      

### 8、静态代码块

格式：
```java
static{
    // 这里写静态代码块中的语句。
}
```

特点：   
随着类的加载而执行，只执行一次。   
用于给类进行初始化。   

静态代码块里面只能访问静态成员。    

```java
class staticCode {
    static{
        System.out.printIn("a");
    }
}
```
```java
class StaticDemo{
    static{
        System.out.printIn("b");
    }
    public static void main(){
        new StaticCode();   // 此时类加载到内存
        new StaticCode();   // 前一句已经加载了，现在不需要再加载
        System.out.printIn("over");
    }
    static{
        System.out.printIn("c")
    }
}
// b c a over
```

> 注意：   
> 如果对象是null，此时类依然不会被加载。   
> 实际用到了类的时候才会加载。   

```java
StaticCode s = null; // 此时类不会加载，static不会执行。 
```

——————————————————————————————————————————      

### 6.9、对象初始化的过程

注意区分构造代码块和静态代码块

```java
Person p = new Person()
```

- new命令执行， Person.class类文件加载到内存，开辟了堆内存空间。

- 静态代码块执行，给类进行初始化。

- 开辟空间，分配内存地址

- 在堆内存中，分配对象private，public成员变量的堆内存空间，此时成员变量是默认值。

- 对属性进行显示初始化。

- 对对象进行构造代码块初始化。

- 执行构造函数，给成员变量赋值，对象初始化。

- 将内存地址赋值给栈内存的p变量

——————————————————————————————————————————      

### 6.10、对象调用成员的过程

```java
Person p = new Person();
p.setName("lisi");
```
——————————————————————————————————————————      

- 堆区
- 栈区
- 方法区：放的都是方法，public private都会放到这里，static的也会放到这里。

——————————————————————————————————————————      

static方法用Person调用，也就是当前的类。   
非静态方法用对象this调用。   

——————————————————————————————————————————      

### 6.11、单例设计模式

对某一类问题常用的规律，形成了模式，这就是设计模式。   

GOF。   

纯偏思想，不是代码。   
如果没有真实业务需求，很枯燥。   

设计模式：解决某一类问题最行之有效的方法。   
java中有23种设计模式。   

——————————————————————————————————————————      

单例设计模式：   
解决一个类在内存中只存在一个对象的问题。   
有些时候，会要求这个类，只能有一个对象，不能有更多的，就可以用单例设计模式。  

——————————————————————————————————————————      


**第一种方式**。   

怎么保证只有一个对象？   
1、为了避免其他程序过多建立该类对象，先禁止其他程序禁止建立该类对象   
2、为了让其他程序可以访问到该类对象，只好在本类中自定一个对象。   
3、为了方便其他程序对自定义对象的访问可以对外提供一些访问方式。   


怎么用代码体现？   
1、把构造函数private，私有化
2、在类中国创建一个本类对象；   
3、提供一个方法可以获取到该对象   

——————————————————————————————————————————      

对于事物该怎么描述，还是怎么描述；   
当需要将该事物的对象在内存中保持唯一时，就可以用单例设计模式。   

```java
class Single{
    private Single(){}

    // 创建一个唯一的实例
    private static Single s = new Single();

    // 获取实例的方法
    public static Single getInstance(){
        return s;
    }
}

class SingleDemo{
    public static void main(){
        System.out.printIn("hallo world");

        // 通过类名调用静态方法，获取到这个唯一的对象。
        Single ss = Single.getInstance();

        // 即使再次调用静态方法，此时的s1和ss指向的是同一个对象。
        Single s1 = Single.getInstance();
    }
}
```

——————————————————————————————————————————      


单例设计模式的**第二种方式**。   

第一种模式是先初始化对象，饿汉式。   
Single类一进入内存，对象就已经创建好了。   
一般开发用饿汉式，安全，简单。   


第二种模式是后初始化对象，懒汉式。   
是一种延迟加载，只有调用了方法的时候，才会创建对象。   
如果多个程序都用这个东西，可能会有问题。   
CPU执行多任务，乱序执行，可能会让下面那个if执行多次。   


```java
class Single{
    private static Single s = null;
    private Single();

    public static Single getInstance(){
        if(s === null){
            s = new Single()
        }
        return s;
    }
}
```
——————————————————————————————————————————     