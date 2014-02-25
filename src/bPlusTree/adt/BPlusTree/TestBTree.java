package bPlusTree.adt.BPlusTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestBTree<K extends Comparable<? super K>,V> {
	
	BTree t1;
	BTree t2;
	BTree t3;
	@Before
	public void criaTrees(){
		t1 = new BTreeImpl<K,V>(3);
		t2 = new BTreeImpl<K,V>(3);
		t3 = new BTreeImpl<K,V>(3);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testInsert(){
		
		t1.insert(15, 15);
		t1.insert(36, 36);
		t1.insert(1, 1);
		t1.insert(8, 8);
		t1.insert(22, 22);
		t1.insert(29, 29);
		t1.insert(43, 43);
		t1.insert(50, 50);
		t1.insert(47, 47);
		t1.insert(51, 51);
		t1.insert(30, 30);
		t1.insert(31, 31);
		
		Assert.assertEquals("[36]",t1.getRoot().getElements().toString());
		Assert.assertEquals("[[22], [47]]",t1.getRoot().getChildren().toString());
		Assert.assertEquals("[[15], [29, 30]]",t1.getRoot().getChildrenAt(0).getChildren().toString());
		Assert.assertEquals("[[22], [29], [30, 31]]",t1.getRoot().getChildrenAt(0).getChildrenAt(1).getChildren().toString());
		Assert.assertEquals("[[43], [50]]",t1.getRoot().getChildrenAt(1).getChildren().toString());
		Assert.assertEquals("[[36], [43]]",t1.getRoot().getChildrenAt(1).getChildrenAt(0).getChildren().toString());
		Assert.assertEquals("[[47], [50, 51]]",t1.getRoot().getChildrenAt(1).getChildrenAt(1).getChildren().toString());
		
		Assert.assertEquals(3, t1.height());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testSearch(){
		t1.insert(15, 15);
		t1.insert(36, 36);
		t1.insert(1, 1);
		t1.insert(8, 8);
		t1.insert(22, 22);
		t1.insert(29, 29);
		t1.insert(43, 43);
		t1.insert(50, 50);
		t1.insert(47, 47);
		t1.insert(51, 51);
		t1.insert(30, 30);
		t1.insert(31, 31);
		
		BNode<K, V>[] bigNode = t1.depthLeftOrder();
		
		
		assertEquals(new Integer(15), t1.searchData(15).node.getElementAt(t1.searchData(15).position).value);
		assertEquals(new Integer(36), t1.searchData(36).node.getElementAt(t1.searchData(36).position).value);
		assertEquals(new Integer(1), t1.searchData(1).node.getElementAt(t1.searchData(1).position).value);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		assertEquals(new Integer(22), t1.searchData(22).node.getElementAt(t1.searchData(22).position).value);
		assertEquals(new Integer(29), t1.searchData(29).node.getElementAt(t1.searchData(29).position).value);
		assertEquals(new Integer(43), t1.searchData(43).node.getElementAt(t1.searchData(43).position).value);
		assertEquals(new Integer(50), t1.searchData(50).node.getElementAt(t1.searchData(50).position).value);
		assertEquals(new Integer(47), t1.searchData(47).node.getElementAt(t1.searchData(47).position).value);
		assertEquals(new Integer(51), t1.searchData(51).node.getElementAt(t1.searchData(51).position).value);
		assertEquals(new Integer(30), t1.searchData(30).node.getElementAt(t1.searchData(30).position).value);
		assertEquals(new Integer(31), t1.searchData(31).node.getElementAt(t1.searchData(31).position).value);
		
		t2 = new BTreeImpl<K,V>(3);
		
		for(int i = 0; i < 5000; i++){
			t2.insert(i, i);
		}
		
		for(int i = 0; i < 5000; i++){
			BNodePosition<K, V> nodePos = t2.searchData(i);
			int pos = nodePos.position;
			assertEquals(new Integer(i), nodePos.node.getElementAt(pos).value);
		}
		
		
		
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testDepth(){
		t1.insert(15, 15);
		t1.insert(36, 36);
		t1.insert(1, 1);
		t1.insert(8, 8);
		t1.insert(22, 22);
		t1.insert(29, 29);
		t1.insert(43, 43);
		t1.insert(50, 50);
		t1.insert(47, 47);
		t1.insert(51, 51);
		t1.insert(30, 30);
		t1.insert(31, 31);
		
		assertEquals(16, t1.depthLeftOrder().length);
		
		
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testSize(){
		t1.insert(15, 15);
		t1.insert(36, 36);
		t1.insert(1, 1);
		t1.insert(8, 8);
		t1.insert(22, 22);
		t1.insert(29, 29);
		t1.insert(43, 43);
		t1.insert(50, 50);
		t1.insert(47, 47);
		t1.insert(51, 51);
		t1.insert(30, 30);
		t1.insert(31, 31);
		
		assertEquals(1, t1.getRoot().size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRemove(){
		
		t1 = new BTreeImpl<K,V>(3);
		BNode<K, V>[] array = null;
		
		
		t1.insert(15, 15);
		t1.insert(36, 36);
		
		t1.insert(1, 1);
		t1.insert(8, 8);
		t1.insert(22, 22);
		t1.insert(29, 29);
		t1.insert(43, 43);
		t1.insert(50, 50);
		t1.insert(47, 47);
		t1.insert(51, 51);
		t1.insert(30, 30);
		t1.insert(31, 31);
		
		
		
		
		
		array = t1.depthLeftOrder();
		
		for(int i = 0; i < array.length; i++){
			if(array[i].isLeaf()){
				
				BNode<K, V> aux = array[i];
				for(int j = 0; j < aux.size(); j++){
					assertTrue(aux.getElementAt(j).value != null);
				}
				
				
			}else{
				BNode<K, V> aux = array[i];
				
				for(int j = 0; j < aux.size(); j++){
					assertTrue(aux.getElementAt(j).value == null);
				}
				
			}
			
		}
		
		
		
		t1.remove(50);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		t1.remove(30);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		t1.remove(43);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		t1.remove(29);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		t1.remove(15);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		t1.remove(1);
		
		
		assertEquals(new Integer(36), t1.searchData(36).node.getElementAt(t1.searchData(36).position).value);
		assertEquals(new Integer(8), t1.searchData(8).node.getElementAt(t1.searchData(8).position).value);
		assertEquals(new Integer(22), t1.searchData(22).node.getElementAt(t1.searchData(22).position).value);
		assertEquals(new Integer(47), t1.searchData(47).node.getElementAt(t1.searchData(47).position).value);
		assertEquals(new Integer(51), t1.searchData(51).node.getElementAt(t1.searchData(51).position).value);
		assertEquals(new Integer(31), t1.searchData(31).node.getElementAt(t1.searchData(31).position).value);
		
		
		array = t1.depthLeftOrder();
		
		int cont = 0;
		
		for(int i = 0; i < array.length; i++){
			if(array[i].isLeaf()){
				++cont;
				BNode<K, V> aux = array[i];
				for(int j = 0; j < aux.size(); j++){
					assertTrue(aux.getElementAt(j).value != null);
				}
				
				
			}else{
				BNode<K, V> aux = array[i];
				++cont;
				for(int j = 0; j < aux.size(); j++){
					assertTrue(aux.getElementAt(j).value == null);
				}
				
			}
			
		}
		
		assertEquals(cont, array.length);
		
		
		assertEquals(null, t1.searchData(50));
		assertEquals(null, t1.searchData(30));
		assertEquals(null, t1.searchData(43));
		assertEquals(null, t1.searchData(29));
		assertEquals(null, t1.searchData(15));
		assertEquals(null, t1.searchData(1));
		
		
		
		t3 = new BTreeImpl<K,V>(3);
		
		for(int i = 0; i < 10; i++){
			t3.insert(i, i);
		}
		
		for(int i = 0; i < 5; i++){
			t3.removeData(i);
			assertEquals(null, t3.searchData(i));
			//assertEquals(new Integer(1), t3.searchData(1).node.getElementAt(t3.searchData(1).position).value);
		}
		
		t3.insert(0, 0);
		t3.insert(1, 1);
		
		array = t3.depthLeftOrder();
		
		for(int i = 0; i < array.length; i++){
			if(array[i].isLeaf()){
				
				BNode<K, V> aux = array[i];
				for(int j = 0; j < aux.size(); j++){
					assertTrue(aux.getElementAt(j).value != null);
				}
				
				
			}else{
				BNode<K, V> aux = array[i];
				
				for(int j = 0; j < aux.size(); j++){
					assertTrue(aux.getElementAt(j).value == null);
				}
				
			}
			
		}
		
		
		

		
	}
	@Test
	public void otherTests(){
		t1 = new BTreeImpl<K,V>(5);
		
		for(int i = 0 ; i < 10000; i++){
			t1.insert(i, i);
		}
		
		System.out.println(t1.height());
		
		for(int i = 0 ; i < 9000; i++){
			t1.removeData(i);
		}
		System.out.println(t1.height());
		
		BNode<K, V>[] array = t1.depthLeftOrder();
		
		for(int i = 0; i < array.length; i++){
			if(array[i].isEmpty()){
				System.out.println(array[i].toString());
			}
		}
		
		
	}
	
	
	
}
