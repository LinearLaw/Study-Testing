#include <iostream>
#include <vector>
#include <algorithm>

#include <functional>

#include "FunctionalTest.h"

using namespace std;
/*
	4.3、内建函数对象
		template<class T> T plus<T>		//加法仿函数
		template<class T> T minus<T>	//减法仿函数
		template<class T> T multiplies<T>	//乘法仿函数
		template<class T> T divides<T>	//除法仿函数
		template<class T> T modulus<T>	//取模仿函数
		template<class T> T negate<T>	//取反仿函数

		template<class T> bool equal_to<T>		//等于
		template<class T> bool not_equal_to<T>	//不等于
		template<class T> bool greater<T>		//大于
		template<class T> bool greater_equal<T>	//大于等于
		template<class T> bool less<T>			//小于
		template<class T> bool less_equal<T>	//小于等于

		template<class T> bool logical_and<T>	//逻辑与
		template<class T> bool logical_or<T>	//逻辑或
		template<class T> bool logical_not<T>	//逻辑非
 */

// 1、取反仿函数
void testNegate(){
	negate<int> n;
	cout << n(50) << endl;
}

// 2、加法仿函数
void testPlus() {
	plus<int> p;
	cout << p(354,26) << endl;
}

// 3、大于仿函数
void testGreater() {
	vector<int> a = { 23,12,534,4,25,545,234,34,234,5,74,41,5 };

	// 从大到小排序
	sort(a.begin(), a.end(), greater<int>());
	for (int i = 0; i < a.size(); i++)
	{
		cout << a[i] << endl;
	}
}

void runFunctionalTest() {
	cout << "---1、取反---" << endl;
	testNegate();

	cout << "---2、加法---" << endl;
	testPlus();
	
	cout << "---3、大于---" << endl;
	testGreater();
}


