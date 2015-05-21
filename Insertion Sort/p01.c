/* code for Insertion sort - CS575 Spring 2015 Program 01.
 * Name: Ramesh Sangaraju 
 * Date: 3rd Febraury, 2015
 */
#include <stdio.h>

#define MAXSIZE 100000

int data[MAXSIZE];
int n;

int main()
{
	int i, j, key;

  	scanf("%d", &n);
 	for (i = 0; i < n; ++i)
	    	scanf("%d", &data[i]);

	 // Insertion sort logic.
	for(j = 1; j < n; j++){
		key = data[j];
		for(i = j-1; i >= 0 && data[i] > key; i--){
			data[i+1] = data[i];
		}
		data[i+1] = key;
	}

	for (i = 0; i < n; ++i)
    		printf("%d\n", data[i]);
	return 0;
}
