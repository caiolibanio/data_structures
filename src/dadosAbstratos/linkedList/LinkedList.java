package dadosAbstratos.linkedList;

public interface LinkedList<T> {
	
	public boolean isEmpty();
	public int size();
	public T search(T element);
	public void insert(T element);
	public void remove(T element);
	public T[] toArray();

}
