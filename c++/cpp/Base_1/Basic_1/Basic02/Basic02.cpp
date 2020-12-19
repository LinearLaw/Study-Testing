#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

#include "game1.h"
#include "game2.h"
void test01() {
	cout <<"--------Test1-----------"<< endl;
	LOL::GoAtk();
	KingGlory::GoAtk();
}

/*  1、命名空间下可以放函数、变量、结构体、类，
	2、如果要能直接访问，要求在全局作用域下
	3、namespace可以互相嵌套
*/
namespace A{
	void func() {};
	int ma = 100;
	struct Person {
		char name;
	};
	class Animal {};
	namespace B {
		int ma = 200;
	}
}
void test02() {
	cout << "----------Test2---------" << endl;
	cout << "A::ma=" << A::ma << endl;
	cout << "A::B::ma=" << A::B::ma << endl;
}

/*  4、namespace可以添加内容，
	5、也可以起别名
 */
// 调用两次namespace C，两个的命名空间会相互合并
namespace C{
	int mb = 1000;
}
namespace C {
	int mc = 500;
}
void test03(){
	namespace COther = C; // 起别名
	C::mc = 100;	// 添加新内容
	cout << "-----------Test3--------" << endl;
	cout << "C的新成员：mc="<< C::mc << endl;
}

/*  6、匿名的namespace
		相当于是static int ma; 仅限在当前文件中使用
 */
namespace {
	int ma = 50;
}
void test04() {
	cout << "-----------Test4--------" << endl;
	cout << "匿名namespace：ma=" << ma << endl;
	cout << "------------------------" << endl;
}


int main()
{
	// 一、双冒号作用域运算符、namespace限定作用域
	/*	game1.h + game1.cpp
		game2.h + game2.cpp
	*/
	test01();


	// 二、namespace 可以互相嵌套
	test02();

	// 三、可以给命名空间添加内容
	test03();

	// 四、匿名namespace
	test04();

}