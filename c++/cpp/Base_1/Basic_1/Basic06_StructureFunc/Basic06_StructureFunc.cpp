#include <iostream>

using namespace std;

/*
4.3、对象构造方法
	
	拷贝构造函数的执行规则；

*/


class Person {
public:
	Person() {
	
	}
	Person(int a) { age = a; }
	~Person() {
	
	}
	int age;

};
// 1、使用旧对象初始化新对象
void test01() {
	Person p(10);
	Person p1(p);
	Person p2 = Person(p);
	Person p3 = p;  // -> 等价于 Person p3 = Person(p)
}

// 2、传递的参数是普通对象，函数参数也是普通对象，传递时，将会调用拷贝构造
void doBusiness(Person p) {}
void test02() {
	Person p(10);
	doBusiness(p);
}

/* 3、函数返回局部对象 -> 这里需要关注
	打印结果：
		在局部变量中的p:003AF9F4
		在调用函数后返回的p：003AFAEC

	编译器会对返回值进行优化，叫做 RVO (return value optimization) 技术。
		在vs debug模式下不会有这种优化，此时拷贝构造函数会被调用；
		vs release和qt下，会有这种优化，拷贝构造函数不会被调用
	
	为什么会调用拷贝构造函数？
		在返回了一个局部变量p对象时，
		会使用p的拷贝构造函数生成一个临时的Person对象进行返回，然后原有的局部变量p会被析构；
		这里就多出了一次析构和一次拷贝，
		RVO技术就是为了减少这个析构和拷贝的操作，直接把外面的p作为参数传入。

*/
Person MyBussiness() {
	Person p(10);
	cout << "在局部变量中的p:" << (int*)&p <<endl;
	return p;
}
void test03() {
	Person p = MyBussiness();
	cout << "在调用函数后返回的p："<< (int*)&p <<endl;
}

/*	编译器可能会将函数优化为：
	void MyBussiness(Person &result) {
		result.X:X(); // 调用Person的默认拷贝构造函数
		return;
	}
	int main() {
		Person p;
		MyBussinesss(p);
	}
*/
int main()
{
	test03();
}