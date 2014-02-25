package dadosAbstratos;

import Exceptions.QueueOverflowException;
import Exceptions.QueueUnderflowException;

public interface QueueInterface<T> {
	public void enQueue(T i) throws QueueOverflowException;
	public T deQueue() throws QueueUnderflowException;
	public T head();
	public boolean isEmpty();
	public boolean isFull();
	
}
