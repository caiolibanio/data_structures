package Exceptions;

public class QueueUnderflowException extends Exception {
	public QueueUnderflowException(){
		super("Fila Vazia!");
	}
}
