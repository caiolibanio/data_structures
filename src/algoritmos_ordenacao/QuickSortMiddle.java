package algoritmos_ordenacao;

public class QuickSortMiddle {


	public static void main(String[] args) {
		int[] array = {21,23,2,34,245,33,66,1};
		
		long antes = System.nanoTime();
		quickSort(array, 0, array.length - 1);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}
		System.out.println("Quick Sort Middle!");

	}

	private static void quickSort(int[] array, int left, int right) {
		   int mid,tmp,i,j;

		   i = left;
		   j = right;
		   mid = array[(left + right)/2];
		   do {
		        while(array[i] < mid)
		           i++;
		       while(mid < array[j])
		           j--;
		       if (i <= j) {
		           tmp = array[i];
		           array[i] = array[j];
		           array[j] = tmp;
		           i++;
		           j--;
		       }
		   } while (i <= j);
		   if (left < j) quickSort(array,left,j);
		   if (i < right) quickSort(array,i,right);
		
	}

}
