package exceptions;

public class PositionOutOfTheBoardException extends Exception {

	@Override
	public String getMessage() {
		return "That position is out of the board.";
	}
}
