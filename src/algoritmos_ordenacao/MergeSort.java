package algoritmos_ordenacao;

import java.util.List;
import java.util.Vector;



public class MergeSort {
	public static void main(String[] args){
		int[] array = {21,23,2,34,245,33,66,1};
		
		
		long antes = System.nanoTime();
		array = mergeSort(array);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}
		System.out.println("Merge Sort!");
	}

	private static int[] mergeSort(int[] array) {
		
		if(array.length <= 1) return array;
		
		int middle = array.length / 2;
		int[] left = new int[middle];
		System.arraycopy(array, 0, left, 0, left.length);
		int[] right = new int[array.length - left.length];
		System.arraycopy(array, left.length, right, 0, right.length);
		int[] result = new int[array.length];
		
		left = mergeSort(left);
		right = mergeSort(right);
		result = merge(left, right);
		return result;
	}


	private static int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = 0;
		
		while (leftIndex < left.length && rightIndex < right.length) {
			
			    if (left[leftIndex] < right[rightIndex]) {
			    	result[resultIndex] = left[leftIndex];
			    	leftIndex++;
			    } else {
			    	result[resultIndex] = right[rightIndex];
			    	rightIndex++;
			    }
			    resultIndex++;
			}
		int[] resto;
		int restoIndex;
		if (leftIndex == left.length) {
		    // The left array has been use up...
		    resto = right;
		    restoIndex = rightIndex;
		}
		else {
		    // The right array has been used up...
		    resto = left;
		    restoIndex = leftIndex;
		}

		// Copy the rest of whichever array (left or right) was
		// not used up.
		for (int i = restoIndex; i < resto.length; i++) {
		    result[resultIndex] = resto[i];
		    resultIndex++;
		}
		return result;
			
			
	}

}
