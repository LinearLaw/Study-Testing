#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <regex>
#include <queue>

using namespace std;

#define BUFFER_SIZE 500
#define MAP_SIZE 201
#define MAX_DIS 1000000

// 输入字符串，将字符串根据分割符分割，存入vector中。
vector<string> split(const string& s, const string& seperator) {
	vector<string> result;
	unsigned int posBegin = 0;
	unsigned int posSeperator = s.find(seperator);

	while (posSeperator != s.npos) {
		result.push_back(s.substr(posBegin, posSeperator - posBegin));// 
		posBegin = posSeperator + seperator.size(); // 分隔符的下一个元素
		posSeperator = s.find(seperator, posBegin);
	}
	if (posBegin != s.length()) // 指向最后一个元素，加进来
		result.push_back(s.substr(posBegin));

	return result;

}


int main(int argc, char *argv[])
{
	// 记录是否遍历过
	int isRead[201] = { 0 };
	// 邻接矩阵
	int map[201][201] = { 0 };

	// 记录当前最短距离
	int dis[201] = { 0 };

	// 记录上一个节点
	int prev[201][201] = {0};


	// 1、邻接数组的元素，初始值设为最大距离 ->不可达
	for (int i = 0; i < MAP_SIZE; i++)
	{
		for (int j = 0; j < MAP_SIZE; j++)
		{
			map[i][j] = MAX_DIS;
		}
	}
	// 
	// 2、读取文件，写入到map中
	ifstream fin(argv[1]);

	if (!fin) {
		cout << "fail to open the file" << endl;
		return 0; //或者抛出异常。
	}

	char buffer[BUFFER_SIZE] = { 0 };
	while (fin.getline(buffer, sizeof(buffer), '\n')) {
		// 1、根据 分隔符，将本行内容存入数组
		vector<string> sv = split(string(buffer), "\t");

		// 2、解析节点数据
		vector<string> temp;
		int index = stoi(sv[0]); // 数组首元素为 index

		// 3、节点数据生成链表，加入到邻接表中
		int count = 0;
		for (const auto& s : sv) {
			if (count > 0) {
				temp = split(s, ",");
				int targetIndex = stoi(temp[0]);
				int targetDis = stoi(temp[1]);

				map[index][targetIndex] = targetDis;
			}
			else {
				map[index][index] = 0;
			}
			count++;
		}
	}
	
	// 3、Dijkstra
	int i, j,k;
	int currentMin;
	int currentStep;
	for (i = 1; i < MAP_SIZE; i++)
	{
		// 初始化dis、prev
		memcpy(dis, map[i], sizeof(map[i]));
		memset(isRead, 0, sizeof(isRead));
		isRead[i] = 1;

		// 两个连通的点i j，prev[i][j] = i，指向i
		for (j = 1; j < MAP_SIZE; j++)
		{
			if (map[i][j] < MAX_DIS && map[i][j] >= 0) {
				prev[i][j] = i;
			}
		}
		prev[i][i] = 0;

		for (k = 0; k < MAP_SIZE - 1; k++)
		{
			currentMin = MAX_DIS;
			currentStep = 1;
			for (j = 1; j < MAP_SIZE - 1; j++)
			{
				// 节点没有被访问过 && dis[j]
				if (!isRead[j] && currentMin > dis[j]) {
					currentMin = dis[j];
					currentStep = j;
				}
			}
			isRead[currentStep] = 1;

			for ( j = 0; j < MAP_SIZE; j++)
			{
				int temp = dis[currentStep] + map[currentStep][j];
				if (!isRead[j] && temp < dis[j]) {
					dis[j] = temp;
					prev[i][j] = currentStep;
					cout << currentStep << endl;
				}
			}
		}
	}

	// 4、等待用户输入
	
	int stack[100] = { 0 };

	int current;
	int target;
	int distance;

	int n = 0;
	char buf[BUFFER_SIZE] = { 0 };
	while (1)
	{
		memset(buf, 0, sizeof(buf));
		cout << "请输入要查询的节点，以逗号分隔："	;
		scanf_s("%s", buf, sizeof(buf));

		// 读取输入的两个节点值
		vector<string> temp = split(string(buf), ",");
		current = stoi(temp[0]);
		target = stoi(temp[1]);

		i = 0;
		j = 0;

		// 初始化栈
		memset(stack, 0, sizeof(stack));
		distance = 0;	// 记录当前的distance

		int top = 0;
		stack[top++] = target;
		while (target != current)
		{
			i = prev[current][target];

			stack[top++] = i;
			distance += map[i][target];

			target = i;
		}

		// 将入栈的路径和距离进行输出
		cout << "距离："<< distance;
		if (top > 0)
		{
			cout << " " << stack[--top];
		}

		while (top > 0)
		{
			current = stack[--top];
			cout << "->%d" << current;
		}
		cout << endl;

	}
	
}