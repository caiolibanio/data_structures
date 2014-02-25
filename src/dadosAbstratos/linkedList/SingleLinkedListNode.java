package dadosAbstratos.linkedList;

public class SingleLinkedListNode<T> {
	protected T data;
	protected SingleLinkedListNode<T> next;
	
	public SingleLinkedListNode(){}  // NILL
	
	public SingleLinkedListNode(T data, SingleLinkedListNode<T> next){
		this.data = data;
		this.next = next;
		
	}
	
	public boolean isNill(){
		return (data == null);
	}
	
}
