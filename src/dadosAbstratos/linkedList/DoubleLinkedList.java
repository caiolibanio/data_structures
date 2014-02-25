package dadosAbstratos.linkedList;

public interface DoubleLinkedList<T> extends LinkedList<T>{
	
	public void insertLast(T element);
	public void removeFirst();
	public void removeLast();
	
}
