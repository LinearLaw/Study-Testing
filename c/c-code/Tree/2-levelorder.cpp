#include "1-btree.cpp"
#define MaxSize 100


//------------------------------------------------------
// 队列的基本方法
typedef struct{
    BTNode *data[MaxSize];
    int front,rear;
}SqQueue;

void InitQueue(SqQueue *&q){
    q=(SqQueue *)malloc (sizeof(SqQueue));
    q->front = q->rear = 0;
}

void DestoryQueue(SqQueue *&q){
    free(q);
}

bool QueueEmpty(SqQueue *q){
    return (q->front == q->rear);
}

bool EnQueue(SqQueue *&q,BTNode *e){
    if((q->rear+1)%MaxSize == q->front){
        return false;
    }
    q->rear = (q->rear+1)%MaxSize;
    q->data[q->rear] = e;
    return true;
}
bool DeQueue(SqQueue *&q,BTNode *&e){
    if(q->front == q->rear){
        return false;
    }
    q->front = (q->front + 1)%MaxSize;
    e = q->data[q->front];
    return true;
}
//------------------------------------------------------
// 层次遍历
void levelOrder(BTNode *b){
    BTNode *p;
    SqQueue *qu;
    InitQueue(qu);
    EnQueue(qu,b);
    while(!QueueEmpty(qu)){
        DeQueue(qu,p);
        printf("%c",p->data);
        if(p->lchild !=NULL){
            EnQueue(qu,p->lchild);
        }
        if(p->rchild !=NULL){
            EnQueue(qu,p->rchild);
        }
    }
}
int main(){
    BTNode *b;
    char str[15];

    // 注意，strcpy_s用于复制字符串到数组中，
    // 在vc++6.0要用strcpy
    // 在vs 2017要用strcpy_s
    strcpy_s(str, "A(B(D,E),C(,F))");
    CreateBTree(b,str);
    printf("b:");
    DispBTree(b);
    printf("\n");

    printf("层次遍历序列：");
    levelOrder(b);
    printf("\n");
    DestoryBTree(b);
    return 1;
}