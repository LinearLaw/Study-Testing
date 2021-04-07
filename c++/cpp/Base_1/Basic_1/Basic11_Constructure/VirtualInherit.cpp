#include <iostream>
#include "VirtualInherit.h"

using namespace std;

BigBase::BigBase(int x) { p = x; }
void BigBase::func() {}

// 1、直接使用菱形继承
Base1::Base1() : BigBase(10) {}
Base2::Base2() : BigBase(11) {}
Derived_D::Derived_D() {}

void Derived_D::calcBase12() {
	cout << "BigBase : " << sizeof(BigBase) << endl; // 4
	cout << "Base1 : " << sizeof(Base1) << endl; // 4
	cout << "Base2 : " << sizeof(Base2) << endl; // 4
	cout << "Derived_D : " << sizeof(Derived_D) << endl; // 8
}

// 2、使用虚继承
BaseV1::BaseV1() :BigBase(13) {}
BaseV2::BaseV2() :BigBase(14) {}
Derived_V::Derived_V() {}

void Derived_V::calcBaseV12() {
	cout << "BigBase : " << sizeof(BigBase) << endl; // 4
	cout << "BaseV1 : " << sizeof(BaseV1) << endl; // 8
	cout << "BaseV2 : " << sizeof(BaseV2) << endl; // 8
	cout << "Derived_V : " << sizeof(Derived_V) << endl; // 1
}

void testVirtualInherit() {

	cout << "-------1、直接菱形继承------" << endl;
	Derived_D d;
	d.calcBase12();

	cout << "-------2、虚继承------" << endl;
	Derived_V v;
	v.calcBaseV12();
}