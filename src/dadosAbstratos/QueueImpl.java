package dadosAbstratos;

import Exceptions.QueueOverflowException;
import Exceptions.QueueUnderflowException;

public class QueueImpl<T> implements QueueInterface<T>{
	private T[] queue;
	private int tail;
	private int head;
	
	public QueueImpl(int size){
		queue = (T[]) new Object[size + 1];
		
		tail = 0;
		head = 0;
	}

	@Override
	public void enQueue(T i) throws QueueOverflowException {
		if(isFull()) throw new QueueOverflowException();
		else{
			queue[tail] = i;
			tail = (tail + 1) % queue.length;
		}
	}

	@Override
	public T deQueue() throws QueueUnderflowException {
		T result;
		if(isEmpty()) throw new QueueUnderflowException();
		else{
			result = queue[head];
			head = (head + 1) % queue.length;
		}
		return result;
	}

	@Override
	public T head() {
		return queue[head];
	}

	@Override
	public boolean isEmpty() {
		return (head == tail);
	}

	@Override
	public boolean isFull() {
		return (head == ((tail + 1) % queue.length));
	}

}
