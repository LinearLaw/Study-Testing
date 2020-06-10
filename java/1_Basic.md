# JAVA




## Vol.05、07 

private 私有，仅在本类中有效。   

一个成员被private修饰后，在类的外部不能直接访问该成员。   
所以，需要对外提供访问该成员的方法，   
在方法里，可以对访问做出一些限制。   

私有 ≠ 封装   

3.4、构造函数   

1、和类名同名，无需定义返回值类型。   
不可以return，   
2、作用是：给对象进行初始化 → 只调用一次   

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

3、构造函数可以重载   

```java
Person(){}
Person(String name){}
Person(String name,int age){}

```
练习：构造函数重载；   

4、什么时候定义构造函数？   
需要初始化对象的时候。   

5、构造代码块   

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

6、this关键字   
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

7、多个重载构造函数不可以互相调用   
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