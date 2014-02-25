package algoritmos_ordenacao;

public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {21,23,2,34,245,33,66,1};
		long antes = System.nanoTime();
		selectionSort(array);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}
		System.out.println("Selection Sort!");

	}

	private static void selectionSort(int []array){
		int min = 0;
		for(int i = 0; i < array.length; i++){
			min = i;
			for(int j = (i + 1); j < array.length; j++){
				if(array[j] < array[min]){
					min = j;
				}
			}
			swap(array, min, i);
		}
	}
	private static void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		
	}

}
