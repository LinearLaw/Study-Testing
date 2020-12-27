
/*	set集合
	1、所有的元素会根据元素的键值自动排序
	2、set的元素既是key也是value -> 也就是单个节点只有一个值
	3、set不允许两个元素有相同的key

 */

#include "rbtree.h"

typedef struct {
	RBTree* tree;
	int length;
} Set;

// 1、初始化一个set
int set_init(Set* set, int data[], int length);

// 2、插入一个节点
int set_insert(Set *set, void  *data);

// 3、删除一个节点
int set_remove(Set *set, void **data);

// 4、清空节点
int set_clear(Set *set);

// 5、查找一个节点
int set_find(Set* set, void *data);

// 6、求两个集合的交集
int set_intersection(Set *seti, Set *set1, Set *set2);

// 7、求两个集合的并集
int set_union(Set *setu, Set *set1, Set *set2);

// 8、求两个集合的差集
int set_difference(Set *setd, Set *set1, Set *set2);