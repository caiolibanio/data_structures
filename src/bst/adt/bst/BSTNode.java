package bst.adt.bst;

public class BSTNode<K extends Comparable<? super K>, V> {
	protected K key;
	protected V value;
	protected BSTNode<K, V> left;
	protected BSTNode<K, V> right;
	protected BSTNode<K, V> parent;
	
	public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right, BSTNode<K, V> parent){
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public BSTNode(){};
	
	public boolean isEmpty(){
		return this.key == null;
	}
	
	@Override
	public String toString(){
		String resp = "NIL";
		if(!isEmpty()){
			resp = key.toString();
		}
		return resp;
	}

	@Override
	public boolean equals(Object obj){
		boolean resp = false;
		if(obj instanceof BSTNode){
			resp = 	(this.isEmpty() && ((BSTNode<K,V>)obj).isEmpty()) || 
					this.key.equals(((BSTNode<K,V>)obj).key);
		}
		return resp;
	}
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public BSTNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<K, V> left) {
		this.left = left;
	}

	public BSTNode<K, V> getRight() {
		return right;
	}

	public void setRight(BSTNode<K, V> right) {
		this.right = right;
	}

	public BSTNode<K, V> getParent() {
		return parent;
	}

	public void setParent(BSTNode<K, V> parent) {
		this.parent = parent;
	}
	

}
