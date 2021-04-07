#include <iostream>
#include "SameName.h"
using namespace std;

// 1、基类
Base::Base():mParam(0) {}
void Base::Print() { cout << mParam << endl; }
void Base::func() { cout << "Base func" << endl; }
void Base::func(int param) { cout << "Base func(int param)" << endl; }


// 2、子类
Derived::Derived() :mParam(10) {}

// 2.1、子类访问父类的成员属性
void Derived::Print() {
	// 使用Base::可以访问到父类的成员属性
	cout << "Base: " << Base::mParam << endl;
	cout << "Derived: " << mParam << endl;
}

//	2.2、子类重定义基类的函数
void Derived::func() { cout << "Derived func" << endl; }
void Derived::testFunc() {
	Base::func(); // 和成员属性类似，可以用Base::访问
	func();
}

/*	2.3、子类重载基类的成员函数
	Tips：
		如果子类不重载父类的func函数，子类是可以使用父类的重载函数的。
		在重载了func之后，基类的func重载函数就会被隐藏，不能直接调用
*/
void Derived::func(int p1,int p2){ cout << "Derived func(int p1, int p2)" << endl; }



void testSameName() {
	Derived d;

	cout << "----1、子类访问父类成员属性-----" << endl;
	d.Print();  // 0   10

	cout << "----2、子类访问已被重定义的父类函数-----" << endl;
	d.testFunc();

	cout << "----3、子类重载了函数，则父类的重载函数会被隐藏-----" << endl;
	// d.func(1); // 此时无法直接使用父类的func(int param)函数
	d.func(1,2);
	cout << "---------" << endl;

	


}