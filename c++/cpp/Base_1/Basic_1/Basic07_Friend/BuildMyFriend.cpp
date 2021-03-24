#include <iostream>
#include "BuildMyFriend.h"

using namespace std;

// 1、实现 MyFriend 的几个方法
void MyFriend::lookAtBedRoom(Building &building) {
	cout << "My friend in my bed room : " << building.mBedroom << endl;
}
void MyFriend::playInBedRoom(Building &building) {
	cout << "My friend play in my bed room : " << building.mBedroom << endl;
}

void CleanBedRoom(Building &building) {
	cout << "友元全局函数访问 ： " << building.mBedroom << endl;
}
Building::Building() {
	this->mBedroom = "WWWWWW";
	this->msRoom = "LLLLLLL";
}