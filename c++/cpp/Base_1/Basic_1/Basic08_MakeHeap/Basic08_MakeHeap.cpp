#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include <queue>

using namespace std;

class Mh {
public:
	void testMakeHeap() {
		vector<int> arr = { 2323,3,3,52,54,3,545,45,5 };

		priority_queueFunc(arr);
	}
	// 用优先队列来对数组排序 -> 来完成堆排序
	void priority_queueFunc(vector<int>& arr) {
		priority_queue<int, vector<int>,greater<int>> q;
		for (const int val : arr) {
			q.push(val);
		}
		vector<int> res;
		while (!q.empty()) {
			res.push_back(q.top());
			q.pop();
		}
		showVector(res);
	}

	// 使用set作为排序方法 -> 来完成堆排序
	void setFunc(vector<int>& arr) {
		set<int> s;
		for (const int val:arr) {
			s.insert(val);   // 默认是从小到大排序
		}
		showVector(arr);
	}

	static bool s(int a , int b) {
		return a - b;
	}

	void showVector(vector<int> arr) {
		for (const int val : arr) {
			cout << val << endl;
		}
	}

};


int main()
{
	Mh().testMakeHeap();
}