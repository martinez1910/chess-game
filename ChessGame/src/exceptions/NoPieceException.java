package exceptions;

public class NoPieceException extends Exception {

	@Override
	public String getMessage() {
		return "There's no piece in that position.";
	}
}
