#include <iostream>

using namespace std;

void testLRValue() {
	int a = 1;
	int &b = a;
	int &&c = move(a);

	cout << "a :" << a << endl;
	cout << "b :" << b << endl;
	cout << "c :" << c << endl;
}

int main()
{
	testLRValue();
}