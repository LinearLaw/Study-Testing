#pragma once
#include <string>

using namespace std;

/*
4.5、友元函数
	类A，有自己的方法
	类B，在类B中，将 A的方法 设置为friend
	A被设置为 friend 的方法，可以直接访问 B 的 private 成员

	在B中，将A设置为友元，
*/

class Building;

// 第一个类，MyFriend
class MyFriend {
public:
	void lookAtBedRoom(Building &building);
	void playInBedRoom(Building &building);
};

/* 第二个类，Building，在内部指定了其友元，
	友元可以安插MyFriend的方法
		可以安插全局函数
		可以把MyFriend也作为类；

	指定好了友元之后，在外部调用友元，就可以访问到Building对象的private属性了；
*/
class Building {
	// 1、全局函数做友元函数
	friend void CleanBedRoom(Building &building);

	// 2、成员函数做友元函数
	friend void MyFriend::lookAtBedRoom(Building &building);
	friend void MyFriend::playInBedRoom(Building &building);

	// 3、友元类
	friend class MyFriend;

public:
	Building();
public:
	string msRoom;
private:
	string mBedroom;
};