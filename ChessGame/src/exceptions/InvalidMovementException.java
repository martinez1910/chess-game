package exceptions;

/**
 * Exception used when a piece cannot be moved to the given position.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class InvalidMovementException extends Exception {

	@Override
	public String getMessage() {
		return "That piece cannot perform that movement.";
	}
}
