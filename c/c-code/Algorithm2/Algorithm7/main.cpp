// #pragma warning(disable : 4996) 
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <regex>

using namespace std;

#define BUFFER_SIZE 255

void printVector(vector<vector<string>> temp) {
	for (int i = 0; i < temp.size(); i++)
	{
		for (int j = 0; j < temp[i].size(); j++)
		{
			cout << temp[i][j] << endl;
		}
	}
}

/*
	1、读取文件，建立邻接表
	2、遍历邻接表，查找每一个索引到达的路径
*/



void CreateTableFromFile(char* name) {
	// FILE *fin = fopen(name, "rt");

	ifstream f("data.txt");
	if (!fin)
	{
		cout << "fail to open the file" << endl;
		return;//或者抛出异常。
	}
	char buffer[BUFFER_SIZE] = {0};

	vector<vector<string>> temp;
	regex r("\\s");
	
	while ( fin.getline(buffer, sizeof(buffer), '\n') ) {

		string index;	// 获取本次索引
		int i = 0;
		while ( !regex_match(string(1, buffer[i]), r)) {
			index += buffer[i];
			i++;
		}
		// 读取节点信息
		string node;
		vector<string> nodeBox;
		
		while (i < BUFFER_SIZE) {
			bool res = regex_match(string(1, buffer[i]), r); 
			cout << index << "___" << res <<"____" << buffer[i] <<"__current:" << node <<endl;
			if ( res ) {
				if (node.length() > 0) {
					nodeBox.push_back(node+"");
				}
				node = "";
			} else {
				node += buffer[i];
			}
			i++;
		}
		
		temp.push_back(nodeBox);
	}
	fin.close();

	printVector(temp);
}




int main(int argc, char *argv[])
{
	if (argc != 2) {
		cout<<"argument error: add data.txt into argument"<<endl;
		exit(EXIT_FAILURE);
	}

	CreateTableFromFile(argv[1]);
}
