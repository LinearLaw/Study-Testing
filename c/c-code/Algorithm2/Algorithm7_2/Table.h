#pragma once


typedef struct TableNode {
	int index;	// 邻接节点的索引
	int distance;	// 到邻接节点的距离
	TableNode* next;
}TableNode;


void TableInsert(TableNode* root, TableNode* newNode);