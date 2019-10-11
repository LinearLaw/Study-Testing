/**
 * @desc 先序、中序和后序遍历，递归算法
 */

#include "1-btree.cpp"

void PreOrder(BTNode *b){
    if(b!=NULL){
        printf("%c",b->data);
        PreOrder(b->lchild);
        PreOrder(b->rchild);
    }
}

void InOrder(BTNode *b){
    if(b!=NULL){
        InOrder(b->lchild);
        printf("%c",b->data);
        InOrder(b->rchild);
    }
}

void PostOrder(BTNode *b){
    if(b!=NULL){
        PostOrder(b->lchild);
        PostOrder(b->rchild);
        printf("%c",b->data);
    }
}

int main(){
    BTNode *b;
    char str[] = "A(B(D(,G)),C(E,F))";
    CreateBTree(b,str);

    printf("b:");
    DispBTree(b);
    printf("\n");

    printf("先序遍历序列：");PreOrder(b);printf("\n");
    printf("中序遍历序列：");InOrder(b);printf("\n");
    printf("后序遍历序列：");PostOrder(b);printf("\n");
    DestoryBTree(b);
    return 1;
}