// Dijkstra.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include<stdio.h>

#define V_NUMBER 200
#define MAXDIS 1000000
#define BUF_SIZE 500


int main(int argc, char *argv[])
{
	FILE* input;
	errno_t err;
	char buf[BUF_SIZE] = { 0 };
	char cur_c;

	int v[201] = { 0 };
	int G[V_NUMBER + 1][V_NUMBER + 1] = { 0 }; // 邻接数组
	int i, j, k;
	int m, n;

	int cur_v;
	int nxt_v;
	int cur_step;
	int dist_cur;

	int dist[201];
	int prev[201][201];
	int stack[100] = { 0 };
	int top = 0;

	memset(prev, -1, sizeof(prev)); //初始化prev数组

	for (i = 0; i < V_NUMBER + 1; i++)   //邻接数组初始化
	{
		for (j = 0; j < V_NUMBER + 1; j++)
		{
			G[i][j] = MAXDIS;
		}
	}
	for (i = 0; i < V_NUMBER + 1; i++)
		G[i][i] = 0;


	if (argc == 1)
		return EXIT_FAILURE;


	err = fopen_s(&input, argv[1], "r+");
	if (input == NULL)
		return EXIT_FAILURE;

	cur_c = ' ';

	while (!feof(input))
	{

		while (cur_c != EOF && isspace(cur_c))
			cur_c = fgetc(input);

		/*--- 取顶点序号 ---*/
		i = 0;
		while (cur_c != EOF && cur_c != '\t')
		{
			buf[i++] = cur_c;
			cur_c = fgetc(input);
		}

		buf[i] = '\0';
		cur_v = atoi(buf);
		//        printf("cur_v = %d\n", cur_v);
		memset(buf, 0, sizeof(buf));
		i = 0;

		while (cur_c != '\n' && cur_c != EOF)   //按行处理
		{
			while (cur_c != EOF && cur_c == '\t')  //跳过空格
				cur_c = fgetc(input);

			while (cur_c != EOF && cur_c != '\t')
			{
				if (cur_c >= '0' && cur_c <= '9')
				{
					buf[i++] = cur_c;
					cur_c = fgetc(input);
				}
				else if (cur_c == ',')      //扫到逗号,取到达节点的序号
				{
					buf[i] = '\0';
					nxt_v = atoi(buf);
					memset(buf, 0, sizeof(buf));
					i = 0;
					cur_c = fgetc(input);
				}
			}
			if (cur_c == '\t')
				cur_c = fgetc(input);
			buf[i] = '\0';
			dist_cur = atoi(buf);
			G[cur_v][nxt_v] = dist_cur;
			memset(buf, 0, sizeof(buf));
			i = 0;
		}
		cur_c = fgetc(input);
	}


	for (i = 1; i < V_NUMBER + 1; i++)      //迪杰斯特拉算法循环
	{
		memcpy(dist, G[i], sizeof(G[i]));

		for (j = 1; j < V_NUMBER + 1; j++)
		{
			if (G[i][j] >= 0 && G[i][j] < MAXDIS)
			{
				prev[i][j] = i;
			}
		}
		prev[i][i] = 0;
		memset(v, 0, sizeof(v));
		v[i] = 1; // 

		for (m = 1; m < V_NUMBER; m++)
		{

			//        printf("size v:%d", sizeof(v));

			k = MAXDIS;
			cur_step = 1;
			for (j = 1; j < V_NUMBER + 1; j++)
			{
				if (!v[j] && k > dist[j])
				{
					k = dist[j];
					cur_step = j;
				}
			}
			v[cur_step] = 1;

			for (j = 1; j < V_NUMBER + 1; j++)
			{
				if (!v[j] && dist[cur_step] + G[cur_step][j] < dist[j])
				{
					dist[j] = dist[cur_step] + G[cur_step][j];
					prev[i][j] = cur_step;
				}
			}
		}

	}

	int tag = 0;
	char num_buf[100] = { 0 };
	//    printf("%d to %d:%d", 45, 6, prev[45][6]);
	n = 0;
	while (1)
	{
		memset(buf, 0, sizeof(buf));
		memset(num_buf, 0, sizeof(num_buf));
		printf("请输入要查询的节点，以逗号分隔：");
		scanf_s("%s", buf, sizeof(buf));

		i = 0;
		j = 0;
		tag = 0;
		while (buf[i] != '\0')
		{
			if (buf[i] != ',')
				num_buf[j++] = buf[i];
			else
			{
				num_buf[j] = '\0';
				n = cur_v = atoi(num_buf);
				memset(num_buf, 0, sizeof(num_buf));
				j = 0;

			}
			++i;
		}

		num_buf[j] = '\0';
		nxt_v = atoi(num_buf);
		top = 0;
		memset(stack, 0, sizeof(stack));
		dist_cur = 0;
		stack[top++] = nxt_v;
		while (nxt_v != cur_v)
		{
			i = prev[cur_v][nxt_v];
			stack[top++] = i;
			dist_cur += G[i][nxt_v];
			nxt_v = i;
		}
		printf("距离：%d  ", dist_cur);
		if (top > 0)
		{
			printf("%d", stack[--top]);
		}

		while (top > 0)
		{
			cur_v = stack[--top];
			printf("->%d", cur_v);
		}
		printf("\n");

	}
	fclose(input);
	std::cout << "Hello World!\n";
}
