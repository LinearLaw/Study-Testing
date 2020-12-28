
/*	set集合
	1、所有的元素会根据元素的键值自动排序
	2、set的元素既是key也是value -> 也就是单个节点只有一个值
	3、set不允许两个元素有相同的key

 */

#include "rbtree.h"

typedef struct {
	RBRoot* tree;
	int length;
} Set;

// 1、初始化一个set
int set_create(Set* set);
int set_init(Set* set, int data[], int length);

// 2、插入一个节点
int set_insert(Set *set, int  data);

// 3、删除一个节点
int set_remove(Set *set, int data);

// 4、清空节点
int set_clear(Set *set);

// 5、查找一个节点
int set_find(Set* set, int data);

// 6、求两个集合的交集
int set_intersection(Set *seti, Set *set1, Set *set2);

// 7、求两个集合的并集
int set_union(Set *setu, Set *set1, Set *set2);

// 8、求两个集合的差集
int set_difference(Set *setd, Set *set1, Set *set2);

void print_set(Set* set);
void set_preorder_findi(Set* seti, RBTree tree, Set *set2);
void set_preorder_findu(Set* seti, RBTree tree);
void set_preorder_findd(Set* setd, RBTree tree, Set *set2);