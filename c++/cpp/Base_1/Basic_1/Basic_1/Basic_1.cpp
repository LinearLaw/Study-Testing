#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

void Hello01() {
	cout << "hello world" << 123456 << endl;
}

int atk = 200;
void Test02() {
	int atk = 100;

	cout << "局部变量atk直接引用即可：" << atk << endl;
	cout << "用::可以访问到全局变量下的atk" << ::atk << endl;
}

int main()
{
	// 1、hello world
	Hello01();

	// 2、全局变量，作用域
	Test02();
	system("pause");
	return EXIT_SUCCESS;
}