package exceptions;

public class NotYourTurnException extends Exception {

	@Override
	public String getMessage() {
		return "You cannot move that piece. It's not its turn.";
	}
}
