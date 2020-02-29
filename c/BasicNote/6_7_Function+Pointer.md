# 6_函数

声明函数需要放在main的下面，可能会出错；

```c
// 1、声明，此时定义了一个函数变量，但是没有给其分配内存空间
extern int add(int a,int b);

// 2、函数定义，如果在头部有声明，则放在main前后都可以
// 此时，开辟了内存空间存放函数内容
int add(int a ,int b)

// 3、调用，函数应当先声明、定义，之后才能调用。
```
```c
extern int dive(int a,int b);
int dive(int a ,int b)
{
    if(a)
    {
        exit(0);
    }
    return a/b;
}
// Tips：如果不写声明，定义就要写在main的上面，
// 如果有声明，就要将声明卸载main上面；

```
———————————————————————————————————————————————     
多文件联合编程

- 主文件 .c
> 在主文件中需要写#include "头文件.h"，     
> 在主文件中可以调用子文件中的东西；  

- 子文件 .c
> 在子文件中写各个函数的定义，定义的变量或函数在全局都可使用，因此命名不可以重复    
> 同时也需要#include "头文件.h"     

- 头文件 .h
> 头文件中写 #include <stdio.h>

> 头文件的作用：
>> - 函数、变量的声明
>> - 系统库的调用   
```c
gcc -o .exe a.c b.c head.h
```
```c
// 注意，头文件只能include一次，重复的include会导致出错
// 此时可以使用 #pragma once

#pragma once // 意味着仅进行一次include

 // 注意__HEAD_FILE_H__命名也不可以重复；
 // 如果没有头文件，则定义
#ifndef __HEAD_FILE_H__    
#define __HEAD_FILE_H__

// 在中间写自己引入的头文件

#endif
```
```c
#include <stdio.h>
int main (int argc, char *argv[])
{
    // argc 代表传递参数的个数
    // argv 传递参数的内容，里面放有所有传入的参数
    if(argc)
    {
        printf("缺少参数\n");
        return -1;
    }
    char arr[1000];
    char temp[256];

    strcpy(arr,"gcc -o");
    strcpy(temp,argv[1]);
    char *p = strtok(temp,".");
    
    strcat(arr,p);
    strcat(arr," ");
    strcat(arr,argv[1]);

    // Tips：使用命令mygcc a.c，就可以执行gcc -o a.c a
}
```
```c
// 实现调用命令执行gcc
// a.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(int argc, char *argvp[])
{
    if(argc <= 1)
    {
        printf("缺少参数\n");
        return -1;
    }
    char arr[1000];
    char temp[256];
    
    strcpy(arr,"gcc -o");
    strcpy(temp, argv[1]);
    char *p = strtok(temp, ".");
    strcat(arr, p);
    strcat(arr, " ");

    strcat(arr, argv[1]);

    system(arr);    // 调用命令，命令就是arr中的内容；

}
// 编译当前程序 gcc -o a a.c
// 然后就可以用本程序来编译其他的c程序了
// eg: ./a b.c
// 即可编译 b.c 为可执行文件 b
```
———————————————————————————————————————————————    
# 7_指针

```c
int my_strcmp(char s1[],char s2[])
{
    int i = 0;
    while(s1[i] !='\0' && s2[i] != '\0')
    {
        if(s1[i] != s2[i])
        {
            return s1[i] - s2[i];
        }
        i++;
    }
    if(s1[i] !='\0' || s2[i] != '\0')
    {
        return s1[i] - s2[i];
    }
    return 0;
}
```
指针，指向内存地址
> 注意，内存地址都是无符号整形      
```c
int a = 10;
int *p = &a; // p代表了一个int变量的地址

printf("%p\n",&a);
printf("%d\n",&p); // 这两种打印等价

// *p 指向a的值
// p  指向a的地址
// &a 指向a的地址
```
```c
// 以下三种定义方式是等价的
int * p
int* p
int *p

sizeof(int *)
32位OS，int * 占4B，
64位OS，int * 占8B。
```
```c
int a = 10;
int *p = &a; // 代表a的地址
*p = 20;     // 此时，a的值会被改为20
```
———————————————————————————————————————————————     
野指针（野指针在编程当中应该避免，野指针容易使程序出现重大问题）    
> 外挂就是利用的野指针      
```c
int a = 10;
int *p = &a;
p = 100;    // 擅自给指针p赋了一个值，此时，p变成了一个野指针，指向了未知的地址
*p = 200;   // 此时，操作了野指针，更改了野指针指向的未知的地址的值，程序会出错

// Tips：地址0-255都是系统保留不可读也不可写的区域；
```
———————————————————————————————————————————————     
空指针（空指针是允许的，可以用来进行条件判断）      
```c
int *p;     // 定义了一个指针p，没有指向任何地址，这是一个空指针；
p = NULL;
*p = 100;   // 给NULL的地址中赋值，不会起效果；

// Tips：空指针可以用来判断空间是否有
```
———————————————————————————————————————————————     
万能指针    
```c
void *p = &a;   // 用void没有指定指针的类型，此时可以指向任何类型
*(int *)p = 100;    // 先转为int *，再对*赋值

// Tips：void指针，要用的时候都必须要转换类型才能用；
```
```c
int a = 10;
void *p = &a;
printf("%d\n",*p); // 报错，void类型直接用printf是无法输出的
printf("%d\n",*(int *)p); // 正确
```
```c
int arr[10] = {0};
void *p = arr;
*(int *)p = 100;    // 给arr设置值100；

// p作为int指针，+4就会向前移动4个int大小的长度，所以这里会把arr[4]设置为200
*((int *)p + 4) = 200;  
```
———————————————————————————————————————————————     
const 修饰

> 有四个例子，来说明const定义的变量是否可以更改
```c
const int a = 10;
int *p = &a;
*p = 100; // 在这里用指针修改了const定义的a的值
```

```c

// 本例中，p可以更改，而*p不可更改；
int a = 10;
const int *p; // 用const修饰指针，指针变量一旦赋值就不可更改；
p = &a;     // 从而使得指针指向的值变得不可更改，但是指针的地址还是可以更改的；


```

```c
// 在这里，*p可以更改，p不可以更改
int * const p = &a;
*p = 100;   
```

```c
// 此时完全锁定，p不可以更改，*p也不可以更改
const int * const p = &a;
```
———————————————————————————————————————————————     
指针和数组
```c
int arr[10] = {0};
// arr → 是一个常量，里面存的是指针
// &arr[0] → 是一个值，arr[0]的值

int *p = arr;
// p[0]、p[1]等价于arr[0]、arr[1]
// *(p+0)，*(p+1)，*(p+2)
// 这里的+1 +2 乘以int的大小，就是新指向的地址，p的值始终未变
```
```c
int *p = arr; 
p++; // 这里会把p的值更改，p++会向前移动一个int大小的地址


// Tips：数组下标越界，分为上越界，下越界
```
———————————————————————————————————————————————     
指针运算    
> 注意，指针的加法并非简单的数据加法    
```c
int * p = &a;
p= p + 2;   // 注意，此时，p移动了2个int大小的地址
```
```c
int arr[10] = {1,2,3,4,5,6,7,8,9,10};
int *p = arr;
p = &arr[9];
p-arr;  // 返回9，即9个int大小的地址

```
```c
int main()
{
    int arr[10] = {1,2,3,4,5,6,7,8,9,10};
    int * p;

    // arr[9]为最大的元素，而arr[10]数组会越界，但是不会报错
    for(p = arr;p < &arr[10];p++)
    {   
        // p[0] 可以输出，p[0]代表了p向前走0个int的大小；
        printf("%d\n",p[0]);
    }
}

```
```c
// 数组名和指针的区别
sizeof(arr) → return 40 → 是整个数组的大小
sizeof(p) → return 4 → 求出的是int的大小
```
```c
// 例、指针实现冒泡
int bubble(int *p,int len)
// 技巧：*(p + i) 就指向了目标的数据arr[i]，二者等价
```
```c
// 例、实现strchr
char arr1[] = "hello world";
char ch = '\0';
char *p = strchr(arr1 , ch);

// p打印出值为 0 world
// Tips：strchr，在字符串中查找字符首次出现的位置，并返回该位置的地址；
char *mystrchr(char * arr1,char ch)
{
    int i =0;
    while(arr1[i] != '\0')
    {
        if(arr1[i] == ch)
        {
            return &arr[i];
        }
        i++;
    }
    return NULL;
}
```
```c
// 例、字符串的逆置 → 数组逆置，用指针来完成
```
———————————————————————————————————————————————     
指针数组    

把数组元素拿来存指针，就变成了指针数组，其元素全都是地址；
```c
int a = 10,b = 20, c = 30;
int *arr[] = {&a,&b,&c};
*arr[0] = 100;
```

```c
char * arr[] = {"hello","world","abc"};
*arr[0] → 会打印一个h
arr[0]、arr[1] 是"hello" 、"world"两个字符串的首地址；
```

```c
*arr[0] + 1     找到的是world
*(arr[0] + 1)   找到的是e
strlen(arr[1])  计算的是world的长度
```

```c
// 例、字符串数组，按字母排序
char *arr[] // 存的都是地址
```

**用一个指针指向一个一维数组，指针+1的步进就是数组元素所占空间的大小。**    
用一个指针指向一个二维数组，情况就不一样了。
———————————————————————————————————————————————     
多级指针    

```c
int a = 10;
int *p = &a;    // 一级指针p
int **pp = &p;  // 二级指针pc

*pp = &a
**pp = 20;  // 等价于*(*p)  先寻址到p的值，p的值是一个地址，再访问这个地址，设置为20

int *** ppp = &pp;  // 三级指针
```
> Tips：关系式      
> ***ppp == **pp == *00 == p == &a

> char的取值范围是0-256     
> 如果函数定义为void tab(){}，在tab内return 100，不会有什么意义；也就是定义void返回值，而返回的不是void；

```c
int tab(int * a ,int b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}
int main()
{
    int a = 100;
    int b = 20;
    tab(&a, &b); // 向tab函数内，传入a、b的地址
    return 0;
}
// Tips：函数参数中如果有数组，则会转为指针；
sizeof(arr) → 40
sizeof(p)   → 4 → 数组传入函数中，其实是地址传入，函数内操作的是指针，长度是4
```

> Tips：int型的数组是这样的规律；但是char型的数组不是这个的规律；   

```c
void print(char *arr)
{
    int len = strlen(arr);
    int i = 0;
    while(arr[i] !='\0')
        i++;
}
int main(void)
{
    char arr[] = "hello world";
    char arr2[] = {'h','e','l','l','o'};
    print(arr);
}
/*
1、当数组传入到函数中后，数组变量会退化为指针，
2、在传递数组到函数中时，需要将数组元素个数传递进去，否则你无法知道数组有多长
*/

```
```c
/*
函数调用，如果是在函数内部定义的变量，return到函数外部，地址可以访问到，
但是其值并不可用，
因为函数调用结束后，在函数内定义的变量的空间会被回收，
这就return出了一个野指针；
*/
char *test()
{
    char arr[] = "hello world";
    return arr; // 这个arr必须保证其里面有内容
}
int main()
{
    char *p = test();
    printf("%p     %s\n",p,p); // 地址%p可以打印值，会乱码，因为arr已经被回收了
}
// 但是，下面这种情况特例
char *test2()
{   
    // 定义了一个字符串指针，这个指针指向的hello world字符串，是一个字符串常量
    char *arr = "hello world"; 

    // 这里return出去的arr，字符串常量不会被回收，在外部函数中依然能访问到hello world
    return arr;
}

/*
以下变量在函数执行完之后不会被回收
1、全局变量
2、函数内的字符串常量
3、static修饰的变量
*/
```
```c
/*
例、strstr的实现 → 比较两个字符串是否一致
可以借助指针来实现，
1、硬解算法
2、KMP算法
*/
```
———————————————————————————————————————————————     
指针和字符串
```c
char arr[] = "hello world";
char *p;
p = arr;
*p = 'A';   // 相当于arr[0] = 'A'
p++;        // p是arr[0]，p++之后就指向了arr[1]
*p = 'B';   // 相当于arr[1] = 'B'
printf("%s   %s",p,arr);    // p会打印Bllo world，arr会打印ABllo world

// Tips：在printf时，字符串会从当前地址打印直到\0
```
```c
// 这是一个字符串常量，可读不可写，存放在内存的常量区
char *arr = "hello world";

// 这是一个字符串数组，可读可写，存放在内存的栈区
char arr[] = "hello world";
```
```c
// 例、strcat函数 → 将一个字符串追加到另一个字符串数组中
void mystrcat(char *arr,char *s1)
{
    while(*arr)
        arr++;
    while(*arr++ = *s1++);
}   

// 例、字符串排序



```
———————————————————————————————————————————————     



