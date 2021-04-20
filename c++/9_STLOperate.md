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



## string





## map





## list





