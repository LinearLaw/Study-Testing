#include <iostream>
#include <vector>

using namespace std;

/*
	1、快速排序
		q   待排序的数组
		l   待排序的数组的起始标号 -> 注意初始是0
		r   待排序的末尾标号 -> 初始是length - 1
 */
void quick_sort(int q[], int l, int r) {
	if (l >= r) {
		return;
	}
	int i = l - 1;
	int j = r + 1;
	int x = q[l + r >> 1]; // 取中间点作为基准

	while (i < j) {
		do i++; while (q[i] < x);	// 从前往后，找一个比基准大的
		do j--; while (q[j] > x);	// 从后往前，好一个比基准小的
		if (i < j) {
			int temp = q[i];	// 两个元素交换位置
			q[i] = q[j];
			q[j] = temp;
		}
	}
	quick_sort(q, l, j);
	quick_sort(q, j + 1, r);
}

int main()
{
	int q[] = { 1,2,3,4325,3463,23 };
	int n = 6;
	quick_sort(q, 0, n - 1);

	for (int i = 0; i < n; i++)
	{
		cout << q[i] << endl;
	}
}