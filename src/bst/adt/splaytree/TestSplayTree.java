package bst.adt.splaytree;

import static org.junit.Assert.*;



import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import bst.adt.bst.BSTNode;

public class TestSplayTree {
	
	private SplayTreeImpl<Integer, Integer> arvore;
	private SplayTreeImpl<Integer, Integer> arvore2;
	
	@Before
	public void setUp(){
		arvore = new SplayTreeImpl<Integer, Integer>();
		arvore2 = new SplayTreeImpl<Integer, Integer>();
	}
	
	@Test
	public void test() {
		long antes = System.nanoTime();
		for(int i = 0; i < 5000; i++){
			arvore.insert(i, i);
			arvore2.insert(i, i);
			
		}
		
		
		for(int i = 0; i < 5000; i++ ){
			assertEquals(new Integer(i), arvore.search(i).getKey());
			assertEquals(new Integer(i), arvore.getRoot().getKey());
		}
		
		//assertTrue(arvore.equals(arvore2));
		long depois = System.nanoTime();
		System.out.println((depois - antes) * (0.000000001));
		System.out.println("Tamanho: " + arvore.size());
		
		
	}
	@Test
	public void test2(){
		for(int i = 0; i < 7; i++){
			arvore.insert(i, i);
			
		}
		
		arvore.search(0);
		
	}

}
