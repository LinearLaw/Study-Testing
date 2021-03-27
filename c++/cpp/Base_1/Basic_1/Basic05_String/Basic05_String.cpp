#include <iostream>
#include <string>
#include <regex>
#include <vector>

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

	void stringStruecture() {
		string a = "123242341234234";

		string b(a);
		string c = string(a);
	}

	// 测试split函数
	vector<string> s_split(const string& in, const string& deli) {
		regex re{ deli };

		sregex_token_iterator s = sregex_token_iterator(in.begin(), in.end(), re, -1);
		return vector<string> { 
			sregex_token_iterator(in.begin(),in.end(),re,-1) , // 相当于起点迭代器
			sregex_token_iterator()		// 相当于终点迭代器
		};
	}

	

};

int main()
{
	TestString temp = TestString();

	temp.reverseString();

	cout << "------" << endl;

	vector<string> a = temp.s_split("5934009240239809480934,5,47,434,3246,45523,5685", ",");
	for (int i = 0; i < a.size(); i++)
	{
		cout << a[i] << endl;
	}
}
