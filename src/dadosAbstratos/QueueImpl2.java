package dadosAbstratos;

import Exceptions.QueueOverflowException;
import Exceptions.QueueUnderflowException;

public class QueueImpl2<T> implements QueueInterface<T>{
	
	private T[] array;
	private int head;
	private int tail;
	
	public QueueImpl2(int size){
		array = (T[]) new Object[size + 1];
		head = 0;
		tail = 0;
	}

	@Override
	public void enQueue(T element) throws QueueOverflowException {
		if(this.isFull()){
			throw new QueueOverflowException();
		}
		array[tail] = element;
		tail = (tail + 1) % array.length;
		
		
	}

	@Override
	public T deQueue() throws QueueUnderflowException {
		T out;
		if(this.isEmpty()){
			throw new QueueUnderflowException();
		}
		
		out = array[head];
		head = (head + 1) % array.length;
		return out;
		
	}

	@Override
	public T head() {
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return head == tail;
	}

	@Override
	public boolean isFull() {
		return (head == ((tail + 1) % array.length));
	}
	

	

	
}
