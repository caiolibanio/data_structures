package dadosAbstratos.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T>{
	
	protected DoubleLinkedListNode<T> last;
	protected DoubleLinkedListNode<T> head;
	
	public DoubleLinkedListImpl(){
		head = new DoubleLinkedListNode<T>();
		last = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insertLast(T element) {
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, null, null);
		newLast.previous = last;
		newLast.next = null;
		last.next = newLast;
		if(last.data == null){
			last = newLast;
			head = newLast;
			++size;
		}else{
			last = newLast;
			++size;
		}
		
		
	}

	@Override
	public void removeFirst() {
		if(head.data != null){
			head =  (DoubleLinkedListNode<T>) head.next;
			head.previous = null;
			--size;
		}else{
			head = last;
			head.previous = null;
		}
		
	}

	@Override
	public void removeLast() {
		if( size == 1){
			last.previous = null;
			last.next = null;
			--size;
		}
		else if(last.data != null){
			last = last.previous;
			last.next = null;
			--size;
		}else{
			head = last;
		}
	}
	
	@Override
	public T search(T element){
		DoubleLinkedListNode<T> auxHead = head;
		DoubleLinkedListNode<T> auxLast = last;
		while((auxHead != auxLast) && (auxHead.data != element) && (auxLast.data != element)){
			auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			auxLast = auxLast.previous;
		}
		if(auxHead.data == element) return auxHead.data;
		else if(auxLast.data == element) return auxLast.data;
		return null;
	}
	@Override
	public void insert(T element){
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, null, null);
		newNode.next = head;
		head.previous = newNode;
		if(head.data == null){
			last = newNode;
			head = newNode;
			++size;
		}else{
			if(size == 1)last = head;
			head = newNode;
			++size;
		}
	}
	@Override
	public void remove(T element){
		if(head.data == element){
			removeFirst();
		}else{
			DoubleLinkedListNode<T> aux = head;
			while((aux.data != null) && aux.data != element){
				aux = (DoubleLinkedListNode<T>) aux.next;
			}
			if(aux.data != null){
				aux.previous.next = aux.next;
				((DoubleLinkedListNode)aux.next).previous = aux.previous;
				--size;
				
			}
			
		}
		
	}
	public static void main(String[] args){
		DoubleLinkedList<Integer> lista = new DoubleLinkedListImpl<Integer>();
		
		System.out.println(lista.isEmpty());
		System.out.println(lista.size());
		lista.insert(3);
		lista.insertLast(2);
		lista.removeLast();
		System.out.println(lista.size());
		System.out.println(lista.isEmpty());
		 

	}
	
}
