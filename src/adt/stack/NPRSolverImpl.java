package adt.stack;
/*
 * Aluno: Caio Libanio Melo Jeronimo
 * Matricula: 21011053
 * 
 * Obs: o metodo evaluate(String expression) considera que a entrada sempre 
 * sera uma entrada valida( como especificado em sala )
 * 
 */
/**
 * The implementation of the NPRSolver interface.
 *
 */
public class NPRSolverImpl implements NPRSolver {

	private Stack<String> stack;
	
	public NPRSolverImpl(int size){
		stack = new StackImpl<String>(size);
		
	}

	@Override
	public String evaluate(String expression) throws InvalidExpressionException {
		String[] arrayExpression = expression.replaceAll(" {2,}", " ").split(" ");
		String out = null;
		int operando2, operando1;
		
		for(int i = 0; i < arrayExpression.length; i++){
			if(arrayExpression[i].equals("+") || arrayExpression[i].equals("-") || arrayExpression[i].equals("*") || arrayExpression[i].equals("/")){
				if(arrayExpression[i].equals("+")){
					try {
						operando2 = Integer.parseInt(stack.pop());
						operando1 = Integer.parseInt(stack.pop());
						int result = operando1 + operando2;
						stack.push(Integer.toString(result));
					} catch (Exception e) {
						throw new InvalidExpressionException(e.getMessage());
						
					}
					
				}
				else if(arrayExpression[i].equals("-")){
					try {
						operando2 = Integer.parseInt(stack.pop());
						operando1 = Integer.parseInt(stack.pop());
						int result = operando1 - operando2;
						stack.push(Integer.toString(result));
					} catch (Exception e) {
						throw new InvalidExpressionException(e.getMessage());
						
					}
				}
				else if(arrayExpression[i].equals("*")){
					try {
						operando2 = Integer.parseInt(stack.pop());
						operando1 = Integer.parseInt(stack.pop());
						int result = operando1 * operando2;
						stack.push(Integer.toString(result));
					} catch (Exception e) {
						throw new InvalidExpressionException(e.getMessage());
						
					}
				}
				else if(arrayExpression[i].equals("/")){
					try {
						operando2 = Integer.parseInt(stack.pop());
						operando1 = Integer.parseInt(stack.pop());
						int result = operando1 / operando2;
						stack.push(Integer.toString(result));
					} catch (Exception e) {
						throw new InvalidExpressionException(e.getMessage());
						
					}
				}
				
				
			}else{
				try {
					stack.push(arrayExpression[i]);
				} catch (Exception e) {
					throw new InvalidExpressionException(e.getMessage());
					
				}
				
			}
		}
		try {
			out = stack.pop();
		} catch (Exception e) {
			throw new InvalidExpressionException(e.getMessage());
		}
		return out;
	}

}
