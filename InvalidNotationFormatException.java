/**
 * InvalidNotationFormatException exception
 * @author Neil Walter
 *
 */
public class InvalidNotationFormatException extends Exception{
	/**
	 * Assigns text to throw when exception is thrown
	 */
	InvalidNotationFormatException(){
		super("Notation format is incorrect");
	}
}
