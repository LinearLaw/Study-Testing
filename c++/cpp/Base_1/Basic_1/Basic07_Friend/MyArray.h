#pragma once

class MyArray {
public:
	MyArray();
	~MyArray();

	// 传入一个容量，用容量初始化
	explicit MyArray(int capacity);

	void SetData(int pos, int val);
	int GetData(int pos);

	void PushBack(int val);
	int GetLength();

private:
	int mCapacity;	// 数组一共可容纳多少个元素
	int mSize;		// 当前有多少个元素
	int* pAddress;	// 指向存储数据的空间
};

