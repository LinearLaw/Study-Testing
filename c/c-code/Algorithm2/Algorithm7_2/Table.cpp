#include <iostream>
#include "Table.h"

// 插入一个节点
void TableInsert(TableNode* root, TableNode* newNode) {
	TableNode* p = root;
	while (p->next != NULL) {
		p = p->next;
	}
	p->next = newNode;
	return;
}