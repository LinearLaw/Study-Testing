#pragma once

class Base {
public:
	Base();
	void Print();
	void func();
	void func(int param);

public:
	int mParam;
};

class Derived : public Base {
public:
	Derived();
	void Print();
	void testFunc();
	void func();
	void func(int p1,int p2);
public:
	int mParam;
};

void testSameName();