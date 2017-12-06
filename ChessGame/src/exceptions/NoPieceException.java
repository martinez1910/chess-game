package exceptions;

/**
 * Exception used when there is no piece in a given position.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class NoPieceException extends Exception {

	@Override
	public String getMessage() {
		return "There's no piece in that position.";
	}
}
