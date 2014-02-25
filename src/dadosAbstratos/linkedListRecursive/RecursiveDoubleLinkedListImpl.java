package dadosAbstratos.linkedListRecursive;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T>{
	
	protected RecursiveDoubleLinkedListImpl<T> previous;
	
	@Override
	public void insertLast(T element) {
		if(this.isEmpty()){
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>();
			
			
			if(previous == null){
				previous = new RecursiveDoubleLinkedListImpl<T>();
			}
		}else{
			((RecursiveDoubleLinkedListImpl<T>)next).insertLast(element);
			if(((RecursiveDoubleLinkedListImpl<T>)this.next).previous.data == null){
				((RecursiveDoubleLinkedListImpl<T>)this.next).previous = this;
			}
		}
		
	}

	@Override
	public void removeFirst() {
		if(! this.isEmpty()){
			this.data = next.data;
			this.next = next.next;
		}
	}

	@Override
	public void removeLast() {
		if(! this.isEmpty()){
			if(this.next.data == null){
				this.data = null;
			}else{
				((RecursiveDoubleLinkedListImpl<T>)next).removeLast();
			}
		}
		
	}
	
	@Override
	public void insert(T element){ //No comeco
		if(this.isEmpty()){
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>();
			((RecursiveDoubleLinkedListImpl<T>)this.next).previous = this;
		}else{
			RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>();
			newNode.data = this.data;
			this.data = element;
			RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.next;
			this.next = newNode;
			newNode.next = new RecursiveDoubleLinkedListImpl<T>();
			newNode.previous = this;
		}
		
	}
	
	@Override
	public void remove(T element){
		if(! this.isEmpty()){
			if(this.data == element){
				if(this.size() == 1){
					this.data = null;
				}else{
					this.previous.next = this.next;
					((RecursiveDoubleLinkedListImpl<T>)this.next).previous = this.previous;
				}
			}else{
				next.remove(element);
			}
			
		}
	}

}
