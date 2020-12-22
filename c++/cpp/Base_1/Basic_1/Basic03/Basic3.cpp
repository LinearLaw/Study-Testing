#include <iostream>

using namespace std;

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


int main()
{
	test03();
}