#include <iostream>
#include "MyPrint.h"

using namespace std;

/*
	4.1、函数对象
	重载类中的()运算符，这个对象就是函数对象，也叫做仿函数；
*/

// 1、构造函数
MyPrint::MyPrint() {
	m_num = 0;
}
// 2、重载这个类的()方法
void MyPrint::operator() (int num) {
	cout << num << endl;
	m_num++;
}

// 3、使用
void testMyPrint() {
	MyPrint m;
	m(20);

	m(40);
	/*	Tips：
		函数对象， 其实超出了普通函数的概念，它可以记录函数调用状态
		例如MyPrint类，有一个成员m_num，
			每次调用了仿函数，m_num都进行了+1，
			这就相当于记录了调用了多少次函数。
	*/
	cout << "------------" << endl;
	cout << m.m_num << endl;
}