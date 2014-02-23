package adt.splaytree;

//Aluno: Caio Libanio Melo Jeronimo  21011053

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;

/**
 * This Structure Represents the Services of a SplayTree, and it implements methods of a
 * BSTTree and an AVLTree. This Structure have an average time of O(log n), and in the worst case,
 * we may have O(n), but this situation must happens only one time, in case of the SplayTree being totally
 * unbalanced to the left or to the right.
 * 
 * @author Caio Libanio
 *
 * @param <K> The key of a node
 * @param <V> The Value that is attached to the node
 */
public class SplayTreeImpl<K extends Comparable<? super K>, V> extends AVLTreeImpl<K, V> implements
		SplayTree<K, V> {
	/**
	 * This method will do the Splay in a Node, making this node reaches to the Root of 
	 * this Tree
	 * @param node
	 * The node that should becomes the new Root
	 */
	private void splay(BSTNode<K,V> node){
		BSTNode<K, V> aux = cloneNode(node);
		K key = aux.getKey();
		while(this.root.getKey().compareTo(key) != 0 ){
			
			if (node != null && node.getParent() != null) {
				if (node.getParent().getKey() == root.getKey()) {
					if (node.getParent().getLeft().getKey() == node.getKey())
						rightRotation(node.getParent());
					else if (node.getParent().getRight().getKey() == node.getKey())
						leftRotation(node.getParent());
				} else if (node.getKey() == node.getParent().getLeft().getKey()) {
					if (node.getParent().getKey() == node.getParent().getParent()
							.getLeft().getKey()) {
						
							//Zig-Zig Rotations
							rightRotation(node.getParent().getParent());
							rightRotation(node.getParent());
							node = node.getParent();
	                        
					} else {
						
							//Zig-Zag Rotation
							rightRotation(node.getParent());
							leftRotation(node.getParent().getParent());
							node = node.getParent();
					}
				} else if (node.getKey() == node.getParent().getRight().getKey()) {
					if (node.getParent().getKey() == node.getParent().getParent()
							.getRight().getKey()) {
						
							//Zig-Zig Rotations
							leftRotation(node.getParent().getParent()); 
							leftRotation(node.getParent());
							node = node.getParent();
							
					} else {
						
							//Zig-Zag Rotations
							leftRotation(node.getParent());
							rightRotation(node.getParent().getParent());
							node = node.getParent();
						
				}
			}
		}
	}
}
	

	
	@Override
	public void insert(K key, V value){
		insertSupport(this.root, key, value, root.getParent());
		++treeSize;
	}
	
	private void insertSupport(BSTNode<K, V> node, K key, V value, BSTNode<K, V> parent){
		if(node.isEmpty()){
			node.setKey(key);
			node.setValue(value);
			node.setLeft(new BSTNode<K, V>(null, null, null, null, node));
			node.setRight(new BSTNode<K, V>(null, null, null, null, node));
			node.setParent(parent);
			splay(node);
			
		}else{
			if(key.compareTo(node.getKey()) <= 0){
				insertSupport(node.getLeft(), key, value, node);
			}else if(key.compareTo(node.getKey()) > 0){
				insertSupport(node.getRight(), key, value, node);
				
			}
			
		}
		
		
	}
	
	
	private void SplayRemove(K key){
        BSTNode<K, V> node = new BSTNode<K, V>();
        node.setKey(key);
        node = searchNonRecursive(root, key);
       
        remove(node);
        --treeSize;
	}
	
	private void remove(BSTNode<K, V> node) {
        
        if(node != null && !node.isEmpty()){
                if(node.getLeft().isEmpty() && node.getRight().isEmpty()){
                        node.setKey(null);
                        node.setValue(null);
                        node.setLeft(null);
                        node.setRight(null);
                       
                } else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || (node.getLeft().isEmpty() && !node.getRight().isEmpty()) ){
                        if(!node.equals(root) || (node.equals(root) && node.getParent() != null && node.getParent().equals(root))){
                                if(!node.getParent().getLeft().isEmpty() && node.getParent().getLeft().equals(node)){
                                        if(!node.getLeft().isEmpty()){
                                                node.getParent().setLeft(node.getLeft());
                                        }else{
                                                node.getParent().setLeft(node.getRight());
                                        }
                                }else{
                                        if(!node.getLeft().isEmpty()){
                                                node.getParent().setRight(node.getLeft());
                                        }else{
                                                node.getParent().setRight(node.getRight());
                                        }
                                }
                               
                        } else {
                                if(root.getLeft().isEmpty()){
                                        root = root.getRight();
                                }else if(root.getRight().isEmpty()){
                                        root = root.getLeft();
                                }
                        }
                       
                } else {
                        BSTNode<K, V> sucessor = sucessor(node);
                        node.setKey(sucessor.getKey());
                        node.setValue(sucessor.getValue());
                       
                        remove(sucessor);
                }
        }
       
}
	@Override
    public void remove(K key){
    	BSTNode<K, V> node = new BSTNode<K, V>();
    	
    	
    	if(treeSize > 4000){
    		node = searchNonRecursive(root, key);
    	}else{
    		node = search(root, key);
    	}
    	
        if(node.isEmpty()){
                splay(node.getParent());
        }else{
        	
        		if(node.equals(root)){
        			BSTNode<K, V> smallestRight = minimum(root.getRight());
        			this.SplayRemove(node.getKey());
        			splay(smallestRight);
        			
        		}else{
        			this.SplayRemove(node.getKey());
        			splay(node.getParent());
                    
        		}
               
        }
}
	
	
	@Override
	public BSTNode<K,V> search (K key) {
		BSTNode<K, V> aux = new BSTNode<K, V>();
		if(treeSize > 4000){
			aux = searchNonRecursive(root, key);
		}else{
			aux = search(root, key);
		}
		
		BSTNode<K, V> aux2 = cloneNode(aux);
		
		if(! aux.isEmpty()){
			splay(aux);
		}else{
			splay(aux.getParent());
		}
		return aux2;
		
	}
	
	
}
