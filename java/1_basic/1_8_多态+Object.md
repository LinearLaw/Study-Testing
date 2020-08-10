# Vol.08 

## 8.1、多态

多态，也就是某一类事物具有的多种形态。

- 多态的体现
    - 父类的引用，指向了自己的子类对象。
    - 父类的引用也可以接收自己的子类对象。
```java
abstract class Animal(){
    abstract void eat(Animal a){

    };
}
class Cat extends Animal(){
    public void eat(){}
}
class Dog extends Animal(){
    public void eat(){}
}

class Main{
    public static void main(String[] args){

        // 一个对象具有多种形态，就是多态。
        // 例如这里，对于一个new Cat对象，可以用Animal接收，也可以用Cat接收。
        Animal c = new Cat();
        Cat d = new Cat():
    }
}
```

- 前提：类与类之间有关系，要么继承，要么实现。
    - 方法有覆盖
    - 有局限性，虽然提高了扩展性，但仅可使用父类引用来访问父类成员。

- 好处：大大提高了程序扩展性

- 应用
    - 类型提升
    ```java
    // 这里，将Cat对象进行了向上转型，转成了父类型Animal
    Animal a = new Cat();

    // 如果要调用Cat的方法， 那么就要向下转型
    (Cat)a.eat();

    ```

    - 类型转换
    ```java
    Cat c = (Cat)a;
    c.catchMouse();
    ```

——————————————————————————————————————————      

注意：      
父类可以转换成子类，    
但是子类不可以转换成父类。      
多态始终是子类对象在变化， 都是子类在改变。     

——————————————————————————————————————————      

```java
/**
    在这里，传递的是一个Animal类型的函数，
    在内部，通过instanceof来判定具体的属于Animal的哪个子类
 */
public static void function(Animal a){
    a.eat();
    if(a instanceof Cat){
        Cat a = (Cat)a;
    }else if(a instanceof Dog){

    }
}
```

——————————————————————————————————————————      

在多态中，对于某一个成员函数，  
编译时，参与引用型变量所属的类中是否有调用的方法，如果有则，编译通过，没有则失败；  
运行时，参与对象所属的类中是否有调用的方法。

总而言之，非静态的成员函数进行多态调用，编译看左边，运行看右边。

在多态中，多余某一个成员变量，  
无论编译和运行，都参考左边。    

在多态中，静态成员函数，    
无论编译和运行，都参考左边。

```java
Fu f = new Zi();
Zi z = new Zi();

f.method(); // 运行看右边，调用的是Zi的方法
z.method(); 
```
——————————————————————————————————————————      

## 8.2、多态的应用

接口型引用指向自己的子类对象。  

接口，提高了扩展性，减少了耦合性；  
多态，使得接口的使用变得更加方便；  

例如，数据库的操作，
- 连接数据库，有多种方式，JDBC，Hibernate；

- 操作，CRUD；

- 关闭连接

## 8.3、Object类

Object是所有类的根类，是所有类的直接/间接父类；

- obj1.equals(obj2) ： 比较两个类是否相等；
    - 事实上比较的是 obj1 和 obj2 的地址；
    - 子类可以重写equals方法，来定义新的比较方法。

- obj.toString() ：返回一个十六进制的哈希值；
- obj.hashCode() ：返回一个十进制的哈希值；
- obj.getName() ：返回该对象的类名；    