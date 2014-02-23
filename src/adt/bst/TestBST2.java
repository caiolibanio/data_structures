package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBST2 {
	//lascou
	//conflitohahakkkresolvido
	protected BSTImpl<Integer, Integer> tree1;
	protected BSTImpl<Integer, Integer> tree2;
	protected BSTImpl<Integer, Integer> tree3;
	protected BSTImpl<Integer, Integer> tree4;
	protected static final int SIZE = 5;

	@Before
	public void setUp() throws Exception {
		// Somente filhos a direita
		tree1 = new BSTImpl<Integer, Integer>();
		for (int i = 0; i < SIZE; i++) {
			tree1.insert(i, i);
		}
		
		// Somente filhos a esquerda
		tree2 = new BSTImpl<Integer, Integer>();
		for (int i = SIZE-1; i >= 0 ; i--) {
			tree2.insert(i, i);
		}
		
		// �rvore completa
		tree3 = new BSTImpl<Integer, Integer>();
		tree3.insert(5, 5);
		tree3.insert(3, 3);
		tree3.insert(1, 1);
		tree3.insert(4, 4);
		tree3.insert(8, 8);
		tree3.insert(6, 6);
		tree3.insert(9, 9);
		
		// �rvore vazia
		tree4 = new BSTImpl<Integer, Integer>();
	}

	@Test
	public void testGetRoot() {
		assertEquals(new Integer(0), tree1.getRoot().key);
		assertEquals(new Integer(4), tree2.getRoot().key);
		assertEquals(new Integer(5), tree3.getRoot().key);
		assertEquals(null, tree4.getRoot().key);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(tree1.isEmpty());
		assertFalse(tree2.isEmpty());
		assertFalse(tree3.isEmpty());
		assertTrue(tree4.isEmpty());
	}

	@Test
	public void testHeight() {
		assertEquals(4, tree1.height());
		assertEquals(4, tree2.height());
		assertEquals(2, tree3.height());
		assertEquals(-1, tree4.height());
	}

	@Test
	public void testSearch() {
		// testa a busca por todos os elementos 
		for (int i = SIZE-1; i >= 0; i--) {
			assertEquals(new Integer(i), tree1.search(i).getKey());
		}
		
		// testa a busca por um elemento que n�o existe
		assertEquals(null, tree2.search(10).getKey());
		
		
		// testa a busca por elementos a esquerda e a direita
		assertEquals(new Integer(3), tree3.search(3).getKey());
		assertEquals(new Integer(8), tree3.search(8).getKey());
		assertEquals(new Integer(6), tree3.search(6).getKey());
		assertEquals(new Integer(4), tree3.search(4).getKey());
		
		// testa a busca em uma �rvore vazia
		assertEquals(null, tree4.search(10).getKey());
	}

	@Test
	public void testInsert() {
		int[] keys = {8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
		// insere elementos de 1 a 15 de forma que a �rvore fique completa
		for (int i : keys) {
			tree4.insert(i, i);
		}
		assertArrayEquals(new Integer[]{8,4,2,1,3,6,5,7,12,10,9,11,14,13,15}, tree4.preOrder());
		assertArrayEquals(new Integer[]{1,3,2,5,7,6,4,9,11,10,13,15,14,12,8}, tree4.postOrder());
	}

	@Test
	public void testMaximum() {
		assertEquals(new Integer(4), tree1.maximum(tree1.getRoot()).key);
		assertEquals(new Integer(4), tree2.maximum(tree2.getRoot()).key);
		assertEquals(new Integer(9), tree3.maximum(tree3.getRoot()).key);
		assertEquals(null, tree4.maximum(tree4.getRoot()));
	}

	@Test
	public void testMinimum() {
		assertEquals(new Integer(0), tree1.minimum(tree1.getRoot()).key);
		assertEquals(new Integer(0), tree2.minimum(tree2.getRoot()).key);
		assertEquals(new Integer(1), tree3.minimum(tree3.getRoot()).key);
		assertEquals(null, tree4.minimum(tree4.getRoot()));
	}

	@Test
	public void testSucessor() {
		BSTNode<Integer,Integer> node = new BSTNode<Integer,Integer>();
		node.key = 4;
		node.value = 4;
		assertEquals(null, tree1.sucessor(node));
		
		// cria uma �rvore espec�fica mostrada no slide de EDA
		int[] keys = {15,6,3,7,2,4,13,9};
		for (int i : keys) {
			tree4.insert(i, i);
			node.key = i;
			node.value = i;
		}
		
		node.key = 6;
		node.value = 6;
		assertEquals(new Integer(7), tree4.sucessor(node).key);
		
		node.key = 4;
		node.value = 4;
		assertEquals(new Integer(6), tree4.sucessor(node).key);
		
		node.key = 2;
		node.value = 2;
		assertEquals(new Integer(3), tree4.sucessor(node).key);
		
		node.key = 13;
		node.value = 13;
		assertEquals(new Integer(15), tree4.sucessor(node).key);
		
		node.key = 9;
		node.value = 9;
		assertEquals(new Integer(13), tree4.sucessor(node).key);
	}

	@Test
	public void testPredecessor() {
		BSTNode<Integer,Integer> node = new BSTNode<Integer,Integer>();
		node.key = 0;
		node.value = 0;
		assertEquals(null, tree1.predecessor(node));
		
		// cria uma �rvore espec�fica mostrada no slide de EDA
		int[] keys = {15,23,22,16,18};
		for (int i : keys) {
			tree4.insert(i, i);
			node.key = i;
			node.value = i;
		}
		
		node.key = 23;
		node.value = 23;		
		assertEquals(new Integer(22), tree4.predecessor(node).key);
		
		node.key = 22;
		node.value = 22;		
		assertEquals(new Integer(18), tree4.predecessor(node).key);
		
		node.key = 16;
		node.value = 16;		
		assertEquals(new Integer(15), tree4.predecessor(node).key);
		
		node.key = 18;
		node.value = 18;		
		assertEquals(new Integer(16), tree4.predecessor(node).key);

	}

	@Test
	public void testRemove() {
		// Remove todos os elementos da �rvore com filhos a direita
		tree1.remove(0);
		assertArrayEquals(new Integer[]{1,2,3,4},tree1.preOrder());
		assertArrayEquals(new Integer[]{4,3,2,1},tree1.postOrder());
		tree1.remove(1);
		tree1.remove(2);
		tree1.remove(3);
		assertArrayEquals(new Integer[]{4},tree1.preOrder());
		assertArrayEquals(new Integer[]{4},tree1.postOrder());
		tree1.remove(4);
		assertArrayEquals(new Integer[]{},tree1.preOrder());
		assertArrayEquals(new Integer[]{},tree1.postOrder());
		tree1.remove(4);
		
		// Remove todos os elementos da �rvore com filhos a esquerda
		tree2.remove(4);
		assertArrayEquals(new Integer[]{3,2,1,0},tree2.preOrder());
		assertArrayEquals(new Integer[]{0,1,2,3},tree2.postOrder());
		tree2.remove(3);
		tree2.remove(2);
		tree2.remove(1);
		assertArrayEquals(new Integer[]{0},tree2.preOrder());
		assertArrayEquals(new Integer[]{0},tree2.postOrder());
		tree2.remove(0);
		assertArrayEquals(new Integer[]{},tree2.preOrder());
		assertArrayEquals(new Integer[]{},tree2.postOrder());
		tree2.remove(0);
		
		// Remove todos os elementos da �rvore completa
		tree3.remove(4);
		assertArrayEquals(new Integer[]{5,3,1,8,6,9},tree3.preOrder());
		assertArrayEquals(new Integer[]{1,3,6,9,8,5},tree3.postOrder());
		tree3.remove(1);
		assertArrayEquals(new Integer[]{5,3,8,6,9},tree3.preOrder());
		assertArrayEquals(new Integer[]{3,6,9,8,5},tree3.postOrder());
		tree3.remove(5);
		assertArrayEquals(new Integer[]{6,3,8,9},tree3.preOrder());
		assertArrayEquals(new Integer[]{3,9,8,6},tree3.postOrder());
		tree3.remove(8);
		assertArrayEquals(new Integer[]{6,3,9},tree3.preOrder());
		assertArrayEquals(new Integer[]{3,9,6},tree3.postOrder());
		tree3.remove(9);
		assertArrayEquals(new Integer[]{6,3},tree3.preOrder());
		assertArrayEquals(new Integer[]{3,6},tree3.postOrder());
		tree3.remove(6);
		assertArrayEquals(new Integer[]{3},tree3.preOrder());
		assertArrayEquals(new Integer[]{3},tree3.postOrder());
		tree3.remove(3);
		assertArrayEquals(new Integer[]{},tree3.preOrder());
		assertArrayEquals(new Integer[]{},tree3.postOrder());
	}

	@Test
	public void testPreOrder() {
		assertArrayEquals(new Integer[]{0,1,2,3,4}, tree1.preOrder());
		assertArrayEquals(new Integer[]{4,3,2,1,0}, tree2.preOrder());
		assertArrayEquals(new Integer[]{5,3,1,4,8,6,9}, tree3.preOrder());
		assertArrayEquals(new Integer[]{}, tree4.preOrder());
	}

	@Test
	public void testOrder() {
		assertArrayEquals(new Integer[]{0,1,2,3,4}, tree1.order());
		assertArrayEquals(new Integer[]{0,1,2,3,4}, tree2.order());
		assertArrayEquals(new Integer[]{1,3,4,5,6,8,9}, tree3.order());
		assertArrayEquals(new Integer[]{}, tree4.order());
	}

	@Test
	public void testPostOrder() {
		assertArrayEquals(new Integer[]{4,3,2,1,0}, tree1.postOrder());
		assertArrayEquals(new Integer[]{0,1,2,3,4}, tree2.postOrder());
		assertArrayEquals(new Integer[]{1,4,3,6,9,8,5}, tree3.postOrder());
		assertArrayEquals(new Integer[]{}, tree4.postOrder());
	}

	@Test
	public void testSize() {
		assertEquals(5, tree1.size());
		tree1.insert(5, 5);
		assertEquals(SIZE+1, tree1.size());
		
		assertEquals(5, tree2.size());
		tree2.insert(5, 5);
		assertEquals(SIZE+1, tree2.size());
		
		assertEquals(7, tree3.size());
		tree3.remove(5);
		assertEquals(6, tree3.size());
		
		assertEquals(0, tree4.size());
		tree4.insert(0, 0);
		assertEquals(1, tree4.size());
	}
	

}
