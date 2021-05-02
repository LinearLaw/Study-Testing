#include <iostream>
#include <set>

using namespace std;

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
		int c = a.count(6);
		cout << c << endl;
		// cout << *a.end() << endl; // 本句会直接报错
	}

	// *iter
	void find3() {
		auto iter2 = a.begin();
		while (iter2 != a.end())
		{
			cout << *iter2 << endl;
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

void testFind() {
	SetFind temp;

	cout << "-----1、find----" << endl;
	temp.find1();
	
	cout << "-----2、count----" << endl;
	temp.find2();

	cout << "-----3、*iter----" << endl;
	temp.find3();

	cout << "-----4、lower_bound----" << endl;
	temp.find4();

	cout << "-----5、upper_bound----" << endl;
	temp.find5();
}