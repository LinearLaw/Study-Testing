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