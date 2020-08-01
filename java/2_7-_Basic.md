## Vol.07

### 7.1、继承

面向对象的第二个特征。   

事物的共性，进行抽取，构成了父类。   
子类继承父类的属性与功能。   

——————————————————————————————————————————      

继承：   
1、提高了代码复用性。   
2、继承类与类之间产生了关系，有了这个关系，才有了多态的关系。   

注意：   
千万不要为了获取其他类的功能，简化代码而继承；  

**必须是类与类之间有所属关系**，才进行继承。   
所属关系：a is b。   

```java
class Person {
    String name;
    int age;
}
class Student extends Person{
    void study(){
        // study
    }
}
```  

java中，只支持单继承，不支持多继承。   
C++可以多继承。   

**多继承容易带来安全隐患**。   
多继承时，多个功能同名，但是内容不同，子类对象会不确定执行哪一个。   

java保留了继承机制，用了另一个体现形式完成表示，也就是多实现。   
java支持多层继承。

```java
class A{
    void show(){

    }
}
class B{
    void show(){

    }
}

class C extends A,B{

}

C ccc = new C();
ccc.show();     // 此时应该调用A的show还是B的show？不知道！
```

——————————————————————————————————————————      


如何使用继承体系中的功能？   

想要使用体系，先要查阅体系中父类的描述。   
因为父类中定义的是该体系中的共性内容，通过了解共性功能，就可以知道该体系的基本功能。   

那么这个体系就已经可以基本使用了。   

具体调用时，创建的是最末端的子类对象，   
1、因为可能父类不能创建对象，比如抽象类。   
2、创建子类可以使用更多的功能，有基本功能也有抽象功能。   

——————————————————————————————————————————      

### 7.2、组合、聚合

聚集：has a    

聚合：   

组合：   

——————————————————————————————————————————      

### 7.3、子父类变量特点

子父类出现后，类成员的特点：   

类中成员：
- 变量
- 函数
- 构造函数   


```java
class Parent{
    int num = 4;
    private int num1 = 5;
}

class Children extends Parent{
    int num = 5;

    void show(){
        System.out.printIn(super.num);  // 此时访问的是父类的num
        System.out.printIn(this.num);  // 此时访问的是当前类的num
    }
}

class ExtendsDemo{
    public static void main(String[] args){
        Children z = new Children();
        // 父类子类同名成员，优先取更近的。


    }
}
```
注意：   
加载类的时候，会先加载父类，再加载子类。   
然后再创建了super this。   

——————————————————————————————————————————      

1、变量   

如果子类中出现非私有的同名成员变量时，   
子类访问本类变量，用this   
子类访问父类的同名变量，用super   

super的使用和this基本一致。   
this指向子类，super指向父类。   
 
如果用this.num在本类中没找到num，会继续找父级是否有num，直到根类。   

——————————————————————————————————————————      

2、子父类中的函数   

子类出现和父类一模一样的函数时，   
当子类对象调用该函数，会运行子类函数的内容。   

父类的函数被子类函数覆盖了。   
这种情况是函数另一个特性：重写（覆盖）。   

**什么时候用覆盖？**   
子类和父类函数功能是重复的，那么可以设计成子类把父类的相同功能覆盖掉。   

子类继承了父类，沿袭了父类功能，   
但是子类虽然具备相同功能，但功能内容和父类不一致。
子类覆盖了父类定义，保留父类功能，   
父类的功能依然有用，只是优先调用的是子类的方法。   

这种方法有利于功能扩展。   

——————————————————————————————————————————      

覆盖：   
- 1、子类覆盖父类，必须保证子类权限大于等于父类权限，才可以覆盖，否则编译失败。

- 2、静态只能覆盖静态。   

```java
class Parent{
    int num = 4;
    private int num1 = 5;
    void show(){

    }
    void speak(){
        System.out.printIn("vb")
    }
}

class Children extends Parent{
    int num = 5;
    void show(){

    }
    void speak(){
        super.show();
        System.out.printIn("java");
    }
}

class ExtendsDemo{
    public static void main(String[] args){
        Children z = new Children();
        // 父类子类同名成员，优先取更近的。
        z.show1();
    }
}
```
——————————————————————————————————————————      

记住，   
重载：只看同名函数的参数列表。   
重写：子父类的方法要一模一样，返回值类型也要一样，否则调用的时候不知道调用谁，编译会报错。   

——————————————————————————————————————————      

3、构造函数，子类实例化   

子父类的构造函数显然是不一样的，显然不会被覆盖。   

在对子类对象初始化时，父类构造函数也会执行。   
因为，子类构造函数默认第一行会有一句隐式的语句super()；   

super()，所有的构造函数第一句都是super()   

```java
// 这里会先走Fu()，再走子类。
class Fu{
    Fu(){

    }
}

class Zi extends Fu{
    Zi(){
        // super()，子类的构造函数会默认执行这一句，执行父类的构造函数。
        // this()，调用本类的构造函数
    }
}

class ExtendDemo{
    public static void main(String[] args){

    }
}
```

——————————————————————————————————————————      

为什么子类一定要访问父类构造函数super()？   
 
因为父类中的数据，子类是可以获取的，所以子类对象在建立的时候，   
需要先看一下，父类对这些数据是怎么初始化的。   
所以子类构造函数里， 先要访问一下父类的构造函数。   

如果要访问指定的构造函数，   
如果父类的构造函数不是空参数，可以手动调用super('传参数进去')。   

```java
class Person{
    String name;
    Person(String name){
        this.name = name
    }
}
class Student extends Person{
    Student(String name){
        // 本来我需要自己写语句，在构造子类对象的时候给父类name赋值
        super.name = name;

        // 而现在，我可以直接super调用父类构造函数来给父类的name赋值。
        // 这就是为啥要先调用父类的构造函数的存在意义。
        super(name)
    }
}
```

——————————————————————————————————————————      

子类的实例化过程：   

注意：super()一定要写在子类构造函数第一行。     

子类的所有构造函数，默认都会访问父类中空参数的构造函数，   
因为子类每一个构造函数的第一行都有一个隐式的super()，

如果父类里面没有空参数的构造函数，子类必须手动用super指定访问父类的构造函数。   

子类的构造函数的第一行也可以手动指定this语句来访问本类的构造函数，   
子类中至少会有一个构造函数访问父类的构造函数。   


所有类的父类：Object   


```java
class Zi extends Fu{
    Zi(){

    }
    Zi(int x){
        this(); // 此时，这里没有了隐式的super
    }
}
```

——————————————————————————————————————————      

### 7.4、抽象类

```java
/**
 * 原本的做法，留下一个空函数，给子类继承重写
 */ 
class Student 
{
    void study(){/* Student类中，留下一个空函数，没写具体实现 */}
}
class AdvStudent extends Student
{
    void study(){
        // AdvStudent继承了Student，在这里写具体实现
    }
}
```

抽象类，父类只定义功能，具体功能都由子类来实现。   

多个类，相同的功能，但是功能主体不同，就可以向上抽取，   
此时只抽取功能的定义，不抽取功能的主体。   
这就是一个抽象类，或者抽象方法。

```java
abstract class Student
{
    void Student();
}
```

——————————————————————————————————————————      

abstract 可以修饰类，也可以修饰方法，   
抽象类里面的方法，只进行定义，不进行具体的功能实现，不写方法体。   

——————————————————————————————————————————      

**抽象类的特点**：
- 1、抽象方法一定在抽象类中。

- 2、抽象方法和抽象类都必须被abstract关键字修饰。

- 3、抽象类不可以用new创建对象。因为调用抽象方法没意义。

- 4、抽象类中的抽象方法，必须由子类复写起所有的抽象方法后，建立子类对象调用。
    - 如果子类只覆盖了部分抽象方法，那么该子类还是一个抽象类。

——————————————————————————————————————————      

抽象类中，可以抽象方法和具体方法并存。   
但是，子类继承抽象类的时候，抽象方法必须要定义具体实现。   

> 特技：    
> 定义一个抽象类，abstract修饰，但是里面不定义抽象方法。    
> 也就是里面的方法全都有方法体。   
> 
> 这可以阻止使用该类建立对象。   

——————————————————————————————————————————      

抽象类、抽象方法可以用什么关键字修饰？

- abstract：被abstract修饰的类，一定是一个父类；

- final：被final修饰的类不能有子类，被final修饰的方法不能复写；
    - 所以final不能修饰抽象类，也不能修饰抽象方法；

- private：如果抽象类中抽象方法加上private，此时该方法不能被复写，
    - 因此抽象方法不能用private；

- static：static修饰抽象方法没意义，因为抽象方法里面没有方法体。

——————————————————————————————————————————      

### 7.5、模板方法模式

需求：   
获取一段程序运行的时间。   
> 1、获取开始时间，获取结束时间；   
> 2、两个时间相减。   

怎么封装？

```java
// 1、定义一个抽象类，里面定义一个函数getTime，来计算时间
class GetTime
{
    public final void getTime(){
        long start = System.currentTimeMillis();

        runCode();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    // 定义一个抽象方法
    public abstract void runCode();
}
/**
 * 2、定义一个子类，继承GetTime，重写它的runCode方法
 */
class SubTime extends GetTime{
    public void runCode(){
        // 写具体方法
    }
}

class TemplateDemo
{
    public static void main(String[] args){
        // 3、创建一个SubTime对象，然后调用getTime方法
        // 此时会打印出SubTime对象的runCode函数的执行时间。
        SubTime gt = new SubTime();
        gt.getTime();
    }
}

```

这就是模板方法模式。    
定义功能时，功能的一部分是确定的，其他是不确定的，  
此时将不确定的部分暴露出去，由该类的子类来实现这个不确定的部分。    

注意：  
模板方法模式不一定是抽象类，因为重写的方法可能有默认的动作。    

——————————————————————————————————————————      

### 7.6、接口

接口，可以认为是一个特殊的抽象类。  

当抽象类中的方法，全都是抽象的，此时可以用接口的形式来表示。    

- class 用来定义类    

- interface 用来定义接口  

- 类与类 用extends

- 类与接口 用implements

子类对接口中抽象方法全都覆盖，子类才可以实例化，否则子类是一个抽象类。

```java
interface Inter
{
    // 在接口中，成员的前缀public static可以省略。
    // 接口中的成员全都是public 
    int Num = 3;
    void show();
}
```

——————————————————————————————————————————      

接口可以被类多实现，这是对多继承的补足。    
java不支持多继承，但是可以用接口多实现。
```java
interface InterA{
    public abstract void show();
}
interface InterB{
    public abstract void show();
}
class Demo{
    public void function(){}
}

class Test extends Demo implements InterA,InterB{
    public void show(){

    }
}
```
——————————————————————————————————————————      

接口与接口之间，可以有继承关系。    
```java
interface A{}
interface B{}
interface C extends B{}
class D implements C{
    // 在D类中，需要将B C接口的方法都进行实现才行。
}

// 接口与接口可以多继承，但是要成员名称不冲突
interface D extends B,A{}

```

重点！  
java类不能多继承，但是java接口可以多继承

——————————————————————————————————————————      

接口的特点      
- 1、对外暴露规则

- 2、是程序的功能扩展

- 3、可以用来多实现

- 4、类和接口是实现关系，类可以继承一个类的同时实现多个接口

- 5、接口与接口之间可以有继承关系

——————————————————————————————————————————      