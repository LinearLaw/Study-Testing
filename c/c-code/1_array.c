#include <stdio.h>

/**
 * @desc 总结，二维数组步进跟一维是一样的，
 *      p++的时候，2会指向3,3会指向4 
 */

/**
 * @desc 二维数组指针的步进
 */ 
int main01()
{
    int arr[2][3] = {1,2,3,4,5,6};
    int *p0 = &arr[0][0];
    int *p1 = arr[0];
    int *p2 = arr;
    p0++;
    p1++;
    p2++;
    
    printf("%d\n",*p0);  // 这里输出了一个2
    printf("%d\n",*p1);  // 这里输出了一个2
    printf("%d\n",*p2);  // 这里输出了一个2

    system("pause");
    return 0;
}
int main()
{
    int arr[2][3] = {1,2,3,4,5,6};
    int *p0 = &arr[1][1];
    int *p1 = arr[1];
    int *p2 = arr;
    p0++;
    p1++;
    p2++;
    
    printf("%d\n",*p0);  // 这里输出了一个6
    printf("%d\n",*p1);  // 这里输出了一个5
    printf("%d\n",*p2);  // 这里输出了一个2

    system("pause");
    return 0;
}

