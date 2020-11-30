#pragma warning(disable : 4996) 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "llgen.h"          
#include "llapp.h"   

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
			printf("%s\n", temp);
			if (!AddNodeAscend(L1, temp)) {
				return -1;
			}
		}
		Link head1 = L1->LHead;
		for (i = 0; i < len; i++)
		{
			printf("%d:%s\n", i, ((pND1)(head1->pdata))->word);
			head1 = head1->next;
		}

		
		// 头指针尾指针，判断是否相等
		Link head = L1->LHead;
		Link tail = L1->LHead->prev;
		for (i = 0; i < len/2; i++)
		{
			if (head != tail && head!=NULL && tail!=NULL) {
				if ( NodeDataCmp1(head,tail) ) {
					return 0;
				}
			}
		}
		return 1;
	}
	else {
		return 1;
	}
}

int main ( int argc, char *argv[] )
{
	// 用户输入的字符串
	char cmd[128];
	printf("输入数字字母组成的字符串：");
	scanf("%s", &cmd);

	int len = strlen(cmd);
	cmd[len] = '\0';

	int result = judgeChar(cmd,len);
	printf("\nResult:%d", result);
}
