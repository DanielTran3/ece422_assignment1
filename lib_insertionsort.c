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

jint* insertion_sort(jint *, jsize);

/* Recursive binary search function */

JNIEXPORT jintArray JNICALL Java_Insertionsort_insertSort
  (JNIEnv *env, jobject object, jintArray array_to_sort) {
	jsize len;
	jint *array_copy;
	jboolean *is_copy = 0;
	jintArray result;

	len = (*env)->GetArrayLength(env, array_to_sort);
	if (len == NULL) {
		printf("Cannot get length\n");
	    exit(0);
	}
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

    array_copy = insertion_sort(array_copy, len);

	(*env)->SetIntArrayRegion(env, result, 0, len, array_copy);
	if (result == NULL) {
		printf("Error in converting data\n");
	    exit(0);
	}

    return result;

}

/*---------------------------------------------------------------*/

jint* insertion_sort(jint *array_to_sort, jsize array_len){

	int sorted_pointer = 1;
	int elementValue;
	int hole;

	for (sorted_pointer = 1; sorted_pointer < array_len; sorted_pointer++) {
		elementValue = array_to_sort[sorted_pointer];
		hole = sorted_pointer;

		while((hole > 0) && (array_to_sort[hole - 1] > elementValue)) {
			array_to_sort[hole] = array_to_sort[hole - 1];
			hole--;
		}
		array_to_sort[hole] = elementValue;
	}
}
