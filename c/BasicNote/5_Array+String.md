# 5_数组 & 字符串

## 数组

数组属于构造数据类型。   
数组元素，可以是基本数据类型，也可以是构造类型；   

```
空格 \t
换行 \n
```
数组的大小
```c
int score[10];
sizeof(score); // 40，一个int为4B，10个占用40B
sizeof(int); // 4

int length = sizeof(score)/sizeof(int); // 可以通过这种方式计算出数组长度

// 注意：即使数组越界，sizeof依然会返回一个正确的数组元素占用的大小值
// 例如，score[10000]，下标越界，但是sizeof依然可以返回4
int len = sizeof(score)/sizeof(score[10000]); 
```
数组初始化
```c
int a[10] = {1,2,3,4,5,6,7,8,9,0};
int b[10] = {1,2,3};  // 前3个赋值，后7个全部为默认0；
int a[10] = {0}; // 10个元素全部赋值为0
int a[] = {1,2,3,4,5};; // 未指定数组长度，只指定成员
int a[10] = {1}; // 第0个为1，其余9个全部为0

```
数组的内存结构
```c
score   // 是一个地址，也可以视作是一个指针
score[0]    // 是一个值，数组元素的值
&score[0]   // 是一个地址，内存地址

printf("%X\n",&score[0]);
```
> 大端方式，内存地址存放数据0000 0063 → 和正常的思维方式相同   
> 小端方式，内存地址存放数据6300 0000 → 和正常思维方式相反   
> 一般内存默认按字节编址，一个字节8位，也就是十六进制的两位，   
> 也就是说，一个内存地址里面可以放8位的数据。   

```c
// 例1、输入10个值存到数组，找到其中的最大值；

// 例2、数组逆置
#include <stdio.h>
int main()
{
    int arr[10] = {1,2,3,4,5,6,7,8,9,0};
    for(int i=0;i<10;i++>)
    {
        printf("%d\n",arr[i]);
    }
    int i = 0;
    int j = sizeof(arr)/sizeof(arr[0]);
    while(i<j>)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++;
        j--;
    }
}
```
```
// 例3、冒泡排序
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{
    int arr[10] = {7,4,5,75,345,26,457,623,645,856};
    int len = sizeof(arr) / sizeof(arr[0]) - 1;
    for(int i = 0;i<=len;i++>)
    {   
        for(int j = 0;j<len-i;j++>)
        {   
            if(arr[j]>arr[j+1])
            {   
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1 = temp;]
            }
        }
    }
}
```

二维数组
- 按行优先存储 → 默认情况下
- 按列优先存储

二维数组内存模型
```c
arr
arr[0] // arr和arr[0]、&arr[0][0]指向的是同一个地址
&arr[0][0]

arr[1] // arr[1]和arr[0]相差了一个行元素个数*类型大小的地址单元  
&arr[0][1]
```
二维数组初始化
```c
int a[3][4] = {
    {1,2,3,4},
    {5,6,7,8},
    {9,10,11,12},
    {13,14,15,16}
}
int a[3][4] = {1,2,3,4,5,6,7,8,9,10,11,12}; // 连续赋值
int a[3][4] = {1,2,3,4}; // 部分赋值
int a[3][4] = {0}; //二维数组全部赋值为0
int a[][4] = {1,2,3,4,5,6,7,8}; // []中不定义元素个数，定义的时候必须要初始化赋值；

int a[4][]; // 这种初始化方式会报错
```
```c
例、10名学生、三门成绩，求出每名学生的总成绩、平均成绩、各科平均成绩；

例、二维数组排序
```

多维数组
```c
int a[3][4][5] = {
    {
        {1,2,3,4},
        {5,6,7,8,9},
        {0},
        {0}
    },
    { {0},{0},{0},{0} },
    { {0},{0},{0},{0} }
};
```

字符数组
> 注意：C语言没有字符串，字符串要用字符数组来表示；   

```c
char arr[10];
```
```c
char arr[10] = {'H','E','L','L','O'};

/**
 * 字符串数组的接收有两种
 * %s → 会接收char结束标志之前的字符；
 * %c → 会接收字符数组里全部字符，\0也会打印出来；
 */
```
```c
char c[] = {'a','','b','c'};
printf("%s\n",c); // 打印a
printf("%c\n",c); // 打印a bc 中间空的为空字符

char arr[1000] = {"hello"};
char arr[10] = "hello"; // 这是一个字符串，实际占用了6个字符位置，有一个结尾的标志符\n

char arr[10] = {'h','e','l','l','o'}; // 占用5个字符位置，没有结束标志
printf("%s\n",arr); // 因为arr没有结束标志，所以会打印arr的全部10个元素的值，在hello的后面是一堆乱码
```

```c
// Tips: 空格可以作为scanf的结束标志
scanf("%s",arr); // 输入hello world
printf("%s\n",arr);  //会打印hello，空格会被认为是\0

scanf("%[^\n]",arr);  // 此时输入的值里面就可以带空格，hello world
printf("%s\n",arr); // 此时打印的是hello world
```

字符串追加
```c
#include <stdio.h>
int main(){
    char str[] = "abcdef";
    char str2[] = "123456";
    char dst[100]; // 此时也可以char dst[100] = {0}，这样就可以不用自己加字符串结束符了
    int i = 0;
    while(str[i] != 0)
    {
        dst[i] = str[i];
        i++;
    }
    int j = 0;
    while(str2[j] != 0)
    {
        dst[i+j] = str2[j];
        j++;
    }
    dst[i+j] = 0; // 手动加上字符串结束符
    printf("%s\n",dst);
    return 0;
}
```

函数，产生随机数
```c
#include <time.h>
time_t time(time_t *t);

#include <stdlib.h>
void srand(unsigned int seed);
int rand(void);

srand((unsigned int)time(NULL)); // 添加随机数种子
rand();  // 返回一个0-10000的随机数
```
```c
// 例、双色球

srand((unsigned int)time(NULL));
rand()%33 + 1; // 生成1-33的随机数

for(int i = 0;i<6;i++)
{
    ball[i] = rand()%33 + 1;
    for(int j = 0;j<i;j++)
    {
        if(ball[i] == ball[j])
        {
            i--;
            continue;
        }
    }
}
// Tips: 有\0就是字符串，没有就只是字符串数组
```

字符串处理函数

- （1）、gets() 输入字符串，存入某一个变量中
```c
char ch[100];
gets(ch);       //  类似scanf，要求用户输入内容
printf("%s\n",ch);
```

- （2）、puts()
```c
puts(ch); // 输出一个字符串，自带换行
```
> Tips：若ch[100]，而gets中输入的字符内容多于100，puts会报错;   
> Tips：puts可以输入空格；   
> Tips：返回值，gets(ch)返回字符串的值，puts(ch)返回一个非负数（成功）或-1（失败）；   
```c
int value = puts(ch); // 返回了一个数
char * p = gets(ch); // 返回了一个字符串，和ch的字符串一致
```




