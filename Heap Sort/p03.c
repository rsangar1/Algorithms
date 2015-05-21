/* CS575 Spring 2015 Program 03.
 *Name: Ramesh Sangaraju
 *Date: 19th Feb, 2015 
 */

#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 100000

int data[MAXSIZE];
int n;
int heapSize;

//This functions adjust the elements so that it satisfies max heap condition
void maxHeapify(int data[], int index){
	int leftIndex = 2*index + 1;
	int rightIndex = 2*index + 2;
	int largest = index;
	int temp;
	if(leftIndex<heapSize && data[leftIndex] > data[index]){
		largest = leftIndex;
	}
	if(rightIndex<heapSize && data[rightIndex] > data[largest]){
		largest = rightIndex;
	}
	if(largest != index){
		temp = data[index];
		data[index] = data[largest];
		data[largest] = temp;
		maxHeapify(data, largest);
	}
}

//This function helps to build the max heap from given array
void buildMaxHeap(int data[]){
	int i=0;
	for(i=(n/2)-1; i>=0; --i){
		maxHeapify(data,i);
	}
}

//This function sorts the given array of elements based on heap sort technique
void heapSort(int data[]){
	int i, temp=0;
	heapSize = n;
	buildMaxHeap(data);
	for(i=n-1;i>0;--i){
		temp = data[0];
		data[0] = data[i];
		data[i] = temp;
		--heapSize;
		maxHeapify(data,0);
	}
}

int main()
{
	int i;
	scanf("%d", &n);
	for (i = 0; i < n; ++i)
		scanf("%d", &data[i]);
	// calling Heap sort method.
	heapSort(data);
	for (i = 0; i < n; ++i)
		printf("%d\n", data[i]);
	return 1;
}

