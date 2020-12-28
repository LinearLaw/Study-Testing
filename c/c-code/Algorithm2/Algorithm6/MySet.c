
#include <stdio.h>
#include <stdlib.h>

#include "MySet.h";

/*
	使用rbtree实现STL set

	返回值：
		1 成功
		0 失败
*/

// 1、初始化一个set
int set_create(Set* set) {
	set->tree = create_rbtree();
	set->length = 0;
	return 1;
}
int set_init(Set* set, int data[], int length) {

	// 创建一棵红黑树
	set_create(set);
	int successCount = 0;
	for (int i = 0; i < length; i++) {
		if (set_insert(set, data[i]) == 1) {
			successCount++;
		}
	}
	printf("== set init success\n");
	printf("== insert count:%d\n", length);
	printf("== success count:%d\n", successCount);
	return 1;
};

// 2、插入一个节点
int set_insert(Set *set, int data) {
	if (set == NULL) {
		return 0;
	}
	if (insert_rbtree(set->tree, data) == 0) {
		set->length++;
		return 1;
	}
	return 0;
};

// 3、删除一个节点
int set_remove(Set *set, int data) {
	if (set == NULL) {
		return 0;
	}
	delete_rbtree(set->tree, data);
	return 1;
};

// 4、清空节点
int set_clear(Set *set) {
	if (set == NULL) {
		return 0;
	}
	destroy_rbtree(set->tree);
	set->tree = NULL;
	set->length = 0;
	return 1;
};

// 5、查找一个节点
int set_find(Set* set, int data) {
	if (set == NULL) {
		return 0;
	}
	if (rbtree_search(set->tree, data) == 0) {
		return 1;
	}
	return 0;
};

/* 6、求两个集合的交集
	前序遍历set1，每遍历到一个节点，都去set2中查找该节点是否存在，
		如果存在，将其插入到seti
*/
int set_intersection(Set *seti, Set *set1, Set *set2) {
	set_preorder_findi(seti, set1->tree->node, set2);
	return 1;
};
void set_preorder_findi(Set* seti, RBTree tree, Set *set2)
{
	if (tree != NULL)
	{
		if (set_find(set2, tree->key) == 1) {
			set_insert(seti, tree->key);
		}
		set_preorder_findi(seti,tree->left,set2);
		set_preorder_findi(seti,tree->right,set2);
	}
}

/* 7、求两个集合的并集
	分别遍历set1和set2，将其插入到setu即可。
*/
int set_union(Set *setu, Set *set1, Set *set2) {
	set_preorder_findu(setu, set1->tree->node);
	set_preorder_findu(setu, set2->tree->node);
	return 1;
};
void set_preorder_findu(Set* seti, RBTree tree)
{
	if (tree != NULL)
	{
		set_insert(seti, tree->key);
		set_preorder_findu(seti, tree->left);
		set_preorder_findu(seti, tree->right);
	}
}

/* 8、求两个集合的差集
	前序遍历set1，每次将结点的key到set2中查找，没有查找到则加入到setd
	前序遍历set2，每次将结点的key到set1中查找，没有查找到则加入到setd

*/
int set_difference(Set *setd, Set *set1, Set *set2) {
	set_preorder_findd(setd, set1->tree->node, set2);
	set_preorder_findd(setd, set2->tree->node, set1);
	return 1;
};
void set_preorder_findd(Set* setd, RBTree tree, Set *set2)
{
	if (tree != NULL)
	{
		if (set_find(set2, tree->key) != 1) {
			set_insert(setd, tree->key);
		}
		set_preorder_findd(setd, tree->left,set2);
		set_preorder_findd(setd, tree->right, set2);
	}
}

// 9、打印set
void print_set(Set* set) {
	if (set != NULL) {
		printf("\n== set的详细信息\n");
		printf("set length:%d\n", set->length);
		print_rbtree(set->tree);
	}
};