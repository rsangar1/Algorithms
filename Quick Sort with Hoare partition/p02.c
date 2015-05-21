/* CS575 Spring 2015 Program 02.
 *Name: Ramesh Sangaraju
 *Date: 12th Feb, 2015 
 */
#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 100000

int data[MAXSIZE];
int n;

//This generates random index from elements of array
int getRandomIndex(int first, int last){
	int range = last - first + 1;
	return first + rand()%range;
}

//The pivot element in Hoare partition algorithm is selected randomly.
int HoarePartition(int data[], int first, int last){
	int randomIndex = getRandomIndex(first, last);
	int pivot = data[randomIndex];
	int i = first - 1;
	int j = last + 1;
	while(1){
		do{
			j--;
	    }while(data[j] > pivot);

		do{
			i++;
		}while(data[i] < pivot);
		
		if(i < j){
			int temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}else
			return j;
	}
}

//Quick sort method that uses Hoare partition algorithm.
void quickSort(int data[], int first, int last){
	if(first < last){
		int divide = HoarePartition(data, first, last);
		quickSort(data, first, divide);
		quickSort(data, divide+1, last);
	}
}

int main()
{
	int i;
	scanf("%d", &n);
	for (i = 0; i < n; ++i)
	scanf("%d", &data[i]);
	// calling Quick sort method.
	quickSort(data, 0, n-1);
	for (i = 0; i < n; ++i)
	printf("%d\n", data[i]);
	return 1;
}
