#pragma once

// 1、定义一个类
class MyPrint {
public:
	MyPrint();
	// 2、重载这个类的()方法
	void operator() (int num);
	int m_num;
};

// 3、使用
void testMyPrint();