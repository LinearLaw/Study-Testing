#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

/*
1、测试struct的大小

*/
struct AB {
	// 冒号代表了当前成员所占的空间 3bit
	// x y z 共享int空间 32bit，所以是8 B
	int x : 3; 
	int y : 4;
	int z : 5;
	double a; // 8 B

};
void run01() {
	// 8+8=16
	cout << sizeof(AB) << endl;
}