package dadosAbstratos.linkedList;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestLinkedLists {
	private LinkedList<Integer> listaSimples = new SingleLinkedListImpl<Integer>();
	private DoubleLinkedList<Integer> listaDupla = new DoubleLinkedListImpl<Integer>();
	
	@Test public void testaListaSimples(){
		Assert.assertEquals(true, listaSimples.isEmpty());
		listaSimples.insert(3);
		listaSimples.insert(2);
		listaSimples.insert(1);
		listaSimples.remove(3);
		Assert.assertEquals(2, listaSimples.size());
	}
	
	@Test public void testaListaDupla(){
		Assert.assertEquals(true, listaDupla.isEmpty());
		listaDupla.insertLast(0);
		listaDupla.insert(3);
		listaDupla.insert(2);
		listaDupla.insert(1);
		listaDupla.insert(10);
		//listaDupla.insertLast(0);
		listaDupla.remove(2);
		listaDupla.removeFirst();
		listaDupla.removeLast();
		Assert.assertEquals(2, listaDupla.size());
		
	}
	
	
	
}
