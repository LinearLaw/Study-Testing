# -----Status: Not Complete-----

# C语言概述

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

程序编译阶段   
- 预处理：头文件展开，宏定义展开，条件编译；→ 将头文件引入到当前项目
- 编译：检查语法，生成汇编文件；→ 此时高级语言变成汇编语言
- 汇编：生成二进制文件；
- 链接：系统函数功能等的链接；例如dll动态链接库，进行关联到代码；   

> Tips：.so是静态代码库，.dll是动态链接库

程序执行阶段   
内存四区
- 代码区
- 数据区
- 栈区
- 堆区
