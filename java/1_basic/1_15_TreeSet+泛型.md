# Vol.15 TreeSet

## 15.1、TreeSet

- Set ：无序表，元素不重复，功能和Collection基本一致。     
    - HashSet
    - TreeSet

TreeSet，也就是二叉树，可以进行排序，   
元素的插入顺序，和真实顺序并不一样。    

> 那么，    
> 如何按指定的规则排序？    
> 重写companyTo()方法！ 

——————————————————————————————————————————      

当主要条件相同，就判断次要条件。
```java
class Student implements Comparable
{
    private String name;
    private int age;

    Student(String name,int age){
        this.name = name;
        this.age = age;
    }
    // 这里传入的对象，是一个Object，这是啥，这是多态。
    public int compareTo(Object obj){
        if(!obj instanceof Student){
            throw new RuntimeException("该对象不是Student对象");
        }

        // 判断该对象没有问题之后，就强转成Student类型。
        Student s = (Student)obj;

        System.out.println(this.name + "------compare to------" + s.name);
        if(this.age >s.age){
            return 1;
        }else if(this.age == s.age){
            // 如果age一样，就返回两个对象名称的比较值
            return this.name.compareTo(s.name);
        }
        return -1;
    }
}
```

```java
TreeSet ts = new TreeSet();
ts.add(new Student("ssss3333",22));
ts.add(new Student("sss4444",23));
```
——————————————————————————————————————————      

排序方法，有两种
- implements Comparable，然后重写compareTo方法，return int

- implements Comparator，然后实现其compare方法，return int

```java
class MyCompare implements Comparator
{
    // 传入两个对象，返回两者比较的值
    public int compare(Object o1,Object o2){
        Student s1 = (Student)o1;
        Student s2 = (Student)o2;

        int num = s1.getName().compareTo(s2.getName());
        if(num == 0){
            return new Integer(s1.getAge()).compareTo(new Integer(s2.getAge());)
        }
        return num;
    }
}

// 在new对象的时候，就将这个比较器传入
TreeSet ts = new TreeSet(new MyCompare());
```

> Tips：    
> Comparable，是将指定的对象，  
> 实现Complement接口，重写compareTo方法。   
>   
> Comparator，则是在创建TreeSet对象的时候，将Comparator的子类实例传入  
> 其中，定义子类的时候，需要重写Comparator的compare方法     

——————————————————————————————————————————      

## 15.2、泛型

泛型，是一种类型安全的机制，在集合框架中比较常见。  
```java
ArrayList<String> a1 = new ArrayList<String>();
```

泛型主要是解决类型不一致的问题，    
主要是避免强制类型转换，将集合中要存的类型进行规定好某一种类型。    

同时，在使用迭代器进行取数据的时候，也要指定泛型。  
```java
Iterator<String> it = a1.iterator();
```

> 注意：  
> 没有泛型之前，  
> 如果存入了不同类型的数据，iterator取出时会报错。    
> 现在引入了泛型之后，强制指定了以某种类型方式进行存取元素。  

```java
// before
class LemCom implements Comparator{
    public int compare(Object o1,Object o2){
        // 因为传入的是两个Object，就必须要强制类型转换。
        // 转成了String，再做进一步操作
        String a1 = (String)o1;
        String a2 = (String)o2;

        // do other things
    }
}

// after
class LemCom implements Comparator<String>
{   
    // 无需强制类型转换，直接就将o1和o2当做String类型来操作
    public int compare(String o1,String o2){
        // do other things
    }
}
```
——————————————————————————————————————————      

## 15.3、泛型类


```java
/*
 * 类中要操作的引用数据类型不确定的时候，
 *      如果要对某一个不确定类型的数据进行赋值，
 *      那么就会用Object来做通用类型。
 */
class Utils
{
    private Object a;
    public void setObj(Object a){
        this.a = a;
    }
}

// 使用泛型后，直接定义一个形式类型，作为以后要传入的类型
class Utils<QQ>
{
    private QQ a;
    public void setObj(QQ a){
        this.a = a;
    }
}
```

——————————————————————————————————————————      

## 15.4、泛型类

如果某一个方法的类型不确定，可以用泛型来做形式类型。

```java
public <Q> void print(Q q){
    System.out,println("q");
}
```

```java
// 注意：
Demo<Integer> d = new Demo<Integer>();

// 某一个对象，在new之后，
// 这个d里面的类型，就明确只能用int了，
```

所以说，泛型并不是让类型变得无限制，    
在new出了真实对象之后，类型就确定了，   
之后对对象的操作，都需要传入初始化时确定的对象。    

只不过，在定义函数、类的时候，可以只使用形式上的类型。  

——————————————————————————————————————————      

## 15.5、静态泛型方法

```java
// 下面这种写法会报错，
// 因为static函数先于对象加载，类上面声明的W类型并不能获取到。
class Demo<W>{
    public static <W> void method(W t){
        
    }
}

// 下面的写法是正确的
class Demo{
    public static <W> void method(W t){
        
    }
}
```

> Tips：    
> static方法不能获取class上面定义的泛型。       
> 如果static方法需要使用泛型，可以将泛型放到方法上。    

——————————————————————————————————————————      

## 15.6、泛型接口

```java
interface Inter<T>{
    void show(T t);
}
class InterTemp<T> implements Inter<T>{
    public void show(T t){

    }
}
```
——————————————————————————————————————————      

## 15.7、泛型集合

传入方法的参数，是一个集合对象。

```java
/*
    定义集合时使用泛型。
    ArryaList<?> a1
    ArrayList a1
 */
public static <T> void printCol(ArrayList<T> a1){

}

// 以下这句会报错，因为左右类型不一致，即使两者是父子类关系。
ArrayList<Person> a1 = new ArrayList<Student>();

```

```java
// 泛型限定，使用一个 ? 来指代父子类关系
Collection<? extends Person> a1
```

**泛型限定**    
- 上限
    - ? extends E

- 下限
    - ? super E 这种写法用的比较少


——————————————————————————————————————————      