package algoritmos_ordenacao;

public class CountingSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arrayA = {21,23,2,34,245,33,66,1};
		//int[] arrayA = {3, 1, 4, 1};
		int[] arrayB = new int[arrayA.length];
		
		long antes = System.nanoTime();
		countingSort(arrayA);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		
		for(int i = 0; i < arrayA.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", arrayA[i]);
		}System.out.println("Counting Sort!");

	}

	public static void countingSort(int[] arrayA){
		if(arrayA.length == 1) return;
		int maior = maior(arrayA);
		int menor = menor(arrayA);
		int passo = 0;
		int[] arrayC= new int[maior - menor + 1];
		
		for(int i = 0; i < arrayA.length; i++){ //Contagem de elementos
			arrayC[arrayA[i] - menor]++;
		}
		for(int j = menor; j <= maior; j++){
			for(int k = arrayC[j - menor]; k > 0; k--){
				arrayA[passo]= j;
				passo++;
			}
			//while(arrayC[j - menor] > 0){
				//arrayA[passo]= j;
				//passo++;
				//arrayC[j - menor]--;
			//}
		}
	}

	private static int maior(int[] arrayA) {
		int maior = arrayA[0];
		for (int i = 0; i < arrayA.length; i++) {
			if (arrayA[i] > maior) {
				maior = arrayA[i];
			}
		}
		return maior;
	}

	private static int menor(int[] arrayA) {
		int menor = arrayA[0];
		for(int i = 0; i < arrayA.length; i++){
			if(arrayA[i] < menor ){
				menor = arrayA[i];
			}
		}return menor;
		
	}
	private static int possuiZero(int[] array){
		for(int i = 0; i < array.length; i++){
			if(array[i] == 0){
				return i;
			}
		}
		return -1;
	}
}

