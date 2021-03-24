#include <iostream>
#include <string>

#include "BuildMyFriend.h"
#include "Television.h"
#include "MyArray.h"

using namespace std;

/*
4.5、友元函数
		三种：友元成员函数、友元类、友元全局函数

	类A，有自己的方法
	类B，在类B中，将 A的方法 设置为friend
	A被设置为 friend 的方法，可以直接访问 B 的 private 成员

	在B中，将A设置为友元，A的方法就可以直接访问B的私有成员了。
*/
/*	1、基本测试 */
void TestBuildMyFriend() {
	Building building;
	MyFriend myf;

	CleanBedRoom(building);
	myf.lookAtBedRoom(building);
	myf.playInBedRoom(building);
}

/*	2、用friend封装电视机 遥控器 类
	
*/
void TestTelevisionDirect() {
	Television television;
	television.ShowTeleState();
	television.OnOrOff(); //开机
	television.VolumeUp(); //增加音量+1
	television.VolumeUp(); //增加音量+1
	television.VolumeUp(); //增加音量+1
	television.VolumeUp(); //增加音量+1
	television.ChannelUp(); //频道+1
	television.ChannelUp(); //频道+1
	television.ShowTeleState();
}
void TestTelevisionByRemote() {
	//创建电视
	Television television;
	//创建遥控
	Remote remote(&television);
	remote.OnOrOff();
	remote.ChannelUp();//频道+1
	remote.ChannelUp();//频道+1
	remote.ChannelUp();//频道+1
	remote.VolumeUp();//音量+1
	remote.VolumeUp();//音量+1
	remote.VolumeUp();//音量+1
	remote.VolumeUp();//音量+1
	remote.ShowTeleState();
}
void TestTelevision() {
	TestTelevisionDirect();
	cout << "-----------" << endl;
	TestTelevisionByRemote();
}

/*
	3、array类
*/
void TestMyArray() {
	MyArray my(50);

	for (int i = 0; i < 65; i++)
	{
		my.PushBack(i);
	}

	for (int i = 0; i < my.GetLength; i++)
	{
		cout << my.GetData(i) << endl;
	}
	cout << endl;
}

int main()
{
	TestBuildMyFriend();

	cout << "---------------" << endl;

	TestTelevision();

	cout << "---------------" << endl;
	
	TestMyArray();

	return EXIT_SUCCESS;
}