#include <stdlib.h>
#include <stdio.h>

int main()
{
    int a = 10;
    int b = 20;
    /** (a>b?a:b) = 100;
     * 本句不能被编译成功，
     * 因为C语言的三目运算符的结果是右值
     * 但是C++就可以编译成功了，因为C++的三目运算符是左值
     */

    return 0;
}