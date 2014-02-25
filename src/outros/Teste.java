package outros;

import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Teste {


	public static void main(String[] args) throws Exception {
		System.out.println(checkIntegrity());

	}
	
	private static boolean checkIntegrity(){
		int[] array = {1, 2, 4, 7, 8, 9, 10};
		for(int i = 1; i < array.length; i++){
			if(array[i-1] > array[i]){
				return false;
			}
		}
		return true;
		
		
		
	}
}