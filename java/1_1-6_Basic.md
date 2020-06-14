# JAVA

## Vol.01

### 1、Dos命令行
- dir   ：列出当前目录和文件夹
- md    ：创建目录
- rd    ：删除目录 → 要求文件夹为空
- cd    ：进入指定目录
- cd .. ：
- cd /  ：
- del   ：删除文件，可以删除所有的文件，
```
del *.txt   // 删除所有的txt后缀的文件
del op      // 删除文件夹
```
- exit  ：退出dos命令行   


### 2、SUN公司，1995年推出 JAVA
- JAVA EE   ：web应用程序
- JAVA SE   ：桌面应用程序 → 基础
- JAVA ME   ：嵌入式应用程序

具备跨平台性：利用JAVA虚拟机
- windows JVM
- linux JVM
- MacOS JVM


### 3、搭建环境
- JRE：java运行环境 → 可以运行程序
- JDK：java开发工具包，利用java.exe，jar.exe，   
用于开发程序，   
其中，JDK是自带JRE的。   


- www.oracle.com
- java.sun.com


### 4、JDK的bin目录，里面都是开发工具，放进path里。   
javac就在里面。   

winmine → 扫雷   

系统变量 → 从上往下找程序。   

> 注意：   
> 降低修改环境变量的风险，可以自定义一个环境变量。   
> 例如JAVA_HOME，path=%JAVA_HOME%，   
> path和命令行有关。   

### 5、set → 可以设置环境变量的值。
```
set path=.;xxx/xxx      //只会在当前目录有效果
```

### 6、
```java
// 123.java
class Demo{

}
```
用javac.exe可以将123.java编译成.class文件，class后缀的文件是二进制文件     
123.java → Demo.class       

用java.exe运行class文件
```
java Demo       // 可以不写.class
```

> 注意：  
> java要能运行目标文件，要求要有main函数，否则会报错。


```java
class Demo{
    public static void main (String[] args){
        // 这就是Demo类的主函数
    }
}
```

### 7、classpath，用于指定java依赖包的目录
```
java Demo.class // 和java有关
```
```
set classpath=D:/路径   // 注意，临时配置的时候可以不加分号
```
命令java Demo.class
- 找到classpath目录下，寻找Demo.class文件，也就是先找classpath目录
- 再找当前目录，找Demo.class文件

> 注意：      
> 当前目录不需要classpath，       
> 要用到包的时候才需要classpath。

### 8、
```java
class Demo{
    public static void main(String[] args){
        System.out.printIn("hello java");
    }
}
```

main函数是java程序的入口，会被JVM调用，有main函数的类可以单独运行。

> 面试题：java程序中是否只能有一个main函数     
>      
> 答：   
> 可以有多个！   
> 答案就是重载，main函数也是函数，所以可以重载。    


重载：   
overload，表示一个类中可以有多个名称相同的方法，   
重载的多个函数要求的参数不同，参数列表不同

重写：   
override，用于覆盖一个方法，对其进行重写。   

> **注意**：   
> 不是所有的类都需要main函数。   


## Vol.02

- 关键字
- 标识符
- 注释
- 常量
- 变量
- 运算符
- 语句
- 函数
- 数组


### 1、常量：不能改变的值

- 整数、小数、布尔（true、false）
- 字符常量：单引号''
- 字符串：双引号""
- null常量：null

### 2、整数
十进制、八进制、十六进制、二进制

编码表ASCII码，7位，   

进制转换：
- 整数：除基取余法
- 小数：乘积取整法


### 3、负数的二进制

6 → 110 → 0000 0000 0000 0000 0000 0000 0000 0110   
-6，就是110取反+1

> 其实就是补码的规则。   


### 4、变量：在内存中开辟一块空间

数据类型：
- 基础数据类型
    - 数值
        - 整数类型：byte（-128 ~ +127）、short（用的很少）、int、long
        - 浮点类型：float、double
    - 字符型char
    - 布尔型boolean

- 引用数据类型
    - 类 class
    - 接口 interface
    - 数组 []

### 5、类型转换

```java
byte b = 3;
b = b+2;    

// 报错，b+2的结果是一个int型，而b是byte型
// 一个byte型的变量无法接收一个int型的数据
```

```java
byte b = 3;
b = (byte)(b+2);    // 强制类型转换，这样不会报错
```

```java
char ch = 'a';
ch = ch + 1;        // 得到的是ASCII码值+1的结果，也即是'b'

```

### 6、运算符：算数运算符，赋值运算符、比较运算符、逻辑运算符、位运算符、三元运算符

> 注意：   
> 1%-5  -------  得到1   
> -1%5  -------  得到-1 

> 规律：      
> 左 < 右，结果为左         
> 左 = 右，结果为0   


### 7、转义字符

- \n
```java
System.out.print();     // 不带换行
System.out.printIn();   // 带换行
```

- \b：退格，相当于backspace
- \r：相当于按下回车
- \t：制表符，相当于一个tab键

> linux，换行符是一个字符 \n     
> windows，里面\r\n换   
> dos系统，\n    


### 8、赋值运算符：比较不常见的就一个 %=

### 9、逻辑运算符

- & ：与
- | ：或
- ^ ：异或，相同false，不同为true
- ! ：
- &&    ：短路运算
- ||    ：短路运算

### 10、位运算符

- <<       
```
3 << 2 = 12 相当于是 3*2*2 = 12
```

- \>\>
```
3 >> 1 = 1 相当于是3/2 = 1
```

- \>\>\>  无符号右移
```
3 >>> 1 = 1 相当于 3/2 = 1
```

- & 与运算
```
6 & 3 = 2 ，全1为真，其余得假
```

- | ：按位取或

- ^ ：按位取异或

- ~ ：按位取反


面试题：如何不适用第三个变量交换两个变量的值？
```java
n = n + m;
m = n - m;
n = n - m;

// 有缺点，第一句可能会溢出
```
```java
n = n ^ m;
m = n ^ m;
n = n ^ m;

// 本算法不会溢出
```

### 11、switch：适合用在具体值的判断   

> **注意**：   
> case是没有顺序的，   
> default无论放到哪里，都会在最后执行。   
> 
> **顺序**：   
> 先匹配switch里面的case，   
> 如果到了default，而default里面没有break，      
> 那么就会把default下面的语句，能执行完的都会被执行完。

## Vol.03

### 1、while、do while、for

### 2、break、continue   
流程控制，      
break跳出循环，循环结束，   
- 可以单独存在，用在循环体中（break、continue）   
- 选择switch（break）

continue，本次循环结束，进入下一次循环。


### 3、函数

```java
public static void main(){

}
```

- public static 修饰符，static是静态，public是公共
- void 返回值类型，如果函数里面没有return，void类型不会报错；

### 4、重载 overload

同一个类中，可以存在同名函数，但是要求参数的个数、类型不同。   

> 注意：   
> 返回值的类型不会影响重载，   
> 也就是说，两个函数，其他都一样，就返回值类型不一样，这不叫重载。


### 5、数组

```java
int[] arr = new int[5];

int[] arr = new int[]{3,4,5,6};

int[] arr = {3,4,5,6};

int arr[] = new int[2];

int []arr = new int[]{12,3,3,4,5};  // 静态初始化。   

int[] arr = new int[5]
```

### 6、操作数组可能出现的问题

运行时，会在堆空间开辟空间给数组。

- 数组越界，ArrayIndexOutofBoundException
- 空指针异常，NullPointerException

数组的操作：获取数组中的元素，arr[0]，arr.length

> 练习：   
> 1、获取数组中的最大值   
> 2、给数组进行排序：选择排序、冒泡排序   

```java
import java.util.*
Arrays.sort(arr);

public void static swap(int a , int b){

}
```

> 练习：   
> 1、简单查找，折半查找   
> 2、进制转换   

十进制 → 二进制
```java
StringBuffer sb = new StringBuffer();
sb.append(num);
System.out.printIn(sb.reverse());
```

十进制 → 十六进制
```java
num & 15  // 和15做与运算，得到数值

n1 - 10 + 'a'   // 超过了10，转成十六进制字符abcdef

int temp = n1>>> 4  // 右移四位计算下一位的十六进制

```

更方便的算法：查表法   
用一个数组来存十六个字符
```java
char[] chs = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
```

> 练习：使用查表法的进制转化   

### 7、二维数组

```
int [][]arr = new int[3][2];
arr[0] 是一个内存地址
arr[0][1] 是数组元素

int [][]arr = new int[3][]; // 外部数组已经初始化，内部数组没有初始化
int [][]arr = {{3,2,55,2},{2,3,4},{6,7,1,8}};

int []x;
int x[];

int [][]y;
int y[][];
int []y[];
```

> 注意：      
> int []x,y[];   
> 这个的结果，x是一位数组，y是二维数组。   



## Vol.05

### 1、
- 面向对象：强调将功能封装进对象   
- 面向过程：强调功能行为   

### 2、类与对象
面向对象：封装、继承、多态；

- 类：是现实中事物的描述
- 对象：是事物、是实实在在的个体。

```java
// c1会在堆内存中创建出来
Car c1 = new Car();
```

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


### 4、匿名对象
```java
new Car().num = 5
```
- 方法一：对象的方法仅需要调用一次时，可以使用匿名对象
- 方法二：将匿名对象作为实参传递；

- 强引用
- 弱引用
- 软引用

### 5、封装 Encapsulation

- 隔离变化
- 便于使用
- 重用性
- 安全性

public 公有，权限很大   
private 私有   

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

private 私有，仅在本类中有效。   

一个成员被private修饰后，在类的外部不能直接访问该成员。   
所以，需要对外提供访问该成员的方法，   
**在方法里，可以对访问做出一些限制**。   

私有 ≠ 封装   

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

### 3、构造函数可以重载   

```java
Person(){}
Person(String name){}
Person(String name,int age){}

```
练习：构造函数重载；   

### 4、什么时候定义构造函数？   
需要初始化对象的时候。   

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

## Vol.06、01

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
但成员被静态修饰后，就多了一种调用方式，出了可以被对象调用，还可以直接被类名调用。      

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

### 2、方法区
内存中的一个区，方法区，也叫共享区、数据区。   
类中共享数据、共享方法都在里面。   

### 3、静态特点
- 随着类的加载而加载，
    - Person这个类进内存的时候，static的成员就已经存在了，static成员也叫类变量、静态成员变量。   
    - 而非static成员，也叫成员变量、实例变量，只有在实例化对象的时候才创建。   
    - 随着类的消失而消失，生命周期最长。

- 优先于对象存在   
    - 静态是先存在的，实例对象是后存在的

- 被所有对象所共享   

- 可以直接被类名所调用   

### 4、实例变量和类变量的区别
- 存放位置：
    - 类变量随着类的加载存在方法区中，随类的消失而消失。
    - 实例变量随着对象的建立存在于堆内存中

- 类变量
    - 类变量生命周期最长，随着类的消失而消失。
    - 实例变量的生命周期随着实例对象的消失而消失。

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

静态方法有利有弊   
- 优点：
    - 对对象的共享数据进行单独空间的存储，减少了内存使用，共享数据没有必要每个对象都存一份
    - 可以直接用类调用静态方法。

- 弊端：
    - 生命周期过长
    - 访问有局限性（静态方法只能访问静态成员）

注意：主函数就是静态的。   

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
* 虚拟机调用main，传啥值？   
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

// 此时传输main的args为["haha","heihei","123"]
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

会生成一堆网页，从index.html可以看到。   
public会被生成文档，但是private不会被提取。   

一个类中默认会有一个空参数的构造函数，   
这个默认的构造函数和所属类是一致，   
如果类是public，构造函数也是public，   
如果类是private，构造函数也是private。   

API帮助文档。   
java也自带文档。   

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

注意：   
如果对象是null，此时类依然不会被加载。   
实际用到了类的时候才会加载。   

```java
StaticCode s = null; // 此时类不会加载，static不会执行。 
```

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


### 6.10、对象调用成员的过程

```java
Person p = new Person();
p.setName("lisi");
```

- 堆区
- 栈区
- 方法区：放的都是方法，public private都会放到这里，static的也会放到这里。

static方法用Person调用，也就是当前的类。   
非静态方法用对象this调用。   

### 6.11、单例设计模式

对某一类问题常用的规律，形成了模式，这就是设计模式。   

GOF。   

纯偏思想，不是代码。   
如果没有真实业务需求，很枯燥。   

设计模式：解决某一类问题最行之有效的方法。   
java中有23种设计模式。   


单例设计模式：   
解决一个类在内存中只存在一个对象的问题。   
有些时候，会要求这个类，只能有一个对象，不能有更多的，就可以用单例设计模式。  

**第一种方式**。   

怎么保证只有一个对象？   
1、为了避免其他程序过多建立该类对象，先禁止其他程序禁止建立该类对象   
2、为了让其他程序可以访问到该类对象，只好在本类中自定一个对象。   
3、为了方便其他程序对自定义对象的访问可以对外提供一些访问方式。   


怎么用代码体现？   
1、把构造函数private，私有化
2、在类中国创建一个本类对象；   
3、提供一个方法可以获取到该对象   


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


