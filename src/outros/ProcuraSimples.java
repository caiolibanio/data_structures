package outros;

public class ProcuraSimples {

	private static boolean flag = false;

	public static void main(String[] args) {
		int[] array = {5,2,4,1,20, 1568, 3};
		int alvo = 6;
		int size = array.length;
		procura(array, alvo, size);
		System.out.println(flag);
	}

	private static boolean procura(int[] array, int alvo, int size) {
		
		if(alvo != array[size-1]){
			if(size == 1){
				return flag;
			}
			size -= 1;
			flag = procura(array, alvo, size);
		}else{
			flag = true;
			return flag;
		}

		return flag;
	}

}
