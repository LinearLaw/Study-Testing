
#pragma warning(disable : 4996) 

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "llgen.h"
#include "llapp.h"

#define TABLE_SIZE 10	// 哈希表的长度

extern unsigned long ElfHash(char *);

/**
 * @desc 字符串分隔函数
 *		src   原本的字符串
		separator 分隔符
		dest	结果数组
		num		结果数组的长度
 */
void split(char *src, const char *separator, char **dest, int *num) {
	char *pNext;
	int count = 0;
	if (src == NULL || strlen(src) == 0)
		return;
	if (separator == NULL || strlen(separator) == 0)
		return;

	// strtok，获取分隔符前的第一段字符。
	pNext = strtok(src, separator);
	while (pNext != NULL) {
		*dest++ = pNext;
		++count;
		pNext = strtok(NULL, separator);
	}
	*num = count;
}

// 创建一个哈希表
int CreateTable(Link **t)
{
	*t = (Link *)calloc(TABLE_SIZE, sizeof(Link));
	if (*t == NULL) return (0);

	if (**t != NULL) {	/* is the calloc'd memory == NULL? */
		for (int i = 0; i < TABLE_SIZE; i++, t++)
			**t = NULL;
	}

	return (1);
}


int main(int argc, char *argv[])
{
	if (argc < 2) {
		fprintf(stderr, "Error! Usage: Algorithm3 filename\n");
		exit(EXIT_FAILURE);
	}

	if (argc > 2) {
		fprintf(stderr, "Warning: Usage: Algorithm3 filename\n");
	}

	// 创建文件读取流对象
	FILE     *fin;	
	fin = fopen(argv[1], "rt");
	if (fin == NULL) {
		fprintf(stderr, "Error! 文件不存在或无法打开 %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	// 创建哈希表
	Link   *Table;	
	if (!CreateTable(&Table)) {
		fprintf(stderr, "Error! 哈希表创建失败\n");
		exit(EXIT_FAILURE);
	}

	// 创建临时链表
	struct List *L1;	
	L1 = CreateLList(
		CreateData1,
		DeleteData1,
		DuplicatedNode1,
		NodeDataCmp1
	);   
	if (L1 == NULL) {
		fprintf(stderr, "Error creating linked list\n");
		exit(EXIT_FAILURE);
	}


	char buffer[128];  // 设置buffer，用来读取文件中的一行字符
	unsigned hash_key; // 哈希值

	// 临时存放的节点数据
	struct NodeData1 nd;
	struct Node      n;

	// 每一行的字符长度
	int len = 0;

	// 1、读取每一行文本，根据手机号计算哈希值，建立哈希表。
	while (!feof(fin))
	{
		// 读取一行文本
		if (fgets(buffer, 127, fin) == NULL)
			break;

		len = strlen(buffer);
		buffer[len - 1] = '\0';

		char bufferCpy[128];
		strcpy(bufferCpy,buffer);
		nd.word = bufferCpy;

		/* 按逗号分隔，存入revbuf，
			0 - 手机号
			1 -	姓名
			2 - 年龄
		*/
		char *revbuf[4] = { 0 };
		int revbufCount = 0;
		split(buffer, ",", revbuf, &revbufCount);

		
		nd.mobile = strupr(revbuf[0]);
		nd.u = 1;

		/* 开始解析数据 */
		// 利用手机号，生成哈希值
		hash_key = (unsigned int)ElfHash(revbuf[0]);
		hash_key %= TABLE_SIZE;

		// 往链表中插入数据
		L1->LHead = Table[hash_key];
		if (AddNodeAscend(L1, &nd) == 0)  printf("Warning! 插入节点出错\n");

		// 插入完毕，将头结点保存到Table的对应位置
		Table[hash_key] = L1->LHead;

		printf("hash_key->%d : %s\n", 
			hash_key,
			nd.word
		);
	}

	// 2、等待输入命令，根据命令响应不同操作
	// 用户输入的命令
	char cmd[128];
	int findCount = 0;
	Link pcurr;
	while (1) {
		printf("\n-----------------------------\n");
		printf("可用命令：\n");
		printf("%10s：打印出该号码对应的电话记录\n","电话号码");
		printf("%10s：列出所有的电话记录\n","L");
		printf("\n输入命令执行对应操作：");
		scanf("%s", &cmd);
		printf("\n");

		switch (cmd[0]) {
			case 'L':
			case 'l':
				for (int i = 0; i < TABLE_SIZE; i++) {
					pcurr = Table[i];
					if (pcurr == NULL) 
						continue;
					else {
						for (int chain_len = 0; ; pcurr = pcurr->next, chain_len++) {
							memcpy(&n, pcurr, sizeof(struct Node));

							printf("Table[%d]: %-20s   %3u\n",
								i,
								((pND1)n.pdata)->word,
								((pND1)n.pdata)->u);

							if (pcurr->next == NULL) break;
						}
					}
				}
				break;
			default:
				hash_key = (unsigned int)ElfHash(cmd);
				hash_key %= TABLE_SIZE;

				pcurr = Table[hash_key];
				if (pcurr == NULL)
					continue;

				findCount = 0;
				for (int chain_len = 0; ; pcurr = pcurr->next) {
					memcpy(&n, pcurr, sizeof(struct Node));

					findCount++;
					chain_len++;

					if (strcmp(((pND1)n.pdata)->mobile, cmd) == 0) {
						printf("Result: 1\n");
						printf("Find target element: %-20s    %3u\n",
							((pND1)n.pdata)->word,
							((pND1)n.pdata)->u
						);
						printf("Hash key: %d\n", hash_key);
						printf("查找次数: %d\n", findCount);
						break;
					}else if (pcurr->next == NULL){
						printf("Result: 0\n");
						printf("Not found.\n");
						printf("查找次数: %d\n", findCount);
						break;
					} 
				}
				break;
		}
	}
	system("pause");
	return (EXIT_SUCCESS);
}
