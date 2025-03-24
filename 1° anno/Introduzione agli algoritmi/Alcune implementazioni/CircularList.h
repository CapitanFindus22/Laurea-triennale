#include <stdlib.h>
#include <stdio.h>

typedef struct DoubleNode
{
    int value;
    struct DoubleNode *next,*previous;

}DoubleNode;

typedef struct 
{
    DoubleNode* start;
    size_t elements;

}CircularList;

/*
    Create an empty circular linked list
*/
CircularList* CreateEmptyCircularList(int NumElements) {

    if(NumElements<=1) {return NULL;}

    CircularList* l = (CircularList*)calloc(1,sizeof(CircularList));

    l->elements = NumElements;

    l->start = (DoubleNode*)malloc(sizeof(DoubleNode));

    DoubleNode* ptr = l->start;

    for (size_t i = 0; i < NumElements-1; i++)
    {
        ptr->next = (DoubleNode*)malloc(sizeof(DoubleNode));
        ptr->next->previous = ptr;
        ptr = ptr->next;
    }
    
    ptr->next = l->start;
    l->start->previous = ptr;

    return l;

}

/*
    Create a circular linked list
*/
CircularList* CreateCircularList(int *arr, int NumElements) {

    if(NumElements<=1) {return NULL;}

    CircularList* l = CreateEmptyCircularList(NumElements);

    DoubleNode* ptr = l->start;

    for (size_t i = 0; i < NumElements; i++)
    {
        ptr->value = arr[i];
        ptr = ptr->next;
    }

    return l;

}

/*
    Print all the nodes values 
*/
void PrintCList(CircularList* l) {

    DoubleNode* ptr = l->start;

    for (size_t i = 0; i < l->elements; i++)
    {
        printf("%d ",ptr->value);
        ptr=ptr->next;
    }

    printf("\n");
    
}

/*
    Print all the nodes values in reverse
*/
void PrintCListReverse(CircularList* l) {

    DoubleNode* ptr = l->start;

    for (size_t i = 0; i < l->elements; i++)
    {
        printf("%d ",ptr->value);
        ptr=ptr->previous;
    }

    printf("\n");
    
}

/*
    Set new values for the nodes
*/
int SetNewValues(CircularList* l, int *arr, int size) {

    if(size>l->elements) {return -1;}

    DoubleNode* ptr = l->start;

    for (size_t i = 0; i < size; i++)
    {
        ptr->value = arr[i];
        ptr = ptr->next;
    }

    return 0;

}