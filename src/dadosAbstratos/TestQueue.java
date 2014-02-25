package dadosAbstratos;

import static org.junit.Assert.*;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import Exceptions.QueueOverflowException;
import Exceptions.QueueUnderflowException;

public class TestQueue {
	private QueueInterface<Integer> queue;
	
	@Before
	public void iniciateQueue(){
		queue = new QueueImpl2<Integer>(10);
	}
	
	@Test
	public void testQueue() throws QueueOverflowException, QueueUnderflowException{
		
		
		assertTrue(queue.isEmpty());
		
		for(int i = 0; i < 10; i++){
			queue.enQueue(i);
		}
		try{
			queue.enQueue(0);
		}catch(QueueOverflowException e){
			assertEquals("Fila cheia!", e.getMessage());
		}
		assertTrue(queue.isFull());
		
		assertEquals("0", queue.head().toString());
		assertEquals("0", queue.deQueue().toString());
		assertEquals("1", queue.head().toString());
		assertFalse(queue.isFull());
		
		for(int i = 0; i < 9; i++){
			queue.deQueue();
		}
		assertFalse(queue.isFull());
		assertTrue(queue.isEmpty());
		try {
			queue.deQueue();
		} catch (Exception e) {
			assertEquals("Fila Vazia!", e.getMessage());
		}
		
		
	}
}
