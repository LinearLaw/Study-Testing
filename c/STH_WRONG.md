# C 遇到的错误

- 1、写入位置 0x0000000A 时发生访问冲突。
```c
// 说明访问了一个NULL里面的空间，例如：
St[top]->lchild = p;
// 如果top = -3，会出现
// 如果top = NULL，会出现

// 类似于js中的：obj = {};obj.a.b = 1; 
// 此时会报错：Uncaught TypeError: Cannot set property 'b' of undefined

```

- 2、stack around the variable "XX" was corrupted.
```c
// vs 2017，简单地说就是存在堆栈越界
// project -> 配置属性 -> c/c++ -> 代码生成 -> 基本运行时检查，设置这个东西为默认值
```