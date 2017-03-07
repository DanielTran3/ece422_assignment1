#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include "Insertionsort.h"

jint insertion_sort(jint *, jsize, jfloat);

JNIEXPORT jint JNICALL Java_Insertionsort_insertSort
  (JNIEnv *env, jobject object, jintArray array_to_sort, jfloat failureProb) {
	jsize len;
	jint *array_copy;
	jboolean *is_copy = 0;
	jint sortPass;

    // Get the length of the array
	len = (*env)->GetArrayLength(env, array_to_sort);

    // Get a pointer to the array
	array_copy = (jint *) (*env)->GetIntArrayElements(env, array_to_sort, is_copy);
	if (array_copy == NULL){
	    printf("Cannot obtain array from JVM\n");
	    exit(0);
	}

	sortPass = insertion_sort(array_copy, len, failureProb);

    // Modify the pass-by-reference array
	(*env)->SetIntArrayRegion(env, array_to_sort, 0, len, array_copy);

	return sortPass;

}

jint insertion_sort(jint *array_to_sort, jsize array_len, jfloat probFail){

	int sorted_pointer = 1;
	int elementValue;
	int hole;
	int num_mem_accesses = 0;

    // Check for an empty list
	if (array_len > 0) {

        // Start sorted_pointer at an index 1 so the first element is already
        // in the sorted array
		for (sorted_pointer = 1; sorted_pointer < array_len; sorted_pointer++) {
			elementValue = array_to_sort[sorted_pointer];
			num_mem_accesses++;
			hole = sorted_pointer;

            // If there are still values in the array that can be checked
            // and an element is larger than the current element
			while((hole > 0) && (array_to_sort[hole - 1] > elementValue)) {
                // Shift the element value over (fill the hole)
                array_to_sort[hole] = array_to_sort[hole - 1];
				num_mem_accesses += 3;
				hole--;
			}

            // Swap the element into the "hole"
			array_to_sort[hole] = elementValue;
			num_mem_accesses++;
		}
	}

    // Generate failure probability
	float hazard = (float) probFail * num_mem_accesses;
	srand(time(NULL));
	float randomValue = ((float) rand() / (float) RAND_MAX);

    // Return value for a failure (0) or a pass (1)
	if ((randomValue >= 0.5) && (randomValue <= (0.5 + hazard))) {
		return 0;
	}
	return 1;
}
