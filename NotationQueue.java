import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
/**
 * class implementing QueueInterface
 * @author Neil Walter
 *
 * @param <T> data type
 */
public class NotationQueue<T> implements QueueInterface<T> {
	private int queSize;
	int start, finish, count=0;
	public ArrayList<T> queueList;
	
	public NotationQueue(){
		queueList = new ArrayList<T> (queSize);
		queSize = 100;
	}
	public NotationQueue(int tempsize) {
		queSize = tempsize;
		queueList = new ArrayList<T> (queSize);
	}
	@Override
	public boolean isFull() {
		if (count != queSize) {
			return false;
		}else {
			return true;
	}
	}
	@Override
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public T dequeue() throws QueueUnderflowException {
		T Q;
		if(count == 0) {
			throw new QueueUnderflowException();
		}
		else {
			Q = queueList.get(start);
			count--;
			start++;
		}
		return Q;
	}
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(count == queSize) {
			throw new QueueOverflowException();
		}
		else {
			queueList.add(finish,e);
			count++;
			finish++;
			return true;
		}
	}
	@Override
	public String toString() {
		String queHolder = "";
		for(T k : queueList ) {
			queHolder = queHolder + k;
		}
		return queHolder;
	}
	@Override
	public int size() {
		return count;
	}
	@Override
	public void fill(ArrayList<T> arrList) {
		ArrayList<T> queHolder = new ArrayList<T>(arrList);
		count = queueList.size();
		queueList.addAll(queHolder);
	}
	@Override
	public String toString(String del) {
		String queHolder = "";
		for(int k = 0; k < queueList.size(); k++ ){
			if(k != queueList.size() - 1) {
				queHolder = queHolder + queueList.get(k) + del;
			}
			else {
				queHolder = queHolder + queueList.get(k);
			}
		}
		return queHolder;
	}
}