#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include "Insertionsort.h"

//int* read_data(char *filename) {
//	FILE *fp;
//	int *data_array;
//	data_array = (int *) malloc(sizeof(int));
//	int pos = 0;
//	fp = fopen(filename, "r");
//	fscanf(fp, "%i", data_array[0]);
//	while () {
//
//}

jint* insertion_sort(jint *, jsize, jdouble);

/* Recursive binary search function */

JNIEXPORT jintArray JNICALL Java_Insertionsort_insertSort
  (JNIEnv *env, jobject object, jintArray array_to_sort, jdouble failureProb) {
	jsize len;
	jint *array_copy;
	jboolean *is_copy = 0;
	jintArray result;

	len = (*env)->GetArrayLength(env, array_to_sort);
//	if (len == NULL) {
//		printf("Cannot get length\n");
//	    exit(0);
//	}
	result = (*env)->NewIntArray(env, len);
	if (result == NULL) {
		printf("Cannot create new empty array\n");
	    exit(0);
	}

	array_copy = (jint *) (*env)->GetIntArrayElements(env, array_to_sort, is_copy);
	if (array_copy == NULL){
	    printf("Cannot obtain array from JVM\n");
	    exit(0);
	}

    array_copy = insertion_sort(array_copy, len, failureProb);

	(*env)->SetIntArrayRegion(env, result, 0, len, array_copy);
	if (result == NULL) {
		printf("Error in converting data\n");
	    exit(0);
	}

    return result;

}

/*---------------------------------------------------------------*/

jint* insertion_sort(jint *array_to_sort, jsize array_len, jdouble probFail){

	int sorted_pointer = 1;
	int elementValue;
	int hole;
	int num_mem_accesses = 0;	

	for (sorted_pointer = 1; sorted_pointer < array_len; sorted_pointer++) {
		elementValue = array_to_sort[sorted_pointer];
		num_mem_accesses++;		
		hole = sorted_pointer;

		while((hole > 0) && (array_to_sort[hole - 1] > elementValue)) {
			array_to_sort[hole] = array_to_sort[hole - 1];
			num_mem_accesses += 3;			
			hole--;
		}
		array_to_sort[hole] = elementValue;
		num_mem_accesses++;	
	}
	
//	double hazard = probFail * num_mem_accesses;
//	srand(time(NULL));
//	double randomValue = (double) 
}
