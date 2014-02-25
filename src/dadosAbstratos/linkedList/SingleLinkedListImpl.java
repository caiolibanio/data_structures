package dadosAbstratos.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T>{
	
	protected SingleLinkedListNode<T> head;
	protected int size = 0;
	
	public SingleLinkedListImpl(){
		head = new SingleLinkedListNode<T>();
	}
	
	@Override
	public boolean isEmpty() {
		if(size == 0) return true;
		else return false;
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> node = head;
		
		while((! node.isNill()) && (node.data != element)){
			node = node.next;
		}
		return node.data;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>();
		newNode.data = element;
		newNode.next = head;
		head = newNode;
		++size;
		
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> previous = null;
		if(head.data == element){
			head = head.next;
			--size;
		}else{
			SingleLinkedListNode<T> aux = head;
			
			while((! aux.isNill()) && aux.data != element){
				previous = aux;
				aux = aux.next;
			}
			if(! aux.isNill()){
				previous.next = aux.next;
				--size;
			}
		}
		
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		LinkedList<Integer> lista = new SingleLinkedListImpl<Integer>();
		lista.insert(3);
		lista.insert(2);
		lista.remove(3);
		System.out.println(lista.size());
		System.out.println(lista.search(2));
		lista.remove(2);
		System.out.println(lista.isEmpty());
		
	}

}
