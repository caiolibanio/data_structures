package algoritmos_ordenacao;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {21,23,2,34,245,33,66,1};
		long antes = System.nanoTime();
		bubble_sort(array);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}System.out.println("Bubble Sort!");
		
	}

	private static int[] bubble_sort(int[] array) {
		int n = array.length;
		for(int i = 0; i < array.length; i++){
			//boolean flag = false;
			for(int j = 1 ; j < array.length; j++){
				if(array[j] < array[j-1]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					//flag = true;
					
				}
				
				}
			//if(flag == false){  // um aprimoramento
				//break;
			//}
		}return array;
		
	}

}
