# C++错误总结

## C2679 cout输出string时会报错
```cpp
#include <iostream>

void main(){
    string a = "hello world";
    cout << a << endl; // 报错，C2679
}
```

**解决方法：引入\<string>**

```cpp
#include <iostream>
#include <string>

void main(){
    string a = "hello world";
    cout << a << endl; // 不报错
}
```

cout是可以输出string的，cout重载了string类型。  

- CString：在MFC和ATL中实现，只有支持MFC的工程才能使用，
    - linux上面不能用CString
    - MFC中使用时不需要引入CString，但是其他程序需要#include \<CString>

- string：标准的C++类库，也是STL中的类库，是C++标准
    
- string.h：C语言中关于字符数组的函数定义的头文件，包含了strlen、strcmp、strcpy等。
    - \<string.h>是C语言中的，和\<string>两者没有关系。

> Tips：    
> cout 函数重载的是string类库中的string类型，而不是string.h和CString中的    