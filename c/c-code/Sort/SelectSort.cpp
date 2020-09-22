#include "./sqlist.cpp"

/**
 选择排序，核心是两个：简单选择排序、堆排序
 算法思想：遍历元素，
 	第i次在后面n-i+1个元素当中选择一个关键字最小或最大的元素，
 	将其加入到有序序列中的第i个元素，循环这个操作直至第n-1趟。
 
 简单选择排序，时间复杂度o(n^2)，空间复杂度o(1)
 堆排序，时间复杂度，建立堆o(n)，后面有n-1次向下调整的操作，每次o(h)，
 	综合最好和最坏情况，时间复杂度平均为o(nlog2n);
 	空间复杂度o(1)

 */

// 1、简单选择排序
void SelectSort(RecType R[],int n){
	int i,j,min=0;
	RecType tmp; 
	for(i=0;i<n-1;i++){
		min = i; // 记录最小元素位置
		for(j=i+1;j<n;j++){  // 在i到n-1之中，选择一个最小元素
			if(R[j].key < R[min]){
				min = j;
			}
		}
		if(min != i){ // 将最小元素min和i位置的元素交换
			tmp = R[i];
			R[i]= A[min];
			A[min] = tmp;
		}
	}
}

// 2、堆排序
// 2.1、建堆
void sift(RecType R[],int low, int high){
	int i = low,j = 2*i;
	RecType temp = R[i];
	while(j<=high){
		if(j<high && R[j].key<R[j+1].key){
			j++
		}
		if(temp.key<R[j].key){
			R[i] = R[j];
			i=j;
			j=2*i;
		}
		else 
			break;
	}
	R[i] = temp;
}

// 2.2、排序
void HeapSort(RecType R[],int n ){
	int i ;
	RecType tmp;

	// 循环建堆，调用sift算法n/2次
	for(i=n/2;i>=1;i--){
		sift(R,i,n);
	}
	printf("初始堆：");DispList(R,n);

	for(i=n;i>=2;i--){
		tmp=R[1];
		R[1] = R[i];
		R[i] = tmp;

		printf("第%d趟：",n-i+1);DispList(R,n);
		sift(R,1,i-1);
		printf("筛选为：");DispList(R,n);
	}

}