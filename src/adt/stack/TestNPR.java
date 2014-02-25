package adt.stack;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class TestNPR {
	private NPRSolver nprSolver;
	
	@Before public void iniciate(){
		nprSolver = new NPRSolverImpl(10);
		
	}
	@Test public void testingNPRSolve() throws InvalidExpressionException{
		
		Assert.assertEquals("11", nprSolver.evaluate("2    3    * 5    +  "));
		Assert.assertEquals("20", nprSolver.evaluate("3 2 +     10 3 -   3 - *"));
		Assert.assertEquals("-14", nprSolver.evaluate("4 2 / 3 5 * 1 + -"));
		Assert.assertEquals("42", nprSolver.evaluate("100 2 / 10 4 + 3 2 * - -"));

	}
	@Test public void testStack() throws StackOverflowException, StackUnderflowException{
		Stack stack = new StackImpl(3);
		stack.push("a");
		stack.push("b");
		stack.push("c");
		try {
			stack.push("g");
		} catch (Exception e) {
			Assert.assertEquals("Full stack", e.getMessage());
		}
		stack.pop();
		stack.pop();
		stack.pop();
		try {
			stack.pop();
		} catch (Exception e) {
			Assert.assertEquals("Empty stack", e.getMessage());
		}
	}
}
