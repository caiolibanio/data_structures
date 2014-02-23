package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAVLTree {
	
	private AVLTreeImpl<Integer, Integer> arvore = new AVLTreeImpl<Integer, Integer>();

	@Test public void test() {
		//int[] keys = {0, 1, 2, 3, 4, 5, 6, 7};
		// insere elementos de 1 a 15 de forma que a árvore fique completa
		long antes = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			arvore.insert(i, i);
			//assertEquals(i, arvore.size());
		}
		
		arvore.search(0);
		long depois = System.nanoTime();
		System.out.println((depois - antes) * (0.000000001));
		//assertEquals(2, arvore.height());
		//assertEquals(7, arvore.size());
		//assertEquals(2, arvore.height());
		//assertEquals(new Integer(9999), arvore.search(9999).getKey());
		System.out.println("Tamanho: " + arvore.size());
		System.out.println("Altura: " + arvore.height());
		
		
	}

}
