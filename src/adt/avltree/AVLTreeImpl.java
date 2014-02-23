package adt.avltree;
//Aluno: Caio Libanio Melo Jeronimo   21011053

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * This data Structure represents an AVLTree, implementing most of its methods from a previous
 * BST, this tree differs from a simple BST because this tree perform rotations to keep 
 * the correct balance between all the nodes of the structure. This Structure have an average
 * complexity of O(log n)
 * 
 * @author Caio Libanio
 *
 * @param <K> Key of a Node
 * @param <V> Value attached to the Node
 */

public class AVLTreeImpl<K extends Comparable<? super K>, V> 
    extends BSTImpl<K, V> implements AVLTree<K, V> {
	/**
	 * Calculates the Balance of a Node, if the node if pending to the left, it
	 * will returns -1, -2 and so on. If the node if pending to the right, it will
	 * return 1, 2 and so on.
	 * @param node
	 * Node that must be checked
	 * @return
	 * An integer representing how much the specific node is unbalanced
	 */
	//AUXILIARY
	protected int calculateBalance(BSTNode<K, V> node){
		return heightSupport(node.getLeft()) - heightSupport(node.getRight());
	}
	
	/**
	 * This method analyzes the necessity of rotations, and it apply the right one.
	 * @param node
	 * Node that must be rebalanced, if necessary.
	 */
	//AUXILIARY
	protected void rebalance(BSTNode<K, V> node) {
		int balance = calculateBalance(node);
		int sonBalance;

		if (balance > 1) {
			sonBalance = calculateBalance(node.getLeft());
			if (sonBalance >= 0) {
				rightRotation(node); //rotacao simples
			} else { // caso Left-Right
				leftRotation(node.getLeft()); //rotacao esquerda no filho 
				rightRotation(node); //rotacao direita no pai da subarvore
			}

		} else if (balance < -1) {
			sonBalance = calculateBalance(node.getRight()); 
			if (sonBalance <= 0) {
				leftRotation(node); // rotacao simples
			} else {  //caso Right-Left
				rightRotation(node.getRight()); 
				leftRotation(node);
			}
		}
	}
	/**
	 * Performs the left rotations in a specific node.
	 * @param node
	 * Node that must rotate to the left.
	 */
	//AUXILIARY
	protected void leftRotation(BSTNode<K, V> node){
        if(!node.isEmpty() && node!=null && node.getRight().getRight()!=null) {
        	BSTNode<K, V> nDir = node.getRight();
    		BSTNode<K, V> nEsq = node.getLeft();
    		BSTNode<K, V> b = nDir.getLeft();
    		BSTNode<K, V> c = nDir.getRight();
    		
    		K key = node.getKey();
    		V value = node.getValue();
    		
    		node.setKey(nDir.getKey());
    		node.setValue(nDir.getValue());
    		
    		nDir.setKey(key);
    		nDir.setValue(value);
    		node.setRight(c);
    		
    		if(c.getKey() != null){
    			c.setParent(node);
    		}
    		node.setLeft(nDir);
    		nDir.setParent(node);
    		nDir.setLeft(nEsq);
    		
    		if(nEsq.getKey() != null){
    			nEsq.setParent(nDir);
    		}
    		nDir.setRight(b);
    		
    		if(b.getKey() != null){
    			b.setParent(nDir);
    		}
    		
        }
    }
	
	/**
	 * Performs a right rotation in a specific node.
	 * @param node
	 * Node that must rotate to the right.
	 */
	//AUXILIARY
	protected void rightRotation(BSTNode<K, V> node){
        if(!node.isEmpty() && node!=null && node.getLeft().getLeft()!=null) {
    		BSTNode<K, V> y = node.getLeft();
    		BSTNode<K, V> a = y.getLeft();
    		BSTNode<K, V> b = y.getRight();
    		BSTNode<K, V> c = node.getRight();
    		
    		K key = node.getKey();
    		V value = node.getValue();
    		
    		node.setKey(y.getKey());
    		node.setValue(y.getValue());
    		
    		y.setKey(key);
    		y.setValue(value);
    		
    		node.setLeft(a);
    		
    		if(a.getKey() != null){
    			a.setParent(node);
    		}
    		node.setRight(y);
    		y.setParent(node);
    		y.setLeft(b);
    		
    		if(b.getKey() != null){
    			b.setParent(y);
    		}
    		y.setRight(c);
    		
    		if(c.getKey() != null){
    			c.setParent(y);
    		}
    		
    		
        }
        
    }
	
	
	protected void rebalanceUp(BSTNode<K, V> node) {
		BSTNode<K, V> parent = node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = parent.getParent();
		}
	}
	
	@Override
	public void insert(K key, V value){
		insertSupport(this.root, key, value, root.getParent());
		
	}
	
	private void insertSupport(BSTNode<K, V> node, K key, V value, BSTNode<K, V> parent){
		if(node.isEmpty()){
			node.setKey(key);
			node.setValue(value);
			node.setLeft(new BSTNode<K, V>(null, null, null, null, node));
			node.setRight(new BSTNode<K, V>(null, null, null, null, node));
			node.setParent(parent);
		}else{
			if(key.compareTo(node.getKey()) <= 0){
				insertSupport(node.getLeft(), key, value, node);
			}else if(key.compareTo(node.getKey()) > 0){
				insertSupport(node.getRight(), key, value, node);
				
			}
			rebalance(node);
		}
		
		
	}
	@Override
	public void remove(K key) {
        BSTNode<K, V> node = new BSTNode<K, V>();
        node.setKey(key);
        node = search(key);

        remove(node);
    }
	
	private void remove(BSTNode<K, V> node) {

        if (node != null && !node.isEmpty()) {
            if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
                node.setKey(null);
                node.setValue(null);
                node.setLeft(null);
                node.setRight(null);

                rebalanceUp(node);

            } else if ((!node.getLeft().isEmpty() && node.getRight().isEmpty()) || (node.getLeft().isEmpty() && !node.getRight().isEmpty())) {
                if (!node.equals(root) || (node.equals(root) && node.getParent() != null && node.getParent().equals(root))) {
                    if (!node.getParent().getLeft().isEmpty() && node.getParent().getLeft().equals(node)) {
                        if (!node.getLeft().isEmpty()) {
                            node.getParent().setLeft(node.getLeft());
                        } else {
                            node.getParent().setLeft(node.getRight());
                        }
                    } else {
                        if (!node.getLeft().isEmpty()) {
                            node.getParent().setRight(node.getLeft());
                        } else {
                            node.getParent().setRight(node.getRight());
                        }
                    }

                } else {
                    if (root.getLeft().isEmpty()) {
                        root = root.getRight();
                    } else if (root.getRight().isEmpty()) {
                        root = root.getLeft();
                    }
                }

                rebalanceUp(node);

            } else {
                BSTNode<K, V> sucessor = sucessor(node);
                node.setKey(sucessor.getKey());
                node.setValue(sucessor.getValue());

                remove(sucessor);

            }
        }
    }
		
}
	

