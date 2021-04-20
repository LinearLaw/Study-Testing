#include <iostream>
#include <set>
#include <vector>
#include <queue>

using namespace std;

// 1、set对结构体进行排序
// 关键是重载 < 运算符
struct Box {
	int val;
	bool operator< (const Box& temp)const {
		return val < temp.val;
	}
};
void sortStructSet() {
	vector<int> a = { 3,4,2,1 };
	set<Box> s;
	for (int i = 0; i < a.size(); i++) {
		Box temp;
		temp.val = a[i];
		s.insert(temp);
	}

	for (const Box& t : s) {
		cout << t.val << endl;	// 1,2,3,4
	}
}

// 2、set对类对象进行排序
// 关键是重载 < 运算符
class R {
public:
	int val;
	bool operator<(const R& t) {
		return val < t.val;
	}
};

void sortClassSet() {
	vector<int> a = { 3,4,2,1 };
	set<Box> s;
	for (int i = 0; i < a.size(); i++) {
		Box temp;
		temp.val = a[i];
		s.insert(temp);
	}

	for (const Box& t : s) {
		cout << t.val << endl;	// 1,2,3,4
	}
}

// 3、使用排序谓词
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

// 4、priority_queue
void sortClassPriorityQueue() {
	vector<int> a = { 3,4,2,1 };
	priority_queue<Box> q;
	for (int i = 0; i < a.size(); i++) {
		Box temp;
		temp.val = a[i];
		q.push(temp);
	}

	while (q.size() > 0) {
		Box t = q.top();
		cout << t.val << endl;  // 4,3,2,1  注意顺序和set是反的
		q.pop();
	}

}

int main()
{
	sortStructSet();
	cout << "---------" << endl;
	
	sortClassSet();
	cout << "---------" << endl;

	sortSetByFunctor();
	cout << "---------" << endl;

	sortClassPriorityQueue();

}