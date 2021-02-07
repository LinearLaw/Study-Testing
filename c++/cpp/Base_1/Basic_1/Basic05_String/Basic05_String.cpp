#include <iostream>
#include <string>

using namespace std;

class TestString {

public:
	void reverseString() {
		string a = "-2313";
		string b = "1516418";

		// string拼接；
		string c = b[0] + a;

		cout << c << endl;
	}

};

int main()
{
	TestString().reverseString();
}
