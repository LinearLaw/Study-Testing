# C++错误总结

## C1060 编译器的堆空间不足

## C2679 cout输出string时会报错
```cpp
#include <iostream>

void main(){
    string a = "hello world";
    cout << a << endl; // 报错，C2679
}
```
——————————————————————————————————————————      

**解决方法：引入\<string>**

```cpp
#include <iostream>
#include <string>

void main(){
    string a = "hello world";
    cout << a << endl; // 不报错
}
```

——————————————————————————————————————————      

cout是可以输出string的，cout重载了string类型。  

- CString：在MFC和ATL中实现，只有支持MFC的工程才能使用，
    - linux上面不能用CString
    - MFC中使用时不需要引入CString，但是其他程序需要#include \<CString>

- string：标准的C++类库，也是STL中的类库，是C++标准
  
- string.h：C语言中关于字符数组的函数定义的头文件，包含了strlen、strcmp、strcpy等。
    - \<string.h>是C语言中的，和\<string>两者没有关系。

> Tips：    
> cout 函数重载的是string类库中的string类型，而不是string.h和CString中的    

——————————————————————————————————————————      


## C3859 超过了PCH的虚拟内存范围

——————————————————————————————————————————      

## C3861 “cout”: 找不到标识符

cout、endl都是iostream的函数，
该函数在命名空间std下。

```cpp
#include <iostream>
// 前缀加上std
std::cout<< "Go!" <<std::endl;

// 或者将namespace引入
#include <iostream>
using namespace std;
cout<< "Go!" <<endl;


```
——————————————————————————————————————————      

## C4996 fopen 
'fopen':    
This function or variable may be unsafe.    
Consider using fopen_s instead.     
To disable deprecation, use _CRT_SECURE_NO_WARNINGS.    
See online help for details.    

首行加上
```cpp
#pragma warning(disable : 4996) 

```

——————————————————————————————————————————      



## C2678 没有找到接受“const _Ty”类型的左操作数的运算符(或没有可接受的转换)	

二进制“<”: 没有找到接受“const _Ty”类型的左操作数的运算符(或没有可接受的转换)

原因：

set用来存储类数据，set需要进行数据比较，此时要设定谓词作为比较依据

```
Class Player{
public:
	int score;
	Player(int s):score(s){}
    
}

class SPlayer{
public:
    bool operator()(Player& a, Player& b){
        return a.score < b.score;
    }
}

int main(){
    Player p1(122);
    Player p2(133);
    Player p3(555);
    Player p4(222);
    
   	set<Player,SPlayer> s;
   	s.insert(p1);
   	s.insert(p2);
   	s.insert(p3);
   	s.insert(p4);
    
}
```

