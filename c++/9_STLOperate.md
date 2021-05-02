# STL Operate

- array
- **vector**

- string

- **list**  -> 双向链表

- **set**
  - set

  - multiset

- unordered_set
  - unordered_set
  - unordered_multiset

- **map**
  - map  -> 基于红黑树
  - multimap  -> 基于哈希表，其实就是hash_map
    - 首先分配一大块的内存，分成多个桶，然后利用hash函数将key映射到不同的桶中。

- unordered_map
  - unordered_map
  - unordered_multimap

- **queue** 
  - queue  -> 队列
  - priority_queue  -> 优先队列
- deque   -> 双向开口的数组

- stack  -> 单向开口的数组



## vector

### 遍历

```c++
#include <vector>
#include <iostream>
using namespace std;

int main(){
    vector<int> t = {12,235,434,5,3};
    int t1 = 20;
    int t2 = 30;
    int t3 = 40;
    vector<int> s = {t1,t2,t3};
    for(int i = 0; i< t.size();i++){
        cout << t[i] << endl;
    }
}

```

### 排序

```C++
/*	直接使用<algorithm>中的sort函数进行排序
	如果遇到了结构体或者类，需要使用谓词作为比较依据

 */
class Player{
public:
	int score;
	int id;
    Player(int i, int s):id(i),score(s){}
}
class VSortFunctor{ // 谓词，作为排序依据 -> 这个操作要记住
public:
    bool operator()(Player& a, Player& b){
        return a.score < b.score;
    }
}

int main(){
    Player p1(1,233);
    Player p2(2,154);
    Player p3(3,333);
    Player p4(4,666);
    
    vector<Player> pbox = {p1,p2,p3,p4};
    sort(pbox.begin(),pbox.end(),VSortFunctor()); // 第三个参数传入上面定义的谓词
}

```



### 删除元素

```C++
#include <iostream>
#include <vector>
using namespace std;

void printVector(vector<int>& v) { 
    for (int i = 0; i < v.size(); i++) { cout << v[i] << endl; } }

//1、遍历删除，删除某一个元素，并调整原有数组
void DeleteOne() {
	vector<int> a = { 12,23,534,532,34,6 };

	int temp = 534;
	int k = 0;
	for (int i = 0; i < a.size();i++) {
		if (a[i] != temp) { a[i-k] = a[i]; }
		else { k++; }
	}
	for (int i = 0; i < k;i++) { a.pop_back(); }

	printVector(a);
}

// 2、使用erase，删除迭代器指向的元素 -> 这个操作一定要记住
void DeleteTwo() {
	vector<int> a = { 12,23,534,532,34,6 };

	int temp = 534;
	int k = -1;
	for (int i = 0; i < a.size(); i++) {
		if (a[i] == temp) {
			k = i;
			break;
		}
	}
	if (k != -1) {
		a.erase(a.begin() + k);
	}
	printVector(a);
}

int main(){
    cout << "1、普通遍历删" << endl;
	DeleteOne();

	cout << "2、用erase删" << endl;
	DeleteTwo();
}

```

### 查找

```C++
vector<int> a = { 12,23,534,532,34,6 };
// 第一种
for(int i = 0; i< a.size();i++){
    cout << a[i] << endl;
}

// 第二种
for(const int& b: a){
    cout << b << endl;
}

```

### 插入

```C++
void InsertV() {
	vector<int> a = { 12,23,532,34,6 };

	int temp = 534;
	a.insert(a.begin() + 3, temp); // 在a[2]的后面插入534，注意索引规则
	printVector(a);
}
```



## set

### 遍历

```c++
#include <set>
#include <iostream>

int main(){
    set<int> a;
    a.insert(100);
    a.insert(200);
    a.insert(150);

    for(auto const& temp : a){ cout << temp << endl; }
}


```

### 排序

```C++
/*	set的底层基于红黑树，
	在将某一数据insert到一个set容器中之后，set会自动进行排序
	对于结构体和类的变量，需要加入谓词（仿函数）来作为排序依据
*/
class Player { // 玩家
public:
	int id;
	int score;
	Player(int i, int s) :id(i), score(s) {}
};
class SPlayer { // 谓词，作为set的比较依据
public:
	bool operator()(const Player& a, const Player& b) {
		return a.score > b.score;
	}
};

void sortSetByFunctor() {
	set<Player, SPlayer> t; // set的第二参数可以接受一个谓词，作为比较依据
	Player a1(1, 100);
	Player a2(2, 250);
	Player a3(3, 400);
	Player a4(4, 202);

	t.insert(a1);
	t.insert(a2);
	t.insert(a3);
	t.insert(a4);

	for (const Player& temp : t) {
		cout << "id:" << temp.id << " score:" << temp.score << endl;
	}
}

```

### 删除元素

注意，set中的元素一旦加入不能修改。

set的iterator是一种const_iterator，不可改变。

因为set是按照key值来进行排序的，元素值就是key值，改变元素会导致set的组织被破坏。

```c++
/*
set<int> s;

s.insert(123); 	// 插入一个元素

s.clear(); 		// 清除所有元素

s.erase(s.begin());	// 传入一个迭代器，删除迭代器指向的元素
s.erase(s.begin() , s.begin()+3); // 删除迭代器区间内的元素，规则是左闭右开 [start,end)

s.erase(123); // 传入的是一个elem，该值会被删除
*/


```



### 查找

```c++
/*
set<int> s;

find - 返回一个迭代器，如果没找到返回s.end()
	set<int>::iterator iter = s.find(key);
count - 返回key在s中的数量，用来判断是否存在
	int c = s.count(key);  

注意以下两个，其实效果就只差一个等号。
lower_bound - 返回第一个key >= keyElem元素的迭代器
upper_bound - 返回第一个key > keyElemu元素的迭代器
	auto iter = s.lower_bound(keyElem)
	auto iter = s.upper_bound(keyElem)

equal_range(keyElem) - 返回 key == keyElem 的上下限的两个迭代器
*/
class SetFind
{
public:
	set<int> a;
	SetFind() {
		a.insert(34);
		a.insert(54);
		a.insert(34);
		a.insert(45);
		a.insert(6);
		a.insert(89);
		a.insert(99);
	};
	~SetFind() {};

	// find
	void find1() {
		auto iter1 = a.find(6);
		cout << *iter1 << endl; // 取值用*
	}

	// count
	void find2() {
		int c = a.count(6); // 返回6的数量
		cout << c << endl;
		// cout << *a.end() << endl; // 本句会直接报错
	}

	// *iter
	void find3() {
		auto iter2 = a.begin();
		while (iter2 != a.end())
		{
			cout << *iter2 << endl; // 遍历时，使用*iter2进行解引用
			iter2++;
		}
	}

	// lower_bound
	void find4() {
		auto iter3 = a.lower_bound(89); // 打印 >= 89 的元素
		while (iter3 != a.end()) {
			cout << *iter3 << endl;
			iter3++;
		}
	}

	// upper_bound
	void find5() {
		auto iter4 = a.upper_bound(89);
		while (iter4 != a.end()) {
			cout << *iter4 << endl; // 打印 > 89 的元素
			iter4++;
		}
	}
};

```

### 插入

```
/*
set使用了红黑树作为底层结构，所以在插入后，红黑树会自动排序，
	默认是按照从小到大的顺序，
	因此set不支持在指定位置的插入，因为插入本身就是得到一个有序的结果。

set<int> s;

s.insert(44);
*/

```



## string

### 按某字符拆分

```


```



### 类型转换

```c++
/*
首先，和int的转换，其实就是ASCII码的转换

1、int -> char
*/
int a = 3;
string b(a + '0');

// C++ 11 to_string方法
int b = 55345;
string c = std::to_string(b);

/* 2、char -> int
	注意这个单引号
*/
char a = '6';
int ai = a - '0';

/* 3、string -> int
	用sstream来转，注意需要引包，#include<sstream>
*/
string ds = "23534523";
istringstream is(ds);
int i;
is >> i;

```



### KMP算法

```


```





## map





## list

双向链表结构

### 插入



### 删除





