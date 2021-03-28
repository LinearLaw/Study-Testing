#include <iostream>

#include "MyPrint.h"
#include "FunctionalTest.h"

using namespace std;

int main()
{
	// 1、函数对象 - 仿函数
	testMyPrint();

	// 2、内建函数对象
	// 使用头文件 <functional>
	runFunctionalTest();
}