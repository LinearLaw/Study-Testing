#pragma warning(disable : 4996) 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "llgen.h"          
#include "llapp.h"   

/**
	输入一个字符串，判断该字符串是否首尾对称
	0：不对称
	1：对称
	-1：判断出错
 */
int judgeChar(char *c,int len) {
	// 创建一个链表
	struct  List *L1 = CreateLList(
		CreateData1,
		DeleteData1,
		DuplicatedNode1,
		NodeDataCmp1
	);

	if (len > 0) {
		int i = 0;
		char temp[10];
		// 逐个字符读入到双向链表中
		for (i = 0; i < len; i++)
		{	
			temp[0] = c[i];
			temp[1] = '\0';
			if (!AddNodeAtHead(L1, temp)) {
				return -1;
			}
		}

		// 遍历链表，打印链表信息
		Link head = L1->LHead;
		Link tail = NULL;
		for (i = 0; i < len; i++)
		{
			printf("%08x__%d:%s_____Prev->%08x_____Next->%08x\n", 
				head,
				i, 
				((pND1)(head->pdata))->word,
				head->prev,
				head->next 
			);
			if (head->next == NULL) {
				tail = head;
			}
			head = head->next;
		}

		head = L1->LHead;

		/*	头指针从前往后，尾指针从后往前，
			遍历到len/2处停止，
			每一步判断两者的word是否相等
		*/
		for (i = 0; i < len/2; i++)
		{
			if (head != NULL && tail != NULL && head != tail) {
				int res = NodeDataCmp1(head->pdata, tail->pdata);
				if (res != 0 ) {
					return 0;
				}
				head = head->next;
				tail = tail->prev;
			}
		}
		return 1;
	}
	else { // 空链表直接返回0
		return 0;
	}
}

int main ( int argc, char *argv[] )
{
	// 用户输入字符串
	char cmd[128];
	printf("输入数字字母组成的字符串：");
	scanf("%s", &cmd);

	int len = strlen(cmd);
	cmd[len] = '\0';

	// 判断是否首尾对称，用result获取返回的结果
	int result = judgeChar(cmd,len);
	printf("\n");
	printf("Result:%d \n", result);
}
