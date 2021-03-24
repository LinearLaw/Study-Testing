#include "MyArray.h"

MyArray::MyArray() {
	this->mCapacity = 100;
	this->mSize = 0;
	this->pAddress = new int[this->mCapacity];
}

MyArray::MyArray(int capacity) {
	this->mCapacity = capacity;
	this->mSize = 0;
	this->pAddress = new int[this->mCapacity];
}

MyArray::~MyArray() {
	if (this->pAddress != nullptr) {
		delete[] this->pAddress;
	}
}

void MyArray::SetData(int pos, int val) { this->pAddress[pos] = val; }
int MyArray::GetData(int pos) { 
	if (pos >= mCapacity || pos < 0) { return -1; }
	return this->pAddress[pos]; 
}
void MyArray::PushBack(int val) { 
	if (mSize >= mCapacity) { return; }
	this->pAddress[mSize++] = val; 
}
int MyArray::GetLength() { return this->mSize; }
