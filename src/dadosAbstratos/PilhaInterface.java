package dadosAbstratos;

import Exceptions.StackOverflowException;
import Exceptions.StackUnderflowException;

public interface PilhaInterface {
	
	public void push(int i) throws StackOverflowException;  //adiciona
	public int pop() throws StackUnderflowException; // remove
	public int top(); // retorna o maior indice ocupado
	public boolean isEmpty();
	public boolean isFull();
	
}
