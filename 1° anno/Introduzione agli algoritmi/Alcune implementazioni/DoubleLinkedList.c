#include <stdlib.h>
#include <stdio.h>

typedef struct DoubleNode
{
    int value;
    struct DoubleNode *next,*previous;

}DoubleNode;

typedef struct 
{
    DoubleNode* head;
    DoubleNode* tail;
    size_t elements;

}DoubleList;

/*
    Create a double linked list
*/
DoubleList* CreateDoubleList() {

    return (DoubleList*)calloc(1,sizeof(DoubleList));

}

/*
    Insert an element in the list
*/
void InsertDElement(DoubleList* l, int value) {

    if(l->head==NULL) {

        l->head = (DoubleNode*)malloc(sizeof(DoubleNode));
        l->head->value = value;
        l->head->next = NULL;
        l->head->previous = NULL;
        l->tail = l->head;

    }

    else {

        DoubleNode* ptr = l->head;

        while (ptr->next!=NULL)
        {
            ptr = ptr->next;
        }
        
        ptr->next = (DoubleNode*)malloc(sizeof(DoubleNode));
        ptr->next->value = value;
        ptr->next->next = NULL; 
        ptr->next->previous = ptr; 
        l->tail = ptr->next;

    }

    l->elements++;

}

/*
    Insert multiple values at once
*/
void InsertDMultipleValues(DoubleList* l, int* arr, size_t size) {

    for (size_t i = 0; i < size; i++)
    {
        InsertDElement(l,arr[i]);
    }
    

}

/*
    Print all the nodes values 
*/
void PrintDList(DoubleList* l) {

    DoubleNode* ptr = l->head;

    while (ptr!=NULL)
    {
        printf("%d ",ptr->value);
        ptr=ptr->next;

    }

    printf("\n");
    
}

/*
    Print all the nodes values in reverse
*/
void PrintDListReverse(DoubleList* l) {

DoubleNode* ptr = l->tail;

while (ptr!=NULL)
{
    printf("%d ",ptr->value);
    ptr=ptr->previous;

}

printf("\n");

}

/*
    Search an element in the list
*/
DoubleNode* SearchDList(DoubleList* l, int num) {

    DoubleNode* ptr = l->head;
    DoubleNode* ptr2 = l->tail;

    while ((ptr!=NULL)|(ptr2!=NULL))
    {
        
        if(ptr->value==num) {

            return ptr;

        }

        if(ptr2->value==num) {

            return ptr2;

        }

        ptr=ptr->next;

        if(ptr==ptr2) {break;}

        ptr2=ptr2->previous;

    }

    return NULL;
    
}

/*
    Delete the element in the list if present
*/
int DeleteDElement(DoubleList* l, int num) {

    DoubleNode* ptr = SearchDList(l,num);

    // The list doesn't contain num
    if(ptr==NULL) {return -1;}

    l->elements--;

    // Num is the first node
    if(ptr==l->head) {

        l->head=ptr->next;

        if(l->head!=NULL) {l->head->previous=NULL;}

        free(ptr);
        return 0;

    }

    ptr->previous->next = ptr->next;
    if(ptr->next!=NULL) {ptr->next->previous=ptr->previous;}

    free(ptr);

    return 0;
    
}