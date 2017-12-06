package exceptions;

/**
 * Exception used when a given position is not in the board.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class PositionOutOfTheBoardException extends Exception {

	@Override
	public String getMessage() {
		return "That position is out of the board.";
	}
}
