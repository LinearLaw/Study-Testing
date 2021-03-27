## 8、STL

STL六大组件
- 容器：STL容器是一种class Template

- 算法：STL算法是一种function Template

- 迭代器：重载了operator*，operator->，operator++，operator--
    - 是一种class template

- 仿函数：从实现角度来说，仿函数是重载了operator()的class或class Template

- 适配器：用来修饰容器 or 仿函数 or 迭代器

- 空间配置器：负责空间的配置和管理。
    - 配置器就是一个实现了动态空间配置、空间管理、空间释放的class template

> 容器通过空间配置器获得数据存储空间；  
> 算法通过迭代器存储容器中的内容；  
> 仿函数用于协助算法完成不同的策略变化；    
> 适配器用来修饰仿函数；    

————————————————————————————————————————————————————————        

特点：
- C++自带
- 数据和操作分离
    - 由迭代器来作为数据和操作之间的粘合剂
- 高重用性、高性能、高移植性、跨平台

————————————————————————————————————————        

六大组件中，有三个是最重要的
- 容器
    - 常用数据结构
        - 数组 array
        - 链表 list
        - 树 tree
        - 栈 stack
        - 队列 queue
        - 集合 set
        - 映射表 map

    - 两类
        - 序列式容器：强调值的排序，每个元素固定位置
            - Vector、Deque、List
        - 关联式容器：非线性树结构，二叉树，各个元素之间没有严格物理上的顺序关系，
            - 在值中选择一个值作为关键字key，关键字对值起索引作用
            - set/multiset、map/multimap

- 算法
    - 有限步骤解决逻辑或数学上的问题

    - 两类
        - 质变算法：运算过程中会更改区间内元素的内容
            - 拷贝、替换、删除
        - 非质变算法：运算过程中不会改变区间内的元素内容
            - 查找、计数、遍历、寻找极值

- 迭代器
    - 提供一种方法，使之能够依序寻访容器内各个元素
    - 种类：
        - 输入迭代器：只读访问，++、--、==、!=
        - 输出迭代器：只写，++
        - 前向迭代器：读写，并向前推进，++、==、!=
        - 双向迭代器：读写，并可向前or向后操作，++、--
        - 随机访问迭代器：读写，并支持随机访问，++、--、[n]、-n、<、<=、>、>=

————————————————————————————————————————        

### 8.1、string
#include <string>

- char* 是指针，string是一个类
    - string封装了char*，是一个char*的容器
- 无需考虑内存释放和越界。

————————————————————————————————————————        

1、string构造函数
```cpp
// 1、创建一个空的字符串
string a = string();
string b;

// 2、用string对象初始化另一个string对象
string b = string(a);
string b(a);

// 3、用char*初始化
char *s = "12315";
string c = string(s);

// 4、如果是单个char，需要指定该char要初始化的字符数
char c = 'b';
string d = string(1,c);

/*  5、直接初始化
    string重载了operator= 支持char*、string&、char
*/
string str1 = "12325435";
string str2 = str1;
```

另外，还可以用assign函数给string赋值

- 1、把字符串s赋给当前的字符串
    - string& assign(const char *s);

- 2、把字符串s的前n个字符赋给当前的字符串
    - string& assign(const char *s, int n);

- 3、把字符串s赋给当前字符串
    - string& assign(const string &s);

- 4、用n个字符c赋给当前字符串
    - string& assign(int n, char c);

- 5、将s从start开始n个字符赋值给字符串
    - string& assign(const string &s, int start, int n);

————————————————————————————————————————        

存取字符
- str[index]
- str.at(index)

> Tips：两者的区别在于，如果发生了越界，    
>   str[index]会令程序直接挂掉，at不会，at会抛出一个异常       

————————————————————————————————————————        

字符的拼接
```cpp
// 1、重载了+=操作符，可以拼接string、char*、char
string str1 = "a";
char c = 'b';
char* cstar = "cddd";

string str4;
str4 += str1;
str4 += c;
str4 += cstar;

// 2、使用append方法，将字符拼接到当前字符串的末尾
//      也是支持char*、string、char
str4.append(str1);
str4.append(1,c);   // char必须要带上字符个数
str4.append(cstar,2);   // char*可以带上要拼接的字符串个数
```
————————————————————————————————————————        

字符串的比较
- compare函数，可以比较string，也可以比较char*
    - 对于两个字符串，比较的是字符的ASCII码值，区分大小写。 

```cpp
string s1 = "abc";
string s2 = "abcd";

/*  s1 == s2 相等返回0
    s1 > s2 大于返回1
    s1 < s2 小于返回负数
 */
s1.compare(s2);  

```

————————————————————————————————————————        

字符串的子串
- substr(int pos = 0, int n = npos)
    - 返回由pos开始的n个字符组成的字符串

- find(const string& str, int pos = 0)
    - 在字符串中从pos的位置开始查找str字符
    - find(const char* s, int pos, int n)  支持指定s的n个字符
    - 支持char*、char、string
    - 返回查到的字符在字符串中位置的索引值

- rfind(const string& str, int pos = npos)
    - 从npos开始查找str出现的最后一次的位置。
    - rfind(const char* s, int pos, int n)  支持指定s的n个字符
    - 支持string、char*、char
    - 返回索引值

- replace(int pos,int n, const string& str);
    - 从pos位置开始的n个字符，替换成str
    - 支持string，char*

```cpp
string s1 = "abcdebc";
string s2 = s1.substr(1,3); // 截取了从1到3，即bcd


int pos1 = s1.find("bc");   // 查首个
int pos2 = s1.rfind("bc");  // 查最后一个

string s3 = "hello";
s3.replace(1,3,"123456");  // h123456o
```
————————————————————————————————————————        

string的插入和删除
- insert(int pos, const char* s) 
    - 在pos位置插入字符串s
    - 支持char*、string

    - insert(int pos, int n, char c)  从pos位置插入c的前n个字符

- erase(int pos, int n = npos)
    - 从pos开始删除n个字符

```cpp
string s1 = "hello";
s1.insert(1,"111"); // h111ello

s1.erase(1,3); // hello
```
————————————————————————————————————————        

### 8.2、vector

vector和array很像，但是array是静态空间，一旦配置了之后就不能改变，  

如果要array需要大一点或者小一点的空间，
- 首先，要申请一块新的更大的空间，    
- 然后，将原有空间的数据移动到新的空间。  
- 最后，释放原有的空间。  

vector是动态空间，  
随着元素的加入，内部会自动扩充空间以容纳新的元素，  
vector的运用对于内存的合理利用与运用的灵活性有很大帮助。    

———————————————————        

vector的实现，关键在于对大小的控制和重新配置时的数据移动效率；  

- 普通的指针可以作为vector的迭代器。  
- vector提供随机访问迭代器。  

———————————————————        

vector使用连续线性空间，
- 使用迭代器_Myfirst和_Mylast指向配置得来的连续空间中目前已被使用的范围；
- 使用迭代器_Myend指向整块连续内存空间的尾端。

———————————————————        

一个vector的容量 ≥ 加入元素的所有大小  -> 为了将来的扩充；  

Tips：
vector会动态增加大小，  
vector满载时，会申请更大的内存空间，然后将数据拷贝到新空间，释放原空间。    
如果对vector操作导致了空间的重新配置，则指向原vector的所有迭代器都会失效。  

```cpp
#include <iostream>
#include <vector>

using namespace std;

int main(){
    vector<int> v;
    for(int i = 0;i<10;i++){
        v.push_back();

        // v.capacity()  表示容器的容量
        cout << v.capacity() << endl;
    }

    return EXIT_SUCCESS;
}
```



————————————————————————————————————————        
### 8.3、deque



————————————————————————————————————————        
### 8.4、stack


————————————————————————————————————————        
### 8.5、queue


————————————————————————————————————————        
### 8.6、list

list，其实就是一个双向链表。

```cpp
class Person
{
public:
	Person(string name, int age, int stature)
	{
		this->m_name = name;
		this->m_age = age;
		this->m_stature = stature;
	}

	string m_name;
	int m_age;
	int m_stature;//身高
};


void test(){
    list<Person> L;     // 初始化list
    /*
        1、初始化
        list<T> lst
        list(begin,end)     将[begin,end)区间的元素拷贝到list中
        list(n,elem)        初始化list，并生成n个elem的元素
        list(const list &lst)   拷贝另一个list
    */

    Person p1("马  云", 45, 160);
	Person p2("马华腾", 47, 178);
	Person p3("周宏伟", 43, 177);
	Person p4("李彦宏", 39, 185);
	Person p5("任正非", 45, 180);
	Person p6("王健林", 45, 170);

    /*
        2、插入删除
        L.push_back(x)  尾插法
        L.push_front(x) 头插法
        L.pop_back()    从末尾删除
        L.pop_front()   从头部删除

        L.insert(pos,elem)      pos是一个迭代器，在这个位置插入elem
        L.insert(pos,n,elem)    在pos的位置插入n个elem
        L.insert(pos,begin,end) 在pos的位置，插入区间[begin,end)的元素

        L.clear()   移除容器中的所有数据
        L.erase(begin,end)  移除从begin到end区间的数据
        L.erase(pos)    移除pos位置的数据，返回下一个数据的位置

        L.remove(elem)  在list中删除所有和elem匹配的元素

    
    */
	L.push_back(p1);
	L.push_back(p2);
	L.push_back(p3);
	L.push_back(p4);
	L.push_back(p5);
	L.push_back(p6);


    /*
        3、大小操作
        L.size()    返回容器中元素个数
        L.empty()   返回布尔，判断容器是否为空
        L.resize(num)   将容器的长度重置为num
            容器变长，则用默认值填充新位置
            容器变短，超出部分会被直接删除
        L.resize(num,elem)  重置长度，并指定elem为填充值
    */

   /*
        4、赋值操作
        L.assign(begin,end)     将[begin,end)中的数据，拷贝复制给本身
        L.assign(n,elem)    将n个elem拷贝赋值给自身

        lst = lst2   复制lst2到lst

        L.swap(lst)     两个链表的元素互换

        L.front()   返回链表头结点
        L.back()    返回末尾元素

        L.reverse() 链表反转
        L.sort()    链表排序

   */

}



```

————————————————————————————————————————        
### 8.7、set/multiset


### 8.8、pair对组

pair可以跟set、map一起使用，用来作为value值；

set.insert返回的值，就是一个pair。

```cpp
/*  set.insert，返回的值是一个pair，
        pair的first，是一个迭代器，
        pair的second，是insert的结果，成功true，失败false
 */
set<int> s;
pair<set<int>::interator, bool> ret = s.insert(100);
if(ret.second){
    cout << "插入成功 ：" << *ret.first << endl;
}else {
    cout << "插入失败 ：" << *ret.first << endl;
}


```

```cpp
/*
    对组，将一对值，合成一个值，这对值可以具有不同类型，
        两个值分别用 first 和 second 访问
*/
pair<string,int> p(string("123424",100));
cout << p.first << endl;  // 打印123424
cout << p.second << endl;



```

————————————————————————————————————————        
### 8.8、map/multimap

```cpp
vector<int> nums = {-1,2,-1,4,5};

multimap<int, int> resSet;
int i = 0;
for (i = 0; i < size; i++){
    // multimap必须要用 insert 才能插入元素。
	resSet.insert({ nums[i] , i });
}

// 使用find函数，传入一个key值，会返回一个迭代器
int key = -1;
multimap<int,int>::iterator it = resSet.find(key);
for (it; it->first != key; it++)
{
    /* 对于multimap迭代器，迭代出来的结构，有两个域
        it->first   key
        it->second  value
    */	
    cout<<"num="<< it->first <<";index="<< it->second <<endl;
}



```


————————————————————————————————————————        
## 9、常用算法

### 9.1、函数对象


————————————————————————————————————————        
### 9.2、谓词


————————————————————————————————————————       