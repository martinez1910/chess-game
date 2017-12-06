package exceptions;

/**
 * Exception used when it is not the piece's turn.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class NotYourTurnException extends Exception {

	@Override
	public String getMessage() {
		return "You cannot move that piece. It's not its turn.";
	}
}
