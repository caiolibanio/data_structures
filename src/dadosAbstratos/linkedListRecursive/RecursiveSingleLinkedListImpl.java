package dadosAbstratos.linkedListRecursive;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T>{
	
	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	@Override
	public boolean isEmpty() {
		if(data == null) return true;
		else return false;
	}

	@Override
	public int size() {
		if(isEmpty()) return 0;
		else{
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()){
			return null;
		}else if(data == element){
			return data;
		}else{
			return next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if(isEmpty()){
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
		}else{
			next.insert(element);
		}
		
	}

	@Override
	public void remove(T element) {
		if(isEmpty()){}
		else if(data == element){
			data = next.data;
			next = next.next;
		}else{
			next.remove(element);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		ArrayList<T> result = new ArrayList<T>();
		toArrayRecursive(result, this);
		
		return (T[]) result.toArray();
	}
	
	private void toArrayRecursive(ArrayList<T> list, RecursiveSingleLinkedListImpl<T> node){
		if(! node.isEmpty()){
			list.add(node.data);
			toArrayRecursive(list, node.next);
		}
		
	}

}
