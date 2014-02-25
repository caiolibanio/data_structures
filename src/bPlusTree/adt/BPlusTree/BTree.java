package bPlusTree.adt.BPlusTree;

/**
 * 
 * This Structure represent the services offer by a B-Tree, that have an average time of O(log n) 
 * in the operations of Insertion, Search and Deletion.
 * 
 * 
 * @author Caio Libanio
 *
 * @param <K> The key used to find a Value in the B-Tree
 * @param <V> The Value that needs to be inserted in the B-Tree
 */

public interface BTree<K extends Comparable<? super K>, V> {
	
	/**
	 * Returns the root of the tree.
	 */
	public BNode<K, V> getRoot();

	/**
	 * Checks if the tree is empty. 
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the height of a b tree. 
	 */
	public int height();
	
	
	/**
	 * Searches a pair (BNode, position) whose node contains the given key. If the key is not present
	 * the method returns null. 
	 */
	public BNodePosition<K, V> searchKey(K key);
	
	public BNodePosition<K, V> searchData(K key);

	/**
	 * Inserts a new Storable element containing the given key and the given value. 
	 */
	public void insert(K key, V value);

	/**
	 * Returns an array of BNodes visited in depth-left. The algorithm must visit each node 
	 * in the following way: add the ROOT, then visit recursively all children of the ROOT 
	 * (from left to right), and so on. Therefore, the algorithm goes down at the left side 
	 * of the tree. 
	 */
	public BNode<K,V>[] depthLeftOrder();

	/**
	 * Returns the number of elements of all nodes of the tree. 
	 */
	public int size();
	
	/**
	 * Returns the node containing the maximum element of the tree. 
	 */
	public BNode<K, V> maximum(BNode<K, V> node);
	
	/**
	 * Returns the node containing the minimum element of the tree. 
	 */
	public BNode<K, V> minimum(BNode<K, V> node);
	
	/**
	 * Removes the element containing the given key.  
	 */
	public void remove(K key);
	
	public void removeData(K key);
}
