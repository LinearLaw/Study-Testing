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