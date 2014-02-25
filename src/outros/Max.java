package outros;

public class Max {
	public static void main(String[] args){
		
	int n = 3;
	int[] array = {5,3,1};
	System.out.println(maximo_r(n, array));
	
	}
	
	
	private static int maximo_r(int n, int[] array){
		   if (n == 1)
			      return array[0];
			   else {
			      int x = 0;
			      x = maximo_r (n-1, array);  /* máximo de v[0..n-2] */
			      if (x > array[n-1])
			         return x;
			      else
			         return array[n-1]; 
			   }
		
	}
}
