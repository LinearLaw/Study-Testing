// 先序、中序、后序遍历非递归算法
#include "1-btree.cpp"

//--------------------------------------------
typedef struct {
    BTNode *data[MaxSize];
    int top;
} SqStack;

void InitStack(SqStack *&s){
    s = (SqStack *)malloc(sizeof(SqStack));
    s->top = -1;
}

void DestoryStack(SqStack *&s){
    free(s);
}

bool StackEmpty(SqStack *s){
    return (s->top == -1);
}
bool Push(SqStack *&s,BTNode *e){
    if(s->top==MaxSize-1){
        return false;
    }
    s->top ++;
    s->data[s->top] = e;
    return true;
}
bool Pop(SqStack *&s,BTNode *&e){
    if(s->top == -1){
        return false;
    }
    e = s->data[s->top];
    s->top--;
    return true;
}
bool GetTop(SqStack *s,BTNode *&e){
    if(s->top == -1){
        return false;
    }
    e = s->data[s->top];
    return true;
}
//--------------------------------------------

// 先序遍历非递归算法 - 1
void PreOrder1(BTNode *b){
    BTNode *p;
    SqStack *st;
    InitStack(st);
    Push(st,b);
    while(!StackEmpty(st)){
        Pop(st,p);
        printf("%c",p->data);
        if(p->rchild !=NULL){
            Push(st,p->rchild);
        }
        if(p->lchild !=NULL){
            Push(st,p->lchild);
        }
    }
    printf("\n");
    DestoryStack(st);
}
// 先序遍历非递归算法 - 2
void PreOrder2(BTNode *b){
    BTNode *p;
    SqStack *st;
    InitStack(st);
    p=b;
    while(!StackEmpty(st)){
        while(p!=NULL){     // 扫描结点p的所有左下结点，全部进栈
            printf("%c",p->data);
            Push(st,p);
            p=p->lchild;
        }
        if(!StackEmpty(st)){ // 当栈非空时，将结点出栈，并访问结点p，接着处理右子树
            Pop(st,p);
            p=p->rchild;
        }
    }
    printf("\n");
    DestoryStack(st);
}
// 中序遍历非递归算法
void InOrder(BTNode *b){
    BTNode *p;
    SqStack *st;
    InitStack(st);
    if(b!=NULL){
        p=b;
        while(!StackEmpty(st) || p!=NULL){ //扫描结点p的所有左下结点并进栈
            while(p!=NULL){
                Push(st,p); // 结点p进栈，并继续移动到下一个左孩子
                p=p->lchild;
            }
            if(!StackEmpty(st)){    //栈非空时，出栈一个节点p，访问，接着处理该节点的右子树
                Pop(st,p);
                printf("%c",p->data);
                p = p->rchild;
            }
        }
        printf("\n");
    }
    DestoryStack(st);
}

// 后序遍历非递归算法
// 先遍历两个子树，最后再访问根节点
void PostOrder(BTNode *b){
    BTNode *p,*r;
    bool flag;  // flag为true，表示当前节点为栈顶节点
    SqStack *st;
    InitStack(st);
    p = b;
    do{
        while(p!=NULL){     // 扫描结点p的所有的左下结点，并全部进栈
            Push(st,p);
            p=p->lchild;
        }
        r = NULL; // [关键]：借助一个r指针，用来指示刚刚访问过的结点
        flag = true; // flag为true表示正在访问栈顶节点
        while(!StackEmpty(st) && flag){ 
            GetTop(st,p);   // 取出栈顶节点
            if(p->rchild == r){     // 判断栈顶节点p是否为刚刚访问过的结点，是，则将r指向刚刚访问过的结点
                printf("%c",p->data);  
                Pop(st,p);
                r = p;
            }else{
                p=p->rchild; // 如果当前的p不是刚刚访问过的结点，那就说明刚刚访问的是左子树，现在处理右子树
                flag=false;
            }
        }
    }while(!StackEmpty(st));
    printf("\n");
    DestoryStack(st);
}

int main(){
    BTNode *b;
    char str[] = "A(B(D(,G)),C(E,F))";
    CreateBTree(b,str);
    printf("b:");
    DispBTree(b);
    printf("\n");

    printf("先序遍历序列1:");PreOrder1(b);
	printf("先序遍历序列2:");PreOrder2(b);
	printf("中序遍历序列:");InOrder(b);
	printf("后序遍历序列:");PostOrder(b);
    return 1;
}






