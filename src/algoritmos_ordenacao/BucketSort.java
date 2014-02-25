package algoritmos_ordenacao;

public class BucketSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] array = {21,23,2,34,245,33,66,1};
		int[] array = {5, 2 ,9, 15, 3, 20};
		
		bucketSort(array);
		
		for(int i = 0; i < array.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", array[i]);
		}
		System.out.println("Bucket Sort!");

	}

	private static void bucketSort(int[] array) {
		int maxVal = 20;
		int [] bucket = new int[maxVal+1];

        for (int i=0; i<array.length; i++){
            bucket[array[i]]++;
        }
        int outPos=0;
        for (int j=0; j<bucket.length; j++){
            for (int k=0; k<bucket[j]; k++){
                array[outPos++]=j;
            }
        } 
		
		
	}

}
