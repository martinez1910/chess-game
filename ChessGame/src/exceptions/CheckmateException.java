package exceptions;

/**
 * Exception used when a piece is 'in checkmate'.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class CheckmateException extends Exception {

	String winner;
	
	public CheckmateException(String winner) {
		this.winner = winner;
	}
	
	@Override
	public String getMessage() {
		String str = " ###########################";
		str += "\n" +"## CHECKMATE - " +winner.toUpperCase() +" WINS! ##";
		str += "\n" +" ###########################";
		return str;
	}
}
