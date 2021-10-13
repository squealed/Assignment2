/**
 * Notation class
 * @author Neil Walter
 *
 */
public class Notation {
	private static double operation(String firstString, String secondString, char operation) {
		double finalvalue=0;
		double firstvalue = 0;
		double secondvalue = 0;
		switch(operation) {
		case '-':
			firstvalue=Double.parseDouble(firstString);
			secondvalue=Double.parseDouble(secondString);
			finalvalue=firstvalue-secondvalue;
			break;
		case '+':
			firstvalue=Double.parseDouble(firstString);
			secondvalue=Double.parseDouble(secondString);
			finalvalue=firstvalue+secondvalue;
			break;
		case '/':
			firstvalue=Double.parseDouble(firstString);
			secondvalue=Double.parseDouble(secondString);
			finalvalue=firstvalue/secondvalue;
			break;
		case '*':
			firstvalue=Double.parseDouble(firstString);
			secondvalue=Double.parseDouble(secondString);
			finalvalue=firstvalue*secondvalue;
			break;
		default:
			System.out.println("Invalid expression");
		}	
		return finalvalue;
	}
	
	public static String convertPostfixToInfix(String start) throws InvalidNotationFormatException {
		char[] temp = start.toCharArray();
		NotationStack<String> completeStack = new NotationStack<String>(10);
		String fixed = "", broken = "";
		try {
			for(int i = 0; i < temp.length; i++) {
				if(Character.isDigit(temp[i])) {
					completeStack.push(String.valueOf(temp[i]));
				}
				if(temp[i]=='+'||temp[i]=='-'||temp[i]=='/'||temp[i]=='*') {
					if(completeStack.size()<2) {
						throw new InvalidNotationFormatException();
					}
					else {
						broken=completeStack.pop();
						fixed="(" + completeStack.pop() + temp[i] + broken + ")";
						completeStack.push(fixed);
					}
				}
			}
			if(completeStack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackUnderflowException s) {
			s.printStackTrace();
		}
		catch(StackOverflowException d) {
			d.printStackTrace();
		}
		return completeStack.toString();
	}
	public static String convertInfixToPostfix(String initial) throws InvalidNotationFormatException {
		NotationQueue<String> fixedQue = new NotationQueue<String>(20);
		NotationStack<String> fixedStack = new NotationStack<String>(20);
		char[] holder = initial.toCharArray();
		try {
			for(int k = 0; k < holder.length; k++) {
				if(holder[k]=='(') {
					fixedStack.push(String.valueOf(holder[k]));
				}
				if(holder[k]==')') {
					while(!fixedStack.isEmpty() && !fixedStack.top().equals("(")) {
						fixedQue.enqueue(fixedStack.pop());
					}
					if(fixedStack.isEmpty() || !fixedStack.top().equals("(")) {
						throw new InvalidNotationFormatException();
					}
					
					if(!fixedStack.isEmpty() && fixedStack.top().equals("(")){
						fixedStack.pop();
					}
				}
				if(Character.isDigit(holder[k])) {
					fixedQue.enqueue(String.valueOf(holder[k]));
				}else if(holder[k]=='/') {
					if(!fixedStack.isEmpty()) {
						while(fixedStack.top().equals("*")||fixedStack.top().equals("/")) {
							fixedQue.enqueue(fixedStack.pop());
						}
					}
					fixedStack.push(String.valueOf(holder[k]));
				}else if(holder[k]=='+') {
					if(!fixedStack.isEmpty()) {
						while(fixedStack.top().equals("+") || fixedStack.top().equals("-") || fixedStack.top().equals("*") || fixedStack.top().equals("/")) {
							fixedQue.enqueue(fixedStack.pop());
						}
					}
					fixedStack.push(String.valueOf(holder[k]));
				}else if(holder[k]=='*') {
					if(!fixedStack.isEmpty()) {
						while(fixedStack.top().equals("*") || fixedStack.top().equals("/")) {
							fixedQue.enqueue(fixedStack.pop());
						}
					}
					fixedStack.push(String.valueOf(holder[k]));
				}else if(holder[k]=='-') {
					if(!fixedStack.isEmpty()) {
						while(fixedStack.top().equals("+") || fixedStack.top().equals("-") || fixedStack.top().equals("*") || fixedStack.top().equals("/")) {
							fixedQue.enqueue(fixedStack.pop());
						}
					}
					fixedStack.push(String.valueOf(holder[k]));
				}
			}
			while(!fixedStack.isEmpty() && !fixedStack.top().equals("(")) {
				fixedQue.enqueue(fixedStack.pop());
			}
		}
		catch(StackOverflowException stackoverflow) {
			stackoverflow.printStackTrace();
		}
		catch(QueueOverflowException queoverflow) {
			queoverflow.printStackTrace();
		}
		catch(StackUnderflowException stackunderflow) {
			stackunderflow.printStackTrace();
		}
		return fixedQue.toString();
	}
	public static double evaluatePostfixExpression(String postExpression) throws InvalidNotationFormatException {
		NotationStack<String> finalStack =new NotationStack<String>(10);
		char[] holder = postExpression.toCharArray();
		double total = 0;
		String start, finish;
		try {
			for(int k = 0; k < holder.length; k++) {
				if(Character.isDigit(holder[k]) || holder[k]=='(') {
					finalStack.push(String.valueOf(holder[k]));
				}
				else {
					if(finalStack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					else {
						start=finalStack.pop();
						finish=finalStack.pop();
						total=operation(start,finish,holder[k]);
						finalStack.push(Double.toString(total));
						
					}
				}
			}
			if(finalStack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackUnderflowException s) {
			s.printStackTrace();
		}
		catch(StackOverflowException d) {
			d.printStackTrace();
		}
		return total;
	}
	public static double evaluateInfixExpression(String beforeExpression) throws InvalidNotationFormatException {
		double total = 0;
		total = evaluatePostfixExpression(convertInfixToPostfix(beforeExpression));
		return total;
	}
}