#pragma once

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <regex>
#include <queue>
#include "Table.h"

using namespace std;

class Dijkstra {

public:
	Dijkstra(vector<TableNode*> res);

	void findOne(int start, int end);

	void printPath();

	void doFind(int currDis, vector<int> currPath, int currentIndex, int end, int deep, int expand);

};