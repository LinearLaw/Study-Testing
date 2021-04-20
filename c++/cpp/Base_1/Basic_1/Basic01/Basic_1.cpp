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

/*--------------------------------------------*/
class A {
public:
	A(int param) {
		mParam = param;
	}
public:
	int mParam;
};
void Test03() {
	A a1(100);
	A* a2 = new A(200);

	/*	不使用指向成员变量的指针，
		直接使用指针，指向某一对象的某一成员，
		如果要访问另一对象的同名成员，要再创建一个指针
	*/
	int *p1 = &a1.mParam; 

	/*	使用指向成员变量的指针，
		此时，不管是a1还是a2的mParam，都可以用这个指针来操作
	*/
	int A::*p2 = &A::mParam;

	cout << "*p1 : " << *p1 << endl;
	cout << "a1.*p2 : " << a1.*p2 << endl; // 对于一个对象，用.访问
	cout << "a2->*p2 : " << a2->*p2 << endl; // 对于一个对象指针，用->访问
}

int main()
{
	// 1、hello world
	Hello01();

	// 2、全局变量，作用域
	Test02();

	// 3、指向类成员的指针
	cout << "-----------" << endl;
	Test03();


	system("pause");
	return EXIT_SUCCESS;
}