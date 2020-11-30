#pragma warning(disable : 4996) 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "llgen.h"          
#include "llapp.h"          

int main ( int argc, char *argv[] )
{
	// 创建一个链表
    struct  List *L1 = CreateLList ( 
		CreateData1,     
        DeleteData1, 
        DuplicatedNode1,
        NodeDataCmp1 
	);


    
}
