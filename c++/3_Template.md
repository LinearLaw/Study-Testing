## 4、模板

用模板，是为了实现泛型。    

有两种模板机制
- 函数模板
- 参数模板

模板把函数或类要处理的数据类型参数化，表现为参数的多态性；  
模板用于表达逻辑结构相同，具体数据元素类型不同的数据对象的通用行为；

```cpp
// 例：交换值函数
void SwapInt(int& a, int& b){
    int temp = a;
    a = b;
    b = temp;
}
void SwapChar(char& a, char& b){
    c
}

```