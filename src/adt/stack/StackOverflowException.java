package adt.stack;

public class StackOverflowException extends Exception {

	public StackOverflowException() {
		super("Full stack");
	}
	
}
