# 9_复合类型

```c
struct students
{
    int num;
    char name[20];
}sttd;

// students 就是结构体的类型名
// num 和 name[20]是该结构体的成员
// sttd 是结构体变量，在这里相当于定义了一个变量sttd

int main()
{   
    // 定义了一个变量stu，并给成员赋了值
    struct students stu = {"123",{2,66}};

    /*
        访问成员，用stu.num、stu.name
    */
}
```
```c
// 通过下面这种方法，可以给结构体指定的成员赋值
struct students aa = {
    .name = 'M',
    .tel = '1377777',
    .score[1] = 100,
    .score[2] = 6
}
```
```c
stu.age = 20; // 也可直接赋值

struct students 
{

} stu = {...};  // 也可以在定义结构体变量stu的时候给stu赋值
```
```c
// 一次定义多个结构体变量abcdef
struct students
{

}a,b,c,d,e,f;   
```
> Tips：结构体放在main函数的之前，或写在单独的头文件中；

-----------------------------------------------     

> Tips：结构体大小，需要根据数据类型进行内存对齐；      
```
int 4B
char 1B
float 4B
short 2B
double 8B
long 4B

Tips：在定义写结构体成员时，按照从大到小的方式写，可以节省单个结构体占用的内存空间；
Tips：结构体的内存对齐会按照占空间最大的成员进行对齐；
```

-----------------------------------------------     
### 结构体数组
```c
struct stu1
{
    char name[21];
    float scores[3];
}
int main()
{   
    // 输入时，AAA 99 33 66，然后按下回车，以空格为间隔
    scanf("%s %f %f %f", s[i].name, s[i].score[0], s[i].score[1], s[i].score[2]);

    // Tips：如果是%c则需要用其他符号来进行间隔
}
```
```c
// 例、冒泡排序
struct stu1 temp = s[j];
s[j] = s[j+1];
s[j+1] = temp;
```
————————————————————————————————————————————————     

### 结构体内部成员为指针
```c
struct stuinfo
{
    char *name;
    int age;
}
int main()
{
    struct stuinfo si;
    si.name = (char *)malloc(sizeof(char) * 21);

    strcpy(si.name, "张三");
    si.age = 18;

    free(si.name);
}
```
————————————————————————————————————————————————     
### 结构体指针
```c
struct sinfo
{
    char *name;
    int age;
}stu;

int main()
{
    struct sinfo *s = &stu; // 定义了一个结构体指针指向了stu
    s->name = (char *)malloc(sizeof(char) * 21);
}
/*
写法有所区别
    结构体指针访问成员，用s->name
    结构体变量访问成员，用s.name
*/
```
————————————————————————————————————————————————     
### 在堆空间开辟一块内存来存放结构体
```c
struct tec
{
    char *name;
    int age;
} t;

int main()
{   
    // 在堆空间内申请了一块内存来存放结构体
    struct tec *p = (struct tec *)malloc(sizeof(t));
    p->name = (char *)malloc(sizeof(char) * 21);

}
```
```c
// 使用结构体数组，申请一块能放3个结构体的空间
struct stu2 *p = (struct stu2 *)malloc(sizeof(struct stu2) * 3);

// 使用类似数组下标来访问指定的结构体元素
p[i].name
```
```c
struct info 
{
    char name[21];
    int age;
}
void func01(struct info s)
{
    s.age = 20; // 这句不会改变外部结构体的值
}
void func02(struct info *s)
{
    s->age = 20; // 这句利用了指针，可以改变外部结构体的值
}
int main()
{
    struct info s = {"张三" , 18};
    func01(s);
    func02(&s);
}
```

------------------------------------------------
```c
命名规则        
linux命名   int int_role_level
windows    int i_role_level
java    int BubbleSort
        int bubbleSort
```
————————————————————————————————————————————————     
### 结构体嵌套结构体
```c
struct stra
{
    int a;
    float b;
    char c;
} abc;
struct strb
{
    struct stra abc;
    short f;
    char *e;
    short g;
}
int main()
{
    struct strb strbb;
    strbb.abc.a = 100;  // 多次用.语法访问到结构体内部结构体的成员
}

// Tips：大小如何计算？
```
————————————————————————————————————————————————     
### 共用体（联合体）
union : 同一个存储空间存储不同类型数学的类型；

```c
union vars 
{
    double a;
    float b;
    int c;
    short d;
} var;

/*
共用体名称为vars，定义了一个共用体变量var
abcd全部都公用一块内存，按占用最大的那个成员来开辟空间，
比如这里double为8B，那么就会开辟一个8B的空间，
8B可以用来放abcd任意一个，四个成员共享这个8B的空间；
*/

int main()
{   
    // 后面定义的成员的值会挤占前面成员的空间，造成前面有值的成员的值缺损
    var.a = 100;
    var.b = 3.14;
    var.c = 66;
}
```
```c
// 注意，8B对齐，这里的arr[12]实际占用的空间是16B的大小
char arr[12]; 
```
> Tips：实际中共用体用的很少，因为容易乱，但是共用体比较省内存；

————————————————————————————————————————————————     
### 枚举类型
枚举，就是将变量一一列举出来；
```c
enum colors
{
    red = 10,
    blue,
    yellow = 20,
    black,white,green
} col;

/*
在这里，red是10，之后的blue就会被自动赋值为11，
yellow的值是20，black、white、green就会自动赋值21、22、23
*/
int main()
{   
    int val = 0;
    switch(val)
    {
        case red:
            break;
        case blue:
            break;
        ...
    }
}
/*
Tips：枚举类型一般都跟switch case语句配合使用；
Tips：例如，用来实现一连串的动作，同一个对象的多个状态等；
*/
```
————————————————————————————————————————————————     
### typedef
为一种数据类型起一个别名。      

> 注意：typedef并没有创建一个新类型，而只是给原有的东西起了一个别名；

```c
// 本来的写法
auto unsigned int a = 10;
auto unsigned long long b = 10;

// 使用typedef
typedef unsigned long long ull;
ull a = 10; // 本句等价于auto unsigned long long a = 10
```
```c
// 用于重命名一个结构体
struct studentInfoList
{

}
struct studentInfoList sil;

// 使用typedef
typedef struct studentInfoList ss; // 新名字
ss sil;
```
------------------------------------------------

<conio.h>       
_getch()    获取字符

------------------------------------------------    
QT
- 1、画界面；
- 2、main_window里面写全局控制，拖动组件，右键转到槽给按钮绑定事件；

Tips：结构体，用来一次性给某个东西定义一组属性；
Tips：typedef，用来给某个东西定义其别名；
————————————————————————————————————————————————       


