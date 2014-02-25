package dadosAbstratos.linkedListRecursive;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class TestaListasRecursivas {
	
	private LinkedList<Integer> listaSimples = new RecursiveSingleLinkedListImpl<Integer>();
	private DoubleLinkedList<Integer> listaDupla = new RecursiveDoubleLinkedListImpl<Integer>();
	
	
	@Test public <T> void testaListaSimples() {
		listaSimples.insert(1);
		listaSimples.insert(2);
		listaSimples.insert(3);
		listaSimples.insert(4);
		T[] arr = (T[]) listaSimples.toArray();
		org.junit.Assert.assertArrayEquals(arr, listaSimples.toArray());
		Assert.assertEquals(4, listaSimples.size());
		listaSimples.remove(1);
		listaSimples.remove(2);
		Assert.assertEquals("3", listaSimples.search(3).toString());
		listaSimples.remove(3);
		listaSimples.remove(4);
		Assert.assertEquals(0, listaSimples.size());
	}
	
	@Test public void testaListaDupla(){
		listaDupla.insert(1);
		listaDupla.insert(2);
		listaDupla.insertLast(3);
		listaDupla.removeFirst();
		listaDupla.removeLast();
		assertEquals(1, listaDupla.size());
	}

}
