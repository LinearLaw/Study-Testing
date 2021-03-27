#include <iostream>
#include <vector>

using namespace std;

class VectorTest {

public:
	// 1、erase 函数的使用方法
	void testErase(vector<int> a) {
		/*
			// 删除从start到end之间的元素
			erase(const_iterator start, const_iterator end);

			// 删除迭代器指向的元素
			erase(const_iterator pos);	
		*/
		
		cout<<"1.1、删除头结点"<<endl;
		a.erase(a.begin());

		printVector(a);
		
		cout <<"1.2、删除所有节点，以下erase和clear效果等价"<<endl;
		vector<int> b = copyVector(a);
		b.erase(b.begin(), b.end());
		b.clear();

		printVector(b);

		/* 3、删除区间
			a.begin()和a.end()返回的是一个迭代器，
			（1）迭代器可以进行随机访问
				a.begin() + 2 等价于 a[0+2]，删除a[2]这个元素

			（2）取迭代器指向的值，用 *it
				*a.begin()

			注意，删除的区间，左闭右开，
				例如 a.erase(a.begin() + 2,a.begin()+4);
				删除的是 a[2],a[3]
		*/
		cout<<"---* 迭代器规则---"<<endl;
		vector<int>::iterator ita = a.begin();
		vector<int>::iterator it = ita + 3;
		cout << "a.begin()   : "<< *ita <<endl;
		cout << "a.begin()+3 : " << *it << endl;	// 用*来对迭代器取值
		cout << endl;
		
		cout<<"3、删除指定区间的节点"<<endl;
		a.erase(a.begin() + 2, a.begin()+4); // 

		printVector(a);
	}
	/* 2、测试 push 和 pop 的特性
		
		结论：注意，push_back一个数组的时候，
			vector不是简单的把数组的地址push进去，而是将数组复制了一份，复制到了vector中；
			原数组后续的变更不会对res中已push的元素产生影响；
	*/
	void testPush() {
		vector<int> a = { 1,2,3,4,5,6 };
		vector<vector<int>> res;
		
		res.push_back(a);

		a.pop_back();
		res.push_back(a);

		for (int i = 0; i < res.size(); i++)
		{
			cout << i << " : ";
			vector<int>::iterator at = res[i].begin();
			for (int j = 0; j < res[i].size(); j++)
			{
				cout << "[" << &res[i][j] << "]" << res[i][j] << endl;
			}
			cout << endl;
		}
	}
	
	void printVector(vector<int> a) {
		cout<<"-------print--------"<<endl;
		for (int i = 0; i < a.size(); i++)
		{
			cout<<a[i]<<endl;
		}
		cout << endl;
	}
	vector<int> copyVector(vector<int> a) {
		vector<int> b;
		for (int i = 0; i < a.size(); i++)
		{
			b.push_back(a[i]);
		}
		return b;
	}

	/* 使用a.capacity()可以获取当前vector容器的容量 */
	void testCapacity() {
		vector<int> a;
		for (int i = 0; i < 10; i++)
		{
			a.push_back(i);
			cout << a.capacity() << endl;
		}

	}
	void testVectorStru() {
		vector<int> a = { 12434,23,34,644,23,5,23 };
		vector<int>::iterator c;
		vector<int> b = vector<int>{ a.begin(),c };

		printVector(b);
	}

};

int main()
{
	vector<int> a = { 0,1,2,3,4,55,666,7777 };

	// 1、erase
	cout << "-----1------" << endl;
	VectorTest vt = VectorTest();
	vt.testErase(a);

	// 2、push和pop
	cout << "-----2------" << endl;
	vt.testPush();

	// 3、a.capacity()
	cout << "-----3------" << endl;
	vt.testCapacity();

	cout << "-----4------" << endl;
	vt.testVectorStru();

}
