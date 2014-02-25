package bPlusTree.adt.BPlusTree;

import java.util.ArrayList;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BTreeImpl<K extends Comparable<? super K>, V> implements
		BTree<K, V> {

	protected BNode<K,V> root;
	protected int order;
	protected int numMinKeys;
	protected K lastKeyDeleted;
	protected K buffer;
	
	
	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<K,V>(order);
		this.numMinKeys = (int) Math.floor(((double) (order - 1)) / ((double) 2));
		
	}
	
	@Override
	public BNode<K, V> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}
	private int height(BNode<K,V> node){
		int resp = -1;
		if(!node.isEmpty()){
			if(node.isLeaf()){
				resp = 0;
			}else{
				resp = 1 + height(node.getChildrenAt(0));
				//a id�ia � pegar a algura do primeiro filho apenas e somar com 1.
				//Se voce usar array vai ter que ajustar o codigo aqui.
			}
		}
		return resp;
	}
	@Override
	public BNode<K, V>[] depthLeftOrder() {
		List<BNode<K, V>> array = new ArrayList<BNode<K, V>>();
        
        depthLeftOrder(root, array);
        BNode<K, V>[] a = new BNode[array.size()];
        for (int i = 0; i < array.size(); i++) {
			a[i] = array.get(i);
		}
        
        return  a;
	}
	private List<BNode<K, V>> depthLeftOrder(BNode<K,V> node, List<BNode<K, V>> array){
        if(!node.isEmpty()){
        	if (node.getParent() == null) {
				array.add(node);
			}
        	for(int i = 0;i<node.getChildren().size();i++){
        		array.add(node.getChildrenAt(i));
        		depthLeftOrder(node.getChildrenAt(i), array);
        	}
        }
        return array;
}

	@Override
	public int size() {
		return size(root);
    }
    public int size(BNode<K,V> node){
            int resp  = 0;
            if(!node.isEmpty()){
                    resp += node.size();
                    for(int i = 0;i<node.getChildren().size();i++){
                            resp += size(node.getChildrenAt(i)) ;
                    }
            }
            return resp;
    }
	
    public BNodePosition<K, V> searchData(K key){
    	BNode<K, V> aux = findLeafForData(root, key);
    	boolean found = false;
    	for(int i = 0; i < aux.getParent().size(); i++){
    		
    	}
    	
    	
    	
    	BNodePosition<K, V> resp = null;
    	
    	for(int i = 0; i < aux.getElements().size(); i++){
    		if(aux.getElementAt(i).key.compareTo(key) == 0){
    			resp = new BNodePosition<K, V>(aux, i);
    			return resp;
    		}
    	}
    	
    	
    	
    	return resp;
    	
    }
    

    @Override
	public BNodePosition<K, V> searchKey(K key) {
		return searchRecursive(root,key);
	}
	private BNodePosition<K, V> searchRecursive(BNode<K,V> node,K key){
		BNodePosition<K, V> resp = null;
		if(!node.isLeaf()){
			if(node.size() > 1){
				if(key.compareTo(node.getElementAt(0).key) < 0)//c ja for menor vai pro filho menor
					return searchRecursive(node.getChildren().getFirst(),key);
				else if(key.compareTo(node.getElements().getLast().key) > 0)//c ja for maior q o ultimo elemento ja vai pro filho maior
					return searchRecursive(node.getChildren().getLast(),key);
				else{//c chave tiver entre o menor e maior, procura o filho a ser inseriodo
					for (int i = 0; i < node.size(); i++) {
						if(node.getElementAt(i).key.compareTo(key) == 0)
							resp = new BNodePosition<K, V>(node, i);
						if(node.getElementAt(i).key.compareTo(key) < 0 && key.compareTo(node.getElementAt(i+1).key) < 0 )
							return searchRecursive(node.getChildren().get(i+1),key);
					}
				}
			}else{
				if(key.compareTo(node.getElementAt(0).key) == 0)
					resp = new BNodePosition<K,V>(node,0);
				else if(key.compareTo(node.getElementAt(0).key) > 0)
					return searchRecursive(node.getChildren().get(1),key);
				else if(key.compareTo(node.getElementAt(0).key) < 0){
					return searchRecursive(node.getChildren().get(0),key);
				}
			}
		}else{
			if(!node.isEmpty()){
				if(node.size() > 1){
					for (int i = 0; i < node.size(); i++) {//c for raiz percorre pra saber.
						if(node.getElementAt(i).key.compareTo(key) == 0)
							resp = new BNodePosition<K, V>(node, i);
					}
				}else{
					if(node.getElementAt(0).key.compareTo(key) == 0)
						resp = new BNodePosition<K, V>(node, 0);
				}
			}
		}

		return resp;
	}

	@Override
	public void insert(K key, V value) {
		Storable<K, V> element = new Storable<K, V>(key, value);
		BNode<K, V> aux = findLeafForData(root, key);
		aux.addElement(element);
		while (aux.getParent() != null) {
			
			if(aux.isLeaf() && aux.overFlow()){
				aux.split();
				aux.promoteLeaf();
				
			}
			
			else if (aux.overFlow()) {
				aux.split();
				aux.promote();
				
			}
			aux = aux.getParent();
		}
		
		if (aux.overFlow()) {
			BNode<K, V> node = new BNode<K, V>(aux.getMaxChildren());
			node.addChild(0, aux);
			aux.setParent(node);
			root = node;
			if(aux.isLeaf()){
				aux.split();
				aux.promoteLeaf();
				
			}else{
				aux.split();
				aux.promote();
				
			}
			
		}
		
	}
	
	private BNode<K, V> findLeaf(BNode<K, V> node, K key){
		int i = 0;
        while((i < node.size()) && (node.getElementAt(i).compareTo(new Storable<K, V>(key, null)) < 0)){
        	i++;
        }
        if(node.isLeaf()){
        	return node;
        }
        return findLeaf(node.getChildrenAt(i), key);
	}
	
	private BNode<K, V> findLeafForData(BNode<K, V> node, K key){
		int i = 0;
        while((i < node.size()) && (node.getElementAt(i).compareTo(new Storable<K, V>(key, null)) < 0)){
        	i++;
        }
        
        
        if(node.isLeaf()){
        	return node;
        }
        
        if(node.getElements().contains(new Storable<K, V>(key, null)) && (node.getChildrenAt(0).getElements().contains(new Storable<K, V>(key, null)))){
        	return findLeafForData(node.getChildrenAt(0), key);
        }
        
        else if(node.getElements().contains(new Storable<K, V>(key, null)) && i < (node.getChildren().size() - 1)){
        	return findLeafForData(node.getChildrenAt(i + 1), key);
        }else{
        	if(node.getChildren().size() == i){
        		return findLeafForData(node.getChildrenAt(i - 1), key);
        	}else{
        		return findLeafForData(node.getChildrenAt(i), key);
        	}
        	
        }
        
        
	}

	//NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<K, V> maximum(BNode<K, V> node) {
		//NAO PRECISA IMPLEMENTAR
		return null;
	}
	@Override
	public BNode<K, V> minimum(BNode<K, V> node) {
		//NAO PRECISA IMPLEMENTAR
		return null;
	}
	
	public void removeData(K key){
		
		// se ao procurar a chave na �rvore e n�o encontrar
				boolean joined = false;
				BNode<K, V> removeNode = findLeafForData(root, key);
				boolean canJoin = false;
				if(removeNode == null){
					return;
				}else{
					BNode<K, V> parent = removeNode.getParent();
					lastKeyDeleted = key;
					// se o n� que cont�m a chave for folha
						removeNode.removeElementByKey(key);
						
						if(! removeNode.equals(root) && removeNode.underFlow()){
							
							if(!(parent.getChildren().size() == (order / 2) &&  parent.getChildren().get(0).isEmpty())){
								joined = balanceLeaf(removeNode);
							}
							
							
							
							
							
							
							if(parent.getElements().contains(new Storable(lastKeyDeleted, null))){
								remove(lastKeyDeleted);
							}
							
							
						}
						
					
				}
	}
	
	private BNode<K, V> findAntecessorInLeaf(K key, LinkedList<BNode<K, V>> children) {
		BNode<K, V> resp = null;
		for(int i = 0; i < children.size(); i++){
			for(int j = 0; j < children.get(i).size(); j++){
				if(children.get(i).getElementAt(j).key.compareTo(key) < 0){
					resp = children.get(i);
				}
			}
			
		}
		
		
		
		return resp;
	}

	private LinkedList<BNode<K, V>> getAdjacentBrothers(BNode<K, V> node) {
		BNode<K, V> parent = node.getParent();
		LinkedList<BNode<K, V>> brothers = parent.getChildren();
		LinkedList<BNode<K, V>> resp = new LinkedList<BNode<K, V>>();
		int index = -1;
		for(int i = 0; i < brothers.size(); i++){
			if(brothers.get(i).equals(node)){
				index = i;
			}
		}
		
		if(index == 0 && brothers.size() > 1){
			resp.add(brothers.get(index + 1));
			
		}else if(index > 0 && brothers.size() > index + 1){
			resp.add(brothers.get(index - 1));
			resp.add(brothers.get(index + 1));
			
		}else if(index > 0 && brothers.size() == index + 1){
			resp.add(brothers.get(index - 1));
			
			}
		return resp;
	}

	/*private void balanceLeafData(BNode<K, V> removeNode) {
		if(!(removeNode.getElements().size() >= numMinKeys)){
			
		
		BNode<K, V> parent = removeNode.getParent();
		int index = -1;
		for(int i = 0; i < parent.getChildren().size(); i++){
			if(parent.getChildrenAt(i).equals(removeNode)){
				index = i;
			}
		}
		
		if(index == 0 && parent.getChildren().size() > 1){
			joinLeafs(removeNode, parent.getChildrenAt(index + 1));
		}else if(index >= 1 && parent.getChildren().size() > 1){
			if((parent.getChildrenAt(index - 1).size() + removeNode.size() <= (order - 1))){
				joinLeafs(removeNode, parent.getChildrenAt(0));
				
				BNode<K, V> brother = parent.getChildrenAt(0);
				for(int i = 0; i < parent.size(); i++){
					if(brother.getElementAt(brother.getElements().size() - 1).key.compareTo(parent.getElementAt(i).key) < 0 && parent.size() > 1){
						remove(brother.getElementAt(brother.getElements().size() - 1).key);
						parent.getElementAt(i - 1).setKey(brother.getElementAt(brother.getElements().size() - 1).key);
					}else if(parent.size() == 1){
						remove(brother.getElementAt(brother.getElements().size() - 1).key);
						parent.getElementAt(i - 1).setKey(brother.getElementAt(brother.getElements().size() - 1).key);
					}else if(i == parent.size() - 1){
						for(int j = 0; j < parent.size(); j++){
							parent.removeElement(j);
						}
						
						brother.getElementAt(brother.getElements().size() - 1);
					}
				}
				
			}else if(parent.getChildren().size() >= index + 1 ){
				joinLeafs(removeNode, parent.getChildrenAt(index + 1));
			}else{
				if(removeNode.isEmpty()){
					parent.getChildren().remove(removeNode);
				}
			}
		}
		
		}
	}*/

	@Override
	public void remove(K key) {
		// se ao procurar a chave na �rvore e n�o encontrar
		BNode<K, V> removeNode = this.searchKey(key).node;
		if(removeNode == null){
			return;
		}else{
			// se o n� que cont�m a chave for folha
			if(removeNode.isLeaf()){
				removeNode.removeElementByKey(key);
				
				if(! removeNode.equals(root)){
					balanceLeaf(removeNode);
				}
				
			}else{
				// se o n� que cont�m a chave n�o for folha
				
				if(! removeNode.isLeaf()){
					
					BNode<K, V> noAntecessor = null;
					
					if(removeNode.getChildrenAt(0).isLeaf()){
						noAntecessor = findAntecessorInLeaf(lastKeyDeleted, removeNode.getChildren());
						if(noAntecessor == null){
							noAntecessor = removeNode;
						}
						
					}else{
						noAntecessor = searchAntecessor(key);
					}
					
					
					
					K iMaiorChaveAntecessor =  noAntecessor.getElementAt(noAntecessor.getElements().size() - 1).key;
					Storable<K, V> auxStorable = noAntecessor.getElementAt(noAntecessor.getElements().size() - 1);
					
					if(removeNode.getChildrenAt(0).isLeaf() && !removeNode.getChildrenAt(0).isEmpty()){
						auxStorable = new Storable(auxStorable.key, null);
						
						removeNode.removeElementByKey(key);
						
						removeNode.addElement(auxStorable);
						
						balanceLeaf(noAntecessor);
						
					}else{
						noAntecessor.removeElementByKey(iMaiorChaveAntecessor);
						
						removeNode.removeElementByKey(key);
						
						removeNode.addElement(auxStorable);
						
						balanceLeaf(noAntecessor);
					}
					
					
					
					
					
				}
				
			}
			
			
			
		}

	}


	

	private BNode<K, V> searchAntecessor(K key) {
		Storable<K, V> aux;
		int iChave = (Integer)key - 1;
		aux = new Storable(iChave, null);
		
		
		
		while(this.searchKey(aux.key) == null){
			iChave--;
			aux = new Storable(iChave, null);
		}
		
		
		return searchKey(aux.key).node;
	}

	private boolean balanceLeaf(BNode<K, V> aNode) {
		boolean joined = false;
		
		
		
		if(aNode.getElements().size() < numMinKeys){
			BNode<K, V> parent = aNode.getParent();//getParent(aNode, root);
			
			
			int j = 0;
			for(int i = 0; i < parent.getChildren().size(); i++){
				if(parent.getChildren().get(i).equals(aNode)){
					j = i;
					break;
				}
			}
			
			if(j == 0 || parent.getChildren().get(j - 1).getElements().size() == numMinKeys){
				if((j == parent.getElements().size()) || parent.getChildrenAt(j+1).getElements().size() == numMinKeys){
					decreaseHeight(aNode);
					joined = true;
					
				}else{
					balance_right_left(parent, j, aNode, parent.getChildrenAt(j + 1));
					
				}
				
			}else{
				joinLeafs(parent, j - 1);
				joined = true;
			}
			
			
		}
		return joined;
		
	}



	private void balance_right_left(BNode<K, V> aPai, int iIndice,
			BNode<K, V> aEsq, BNode<K, V> aDir) {
		
		if(!aDir.isLeaf()){
			for(int i = aDir.getChildren().size(); i > 1; i--){
				aDir.addChild(i + 1, aDir.getChildrenAt(i));
			}
		}
		
		if(aDir.getElements().size() > numMinKeys && aEsq.getElements().size() < numMinKeys){
			if(!aPai.getChildrenAt(0).isLeaf()){
				aEsq.addElement(aPai.getElementAt(iIndice));
			}
			
			
			aPai.removeElement(iIndice);
			
			aPai.addElement(aDir.getElementAt(0));
			aDir.removeElementByKey(aDir.getElementAt(0).key);
			
			
			
			
		}else{
			if(aEsq.getElements().size() > numMinKeys && aDir.getElements().size() < numMinKeys){
				aDir.addElement(aPai.getElementAt(iIndice));
				aPai.removeElement(iIndice);
				
				aPai.addElement(aEsq.getElementAt(aEsq.getElements().size() - 1));
				aEsq.removeElementByKey(aEsq.getElementAt(aEsq.getElements().size() - 1).key);
				
				
				
			}else{
				aDir.addElement(aPai.getElementAt(iIndice));
				aPai.removeElement(iIndice);
				
				aPai.addElement(aEsq.getElementAt(aEsq.getElements().size() - 1));
				aEsq.removeElementByKey(aEsq.getElementAt(aEsq.getElements().size() - 1).key);
				
				
			}
			
			
		}
		if(!aEsq.isLeaf()){
			aDir.addChild(0, aEsq.getChildrenAt(aEsq.getChildren().size() - 1));
			aEsq.removeChild(aEsq.getChildren().size() - 1);
		}
		
	}

	private void decreaseHeight(BNode<K, V> node) {
		
		if(node.equals(root)){
			
			if(node.getElements().size() == 0){
				root = node.getChildrenAt(0);
			}
		}else{
			
			BNode<K, V> parent = node.getParent();
			if(node.getElements().size() < numMinKeys){
				int j = 0;
				for(int i = 0; i < parent.getChildren().size(); i++){
					if(parent.getChildren().get(i).equals(node)){
						j = i;
						break;
					}
				}
				if(j > 0){
						
							joinNode(parent, j - 1);
							
						
						
						
										
					
				}else{
					
				
						joinNode(parent, j);
						
					}
					
					
						
					}
					
				decreaseHeight(parent);
					
				}
				
				
		
				
	}
			
		
		
	

	private void joinNode(BNode<K, V> aNode, int index) {
		BNode<K, V> leftNode = aNode.getChildrenAt(index);
		BNode<K, V> rightNode = aNode.getChildrenAt(index+1);
		
		if(!aNode.getChildrenAt(0).isLeaf()){
			leftNode.addElement(aNode.getElementAt(index));
		}
		
		
		
		
		
		
		aNode.removeElement(index);
		
		
		while(rightNode.getElements().size() > 0 && (rightNode.size() + leftNode.size()) <= (order - 1) ){
			leftNode.addElement(rightNode.getElementAt(0));
			rightNode.removeElement(0);
		}
		
		if(!rightNode.isLeaf()){
			while(rightNode.getChildren().size() > 0 && (rightNode.size() + leftNode.size()) <= (order - 1) ){
				leftNode.addChild(leftNode.getChildren().size(), rightNode.getChildrenAt(0));
				rightNode.removeChild(0);
			}
		}
		
		aNode.removeChild(index + 1);
		
	}
	
	private void joinLeafs(BNode<K, V> aNode, int index){
		BNode<K, V> leftNode = aNode.getChildrenAt(index);
		BNode<K, V> rightNode = aNode.getChildrenAt(index+1);
		boolean joined = false;
		
		
		
		while(rightNode.getElements().size() > 0 && leftNode.getElements().size() > 0){
			leftNode.addElement(rightNode.getElementAt(0));
			rightNode.removeElement(0);
			joined = true;
		}
		if(joined){
			leftNode.split();
			leftNode.promoteLeaf();
		}
		
		if(leftNode.isEmpty()){
			aNode.getChildren().remove(leftNode);
		}else if(rightNode.isEmpty()){
			aNode.getChildren().remove(rightNode);
		}
		
		
		
		
		
		//rightNode.getParent().getChildren().remove(rightNode);
		
		//aNode.removeChild(index + 1);
		
		/*K sucessor = findSucessor(leftNode.getElementAt(leftNode.getElements().size() - 1).key, aNode.getChildren());
		
		if(sucessor != null){
			aNode.addElement(new Storable(sucessor, null));
		}*/
		
		
		
	}
	
	/*private void joinLeafs(BNode<K, V> removeNode, BNode<K, V> brother){
		BNode<K, V> parent = removeNode.getParent();
		if(!brother.overFlow()){
			if((brother.size() + removeNode.size()) <= (order - 1)){
				for(int i = 0; i < removeNode.size(); i++){
					brother.addElement(removeNode.getElementAt(i));
				}
				
				parent.getChildren().remove(removeNode);
				
				
				
			}
		}
		
	}*/

	private BNode<K, V> getParent(BNode<K, V> aFilho, BNode<K, V> root) {
		BNode<K, V> aux;
		
		if(aFilho.equals(root)){
			return null;
		}else{
			for(int i = 0; i < root.getChildren().size(); i++){
				if(root.getChildrenAt(i).equals(aFilho)){
					return root;
				}else if(root.getChildrenAt(i) == null){
					return null;
				}
			}
			
			for(int i = 0; i < root.getChildren().size(); i++){
				aux = getParent(aFilho, root.getChildrenAt(i));
				if(aux != null){
					return aux;
				}
			}
			
			
		}
		return null;
		
	}
}
