## Vol.07

### 7.1、继承

面向对象的第二个特征。   

事物的共性，进行抽取，构成了父类。   
子类继承父类的属性与功能。   

继承：   
1、提高了代码复用性。   
2、继承类与类之间产生了关系，有了这个关系，才有了多态的关系。   

注意：   
千万不要为了获取其他类的功能，简化代码而继承；  
必须是类与类之间有所属关系，才进行继承。   
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