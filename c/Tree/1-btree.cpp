// 二叉树基本算法

#include <stdio.h>
#include <string.h>
#include <malloc.h>

#define MaxSize 100
typedef char ElemType;
typedef struct node{
    ElemType data;
    struct node *lchild;
    struct node *rchild;
} BTNode;

/**
 * @desc 传入一个str，创建二叉树
 */
void CreateBTree(BTNode *&b,char *str){
    BTNode *St[MaxSize];
    BTNode *p=NULL; // 创建一个空数组作为栈，一个指针
    int top = -1,k=0,j=0;
    char ch;
    b = NULL; // b用来指示根节点
    ch = str[j];
    while(ch!='\0'){
        switch(ch){
            case '(':top++;St[top] = p;k=1;break; // 左孩子
            case ')':top--;break;
            case ',':k=2;break; // 右孩子
            default:
                p=(BTNode *)malloc(sizeof(BTNode));
                p->data = ch;
                p->lchild = p->rchild = NULL;
                if(b == NULL){ // b==NULL时，说明刚刚创建的*p为根节点
                    b=p;
                }else{
                    switch(k){
                        case 1:St[top]->lchild = p;break;
                        case 2:St[top]->rchild = p;break;
                    }
                }
        }
        j++;
        ch = str[j];
    }
}

/**
 * @desc 销毁一棵二叉树
 */
void DestoryBTree(BTNode *&b){
    if(b != NULL){
        DestoryBTree(b->lchild);
        DestoryBTree(b->rchild);
        free(b);
    }
}

/**
 * @desc 在二叉树中顺序查找一个元素
 */
BTNode *FindNode(BTNode *b,ElemType x){
    BTNode *p;
    if(b==NULL){
        return NULL;
    }else if(b->data == x){
        return b;
    }else{
        p=FindNode(b->lchild,x);
        if(p!=NULL){
            return p;
        }else{
            return FindNode(b->rchild,x);
        }
    }
}


/**
 * @desc 求二叉树的高度
 */
int BTHeight(BTNode *b){
    int lchildh,rchildh;
    if(b==NULL){
        return 0;
    }else{
        lchildh = BTHeight(b->lchild); // 求左子树高度
        rchildh = BTHeight(b->rchild); // 求右子树高度
        return (lchildh > rchildh)?(lchildh+1):(rchildh+1);
    }
}

// 显示一棵树
void DispBTree(BTNode *b){
    if(b!=NULL){
        // printf("%c",&b->data);
        if(b->lchild!=NULL || b->rchild!=NULL){
            // printf("(");    // 如果当前节点有孩子节点，输出一个前括号包裹
            DispBTree(b->lchild);
            if(b->rchild!=NULL)
                // printf(",");  // 左右节点用逗号分隔
            DispBTree(b->rchild);
            // printf(")");    // 输出一个后括号包裹当前的层次的树节点
        }
    }
}

// 调试
// int main(){
//     BTNode *b;
//     char str[30];
//     strcpy_s(str, "A(B(D,E),C(,F))");
//     CreateBTree(b,str);
//     DispBTree(b);
//     // printf("\n");
// 	return 1;
// }