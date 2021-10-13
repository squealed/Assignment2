import java.util.ArrayList;
import java.util.Collections;
/**
 * class implementing StackInterface
 * @author Neil Walter
 *
 * @param <T> data type
 */
public class NotationStack<T> implements StackInterface<T> {
	
	private int stackSize;
	private ArrayList<T> stackList;
	int count = 0;
	
	public NotationStack(){
		stackSize = 100;
		stackList = new ArrayList<T> (stackSize);
	}
	
	public NotationStack(int size) {
		stackSize = size;
		stackList = new ArrayList<T> (stackSize);
	}
	@Override
	public boolean isFull() {
		if (count != stackSize) {
			return false;
		}else { 
			return true;
		}
	}
	@Override
	public boolean isEmpty() {
		if (count!=0) {
			return false;
		}else { 
			return true;
		}
	}
	@Override
	public T top() throws StackUnderflowException {
		if(count == 0) {
			throw new StackUnderflowException();
		}
		else {
			return stackList.get(count-1);
		}
	}
	@Override
	public T pop() throws StackUnderflowException {
		T finalElement = null;
		if(count == 0) {
			throw new StackUnderflowException();
		}
		else {
			finalElement=stackList.get(count-1);
			stackList.remove(count-1);
			count--;
			return finalElement;
		}
	}
	@Override
	public int size() {
		return count;
	}
	@Override 
	public String toString() {
		String stringStack = "";
		for(T k : stackList ) {
			stringStack += k;
		}
		return stringStack;
	}
	@Override
	public boolean push(T err) throws StackOverflowException {
		if(count >= stackSize) {
			throw new StackOverflowException();
			}
		else {
			stackList.add(err);
			count++;
			return true;
		}
	}
	
	@Override
	public String toString(String del) {
		String stackString = "";
		for(int k = 0; k < stackList.size(); k++ ) {
			if(k != stackList.size()-1) {
				stackString = stackString + stackList.get(k) + del;
			}
			else {
				stackString = stackString +stackList.get(k);
			}
		}
		return stackString;
	}
	@Override
	public void fill(ArrayList<T> arrList) {
		ArrayList<T> arrListCopy = new ArrayList<T>(arrList);
		stackList.addAll(arrListCopy);
		count = stackList.size();
	}
}