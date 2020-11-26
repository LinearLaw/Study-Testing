# Python

## Step 1

## 1、python，按照不同的实现方式，

- Cpython → C++
- Jython → Java
- IronPython → .Net

## 2、PyCharm 集成开发环境

py有两个分支
- Py2 ------ print "hello world"
- Py3 ------ print("hello world")

——————————————————————————————————————————————

①、#号代表单行注释，'''代表多行注释。       
```py
#coding = utf-8  
```
声明当前文件的编码，在py2中中文可能会出问题，所以需要一个声明。     
Py3没有这个问题。

——————————————————————————————————————————————

②、'''  多行注释，可以换行  '''

——————————————————————————————————————————————

③、字符串单引号还是双引号，无所谓。
```py
str4 = ''' 三引号也可以用来标记一个字符串，支持换行 '''
```
——————————————————————————————————————————————

## 3、函数return

边解释边执行，自上而下。        
定义域用缩进来进行划分。

```py
#coding:utf-8

def print_value(a,b)
	print("xxx")
	print(a)
	print(b)
	return 10
	
ret = print_value(100,200)
print(ret)
```

如果函数没有return，那么会返回一个None，类型是NoneType。
```py
#coding:utf-8 
def print_value(a,b)
	print(a)

# ret的值为None，类型是NoneType	
ret = print_value(100,200)

```
——————————————————————————————————————————————


## 4、引包

导入一个包之后，该包会自上而下执行。    
同时，引用包里面的函数，要带包名调用。  

```py
# test4.py    
def abc()
    print("1235423")

# test5.py  
import test4
test4.abc()
```
——————————————————————————————————————————————

很不爽，如何才能去掉包前缀？

```py

# 1、使用from xx import xx可以单独引入包里面的某一个函数
#       用这种方式引入之后，可以不用再加前缀
from test4 import abc
abc()

# 2、引入所有函数
from test4 import *

```

——————————————————————————————————————————————

## 5、\_\_name__

\_\_name__ 表示当前模块的名称，在当前.py文件中可以打印
- main      当前py文件作为主执行函数时，打印main
- test4     当前py文件作为包引用时，打印当前py文件名

```py
if __name__ == '__main__':
    print "在这里可以写对当前模块的单元测试"
```

——————————————————————————————————————————————

## 6、空的函数体

```py
# 1、如果出现了空的函数体，会报错。
def func1():

x = func1()
print(x)

# 2、如果实在想要空函数体，可以用pass
#   pass之后得到的函数返回值是None
def func2():
    pass
y = func2()
print(y)

```
Tips：pass不仅仅在这里用，在if这些语句也可以用，让程序继续往后执行。

——————————————————————————————————————————————

## 7、数据类型

- 整形
- 长整型
    Tips：在C语言中，长整型是有长度限制的，
    如果遇到很长的数据，需要分多段存储。
    而在py中并没有限制，超长数据会自动进行多段存储，编程感知不到。
- 浮点型
- 复数，例如2+3j，分实部和虚部
- 布尔，True、False
- 字符串

——————————————————————————————————————————————

## 8、变量引用计数决定垃圾回收

引用计数

```py
# 1、定义一个a变量的值，之后改变这个值，此时，a原来指向的字符串的引用计数会归零
#   归零之后，下一次的垃圾回收会将该部分的内存回收
#   这个由py的解释器完成，感知不到。
#   所以py定义变量，无需再考虑内存泄漏的问题。
a="abcdef"
a="123"
```

——————————————————————————————————————————————

有一些特殊情况。

```py
# 我们可以用 id(xx) 获取到变量的十进制的地址。
a=10
b=10

''' 
    在这里，id(a)和id(b)打印的是相同的值。
    说明他们在同一个地址。
'''
print id(a)
print id(b)

# ---------------------------------------------------

c=999
d=999
'''
    而在这里，id(c)和id(d)的地址却是不同的
'''
print id(c)
print id(d)

```

在py中，维护了一个0-255的小整形池，     
在变量定义了小整形时，无需多开辟新的空间，直接引用当前已准备好的整形。  

但是在大数时，每次都需要重新开辟空间。

——————————————————————————————————————    


## 9、标准输入输出

- print ----- 输出，相当于printf
- raw_print ----- 输入，相当于scanf

```py
# 1、使用raw_print获取输入，类似scanf
myprint = raw_print("请输入一个数字\n")
print(myprint)

# 2、可以接收一个元组
print "Hello %s %d" %("123",999)

# 3、直接字符串拼接一个字符串变量，没有问题
name = "abc"
print "name="+name  # 正常

# 4、注意，拼接的变量如果不是字符串，就会报错，
name = 123
print "name="+name   # 报错
```

——————————————————————————————————————    


## 10、局部变量和全局变量

```py

# 1、这里表明定义了一个变量num
num = 10

def getValue():
    # 2、在函数内部，因为py不需要声明，所以
    #   在这里其实是创建了一个新的局部变量num，而非引用外面的num
    num = 100

# 3、打印的结果是10
getValue()
print(num)

```

那么，假如确实要访问外部的变量怎么办？      
需要用到 global 声明
```py   

num = 10
def getValue():
    global num = 100

# 这时候打印的num就是100了
getValue()
print num

```

——————————————————————————————————————    


## 11、运算

```py
# 等价于a的b次方
a ** b 

# 等价于a / b向下取整
a // b

# 等价于a != b，这种写法已经废弃了，以前的代码可能看得到
a <> b

```

Tips：      
py不支持++和--，但是+=和-=是支持的。

```py 
 
# 报错
a++
b--

# 不报错
a += 1
b -= 2
 
```
——————————————————————————————————————    


if else 语句
```py
if a>b:
    print(1)
elif a==b:
    print(2)
else:
    print(3)
```

——————————————————————————————————————    


## 12、list

列表，内部实现其实是链表，支持混合类型。

```py
my_list = [1,2,3,4]
for num in my_list
    print(num)
```

Tips：   
进入python环境后，不支持linux语句，   
但是如果是ipython环境，支持linux语句。   
——————————————————————————————————————    

```py
# 1、count，返回该元素在列表中出现的次数
my_list.count(3)

# 2、len，返回列表的长度
a = len(my_list)

# 3、往列表中插入元素
my_list.append(444)

# 4、删除某一个元素
my_list.remove(6)

# 5、将list逆序
my_list.reverse()

# 6、对list进行排序
my_list.sort()


# 7、一种遍历的简洁写法
for _ in my_list
    print(_)

# 8、两个list可以直接做加法合成
my_list + my_list   # 返回的是两者的并集

```
——————————————————————————————————————    

```py
my_list[0]
my_list[1]
my_list[2:]  # 从2到末尾
my_list[-1]  # 访问倒数第一个元素

my_list[1:3]  # 获取1到3，左闭右开
```

——————————————————————————————————————    

## 13、range

```py
range(10) # 返回的是一个[0-9]的数组

range(1,5)
range(2,5)

```
——————————————————————————————————————    

```py
def changeValue(a):
    a = 100

a = 200
changeValue(a)  # a在这里是值传递
print(a)  # 打印200
```
```py
def changeValue(l):
    l[0] = 100
my_list = [1,2,3,4]
changeValue(my_list) # my_list在这里是引用传递
print(my_list[0]) # 打印100
```

Tips：   
传递复杂类型时，list，dict、tuple、类、对象，都是引用传递。   
传递基本类型时，int、float、double都是值传递。   

——————————————————————————————————————    

## 14、链表

```py
# 按照一般的写法来说，这样写
def make_one_list(num):
    my_list = []
    for i in range(num)
        value = doubleValue(i)
        my_list.append(value)
    return my_list

def doubleValue(num):
    return 2*num

# 上面的写法可以简化成下面这种形式
def make_one_list2(num):
    return [doubleValue(i) for i in range(num)]

# Tips：在这里要注意一个问题，就是doubleValue必须要有return的值，否则会报错。

if __name__ == '__main__'
    my_list = make_one_list(5)
```
——————————————————————————————————————    

## 15、元组tuple
元组tuple形式和list是一样的，但是，   
元组一旦初始化，其元素就不可以更改，只能读取。   

```py
# tuple的方法和list的方法基本一样
len([1,2,3])  # list返回长度

3 in [1,2,3]  # list判断存在性，返回true

# tuple元组的定义用小括号
seq = (1,2,3)
seq[0]   # 返回第一个元素
seq[1]

list(seq)
mytuple = ()
type(mytuple)  # 打印tuple
help(tuple)  # 可以查看tuple的相关api


# 元组可以用解构赋值
def retValue:
    name = "zhang"
    uid = 100
    return (name,uid)

name,uid = retValue() 
print(name)
print(uid)
```

## 16、字典
```py
# key值要求必须是字符串，value的类型随便
dict = {
    'a':'b',
    'c':'d'
}

dict.clear()  

```
