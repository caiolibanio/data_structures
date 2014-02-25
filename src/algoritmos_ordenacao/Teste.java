package algoritmos_ordenacao;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args ) {
		int[] array = {21,23,2,34,245,33,66,1};

		
		quickSort(array, 0, array.length-1);
		
		
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
	}
	private static void quickSort(int[] array, int l, int r){
		if(l >= r) return;
		
		int m = partition(array, l, r);
		quickSort(array, l, m-1);
		quickSort(array, m+1, r);
		
	}
	private static int partition(int[] array, int l, int r) {
		int i = l;
		int j = r;
		int p = array[array.length / 2];
		int pIndex = array.length / 2;
		
		while(i <= j ){
			while(array[i] < p){
				i++;
			}
			
			while(array[j] > p){
				j--;
			}
			if(i <= j){
				swap(array, i, j);
				++i;
				--j;
			}
				
			
			
			
			
		}
		return j;
		
	}
	
	private static void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		
	}



}
