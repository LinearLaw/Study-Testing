#include "./sqlist.cpp"

// 冒泡排序
void BubbleSort(RecType R[],int n){
	int i,j;
	RecType tmp;
	for(i=0;i<n-1;i++){
		for(j=n-1;j>i;j--){
			// 比较，找出本趟最小关键字的记录
			if(R[j].key < R[j-1].key){
				tmp = R[j];
				R[j]=R[j-1];
				R[j-1] = tmp;
			}
		}
		printf("  i=%d:  ",i);
		DispList(R,n);
	}
}

// 快速排序
int count = 1;
void QuickPartition(RecType R[],int s, int t){
	int i = s,j=t;
	RecType tmp = R[i];
	while(i<j){
		while(i<j && R[j].key >= tmp.key)
			j--;
		R[i] = R[j];

		while(i<j && R[i].key <= tmp.key)
			i++;
		R[j] = R[i];
	}
	R[i] = tmp;
	return i;
}
void QuickSort(RecType R[],int s, int t){
	int i;
	RecType tmp;
	if(s<t){
		count++;
		i = QuickPartition(R,s,t);
		DispList(R,10); // 打印当前的R数组
		QuickSort(R,s,i-1);
		QuickSort(R,i+1,t);
	}
}

int main(){
	int n = 10; // 用于表征顺序表的元素个数
	RecType R[MAXL]; // 用于表征顺序表的最大长度
	KeyType a[] = {9,8,7,6,5,4,3,2,1,0}; // 数据元素
	CreateList(R,a,n);

	// 1、冒泡排序
	printf("排序之前：");
	DispList(R,n);

	BubbleSort(R,n);
	printf("排序之后：");
	DispList(R,n);

	// 2、快速排序
	QuickSort(R,0,n-1);
	printf("count=%d\n",count);

	return 1;

}