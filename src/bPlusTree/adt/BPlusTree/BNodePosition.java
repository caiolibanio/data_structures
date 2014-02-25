package bPlusTree.adt.BPlusTree;

public class BNodePosition<K extends Comparable<? super K>,V> {
	protected BNode<K,V> node;
	protected int position;
	
	public BNodePosition(BNode<K, V> node, int position) {
		this.node = node;
		this.position = position;
	}
	
	public BNodePosition() {
	}
}
