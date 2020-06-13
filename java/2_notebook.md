## 1、数据类型

- byte：8位，默认值0，带符号，-128 ~ 127

- short ：16位，默认值0，带符号，-32768 ~ 32767

- int ：32位，默认值0，带符号，-2 147 483 648 ~ 2 147 483 648

- long ：64位，默认值0L，带符号，-9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807

- float ：32位，IEEE 754标准单精度浮点数，默认值0.0f

- double ：64位，IEEE 754双精度浮点数，默认值0.0d

- boolean ：true，false，标识1位的信息，默认false

- char ：16位Unicode字符，最小值\u0000（0），最大值\uffff（65535）

## 2、引用类型

- 在Java中，引用类型的变量非常**类似于C/C++的指针**。
    - 引用类型指向一个对象，指向对象的变量是引用变量。
    - 这些变量在声明时被指定为一个特定的类型，比如 Employee、Puppy 等。
    - 变量一旦声明后，类型就不能被改变了。

- **对象、数组都是引用数据类型。**   

- 所有引用类型的默认值都是null。

- 一个引用变量可以用来引用任何与之兼容的类型。

## 3、常量

Java 中使用 final 关键字来修饰常量
```java
final double PI = 3.1415926;
```
常量名也可以用小写，但为了便于识别，通常使用大写字母表示常量。   

## 4、类型转换

低 → 高   

**byte,short,char**   
 —> **int**   
 —> **long**   
 —> **float**   
 —> **double** 

- 1. 不能对**boolean**类型进行类型转换。

- 2. 不能把**对象类型**转换成不相关类的对象。

- 3. 在把**容量大**的类型转换为**容量小**的类型时必须使用强制类型转换。

- 4. 转换过程中可能导致**溢出**或**损失精度**

- 5. 浮点数到整数的转换是通过**舍弃小数**得到，而不是四舍五入

### 4.1、自动的类型转换
必须满足转换前的数据类型的位数要**低于**转换后的数据类型；   

### 4.2、强制类型转换
- 1. 条件是转换的数据类型必须是**兼容**的。→ 怎么理解兼容？

- 2. 格式：(type)value type是要强制类型转换后的数据类型

```java
class ForceSwitch{
    public static void main(String[] args){
        int a = 111;
        byte b = (byte)a;

        System.out.printIn(b);
    }
}
```

### 4.3、隐含强制类型转换

- 1. 整数的默认类型是 int。

- 2. 浮点型不存在这种情况，因为在定义 float 类型时必须在数字后面跟上 F 或者 f。   

Tag：有没有什么例子？

## 5、变量类型

有三种变量类型：类变量、实例变量、局部变量

- 类变量：就是static修饰的成员，也叫静态成员
    - 类变量是类的成员，所有的类的实例都共享同一个类变量
    - 生命周期特别长，类定义后就存在，程序结束才销毁。
    - 存在静态存储区，一般会给它声明为常量。
    - 可以直接用类访问到类变量。
```java
class Person{
    static String name = "abc";
}
// 1、
Person.name;

// 2、
Person a = new Person();
a.name;
```

- 实例变量：就是一个类下的非static修饰的成员
    - 在new 一个类的实例，生成一个对象时，实例变量才被创建出来，
    - 不同的对象实例变量不是同一个。

- 局部变量：定义在函数内部的变量。

```java
class VariableType{
    static int age; // 类变量

    String country; // 实例变量
    int year;   // 实例变量

    public void show(){
        int g = 0;  // 局部变量
    }
}

```
## 6、什么时候用静态
两方面下手，   
静态修饰的有成员变量和成员函数。   

什么时候定义静态变量？   
- 当对象中出现共享数据时，该数据被静态修饰，
- 对象中的特有数据，需要定义成非静态，存在对象内部，堆内存中。

什么时候定义静态函数？   
- 当功能内部没有访问到非静态数据，或对象特有数据，可以定义成静态的。

```java
class Person{
    String name;

    // show没有访问非静态数据，可以设置成静态的
    public static void show(){
        System.out.printIn("hahhhahah ");
    }
}
```

### 7、静态的应用
每个应用程序都有共性的功能，可以将其抽取，独立封装，以便复用。   
```java
class Demo{
    public static void main(String[] args){
        int[] arr = {3,4,5,67,8};
        int max = getMax(arr)
    }

    public static void getMax(int[] arr){

        int max = 0;
        for(int x=1;x<arr.length;i++){
            if(arr[x]>arr[max]){
                max=x;
            }    
        }
        return arr[max]
    }
}

// 多个类共用某一些功能，可以把这些工具封装到某一个类中，定义成其静态成员函数
class Test{

}
```

```java
class Tools{
    public int getMax(int[] arr){

    }
    public int getMin(int[] arr){

    }
}
```
注意：   
编译时，当前类引用了其他类，会把其他类的java文件也编译，如果没有指定相关类的所在目录，会在当前文件夹找。   
如果没找到，javac不会报错，java运行时会报错。   

- 对象是用来封装数据的，工具类并未封装持有数据；
- 操作数组的每一个方法都没有用到工具类中的持有数据；

所以此时，此时是不需要对象的，**可以直接把工具类的方法，全部定义成static，直接通过类名调用**。   

方法静态后，可以方便使用，该类依然可以被其他程序建立对象，   
可以限制该类不能创建实例化对象，   
可以通过对该构造函数私有化，来限制不能进行new。     

能隐藏起来的全部隐藏起来。   

### 8、文档

```
set classpath=.;c/myclass
```

.class文件看不到里面的代码，里面都是二进制。   

如何将ArrayTools文件生成文档？（06-05-10:10）   
```java
/**
类的说明描述
@author 坐着
@version v1.1
*/
class ArrayTools{
    /**
    描述
    @param arr 参数描述
    @return 结果描述
    */
    public static void getMax(int[] arr){

    }
}

```