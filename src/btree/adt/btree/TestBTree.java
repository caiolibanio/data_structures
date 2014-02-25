package btree.adt.btree;
import static org.junit.Assert.assertEquals;

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
		t1.insert(10, 10);
		t1.insert(20, 20);
		t1.insert(30, 30);
		
		t1.insert(40, 40);
		t1.insert(50, 50);
		
		//correto
		Assert.assertEquals("[20, 40]",t1.getRoot().getElements().toString());
		Assert.assertEquals("[10]",t1.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[30]",t1.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[50]",t1.getRoot().getChildren().get(2).toString());
		
		t1.insert(22, 22);
		Assert.assertEquals("[20, 40]",t1.getRoot().getElements().toString());
		Assert.assertEquals("[10]",t1.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[22, 30]",t1.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[50]",t1.getRoot().getChildren().get(2).toString());
		
		t1.insert(11, 11);
		Assert.	assertEquals("[20, 40]",t1.getRoot().getElements().toString());
		Assert.assertEquals("[10, 11]",t1.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[22, 30]",t1.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[50]",t1.getRoot().getChildren().get(2).toString());
		
		t1.insert(44, 44);
		Assert.assertEquals("[20, 40]",t1.getRoot().getElements().toString());
		Assert.assertEquals("[10, 11]",t1.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[22, 30]",t1.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[44, 50]",t1.getRoot().getChildren().get(2).toString());
		//t1 ta beleza
		
		
		//clone da t1 soh falta inserir o no 55,55
		t2.insert(10, 10);
		t2.insert(20, 20);
		t2.insert(30, 30);
		t2.insert(40, 40);
		t2.insert(50, 50);
		t2.insert(22, 22);
		t2.insert(11, 11);
		t2.insert(44, 44);
		
		t2.insert(55, 55);
		Assert.assertEquals("[40]",t2.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t2.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[50]",t2.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[10, 11]",((BNode) t2.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[22, 30]",((BNode) t2.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[44]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		Assert.assertEquals("[55]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(1).toString());
		t2.insert(45, 45);
	
		Assert.assertEquals("[40]",t2.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t2.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[50]",t2.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[10, 11]",((BNode) t2.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[22, 30]",((BNode) t2.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[44, 45]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		Assert.assertEquals("[55]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(1).toString());
		
		t2.insert(46, 46);
		Assert.assertEquals("[40]",t2.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t2.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[45, 50]",t2.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[10, 11]",((BNode) t2.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[22, 30]",((BNode) t2.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[44]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		Assert.assertEquals("[46]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(1).toString());
		Assert.assertEquals("[55]",((BNode) t2.getRoot().getChildren().get(1)).getChildren().get(2).toString());
		
		t3.insert(10, 10);
		t3.insert(20, 20);
		t3.insert(30, 30);
		t3.insert(40, 40);
		t3.insert(50, 50);
		t3.insert(60, 60);
		t3.insert(70, 70);	
		
		Assert.assertEquals("[40]",t3.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t3.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[60]",t3.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[10]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[30]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[50]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		Assert.assertEquals("[70]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(1).toString());
	
	t3.insert(80, 80);
	t3.insert(90, 90);
		
		Assert.assertEquals("[40]",t3.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t3.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[60, 80]",t3.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[10]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[30]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[50]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		Assert.assertEquals("[70]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(1).toString());
		Assert.assertEquals("[90]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(2).toString());
		
		t3.insert(65, 65);
	
		Assert.assertEquals("[40]",t3.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t3.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[60, 80]",t3.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[10]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[30]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[50]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		Assert.assertEquals("[65, 70]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(1).toString());
		Assert.assertEquals("[90]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(2).toString());
		
		t3.insert(66, 66);
		
		Assert.assertEquals("[40, 66]",t3.getRoot().getElements().toString());
		Assert.assertEquals("[20]",t3.getRoot().getChildren().get(0).toString());
		Assert.assertEquals("[60]",t3.getRoot().getChildren().get(1).toString());
		Assert.assertEquals("[80]",t3.getRoot().getChildren().get(2).toString());
		Assert.assertEquals("[10]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(0).toString());
		Assert.assertEquals("[30]",((BNode) t3.getRoot().getChildren().get(0)).getChildren().get(1).toString());
		Assert.assertEquals("[50]",((BNode) t3.getRoot().getChildren().get(1)).getChildren().get(0).toString());
		//atencao aqui >.<
		
		
		
		//Assert.assertEquals("[40]",t3.getRoot().getElements().toString());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testSearch(){
		Assert.assertEquals(null, t1.search(0));
		t1.insert(0, 0);
		//BNodePosition<K, V> a = new BNodePosition<K,V>(0,0);
		Assert.assertEquals(0, t1.search(0).position);
		Assert.assertEquals(null, t1.search(1));
		t1.insert(1, 1);
		Assert.assertEquals(1, t1.search(1).position);
		t1.insert(2, 2);
		Assert.assertEquals(0, t1.search(2).position);
		Assert.assertEquals(0, t1.search(0).position);
		Assert.assertEquals(0, t1.search(1).position);
		t1.insert(3, 3);
		Assert.assertEquals(0, t1.search(2).position);
		Assert.assertEquals(0, t1.search(0).position);
		Assert.assertEquals(0, t1.search(1).position);
		Assert.assertEquals(1, t1.search(3).position);
		t1.insert(4, 4);
		
		Assert.assertEquals(0, t1.search(0).position);
		Assert.assertEquals(0, t1.search(1).position);
		Assert.assertEquals(1, t1.search(3).position);
		Assert.assertEquals(0, t1.search(4).position);
		Assert.assertEquals(0, t1.search(2).position);
		
		Assert.assertEquals(null, t1.search(5));
		t1.insert(5, 5);
		Assert.assertEquals(0, t1.search(0).position);
		Assert.assertEquals(0, t1.search(1).position);
		Assert.assertEquals(1, t1.search(3).position);
		Assert.assertEquals(0, t1.search(4).position);
		Assert.assertEquals(0, t1.search(2).position);
		Assert.assertEquals(1, t1.search(5).position);
		Assert.assertEquals(1, t1.search(5).position);
		t1.insert(6, 6);

		Assert.assertEquals(0, t1.search(0).position);
		Assert.assertEquals(0, t1.search(1).position);
		Assert.assertEquals(0, t1.search(3).position);
		Assert.assertEquals(0, t1.search(4).position);
		Assert.assertEquals(0, t1.search(2).position);
		Assert.assertEquals(0, t1.search(5).position);
		Assert.assertEquals(0, t1.search(5).position);
		Assert.assertEquals(0, t1.search(6).position);
		
		Assert.assertEquals(2, t1.height());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testDepth(){
		t1.insert(0, 0);
		Assert.assertEquals("[0]", t1.depthLeftOrder()[0].toString());
		t1.insert(1, 1);
		Assert.assertEquals("[0, 1]", t1.depthLeftOrder()[0].toString());
		t1.insert(2, 2);
		Assert.assertEquals("[1]", t1.depthLeftOrder()[0].toString());
		Assert.assertEquals("[0]", t1.depthLeftOrder()[1].toString());
		Assert.assertEquals("[2]", t1.depthLeftOrder()[2].toString());
		t1.insert(3, 3);
		Assert.assertEquals("[1]", t1.depthLeftOrder()[0].toString());
		Assert.assertEquals("[0]", t1.depthLeftOrder()[1].toString());
		Assert.assertEquals("[2, 3]", t1.depthLeftOrder()[2].toString());
		t1.insert(4, 4);
		Assert.assertEquals("[1, 3]", t1.depthLeftOrder()[0].toString());
		Assert.assertEquals("[0]", t1.depthLeftOrder()[1].toString());
		Assert.assertEquals("[2]", t1.depthLeftOrder()[2].toString());
		Assert.assertEquals("[4]", t1.depthLeftOrder()[3].toString());
		t1.insert(5, 5);
		Assert.assertEquals("[1, 3]", t1.depthLeftOrder()[0].toString());
		Assert.assertEquals("[0]", t1.depthLeftOrder()[1].toString());
		Assert.assertEquals("[2]", t1.depthLeftOrder()[2].toString());
		Assert.assertEquals("[4, 5]", t1.depthLeftOrder()[3].toString());
		t1.insert(6, 6);
		Assert.assertEquals("[3]", t1.depthLeftOrder()[0].toString());
		Assert.assertEquals("[1]", t1.depthLeftOrder()[1].toString());
		Assert.assertEquals("[0]", t1.depthLeftOrder()[2].toString());
		Assert.assertEquals("[2]", t1.depthLeftOrder()[3].toString());
		Assert.assertEquals("[5]", t1.depthLeftOrder()[4].toString());
		Assert.assertEquals("[4]", t1.depthLeftOrder()[5].toString());
		Assert.assertEquals("[6]", t1.depthLeftOrder()[6].toString());
		t1.insert(7, 7);
		Assert.assertEquals("[3]", t1.depthLeftOrder()[0].toString());
		Assert.assertEquals("[1]", t1.depthLeftOrder()[1].toString());
		Assert.assertEquals("[0]", t1.depthLeftOrder()[2].toString());
		Assert.assertEquals("[2]", t1.depthLeftOrder()[3].toString());
		Assert.assertEquals("[5]", t1.depthLeftOrder()[4].toString());
		Assert.assertEquals("[4]", t1.depthLeftOrder()[5].toString());
		Assert.assertEquals("[6, 7]", t1.depthLeftOrder()[6].toString());
		
		Assert.assertEquals(2, t1.height());
		
		
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testSize(){
		BTreeImpl t1 = new BTreeImpl<K,V>(3);
		
		Assert.assertEquals(0, t1.size());
		t1.insert(0, 0);
		Assert.assertEquals(1, t1.size());
		t1.insert(1, 1);
		Assert.assertEquals(2, t1.size());
		t1.insert(2, 2);
		Assert.assertEquals(3, t1.size());
		t1.insert(3, 3);
		Assert.assertEquals(4, t1.size());
		t1.insert(4, 4);
		Assert.assertEquals(5, t1.size());
		t1.insert(5, 5);
		Assert.assertEquals(6, t1.size());
		t1.insert(6, 6);
		Assert.assertEquals(7, t1.size());
		t1.insert(7, 7);
		Assert.assertEquals(8, t1.size());
		t1.insert(8, 8);
		Assert.assertEquals(9, t1.size());
		t1.insert(9, 9);
		Assert.assertEquals(10, t1.size());
		t1.insert(10, 10);
		Assert.assertEquals(11, t1.size());
		t1.insert(11, 11);
		Assert.assertEquals(12, t1.size());
		t1.insert(12, 12);
		Assert.assertEquals(13, t1.size());
		t1.insert(13, 13);
		Assert.assertEquals(14, t1.size());
		t1.insert(14, 14);
		Assert.assertEquals(15, t1.size());
		t1.insert(15, 15);
		Assert.assertEquals(16, t1.size());
		
	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRemove(){
		//Testando com ordem par
		t1 = new BTreeImpl<K,V>(3);
		
		t1.insert(15, 15);
		t1.insert(36, 36);
		
		t1.insert(1, 1);
		t1.insert(8, 8);
		t1.insert(22, 22);
		t1.insert(29, 29);
		t1.insert(43, 43);
		t1.insert(47, 47);
		t1.insert(50, 50);
		t1.insert(51, 51);
		t1.insert(30, 30);
		t1.insert(31, 31);
		
		
		
		t1.remove(50);
		t1.remove(30);
		t1.remove(43);
		t1.remove(29);
		t1.remove(15);
		t1.remove(1);
		
		Assert.assertEquals("[22, 36]",t1.getRoot().getElements().toString());
		Assert.assertEquals("[[8], [31], [47, 51]]",t1.getRoot().getChildren().toString());
		
		
		assertEquals(null, t1.search(50));
		assertEquals(null, t1.search(30));
		assertEquals(null, t1.search(43));
		assertEquals(null, t1.search(29));
		assertEquals(null, t1.search(15));
		assertEquals(null, t1.search(1));
		
		
		//Assert.assertEquals("[31, 47]",t1.getRoot().getElements().toString());
		//Assert.assertEquals("[[8, 22], [36], [51]]",t1.getRoot().getChildren().toString());
		
		
		/*t1 = new BTreeImpl<K,V>(3);
		
		for(int i = 1; i < 14; i++){
			t1.insert(i, i);
		}
		
		
		System.out.println(t1.depthLeftOrder().length);

		
		t1.remove(13);
		t1.remove(9);
		t1.remove(11);
		
		
		*/
		t2 = new BTreeImpl<K,V>(15);
		
		
		for(int i = 0; i < 50000; i++){
			t2.insert(i, i);
			
		}
		long antes = System.nanoTime();
		
		for(int i = 0; i < 50000; i++){
			BNodePosition<K, V> nodePos = t2.search(i); 
			int pos = nodePos.position;
			assertEquals(new Integer(i), nodePos.node.getElementAt(pos).key);
			
		}
		
		for(int i = 0; i < 10001; i++){
			t2.remove(i);
		}
		for(int i = 0; i < 50000; i++){
			if(i < 10001){
				assertEquals(null, t2.search(i));
				continue;
			}
			BNodePosition<K, V> nodePos = t2.search(i); 
			int pos = nodePos.position;
			assertEquals(new Integer(i), nodePos.node.getElementAt(pos).key);
			
		}
		
		
		
		
		long depois = System.nanoTime();
		System.out.println((depois - antes) * (0.000000001));
		System.out.println(t2.height());
		System.out.println(t2.depthLeftOrder().length);
		System.out.println("BTree2");
		
	}
	
	
	
}
