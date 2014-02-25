package dadosAbstratos.linkedListRecursive;

/**
 * The interface of a double linked list with a head and a last (Deque). 
 * @param <T>
 */
public interface DoubleLinkedList<T> extends LinkedList<T> {
	
	public void insertLast(T element);
	public void removeFirst();
	public void removeLast();

}
