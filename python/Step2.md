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

dict.clear()  # 清空字典
dict.get('a') # 获取key为a的value，相当于dict['a']

for key in dict.keys():
    print(dict[key])

dict.items() 

# 以一个字典来更新另一个字典
dict.update({'name':'123'})
```

Tips：   
如果dict没有某一个key，会自动将这个key插入。   

## 17、随机数

```py
import random
myl = [1,2,3,4,5]

random.choice(myl)  # 返回myl中随机一个元素

random() # 返回一个[0,1)的随机数

random.seed   # 随机数种子
random.shuffle(myl)  # 将myl打乱，返回
random.sample(myl,3)    # 从myl中，随机取三个元素

```

## 18、字符串

注意，字符串是常量，一旦初始化就不可改变。

```py
str1 = "abc"
str[0:2]  # 返回"ab"，左闭右开

str*2  # "abcabc" 生成重复字符串

str2 = r"abc\n"     # 加上r前缀，可以将\n打印出来，而不是换行

help(str)  # 查看str可用的api

str.find(str2,start,end) # 从start到end范围内，找一个子串str2

str.rfind(str2,0,6)     # 从后往前找一个子串
str.index(str2)     # 作用和find一样，但是没有找到的时候就会报错

str.encode(encoding="utf-8")  # 返回按照指定编码格式的字符串
str.decode(encoding="utf-8")

# 把str2换成str3，count是换几个，str不会被改变，而是返回新的字符串
str.replace(str2,str3,count) 

# str表示按照某一种字符来进行划分，2代表划分多少次，整体返回一个list
mylist.split(str=" ",2)

str1.strip() # 去掉空格
str1.rstrip()   # 去掉右空格
str1.lstrip()   # 去掉左空格

```

```py
import os
os.listdir("./")

```