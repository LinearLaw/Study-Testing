#include <iostream>

using namespace std;

/**
	c++编译器优先考虑普通函数
	可以通过空模板实参列表的语法限定编译器只能通过模板匹配
	函数模板可以像普通函数那样可以被重载
	如果函数模板可以产生一个更好的匹配，那么选择模板
 */
template<class T>
T MyPlus(T a, T b) {
	cout<< "Template Plus" <<endl;
	return a + b;
}
int MyPlus(int a, int b) {
	cout << "Function Plus" << endl;
	return a + b;
}
void test03() {
	int a = 10;
	int b = 10;
	char c = 'a';
	char d = 'b';


	cout << "1:" << endl;
	cout << MyPlus(a,b) <<endl;	// Function Plus

	cout << "2:" << endl;
	cout << MyPlus<>(a, b) << endl; // Template Plus

	cout << "3:" << endl;
	cout << MyPlus(c, d) << endl; // Template Plus
}


int main()
{
	test03();
}