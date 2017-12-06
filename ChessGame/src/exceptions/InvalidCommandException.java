package exceptions;

/**
 * Exception used when the string given by the user to move a piece is not well formed.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class InvalidCommandException extends Exception {
	@Override
	public String getMessage() {
		String str = "The command with the movement must be like this regular expresion: ([a-h]|[A-H])[1-8]([a-h]|[A-H])[1-8].";
		str += "\nThat means 'x#y#' where 'x' and 'y' are characters between 'a' and 'h' (uppercase or not) followed by a number between '1' and '8'.";
		return str;
	}
}
