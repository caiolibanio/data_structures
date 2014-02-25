package algoritmos_ordenacao;

public class Merge2 {

	public static void main(String[] args) {
		int[] vetor = {21,23,2,34,245,33,66,1};
		long antes = System.nanoTime();
		vetor = merge(vetor, 0, vetor.length - 1);
		long depois = System.nanoTime();
		System.out.println(depois - antes);
		for(int i = 0; i < vetor.length; i++){
			System.out.format("Indice " + i + " : %s \r\n", vetor[i]);
		}System.out.println("Merge Sort!");
		

	}
	private static int[] merge(int[] vetor, int inicio, int fim) {
		 
        if ((inicio < fim)) {
                int meio = (inicio + fim) / 2;
                merge(vetor, inicio, meio);
                merge(vetor,meio + 1, fim);
                mesclar(vetor, inicio, meio, fim);
                return vetor;
        }
        return null;
}
 
/* 
 * Ordena dois trechos ordenados e adjacente de vetores e ordena-os
 * conjuntamente
 */
private static void mesclar(int[] vetor, int inicio, int meio, int fim) {
 
        int tamanho = fim - inicio + 1;
 
        /*
         * Inicialização de um vetor temporario para auxiliar na ordenação O
         * vetor temporário é uma cópia do trecho que será ordenado
         */
 
        int[] temp = new int[tamanho];

        
        for (int posicao = 0; posicao < tamanho; posicao++) {
        		 temp[posicao] = vetor[inicio + posicao];
        		  }
 
        /*
         * Laço para ordenação do vetor, utilizando o vetor temporário, usando
         * índices i e j para cada trecho de vetor da mesclagem
         */
 
        int i = 0;
        int j = meio - inicio + 1;
 
        //A depender das condições, recebe um elemento de um trecho ou outro
        for (int posicao = 0; posicao < tamanho; posicao++) {
                if (j <= tamanho - 1) {
                        if (i <= meio - inicio) {
                                if (temp[i] < temp[j]) {
                                        vetor[inicio + posicao] = temp[i++];
                                } else {
                                        vetor[inicio + posicao] = temp[j++];
                                }
                        } else {
                                vetor[inicio + posicao] = temp[j++];
                        }
                } else {
                        vetor[inicio + posicao] = temp[i++];
                }
        }
}
}