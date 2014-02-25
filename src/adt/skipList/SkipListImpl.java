package adt.skipList;

public class SkipListImpl<V> implements SkipList<V> {

	protected SkipNode<V> root;
	protected int level;
	protected int maxLevel;

	// SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	// SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean useMaxLevelAsLevel = true;
	protected double probability = 0.5;
	protected SkipNode<V> NIL;

	public SkipListImpl(int maxLevel) {
		if (useMaxLevelAsLevel) {
			this.level = maxLevel;
		} else {
			this.level = 1;
		}
		this.maxLevel = maxLevel;
		root = new SkipNode(Integer.MIN_VALUE, maxLevel, new Integer(
				Integer.MIN_VALUE));
		NIL = new SkipNode(Integer.MAX_VALUE, maxLevel, new Integer(
				Integer.MAX_VALUE));
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < root.forward.length; i++) {
			root.forward[i] = NIL;
		}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		double random = Math.random();
		while (Math.random() <= probability && randomLevel < maxLevel) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, V newValue) {
		int height = randomLevel();

		SkipNode<V> newNode = new SkipNode<V>(key, height, newValue);
		SkipNode<V>[] update = new SkipNode[maxLevel];
		SkipNode<V> node = root;

		for (int i = node.forward.length - 1; i >= 0; i--) {
			while (node.forward[i].key < key) {
				node = node.forward[i];
			}
			update[i] = node;
		}

		node = node.forward[0];
		if (node.key == key) {
			node.satteliteData = newValue;
		} else {
			for (int i = 0; i < height; i++) {
				newNode.forward[i] = update[i].forward[i];
				update[i].forward[i] = newNode;
			}
		}
	}

	@Override
	public void insert(int key, V newValue, int height) {
		SkipNode<V> newNode = new SkipNode<V>(key, height, newValue);
		SkipNode<V>[] update = new SkipNode[maxLevel];
		SkipNode<V> node = root;

		for (int i = node.forward.length - 1; i >= 0; i--) {
			while (node.forward[i].key < key) {
				node = node.forward[i];
			}
			update[i] = node;
		}

		node = node.forward[0];
		if (node.key == key) {
			node.satteliteData = newValue;
		} else {
			for (int i = 0; i < height; i++) {
				newNode.forward[i] = update[i].forward[i];
				update[i].forward[i] = newNode;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipNode<V>[] update = new SkipNode[maxLevel];
		SkipNode<V> node = root;
		
		for (int i = root.forward.length-1; i >= 0 ; i--) {
			while(node.forward[i].key < key){
				node = node.forward[i];
			}
			update[i] = node;
		}
		
		node = node.forward[0];
		if(node.key == key){
			for (int i = 0; i < root.forward.length-1; i++) {
				if(update[i].forward[i] != node){
					break;
				}
				update[i].forward[i] = node.forward[i];
			}			
		}
	}

	@Override
	public int height() {
		return root.forward.length;
	}

	@Override
	public SkipNode<V> search(int key) {
		SkipNode<V> node = root;

		for (int i = root.forward.length - 1; i >= 0; i--) {
			while (node.forward[i].key < key) {
				node = node.forward[i];
			}
		}

		node = node.forward[0];
		if (node.key == key) {
			return node;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		SkipNode<V> node = root;
		int size = 0;
		while (!(node.forward[0].equals(NIL))) {
			size++;
			node = node.forward[0];
		}
		return size;
	}

	@Override
	public SkipNode<V>[] toArray() {
		SkipNode<V>[] array = new SkipNode[size()];
		SkipNode<V> node = root.forward[0];
		int index = 0;

		while (!(node.equals(NIL))) {
			array[index] = node;
			node = node.forward[0];
			index++;
		}
		return array;
	}
}
