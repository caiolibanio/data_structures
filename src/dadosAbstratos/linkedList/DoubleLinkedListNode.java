package dadosAbstratos.linkedList;

public class DoubleLinkedListNode<T> extends SingleLinkedListNode<T>{
	
	protected DoubleLinkedListNode<T> previous;
	
	public DoubleLinkedListNode(){}  //NILL
	
	public DoubleLinkedListNode(T data, DoubleLinkedListNode<T> next, DoubleLinkedListNode<T> previous){
		super(data, next);
		this.previous = previous;
		//this.next = next;
	}
}
