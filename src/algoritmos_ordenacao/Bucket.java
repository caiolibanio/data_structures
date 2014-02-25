package algoritmos_ordenacao;

public class Bucket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {21, 23, 2, 34, 245, 33, 66, 1};
		bucketSort(array);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}
		System.out.println("Bucket Sort!");

	}

	private static void bucketSort(int[] array) {
		int[] balde010  = new int[10];
		int[] balde1120 = new int[10];
		int[] balde2130 = new int[10];
		int cont1 = 0, cont2 = 0, cont3 = 0;
		
		
		for(int i = 0; i < array.length; i++){
			if(array[i] <= 10){
				balde010[i] = array[i];
				++cont1;
			}
			else if( array[i] > 10 && array[i] <= 20){
				balde1120[i] = array[i];
				++cont2;
			}
			else{
				balde2130[i] = array[i];
				++cont3;
			}
		}
		balde010 = sort_buckets(balde010, cont1, array);
		balde1120 = sort_buckets(balde1120, cont2, array);
		balde2130 = sort_buckets(balde2130, cont3, array);
		
		int cont = 0;
		for(int j = 0; j < balde010.length; j++) array[cont++] = balde010[j];
		for(int j = 0; j < balde1120.length; j++) array[cont++] = balde1120[j];
		for(int j = 0; j < balde2130.length; j++) array[cont++] = balde2130[j];
			
	}

	private static int[] sort_buckets(int[] balde, int cont, int[] array) {
		int[] arrayTemp = new int[cont];
		int cont1 = 0;
		for(int i = 0; i < balde.length; i++){
			if(balde[i] != 0){
				arrayTemp[cont1++] = balde[i];
			}
		}
		insertionSort(arrayTemp);
		
		return arrayTemp;
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
