#pragma warning(disable : 4996) 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "llgen.h"          
#include "llapp.h"   

/**
	����һ���ַ������жϸ��ַ����Ƿ���β�Գ�
	0�����Գ�
	1���Գ�
	-1���жϳ���
 */
int judgeChar(char *c,int len) {
	// ����һ������
	struct  List *L1 = CreateLList(
		CreateData1,
		DeleteData1,
		DuplicatedNode1,
		NodeDataCmp1
	);

	if (len > 0) {
		int i = 0;
		char temp[10];
		// ����ַ����뵽˫��������
		for (i = 0; i < len; i++)
		{	
			temp[0] = c[i];
			temp[1] = '\0';
			if (!AddNodeAtHead(L1, temp)) {
				return -1;
			}
		}

		// ������������ӡ������Ϣ
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

		/*	ͷָ���ǰ����βָ��Ӻ���ǰ��
			������len/2��ֹͣ��
			ÿһ���ж����ߵ�word�Ƿ����
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
	else { // ������ֱ�ӷ���0
		return 0;
	}
}

int main ( int argc, char *argv[] )
{
	// �û������ַ���
	char cmd[128];
	printf("����������ĸ��ɵ��ַ�����");
	scanf("%s", &cmd);

	int len = strlen(cmd);
	cmd[len] = '\0';

	// �ж��Ƿ���β�Գƣ���result��ȡ���صĽ��
	int result = judgeChar(cmd,len);
	printf("\n");
	printf("Result:%d \n", result);
}