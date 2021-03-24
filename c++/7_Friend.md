# 友元函数

- friend关键字
- 类A将类B设置为friend
    - A a; B b;
    - b里面的方法，可以获取A a，并让a直接访问private对象
    - 如果没有remote， a->xxx这个操作会曝非法，
    - private成员要用非静态成员函数去访问

- 在头文件中声明friend
- 在cpp文件中，用::来定义成员

----------------------------------------------------------      

Television.h
```cpp
#pragma once

class Television {
	// 将遥控器类视为友元；
	friend class Remote;
public:
	enum { On, Off }; // 电视机状态
	enum { minVol, maxVol = 100 }; // 音量 ，1-100
	enum { minChannel = 1, maxChannel = 255 }; // 频道，1-255

	Television();

	void OnOrOff();
	void VolumeUp();
	void VolumeDown();
	void ChannelUp();
	void ChannelDown();
	void ShowTeleState();

private:
	int mState; // 电视机状态
	int mVolume; // 音量
	int mChannel; // 频道
};

class Remote {
public:
	Remote(Television *tele);

public:
	void OnOrOff();
	void VolumeUp();
	void VolumeDown();
	void ChannelUp();
	void ChannelDown();
	void ShowTeleState();
	void SetChannel(int channel);
private:
	Television *mTele;
};
```

----------------------------------------------------------      


Television.cpp
```cpp
#include <iostream>
#include "Television.h"
using namespace std;


/*	1、电视机类
*/
Television::Television() {
	this->mChannel = Off;
	this->mVolume = minVol;
	this->mChannel = minChannel;
}
// 开机关机
void Television::OnOrOff() { this->mState = (this->mState == On ? Off : On);}
// 增 / 减音量
void Television::VolumeUp() {
	if (this->mVolume == maxVol) { return; }
	this->mVolume++;
}
void Television::VolumeDown() {
	if (this->mVolume == minVol) { return; }
	this->mVolume--;
}

// 换频道
void Television::ChannelUp() {
	if (this->mChannel == maxChannel) { return; }
	this->mChannel++;
}
void Television::ChannelDown() {
	if (this->mChannel == minChannel) { return; }
	this->mChannel--;
}

void Television::ShowTeleState() {
	cout << "状态：" << (mState == On ? "已开机" : "已关机") << endl;
	if (mState == On) {
		cout << "当前音量：" << mVolume << endl;
		cout << "当前频道：" << mChannel << endl;
	}
	cout << "-------------" << endl;
}

/*-------------------------------------------------------------*/


/*	2、遥控器类
*/
Remote::Remote(Television *tele) {
	mTele = tele;
}
void Remote::OnOrOff() { mTele->OnOrOff(); }
void Remote::VolumeUp() { mTele->VolumeUp(); }
void Remote::VolumeDown() { mTele->VolumeDown(); }
void Remote::ChannelUp() { mTele->ChannelUp(); }
void Remote::ChannelDown() { mTele->ChannelDown(); }
void Remote::ShowTeleState() { mTele->ShowTeleState(); }

// 自己扩展的方法
void Remote::SetChannel(int channel) {
	if (channel < Television::minChannel || channel > Television::maxChannel) {
		return;
	}
	/*	说明，
		这里，mChannel是一个private的成员，
		但是在这里却直接访问了，因为Television给Remote设置了友元。
	*/
	mTele->mChannel = channel;
}
```