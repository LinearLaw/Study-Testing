// 顺序表基本算法

#include <stdio.h>
#define MAXL 100
typedef int KeyType;
typedef char InfoType;

// 查找元素的类型
typedef struct{
	KeyType key;
	InfoType data;
} RecType;

// x和y的顺序进行交换
void swap(RecType x,RecType y){
	RecType tmp = x;
	x=y;
	y=tmp;
}

// 创建顺序表
void CreateList(RecType R[],KeyType keys[],int n){
	for(int i = 0;i<n;i++){
		R[i].key = keys[i];
	}
}

// 依次打印所有顺序表内的key元素
void DispList(RecType R[],int n){
	for(int i =0;i<n;i++){
		printf("%d",R[i].key);
	}
	printf("\n");
}





