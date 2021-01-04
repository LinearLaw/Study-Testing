// #pragma warning(disable : 4996) 
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <regex>
#include <queue>

#include "Table.h"

using namespace std;

#define BUFFER_SIZE 500

void printVector(vector<TableNode*> temp) {
	for (int i = 1; i < temp.size(); i++)
	{
		TableNode* p = temp[i]->next;
		cout<<"---------------"<< i <<"---------------" << endl;
		cout << i << endl;
		while (p != NULL) {
			cout << "->" << p->index<<":" << p->distance;
			p = p->next;
		}
		cout << endl;
	}
}

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

/*
	1、读取文件，建立邻接表
	2、遍历邻接表，查找每一个索引到达的路径
*/
vector<TableNode*> CreateTable(char *finame) {
	ifstream fin(finame);
	vector<TableNode*> result;
	if (!fin) {
		cout << "fail to open the file" << endl;
		return result;//或者抛出异常。
	}

	char buffer[BUFFER_SIZE] = { 0 };

	// data.txt从1开始，所以加入一个空节点
	result.push_back(NULL);

	while (fin.getline(buffer, sizeof(buffer), '\n')) {
		// 1、根据 分隔符，将本行内容存入数组
		vector<string> sv = split(string(buffer), "\t");
		
		// 2、解析节点数据
		vector<string> temp;
		int index = stoi(sv[0]); // 数组首元素为 index

		// 3、节点数据生成链表，加入到邻接表中
		int count = 0;
		for (const auto& s : sv) {
			TableNode* newNode = (TableNode*)malloc(sizeof(TableNode));
			if (count > 0) {
				temp = split(s, ",");
				newNode->index = stoi(temp[0]);
				newNode->distance = stoi(temp[1]);
				newNode->next = NULL;

				TableInsert(result[index], newNode);
			}
			else {
				newNode->index = index;
				newNode->distance = 0;
				newNode->next = NULL;
				result.push_back(newNode);
			}
			count++;
		}
	}
	return result;
}

void findMinPath(vector<TableNode*> res){
	int size = res.size();
	int min[210][210] = {0};
	vector<string> minPath;
	
	for (int i = 1; i < size; i++)
	{
		string path = to_string(i);
		TableNode* p = res[i]->next;
		while (p != NULL) {
			if (p->index != i) {
				min[i][p->index] = p->distance;
				path += "_";
				path += to_string(p->index);
				p = p->next;
			}
		}
		minPath.push_back(path);
	}

	for (int i = 1; i < minPath.size(); i++)
	{
		cout << endl;
		cout <<"---"<< minPath[i] <<"---"<<endl;
		for (int j = 1; j < size; j++)
		{
			if (i!=j) {
				cout<<"_"<< min[i][j] <<"_";
			}
		}
		cout << endl;
	}
}



int main(int argc, char *argv[])
{
	if (argc != 2) {
		cout << "argument error: add data.txt into argument" << endl;
		exit(EXIT_FAILURE);
	}
	// 1、读取文件，生成邻接表
	vector<TableNode*> res = CreateTable(argv[1]);

	if (res.size() > 0 ) {
		// 2、打印验证
		printVector(res);
	
		findMinPath(res);
	}
}
