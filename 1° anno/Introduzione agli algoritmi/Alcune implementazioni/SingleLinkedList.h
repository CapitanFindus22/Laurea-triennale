#include <stdlib.h>
#include <stdio.h>


typedef struct SingleNode
{
    int value;
    struct SingleNode* next;

}SingleNode;


typedef struct 
{
    SingleNode* head;
    SingleNode* tail;
    size_t elements;

}SingleList;

/*
    Create a single linked list
*/
SingleList* CreateSingleList() {

    return (SingleList*)calloc(1,sizeof(SingleList));

}

/*
    Insert an element in the list
*/
void InsertSElement(SingleList* l, int value) {

    if(l->head==NULL) {

        l->head = (SingleNode*)malloc(sizeof(SingleNode));
        l->head->value = value;
        l->head->next = NULL;
        l->tail=l->head;

    }

    else {

        SingleNode* ptr = l->head;

        while (ptr->next!=NULL)
        {
            ptr = ptr->next;
        }
        
        ptr->next = (SingleNode*)malloc(sizeof(SingleNode));
        ptr->next->value = value;
        ptr->next->next = NULL;
        l->tail = ptr->next;

    }

    l->elements++;

}

/*
    Insert multiple values at once
*/
void InsertSMultipleValues(SingleList* l, int* arr, size_t size) {

    for (size_t i = 0; i < size; i++)
    {
        InsertSElement(l,arr[i]);
    }
    

}

/*
    Print all the nodes values 
*/
void PrintSList(SingleList* l) {

    SingleNode* ptr = l->head;

    while (ptr!=NULL)
    {
        printf("%d ",ptr->value);
        ptr=ptr->next;

    }
    
    printf("\n");

}

/*
    Search an element in the list
*/
SingleNode* SearchSList(SingleList* l, int num) {

    SingleNode* ptr = l->head;

    while (ptr!=NULL)
    {
        
        if(ptr->value==num) {

            return ptr;

        }

        ptr=ptr->next;

    }

    return NULL;
    
}

/*
    Delete the element in the list if present
*/
int DeleteSElement(SingleList* l, int num) {

    SingleNode* ptr = SearchSList(l,num);

    // The list doesn't contain num
    if(ptr==NULL) {return -1;}

    l->elements--;

    // Num is the first node
    if(ptr==l->head) {

        l->head=ptr->next;
        free(ptr);
        return 0;

    }

    // Find the previous node

    SingleNode* ptr2 = l->head;

    while (ptr2->next!=ptr)
    {
        ptr2=ptr2->next;
    }
    
    ptr2->next = ptr->next;

    free(ptr);

    return 0;
    
}