## 4、模板

用模板，是为了实现泛型。减轻编程工作量，增强函数重用性。    

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
    char temp = a;
    a = b;
    b = temp;
}

// 根据不同的类型，一个交换值的函数会出现多个不同类型的函数
//      但是主逻辑都是一样的，
// 所以有没有什么方法可以将他们抽取出来？
// 模板！

template<class T>
void MySwap(T& a, T& b){
    T temp = a;
    a = b;
    b = temp;
}

void test01(){
    int a = 10;
    int b = 20;
    // template可以自动推导参数类型
    MySwap(a,b);

    char c1 = 's';
    char c2 = 'b';
    // template也可以显式指定类型
    MySwap<char>(c1,c2);
}
```
——————————————————————————————————————————      
```cpp
template<class T>
void PrintArray(T arr[], int len){
    for(int i = 0;i<len;i++){
        cout<< arr[i] <<endl;
    }
    cout<<endl;
}

template<class T>
void MySort(T arr[], int len){
    for(int i = 0;i<len;i++){
        for(int j = len-1;j>i;j--){
            if(arr[j] > arr[j-1]){
                T temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
            }
        }
    }
}
```
——————————————————————————————————————————      

### 4.1、函数模板和普通函数 

- 函数模板不能自动类型转化
- 普通函数可以自动类型转化
```cpp
template<class T>
T TplPlus(T a,T b){
    T ret = a + b;
    return ret;
}

int FuncPlus(int a, char b){
    int ret = a + b;
    return ret;
}

void test02(){
    int a = 10;
    char b = 'a';

    // 使用函数模板，此时的a和b都是T类型，但是a和b并不是同一个类型
    // 报错。
    TplPlus(a,b);

    // 对于普通函数而言，传入参数时，会自动进行类型转换。
    // 这里，即使b是char类型，传入之后会被自动转成int类型
    FuncPlus(b,a)
}
```
——————————————————————————————————————————      

### 4.2、调用规则

- 优先考虑普通函数

- 可以通过空模板实参列表的语法限定编译器只能通过模板匹配

- 函数模板可以像普通函数一样被重载

- 如果函数模板可以产生一个更好的匹配，则选择模板

```cpp
/**
	函数模板和普通函数同名，此时的调用规则
		
		1、c++编译器优先考虑普通函数
		2、可以通过空模板实参列表的语法限定编译器只能通过模板匹配
		3、函数模板可以像普通函数那样可以被重载
		4、如果函数模板可以产生一个更好的匹配，那么选择模板
 */
// 1、函数模板
template<class T>
T MyPlus(T a, T b) {
	cout<< "Template Plus" <<endl;
	return a + b;
}

// 2、普通函数
int MyPlus(int a, int b) {
	cout << "Function Plus" << endl;
	return a + b;
}
void test03() {
	int a = 10;
	int b = 10;
	char c = 'a';
	char d = 'b';

	// 1、如果函数模板和普通函数都能匹配，C++编译器优先考虑调用普通函数
	cout << "1:" << endl;
	cout << MyPlus(a,b) <<endl;	// Function Plus

	// 2、如果加上了类型符号，会自动调用函数模板
	cout << "2:" << endl;
	cout << MyPlus<>(a, b) << endl; // Template Plus

	// 3、普通函数可以匹配，但是需要类型转换，
	//	而函数模板可以更好匹配，此时会优先调用函数模板；
	cout << "3:" << endl;
	cout << MyPlus(c, d) << endl; // Template Plus
}
```

——————————————————————————————————————————      

### 4.3、模板的机制