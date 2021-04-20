#include <iostream>
#include <vector>
#include <algorithm>
#include "v_sort.h"

using namespace std;

Player::Player(int i, int s):id(i), score(s) {}

class VSortFunctor {
public:
	bool operator()(Player& a,Player& b) {
		return a.score < b.score;
	}
};


void TestVSort() {
	Player p1(1, 222);
	Player p2(2,333);
	Player p3(3, 444);
	Player p4(4,233);

	vector<Player> pbox = {p1,p2,p3,p4};
	sort(pbox.begin(), pbox.end(), VSortFunctor());

	for (int i = 0; i < pbox.size(); i++)
	{
		cout << pbox[i].score << endl;
	}
}