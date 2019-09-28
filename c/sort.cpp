#include "./sqlist.cpp"

void BubbleSort(RecType R[],int n){
	int i,j;
	RecType tmp;
	for(i=0;i<n-1;i++){
		for(j=n-1;j>i;j--){
			// 比较j与j-1的大小，将更小的关键字进行向前移动
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

int main(){
	int n = 10; // 用来表征顺序表长度
	RecType R[MAXL]; // 顺序表最大长度
	KeyType a[] = {9,8,7,6,5,4,3,2,1,0}; // 顺序表元素
	CreateList(R,a,n);

	printf("排序前：");
	DispList(R,n);
	printf("排序后：");
	DispList(R,n);
	return 1;

}