package adt.bst;

public interface BST<K extends Comparable<? super K>, V> {
	/**
	 * Returns the root of the tree.
	 * @return
	 */
	public BSTNode<K, V> getRoot();
	
	/**
	 * Say if a BST is empty (NIL).
	 */
	public boolean isEmpty();
	
	/**
	 * The height of a BST. An empty BST has height -1 (there is no root element).
	 */
	public int height();

	/**
	 * Searches a key in a BST. The search starts at the root node.If the key does not exist
	 * the methods returns a NIL (empty) node. 
	 */
	public BSTNode<K, V> search(K key);
	
	/**
	 * Inserts a key in a a BST. 
	 */	
	public void insert(K key, V value);
	
	/**
	 * Returns the node containing the greatest key in a BST rooted by the given node. 
	 * If the node has no right child then it contains the largest key. If the node is empty the method returns null. 
	 */	
	public BSTNode<K,V> maximum(BSTNode<K,V> node);

	/**
	 * Returns the node containing the lowest key in a BST rooted by the given node. 
	 * If the node has no left child then it contains the lowest key. 
	 * If the node is empty the method returns null. 
	 */	
	public BSTNode<K,V> minimum(BSTNode<K,V> node);

	/**
	 * Returns the successor of a node in a BST or null if it does not exist.  
	 */		
	public BSTNode<K,V> sucessor(BSTNode<K,V> node);

	/**
	 * Returns the predecessor of a node in a BST or null if it does not exist.  
	 */		
	public BSTNode<K,V> predecessor(BSTNode<K,V> node);
	
	/**
	 * Removes a node from a BST.  
	 */		
	public void remove(K key);
	
	/**
	 * Returns an array of keys filled according to the pre-order traversing in a BST.  
	 */		
	public K[] preOrder();

	/**
	 * Returns an array of keys filled according to the symmetric order traversing in a BST.  
	 */		
	public K[] order();
	
	/**
	 * Returns an array of keys filled according to the port-order traversing in a BST.  
	 */			
	public K[] postOrder();
	
	/**
	 * Returns the number of nodes (not NIL) in a BST.  
	 */			
	public int size();
}
