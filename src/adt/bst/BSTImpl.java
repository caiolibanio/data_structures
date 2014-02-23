package adt.bst;

import java.util.ArrayList;
import java.util.Stack;


import java.util.List;


public class BSTImpl<K extends Comparable<? super K>, V> implements BST<K, V> {

	protected  BSTNode<K,V> root;
	protected int treeSize = 0;
	
	
	public BSTImpl() {
		root = new BSTNode<K, V>();
	}

	public BSTNode<K, V> getRoot(){
		return this.root;
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return heightSupport(this.root);
		
		
	}

	public int heightSupport(BSTNode<K, V> node){
		if(getRoot().isEmpty()){
			return -1;
		}
		
		if(node.isEmpty()){
			return -1;
		}
		int leftH = heightSupport(node.getLeft());
		int rightH = heightSupport(node.getRight());
		
		if(leftH < rightH){
			return rightH + 1;
		}
		return leftH + 1;
	}
	

	@Override
	public BSTNode<K,V> search(K key) {
		if(treeSize > 4000){
			return searchNonRecursive(root, key);
		}else{
			return search(root, key);
		}
		
	}

	public BSTNode<K,V> search(BSTNode<K, V> node, K key) {
		if(treeSize > 4000){
			return searchNonRecursive(node, key);
		}
		BSTNode<K,V> result = node;
		if (key != null) {
			if (!node.isEmpty()) {
				if (key.compareTo(node.key) == 0) {
					result = node;
				} else if (key.compareTo(node.key) < 0) {
					result = search(node.left, key);
				} else if(key.compareTo(node.key) > 0){
					result = search(node.right, key);
				}
			}
		}

		return result;
	}
	
	public BSTNode<K, V> searchNonRecursive(BSTNode< K, V> node, K key){
		BSTNode<K, V> aux = new BSTNode<K, V>();
		
		while((! node.isEmpty()) && (node.getKey().compareTo(key) != 0)){
			if(key.compareTo(node.getKey()) < 0){
				node = node.getLeft();
			}else if(key.compareTo(node.getKey()) > 0){
				node = node.getRight();
			}
			
		}
		return node;
		
	}
	
	public BSTNode<K, V> searchNode(K key, BSTNode<K, V> node){
		BSTNode<K, V> out = new BSTNode<K, V>();
		
		if(node.key == null || node.key == key){
			return node;
		}else{
			if(key.compareTo(node.key) < 0){
				out = searchNode(key, node.left);
				//return searchNode(key, node.left);
				
			}else if(key.compareTo(node.key) >= 0 ){
				out = searchNode(key, node.right);
				//return searchNode(key, node.right);
			//}else{
				//return null;
			//}
			}
		}
		return out;
	}

	@Override
	public void insert(K key, V value) {
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
			
		}
	}

	@Override
	public BSTNode<K, V> maximum(BSTNode<K, V> node) {
		if(node.isEmpty()){
			return null;
		}
		while(node.right.key != null){
			node = node.right;
		}
		return node;
	}

	@Override
	public BSTNode<K, V> minimum(BSTNode<K, V> node) {
		if(node.isEmpty()){
			return null;
		}
		while(node.left.key != null){
			node = node.left;
		}
		return node;
		
	}

	@Override
	public BSTNode<K, V> sucessor(BSTNode<K, V> node) {
		BSTNode<K, V> result = new BSTNode<K, V>();
		BSTNode<K, V> aux = root;
		
		
		while ((! aux.isEmpty()) && aux.key != node.key){
			if(node.key.compareTo(aux.key) == -1){
				aux = aux.left;
			}else{
				aux = aux.right;
			}
			
		}
		try{
			if(! aux.getRight().isEmpty()){
				result = minimum(aux.getRight());
			}else{
				result = aux.getParent();
				
				while( result != null && aux.equals(result.getRight())){
					aux = result;
					result = result.getParent();
				}
			}
		}catch(Exception e){
			return null;
		}

		return result;
		
		
	}

	@Override
	public BSTNode<K, V> predecessor(BSTNode<K, V> node) {
		BSTNode<K, V> result = new BSTNode<K, V>();
		BSTNode<K, V> aux = root;
		
		
		while ((! aux.isEmpty()) && aux.key != node.key){
			if(node.key.compareTo(aux.key) == -1){
				aux = aux.left;
			}else{
				aux = aux.right;
			}
			
		}
		if(! aux.getLeft().isEmpty()){
			result = maximum(aux.getLeft());
		}else{
			result = aux.getParent();
			
			while( result != null && aux.equals(result.getLeft())){
				aux = result;
				result = result.getParent();
			}
		}

		return result;
	}

	@Override
	public void remove(K key){
		BSTNode<K, V> nodeAux = root;
		
		while ((!(nodeAux.isEmpty())) && key != nodeAux.key) {
			if (key.compareTo(nodeAux.key) == -1) {
				nodeAux = nodeAux.left;
			}else{
				nodeAux = nodeAux.right;
			}
		}
		removeSupport(nodeAux);
		
	}
	
	
	public void removeSupport(BSTNode<K, V> node) {
		
		if (!node.isEmpty()){
			if (node.getLeft().isEmpty() && node.getRight().isEmpty()){
				node.setKey(null);
				node.setValue(null);
			} else{		
				if (!node.getLeft().isEmpty() || !node.getRight().isEmpty()){
					if (!node.equals(root)){
						if (node.getParent().getLeft().equals(node)){
							if (!node.getLeft().isEmpty()){
								node.parent.left = node.getLeft();
							} else{
								node.parent.left = node.getRight();
							}
						} else {
							if (!node.getLeft().isEmpty()){
								node.parent.right = node.getLeft();
							} else{
								node.parent.right = node.getRight();
							}
						}
					} else{
						if (!root.getLeft().isEmpty() && root.getRight().isEmpty()){
							root = root.getLeft();
							root.setParent(null);
						} else{
							if (root.getLeft().isEmpty() && !root.getRight().isEmpty()) {
								root = root.getRight();
								root.setParent(null);
							} else{
								BSTNode<K, V> sucessor = sucessor(node);
								node.setKey(sucessor.getKey());
								node.setValue(sucessor.getValue());
								removeSupport(sucessor);
							}
						}
					}	
				} else{
					if (node.getParent() == null){
						node.setKey(null);
						node.setValue(null);
					}
					BSTNode<K, V> sucessor = sucessor(node);
					node.setKey(sucessor.getKey());
					node.setValue(sucessor.getValue());
					removeSupport(sucessor);
				}
			}
		}
	}

	@Override
	public K[] preOrder() {
		List<Comparable> result = new ArrayList<Comparable>();
		Comparable[] comparable = new Comparable[size()];
		preOrderSupport(result, root);
		
		 return (K[]) result.toArray(comparable);
	}
	
	private void preOrderSupport(List<Comparable> list, BSTNode<K, V> node){
		if(node.key != null){
			list.add(node.key);
			preOrderSupport(list, node.left);
			preOrderSupport(list, node.right);
		}
		
	}

	@Override
	public K[] order() {
		List<Comparable> result = new ArrayList<Comparable>();
		Comparable[] comparable = new Comparable[size()];
		orderSupport(result, root);
		
		return (K[]) result.toArray(comparable);
	}
	
	private void orderSupport(List<Comparable> list, BSTNode<K, V> node){
		if(node.key != null){
			orderSupport(list, node.left);
			list.add(node.key);
			orderSupport(list, node.right);
		}
		
		
	}

	@Override
	public K[] postOrder() {
		List<Comparable> result = new ArrayList<Comparable>();
		Comparable[] comparable = new Comparable[size()];
		postOrderSupport(result, root);
		
		return (K[]) result.toArray(comparable);
	}
	
	private void postOrderSupport(List<Comparable> list, BSTNode<K, V> node){
		if(node.key != null){
			postOrderSupport(list, node.left);
			postOrderSupport(list, node.right);
			list.add(node.key);
		}
		
	}
	
	public BSTNode<K, V> cloneNode(BSTNode<K, V> node){
		BSTNode<K, V> aux = new BSTNode<K, V>(node.getKey(), node.getValue(), 
				node.getLeft(), node.getRight(), node.getParent());
		return aux;
		
	}

	@Override
	public int size() {
		if(treeSize > 4000){
			return treeSize;
		}
		return size(root);
	}
	private int size(BSTNode<K,V> node){
		int result = 0;
		if(!node.isEmpty()){
			result = 1 + size(node.left) + size(node.right);
		}
		return result;
	}
	
	public boolean checkIntegrity(){
//		ArrayList<K> list = new ArrayList<K>();
//		ArrayList<BSTNode<K, V>> stack = new ArrayList<BSTNode<K, V>>();
//		//Stack<BSTNode<K, V>> stack = new Stack();
//		BSTNode<K, V> aux = this.root;
//		while(!stack.isEmpty() || !aux.isEmpty()){
//			if(!aux.isEmpty()){
//				stack.add(0, aux);
//				aux = aux.getLeft();
//			}else{
//				aux = stack.get(0);
//				stack.remove(0);
//				list.add(aux.getKey());
//				aux = aux.getRight();
//			}
//		}
		K[] list = this.order();
		for(int i = 1; i < list.length; i++){
			if(list[(i - 1)].compareTo(list[i]) > 0){
				return false;
			}
		}
		return true;
		
	}
	
	public boolean equals(BSTImpl tree){
		return equals(this.root, tree.root);
	}

	private boolean equals(BSTNode<K, V> node1, BSTNode<K, V> node2) {
		boolean resp = false;
		if(node1.isEmpty()){
			resp = node2.isEmpty();
		}else{
			resp = node1.getKey().equals(node2.getKey()) && 
					equals(node1.getLeft(), node2.getLeft()) && 
					equals(node1.getRight(), node2.getRight());
		}
		return resp;
	}
	

}
