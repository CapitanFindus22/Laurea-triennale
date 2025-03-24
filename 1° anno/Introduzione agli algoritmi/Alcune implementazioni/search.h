/*
    Sequential search for an array
*/
int SearchArray(int* arr, int size, int num) {

for(int i=0;i<size;i++)

    if(arr[i]==num) {

        return i;

    }

    return -1;

}

/*
    Binary search for an ordered array
*/
int BinarySearchArray(int* arr, int size, int num) {

    int i1=0;

    int i2=size;

    int m=i2/2;

    while(arr[m]!=num) {

        if(arr[m]>num){

            i2=m-1;

        }

        else 
        {
            i1=m+1;
        }

        if(i1>i2) {

            return -1;

        }

        m=(i1+i2)/2;

    }
    
    return m;
    
    }