package algoritmos_ordenacao;

public class BubbleBack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {21,23,2,34,245,33,66};
		bubble_sort(array);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}

	}

	private static int[] bubble_sort(int[] array) {
		int n = array.length;
		for(int i = 0; i < array.length; i++){
			for(int j = n - 1 ; j > i; j--){
				if(array[j] > array[j-1]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					
				}
			}
		}return array;
		
	}

}
