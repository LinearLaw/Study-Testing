#include <iostream>
#include <unordered_set>

using namespace std;

// set如果已经插入了一条数据，再执行insert，没有效果
void insertTest() {
	unordered_set<int> s;

	s.insert(500);
	s.insert(500);

	for (auto& a: s) {
		cout << a << endl;
	}
}