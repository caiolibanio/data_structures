package adt.skipList;

public interface SkipList<V> {

	/**
	 * Inserts a new element (with a random height) in the skip list.    
	 */
	public void insert(int key, V newValue);

	/**	
	 * Inserts a new element (with a given height) in the skip list. 
	 */
	public void insert(int key, V newValue, int height);
	
	/**
	 * Removes a node (containing the given key) from the skip list. If the given key is not present 
	 * is any node of the skip list, it remains unchanged.  
	 */
	public void remove(int key);

	/**
	 * Returns the height of the highest node of the skip list. 
	 */
	public int height();

	/**
	 * Searches a node (containing the given key) in the skip list. If the key is not present
	 * in any node of the skip list, it returns null.
	 * 
	 */
	public SkipNode<V> search(int key);
	
	
	/**
	 * Returns the size (number of real elements) of the skip list. The sentinel nodes are not considered. For example, 
	 * just after instantiating a skip list it has the first and the NIL nodes, but its size is 0. 
	 */
	public int size();

	/**
	 * Returns an array containing all nodes (including sentinel nodes) of the skip list in order.
	 */
	public SkipNode<V>[] toArray();
}
