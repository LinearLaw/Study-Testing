#include <iostream>

#include "set_sort.hpp"
#include "set_find.hpp"

using namespace std;


int main()
{
	cout << "-----set_sort------" << endl;
	sortStructSet();

	cout << "---------" << endl;
	sortClassSet();
	
	cout << "---------" << endl;
	sortSetByFunctor();

	cout << "---------" << endl;
	sortClassPriorityQueue();

	cout << "-----set_find-----" << endl;
	testFind();
}