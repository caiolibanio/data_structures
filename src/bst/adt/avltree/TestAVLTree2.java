package bst.adt.avltree;


import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestAVLTree2 {

	protected AVLTree<Integer, Integer> tree1;
	protected AVLTree<Integer, Integer> tree2;
	protected AVLTree<Integer, Integer> tree3;
	protected AVLTree<Integer, Integer> tree4;
	protected AVLTree<Integer, Integer> tree5;
	protected AVLTree<Integer, Integer> tree6;
	protected Integer[] data = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	protected static final int SIZE = 10;
	
	@Before
	public void setUp() throws Exception {
		tree1 = new AVLTreeImpl<Integer, Integer>();
		tree1.insert(1, 1);
		tree1.insert(4, 4);
		tree1.insert(7, 7);
		tree1.insert(10, 10);
		tree1.insert(13, 13);
		tree1.insert(2, 2);
		tree1.insert(5, 5);
		tree1.insert(8, 8);
		tree1.insert(11, 11);
		tree1.insert(14, 14);
		tree1.insert(3, 3);
		tree1.insert(15, 15);
		tree1.insert(6, 6);
		tree1.insert(12, 12);
		tree1.insert(9, 9);


		tree2 = new AVLTreeImpl<Integer, Integer>();
	}
	
	@Test
	public void testInsert() {
		Integer[] preOrder1 = {10,4,2,1,3,7,5,6,8,9,13,11,12,14,15};
		assertEquals(Arrays.toString(preOrder1), Arrays.toString(tree1.preOrder()));

		assertEquals("[]", Arrays.toString(tree2.preOrder()));
		
		Integer[] preOrder2 = {10,4,2,1,3,7,5,6,8,9,13,11,12,15,14,20,18,22};
		tree1.insert(22, 22);
		tree1.insert(18, 18);
		tree1.insert(20, 20);
		assertEquals(Arrays.toString(preOrder2), Arrays.toString(tree1.preOrder()));
	}
	
	@Test
	public void testRemove() {
		//removendo uma folha sem causar rebalanceamento
		tree1.remove(6);
		Integer[] preOrder1 = {10,4,2,1,3,7,5,8,9,13,11,12,14,15};
		assertEquals(Arrays.toString(preOrder1), Arrays.toString(tree1.preOrder()));
		
		//removendo no com um filho apenas sem causar rebaleanceamento
		tree1.remove(11);
		Integer[] preOrder2 = {10,4,2,1,3,7,5,8,9,13,12,14,15};
		assertEquals(Arrays.toString(preOrder2), Arrays.toString(tree1.preOrder()));
		
		
		//removendo um no com dois filhos que causa um rebalanceamento
		tree1.remove(13);
		Integer[] preOrder3 = {7,4,2,1,3,5,10,8,9,14,12,15};
		assertEquals(Arrays.toString(preOrder3), Arrays.toString(tree1.preOrder()));
		
		tree2.remove(10);
		assertEquals("[]", Arrays.toString(tree2.preOrder()));

	}
	
	@Test
	public void testHeight(){
		assertEquals(4,tree1.height());
		assertEquals(-1,tree2.height());
		tree1.insert(22, 22);
		assertEquals(4,tree1.height());
		tree1.insert(18, 18);
		assertEquals(4,tree1.height());
		tree1.insert(20, 20);
		assertEquals(4,tree1.height());
		tree1.insert(17, 17);
		assertEquals(4,tree1.height());
		tree1.insert(16, 16);
		assertEquals(4,tree1.height());
		tree1.insert(19, 19);
		assertEquals(4,tree1.height());
		tree1.insert(21, 21);
		assertEquals(5,tree1.height());
	}
}
