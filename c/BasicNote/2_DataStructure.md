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
