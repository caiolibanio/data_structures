package algoritmos_ordenacao;

public class InsertionSort {

	public static void main(String[] args) {
		int[] array = {21,23,2,34,245,33,66,1};
		long antes = System.nanoTime();
		insertionSort(array);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}
		System.out.println("Insertion Sort!");

	}

	private static void insertionSort(int[] array){
		int chave = 0;
		int j = 0;
		for(int i = 1; i < array.length; i++ ){
			chave = array[i];
			j = i - 1;
			while( j >= 0 && array[j] > chave ){
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = chave;
			
		}
		
	}

}
