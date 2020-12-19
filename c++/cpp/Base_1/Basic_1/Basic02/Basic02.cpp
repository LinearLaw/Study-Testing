#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>

using namespace std;

#include "game1.h"
#include "game2.h"
void test01() {
	cout <<"--------Test1-----------"<< endl;
	LOL::GoAtk();
	KingGlory::GoAtk();
	cout << endl;
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
	cout << endl;
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
	cout << endl;
}

/*  6、匿名的namespace
		相当于是static int ma; 仅限在当前文件中使用
 */
namespace {
	int ma = 50;
}
void test04() {
	cout << "-----------Test4--------" << endl;
	cout << "6、匿名namespace，相当于是static int ma，仅限在当前文件中使用"<< endl;
	cout << "匿名namespace：ma=" << ma << endl;
	cout << endl;
}

/*	7、using关键字，来开启一个命名空间
		使用using后，using之后的目标命名空间内的变量都会被引入
 */
namespace D {
	string temp = "D_temp";
}
namespace E {
	string temp = "E_temp";
}
void test05() {
	cout << "-----------Test5--------" << endl;
	cout << "using 用来打开一个命名空间" << ma << endl;
	using namespace C;
	cout << "1、使用using之后，可以直接访问C中的变量而无需加前缀" << endl;
	cout << "C::mb --->" << mb << endl;
	cout << "C::mc --->" << mc << endl;

	cout << "2、多个using namespace，要注意二义性问题" <<endl;
	using namespace D;
	using namespace E;
	cout << "D::temp = " << D::temp << endl;
	cout << "E::temp = " << E::temp << endl;
	cout << endl;
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

	// 五、using关键字
	test05();

}