package dadosAbstratos;

import Exceptions.StackOverflowException;
import Exceptions.StackUnderflowException;

public class PilhaImpl implements PilhaInterface{
	private int[] pilha;
	private int topo;
	
	public PilhaImpl(int tamanho){
		this.pilha = new int[tamanho];
		this.topo = -1;
	}

	@Override
	public void push(int i) throws StackOverflowException{
		if((top() + 1) == pilha.length) throw new StackOverflowException();
		++topo;
		pilha[topo] = i;
		
	}

	@Override
	public int pop() throws StackUnderflowException {
		if(isEmpty()) throw new StackUnderflowException();
		else{
			--topo;
			return pilha[top() + 1];
		}
	}

	@Override
	public int top() {
		return topo;
	}

	@Override
	public boolean isEmpty() {
		if(top() == -1) return true;
		else return false;
		
	}

	@Override
	public boolean isFull() {
		if(top() == pilha.length - 1) return true;
		else return false;
	}
	
	
}
