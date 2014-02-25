package btree.adt.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BNode<K extends Comparable<? super K>, V> {
	private LinkedList<Storable<K,V>> elements; //PODE TRABALHAR COM ARRAY TAMBEM
	private LinkedList<BNode<K,V>> children; //PODE TRABALHAR COM ARRAY TAMBEM
	private BNode<K,V> parent;
	private int maxKeys;
	private int maxChildren;
	
	public BNode(int order){
		this.maxChildren = order;
		this.maxKeys = order - 1;
		this.elements = new LinkedList<Storable<K,V>>();	
		this.children = new LinkedList<BNode<K,V>>();
	}
	@Override
	public String toString() {
		return this.elements.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if(obj != null){
			if(obj instanceof BNode){
				if(this.size() == ((BNode<K, V>) obj).size()){
					resp = true;
					int i = 0;
					while(i<this.size() && resp){
						resp = resp && this.getElementAt(i).equals(((BNode<K, V>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}
	public boolean isEmpty(){
		return this.size() == 0;
	}
	public int size(){
		return this.elements.size();
	}
	public boolean isLeaf(){
		return this.children.size() == 0;
	}
	public boolean isFull(){
		return this.size()== maxKeys;
	}
	public void addElement(Storable<K,V> element){
		this.elements.add(element);
		Collections.sort(elements);
	}
	public void removeElement(Storable<K,V> element){
		this.elements.remove(element);
	}
	public void removeElement(int position){
		this.elements.remove(position);
	}
	public void removeElementByKey(K key){
		for(int i = 0; i < elements.size(); i++){
			if(elements.get(i).key.compareTo(key) == 0){
				elements.remove(i);
			}
		}
		
	}
	public void addChild(int position, BNode<K,V> child){
		this.children.add(position, child);
		child.parent = this;
	}
	public void removeChild(BNode<K,V> child){
		this.children.remove(child);
	}
	public void removeChild(int position){
		this.children.remove(position);
	}
	public int indexOfChild(BNode<K,V> child){
		return this.children.indexOf(child);
	}
	public Storable<K,V> getElementAt(int index){
		return this.elements.get(index);
	}
	
	public BNode<K, V> getChildrenAt(int index){
		return this.children.get(index);
	}
	
	public LinkedList<BNode<K,V>> getChildren(){
		return children;
	}
	
	public LinkedList<Storable<K,V>> getElements(){
		return elements;
	}
	
	public BNode<K, V> getParent(){
		return parent;
	}
	
	public void setParent(BNode<K, V> newParent){
		parent = newParent;
	}
	
	public int getMaxChildren(){
		return maxChildren;
	}
	
	public boolean overFlow(){
		if (elements.size() > maxKeys) {
			return true;
		}
		return false;
	}
	
	protected void split(){
		int indice = this.elements.size() / 2;
		BNode<K, V> node = new BNode<K, V>(maxChildren);
		node.parent = this.parent;
		this.parent.addChild(this.parent.indexOfChild(this), node);
		
		for (int i = 0; i < indice; i++) {    //dividindo elementos
			node.addElement(this.getElementAt(0));
			this.removeElement(0);
		}
		indice = this.getChildren().size() / 2;   //dividindo filhos
		if (this.getChildren().size() % 2 != 0) {
			indice++;
		}
		for (int i = 0; i < indice; i++) {
			node.addChild(i, this.getChildrenAt(0));
			this.removeChild(0);
		}
	}
	protected void promote(){
		this.parent.addElement(this.getElementAt(0));
		this.removeElement(0);
	}
	
	protected void promoteLeaf(){
		Storable<K, V> aux = new Storable<K, V>(this.getElementAt(0).key, null);
		this.parent.addElement(aux);
		//this.removeElement(0);
	}
	
	
	
	
}
