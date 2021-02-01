#include <iostream>
#include <vector>

using namespace std;

/*
	1、快速排序
		给定数组，要求给数组排序

		q   待排序的数组
		l   待排序的数组的起始标号 -> 注意初始是0
		r   待排序的末尾标号 -> 初始是length - 1
 */
class QuickSort {

public:
	void quick_sort(int q[], int l, int r) {
		if (l >= r) {
			return;
		}
		int i = l - 1;
		int j = r + 1;
		int x = q[(l + r) >> 1]; // 取中间点作为基准

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
	void run_quick_sort() {
		int q[] = { 1,2,3,4325,3463,23 };
		int n = 6;

		// 1、快速排序
		quick_sort(q, 0, n - 1);

		// 2、打印结果
		cout << "-------quick sort--------" << endl;
		for (int i = 0; i < n; i++)
		{
			cout << q[i] << endl;
		}
		cout << "---------------" << endl;
	}
};

/*
	2、归并排序

 */
class MergeSort {
public:
	vector<int> tmp; // tmp用来临时存放数组q从l到r区间的数据
	void merge_sort(int q[], int l, int r) {
		if (l >= r) {
			return;
		}
		int mid = (l + r) >> 1;

		merge_sort(q, l, mid);
		merge_sort(q, mid + 1, r);

		// 1、这里有点像双指针法，首尾指针，向中间遍历，小的先放，大的后放
		int k = 0;
		int i = l;
		int j = mid + 1;
		while (i <= mid && j <=r) {
			if (q[i] <= q[j]) {
				tmp[k++] = q[i++];
			}
			else {
				tmp[k++] = q[j++];
			}
		}
		while (i <= mid) tmp[k++] = q[i++];
		while (j <= r) tmp[k++] = q[j++];

		// 2、然后将本次排序好的内容，还原到q[l] ~ q[r]中
		for ( i = l,j = 0; i <= r; i++,j++ ) {
			q[i] = tmp[j];
		}
	}

	void run_merge_sort() {
		int q[] = { 2,3,1,4325,3463,23 };
		int n = 6;

		// 1、构造tmp数组
		vector<int> a;
		for (int i = 0; i < n; i++){
			a.push_back(q[i]);
		}
		tmp = a;

		// 2、归并排序
		merge_sort(q, 0, n - 1);

		// 3、打印结果
		cout << "-------merge sort--------" << endl;
		for (int i = 0; i < n; i++)
		{
			cout << q[i] << endl;
		}
		cout << "---------------" << endl;
	}
};


int main()
{
	QuickSort().run_quick_sort();

	MergeSort().run_merge_sort();
}