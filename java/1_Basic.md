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
this指代哪个对象？


