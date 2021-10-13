/**
 * QueueUnderflowException class
 * @author Neil Walter
 *
 */
public class QueueUnderflowException extends Exception{
	/**
	 * Assigns text to throw when exception is thrown
	 */
	QueueUnderflowException(){
		super("Full Que");
	}

}
