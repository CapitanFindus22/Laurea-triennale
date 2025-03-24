#include <stdlib.h>

void InsertionSort(int *arr, size_t size) {

    int value,j;

    for (size_t i = 1; i < size; i++)
    {
        value = arr[i];

        j = i-1;

        while ((j>=0)&(arr[j]>value))
        {
            arr[j+1] = arr[j];
            j = j-1;
        }
        
        arr[j+1] = value;
    }
    

}

void SelectionSort(int *arr, size_t size) {

    int min,tmp;

    for (size_t i = 0; i < size-1; i++)
    {
        min = i;

        for (size_t j = i+1; j < size; j++)
        {
            if (arr[j]<arr[min])
            {
                min = j;
            }
            
        }

        if (min != i)
        {
            tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
        
        
    }
    

}

void BubbleSort(int *arr, size_t size) {

    int tmp;

    for (size_t i = 0; i < size - 1; i++)
    {
        for (size_t j = i+1; j < size; j++)
        {
            if (arr[j]<arr[i])
            {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
    }
    

}

void CountingSort(int *arr, size_t size, int max) {

    int *C = calloc(max+1,sizeof(int));

    for (size_t i = 0; i < size; i++)
    {
        C[arr[i]]++;
    }

    size_t j = 0;

    for (size_t i = 0; i <= max; i++)
    {
        while (C[i]>0)
        {
            arr[j] = i;
            j++;
            C[i]--;
        }
        
    }
    
    


}