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

#### 4.3.1、编译过程
- 预处理（Pre-processing）：宏定义展开等；
- 编译（Compiling）：高级语言 -> 汇编语言
- 汇编（Assembiling）：汇编语言 -> 二进制文件
- 链接（Linking）：将二进制文件和引用库链接等；

Tips：
- 编译器并不是把函数模板处理成能够处理任何类型的函数
- 函数模板通过具体类型产生不同的函数
- 编译器会对函数模板进行两次编译，
    - 在声明的地方对模板代码本身进行编译，
    - 在调用的地方对参数替换后的代码进行编译。

#### 4.3.2、函数模板，及其局限性
模板函数有局限性，对于一段逻辑而言，并非所有的类型都可以处理。  
此时可以进行模板的重载，对某些特定类型提供具体化模板
```cpp
// 1、定义一个Person类
class Person{
    public:
    Person(string name,int age){
        this->name = name;
        this->age = age;
    }
    string name;
    int age;
}

// 2、定义一个模板函数
template<class T>
bool myCmp(T &a, T &b){
    if(a == b){return true;}
    return false;
}

// 3、自定义数据类型的模板函数
// 注意，具体化类型后的函数，会优先匹配，通用类型的函数则后匹配
template<> bool myCmp<Person>(Person &a, Person &b){
    if(a.age == b.age){return true;}
    return false;
}
```
——————————————————————————————————————————      

### 4.4、类模板

类模板和函数模板的定义类似；    
类模板用于实现类所需数据的类型参数化；  

注意：类模板定义后，在定义类的时候，需要声明具体类型，不支持自动推导类型；

```cpp
template<class NameType, class AgeType>
class Person{
public:
    Person(NameType name, AgeType age){
        this->mName = name;
        this->mAge = age;
    }
    void showPerson(){
        cout << "name: " << this->mName << endl;
        cout << "age: " << this->mAge << endl;
    }
    NameType mName;
    AgeType mAge;
}

void test(){
    // 下面的语句错误，类模板不能进行类型的自动推导，需要声明
    Person P1("奥巴马",100);

    // 下面的语句正确；
    Person<string,int> P1("特朗普","70");
    P1.showPerson();
}
```

#### 4.4.1、类模板做函数参数
```cpp
// 1、类模板做函数参数，指定其具体的类型
void DoBusi(Person<string,int>& p){
    p.mAge += 20;
    p.mName += "_123"
    p.showPerson();
}
void test_2(){
    Person<string,int> p("希拉里", 30);
    DoBusi(p);
}

// 2、不指定类型，参数模板化 -> 其实就是定义一个函数模板
template<class T1,class T2>
void DoWork(Person<T1,T2> &p){
    //  使用typeid，可以获取到当前对象所属类的信息；
    //  name表示当前类型的名称
    cout << typeid(T1).name() << endl;
    cout << typeid(T2).name()<< endl;
}
void test_3(){
    Person<string,int> p("奥特曼",200);
    DoWork(p);
}

// 3、整体模板化
//      直接将Person也省去，作为模板指定
template<class T>
void DoPush(T &p){
    cout << typeid(T).name() <<endl;
    p.showPerson();
}
void test_4(){
    Person<string,int> p("铁甲", 18);
    DoPush(p);
}

```

#### 4.4.2、类模板派生类模板

```cpp
// 父类
template<class T>
class Base{
    T m;
}

// 子类继承父类的时候，对父类的T指定类型；
template<class T>
class Child_1: public Base<double>{
public:
    T mParam;
}
void test_5(){
    Child_1<int> d2;
}

```

#### 4.4.3、类模板类内实现、类外实现

```cpp
// 1、类内实现，其实就是刚才我们说的类模板的普通形式
template<class NameType, class AgeType>
class Person{
public:
    Person(NameType name,AgeType age){
        this->mName = name;
        this->mAge = age;
    }
    void ShowPerson(){
        cout << this->mName <<"   "<< this->mAge <<endl;
    }
    NameType mName;
    AgeType mAge;
}

// 2、类外实现，在类定义的时候，只声明，在后面再对成员进行定义
// 2.1、首先，定义类，类的成员只进行声明
template<class T1, class T2>
class Person{
public:
    Person(T1 name, T2 age);
    void showPerson;

public:
    T1 mName;
    T2 mAge;
}

// 2.2、接着，定义成员函数，此时和函数模板写法基本一致；
template<class T1, class T2>
Person<T1,T2>::Person(T1 name, T2 age){
    this->mAge = name;
    this->mAge = age;
}

template<class T1, class T2>
void Person<T1,T2>::showPerson(){
    cout << this->mName <<"   "<< this->mAge <<endl;
}
```



——————————————————————————————————————————      

## 5、C++类型转换

——————————————————————————————————————————      

## 6、异常

——————————————————————————————————————————      

## 7、输入和输出流