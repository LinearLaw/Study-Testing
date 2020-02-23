# 1_C语言概述

## 1、库引用

### 1.1、几个例子
例1，调用系统指令   
```c
// b.c
#include <stdlib.h>
int main(void)
{
    // system函数，相当于在命令行界面中输入对应的命令
    system("ll.exe");
    return;
}
```
```cmd
gcc -o b b.c    编译b.c，生成可执行文件b
```
 
例2，system的返回值
```c
// b.c
#include <stdlib.h>
#include <stdio.h>
int main(void)
{
    // system 函数，不同的系统返回值会不同，和POSIX有关
    int value = system("ls -all");
    printf("调用ls：%d\n",value);

    // 在C中，默认将所有返回0代表正确，返回-1表示错误（这个由开发者自己规定）。
    return 0; 
}
```

```
gcc -o b b.c    编译b.c，生成一个可执行文件b
./b             执行b文件
```
例3，WinExec
```c
#include <stdlib.h>  // 引入一个系统库
#include <windows.h> // 引入一个系统库，可以用win exec
void xx()
{   
    // win exec可以执行windows的程序，SW_HIDE用来屏蔽黑框的弹出。
    WinExex("calc",SW_HIDE); 
}
```

> Tips：在Linux中，/bin文件夹中存在的执行文件，可以作为系统命令使用。
也就是相当于windows中的环境变量。  
> Tips：Linux遵循POSIX，而windows对POSIX的支持不是很好；

### 1.2、gcc   

编辑器(如vi、记事本)是用来写程序的（编辑代码），我们用编辑器编写程序，由编译器编译后才可以运行。   

gcc（GNU Compiler Collection，GNU 编译器套件），是由 GNU 开发的编程语言编译器。gcc原本作为GNU操作系统的官方编译器，现已被大多数类Unix操作系统（如Linux、BSD、Mac OS X等）采纳为标准的编译器，gcc同样适用于微软的Windows。   

gcc最初用于编译C语言，随着项目的发展gcc已经成为了能够编译C、C++、Java、Ada、fortran、Object C、Object C++、Go语言的编译器大家族。

**gcc常用编译选项**
```
- gcc -o file 指定生成的输出文件名为file，进行预处理、编译、汇编、链接；
- gcc -E 只进行预处理；
- gcc -S 只进行预处理和编译；
- gcc -c 只进行预处理、编译、汇编；
```

> Tips：Linux一般带gcc；   
> Tips：Windows一般不带gcc；   
> Tips：Linux编译的可执行程序只能在Linux中运行，在windows是运行不了的，windows下编译的程序也一样；   

> Tips：64位Linux下编译的程序只能在64位Linux使用；
> Tips：64位windows下编译的程序在64位和32位windows都可以使用，但是64位系统编译的程序只能在64位系统下运行；   

### 1.3、IDE编辑器

- Qt Creator：可以开发图形化的程序（创建项目不能带中文路径），Qt内部集成了gcc，如果安装了Qt，配置一下环境变量即可使用gcc；
- VS：也可以开发图形化程序（MFC应用程序），也可以开发命令行程序； 

## 2、C语言编译过程

### 2.1、程序编译阶段   
- 预处理：头文件展开，宏定义展开，条件编译；→ 将头文件引入到当前项目
- 编译：检查语法，生成汇编文件；→ 此时高级语言变成汇编语言
- 汇编：生成二进制文件；
- 链接：系统函数功能等的链接；例如dll动态链接库，进行关联到代码；   

> Tips：.so是静态代码库，.dll是动态链接库

### 2.2、程序执行阶段   
内存四区
- 代码区
- 数据区
- 栈区
- 堆区

CPU内部结构和寄存器
> 组成原理的知识，不写了。   

> windows:    dependency → 该命令可以看到依赖库   
> linux:      ldd hello → 该命令可以看到依赖库

反汇编   
> VS中，调试-窗口-反汇编，即可看到汇编语言和C语言之间的对比；

C和汇编语言混写
```c
#include <stdio.h>
int main()
{
    int a;
    int b;
    int c;
    __asm   // __asm用来标识一段汇编语言
    {
        mov a,3
        mov b,4
        mov eax,a   // eax指的是在x86下的寄存器
        add eax,b
        mov c,eax
    }
    printf("%d\n",c);
    return 0;
}
```
关于不安全的输入C4996错误

> C4996，在windows下有这个问题，linux没有这个问题   
例如 scanf("%d",a)，这是一种不安全的操作，如果用户输入的不是int型，就会导致程序出错；   
> 两个解决方法：   
> - 用宏定义，首行加入：#define _CRT_SECURE_NO_WARNINGS
> - 首行加入：#pragma warning(disable:4996)
```

# 2_数据类型和数据结构

- 数据类型：面向应用
- 数据结构：面向存储

## 2.1、基本数据类型

字符型
- char

三种整形
- short
- int
- long 

浮点型，即实型
- float
- double
> Tips：float一般可以显示到小数点后6位，double的小数点后面就更长了；

- unsigned
- signed

结构体类型
- struct
- union : 联合体
- enum : 枚举类型
- void

命名规则（不想写）   
变量定义：数据类型 变量名
```c
int a;
```
常量定义：
- 1、const 数据类型 变量名
```c
const int b = 10;
```
- 2、#define 常量名 值  (也叫宏定义，一般常量名用大些，值会自动设置数据类型)
```c
#define NB 100;
```
> 注意    
> 1、#define定义的常量，类型会自动设置；   
> 2、const修饰的常量不安全，可以用指针来修改；   

## 2.2、进制
十进制转二进制：除基取余法
> HEX → 十六进制   
> DEC → 十进制   
> OCT → 八进制   
> BIN → 二进制   

二进制转十进制：权值法：将二进制各个位数×2^n

十进制转八进制：除基反序取余法
八进制转二进制：八进制每一位转成三个二进制数，841法则
```c
int a = 123; // 十进制
int b= 0123; // 首位为0，代表八进制数
int c = 0xABC; // 首位0x，代表十六进制
```
> 注意：C语言无法直接写二进制数；
```c
printf("%d\n",a);
%d是一个占位符，将数以十进制输出；
%o，以八进制输出；
%x，以十六进制小写输出；
%X，以十六进制大写输出；
```
```c
- long long 长长整形，占8B
- long 长整形，windows 4B，linux(32位) 4B linux(64位) 8B
- short 2B
- int 32位系统下，占4B

- 1个汉字，2B，双字节，WORD
    4B，4字节，DWORD

- float 4B
- double 8B

注意：以上都是32位系统内的占用，如果是64位系统，占用空间会不一样；
注意：sizeof返回的结果是一个size_t类型，32位计算机内返回一个unsigned类型；
```
```c
char c = 'a'; // 单个字符用单引号
```
> char(1) < short(2) < int(4) == float(4) == long(4) < long long(8) == double(8)   

> 无符号数unsigned   
> 有符号数signed：原码、反码、补码   
>>   正数：原码 == 反码 == 补码（三码合一）   
>>   负数：**原码** —(符号位不变，数值位取反)→ **反码** —(+1)→ **补码**   

> Tips:补码只有一种0，而原码和反码有2种0，+0和-0   

```c
#include <stdio.h>
int main(void)
{
    int a;
    scanf("%d",&a); // &代表取地址
    printf("a=%d\n",a);
    return 0;
}
```

## 2.3、字符型

字符型char ，仅可存一个字符，用单引号；   

输出字符：
- printf → 输出一个字符串
- putchar → 输出一个字符
```c
printf("%c",a);
putchar('a');
```

输入字符：
- scanf → 输入一个字符串
- getchar → 输入一个字符
```c
scanf("%c",&b);
getchar(字符)；
char a = getchar();
```

溢出：有符号数的加减操作，进位之后符号位被更改，造成高位数据丢失；

```c
// 以f结尾的是float类型，不以f结尾的是double型
float a = 10.0f; // f可加可不加，默认就是float

```
```c
#include <stdio.h>
int main(void)
{
    float r = 2;
    float s = r*r*3.14
    printf("%.2f\n",s);
    return 0;
}

```
> Tips：科学计数法：3.2e3f → 3200   
> Tips：字符常量，例如'a'，字符串常量"a"相当于是'a''\0';   


类型限定符
- extern：声明一个变量，extern声明变量后，该变量并未立刻申请存储空间；
- const：定义一个常量
- volatile：防止该变量被编译器优化
- register：定义寄存器变量，有空闲寄存器就会把变量放入寄存器；



# 3_运算符和表达式

C语言中没有布尔型，非0的值都为真；

运算符
- 算术运算符
- 赋值运算符
- 比较运算符
- 逻辑运算符：! && ||
- 位运算符
- sizeof运算符

```c
* 取值运算符
& 取地址运算符
```

位运算
- ~a → 将a按位取反
- a&b → 按位与运算
- a^b → 按位异或，相同为0，不同为1；
- a|b → 按位或运算

赋值运算
- a/=1 → 除后赋值
- a+=2 → 加后赋值，a=a+2
- a%=2
- a-=2
- a = a<<3 → a左移3位
- a = a>>2 → a右移2位

类型转换
```c
// eg：强制类型转换
int a = 1;
float b = (float)a;  
```
- 自动转换
- 强制转换

```c
int num = 5;
printf("%d\n",num/2); // 隐式转换
printf("%1f\n",num/2.0); // 隐式转换

// 强制转换
float x = 3.6f;
int i = 0;
i = (int)x; // 将x强制转为整数，并赋值给i

int a = 10,b=3;
(float)a/b; // 先令(float)a，再令强转的结果/b
```


# 4_程序流程

- if...else
- 三目运算符
- switch(){}  →  switch case执行到break即为结束，没有break就会全部执行；
- while(){}
- for   
- goto  →  无条件跳转，很少用；

```c
// eg：输入一个值，判断是否为水仙花数；
// 水仙花数：100-1000之间，个位、十位、百位的立方等于它本身；

#include <Math.h> // 使用数学公式

#include <time.h> // 使用随机数
rand(); // 生成了一个随机数；
```

> Tips：将C49编译成C99
```cmd
gcc -o 文件名 -std=c99
```





