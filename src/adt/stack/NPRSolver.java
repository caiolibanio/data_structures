package adt.stack;

/**
 * The interface of a NPR solver.
 *
 */
public interface NPRSolver {
	
	/**
	 * Evaluates an expression represented in NPR. If the expression is invalid, the method
	 * returns an InvalidExpressionException. Otherwise, it returns an string containing the 
	 * result.
	 * @param expression the Expression (String) to be evaluated.
	 * @return
	 */
	public String evaluate(String expression) throws InvalidExpressionException;
}
