#include <stdio.h>

int* read_data(char *filename) {
	FILE *fp;
	int *data_array;
	data_array = (int *) malloc(sizeof(int));
	int pos = 0;
	fp = fopen(filename, "r");
	fscanf(fp, "%i", data_array[0]);
	while () {
		
}

int * insertionSort() {

}

int main() {
	return 0;
}
