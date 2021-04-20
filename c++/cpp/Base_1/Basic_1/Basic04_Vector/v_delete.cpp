#include <iostream>
#include <vector>

using namespace std;

void printVector(vector<int>& v) {
	for (int i = 0; i < v.size(); i++) {
		cout << v[i] << endl;
	}
}

// 1、遍历删除，删除某一个元素，并调整原有数组
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
// 2、使用erase，删除迭代器指向的元素
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

// 3、插入
void InsertV() {
	vector<int> a = { 12,23,532,34,6 };

	int temp = 534;
	a.insert(a.begin() + 3, temp); // 在a[2]的后面插入534，注意索引规则
	printVector(a);

}


void TestVDelete() {
	cout << "----1、普通遍历删" << endl;
	DeleteOne();

	cout << "----2、用erase删" << endl;
	DeleteTwo();

	cout << "----3、insert" << endl;
	InsertV();
}