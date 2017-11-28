package exceptions;

public class InvalidMovementException extends Exception {

	@Override
	public String getMessage() {
		return "That piece cannot perform that movement.";
	}
}
