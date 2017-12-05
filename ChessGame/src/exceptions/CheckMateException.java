package exceptions;

public class CheckMateException extends Exception {

	String winner;
	
	public CheckMateException(String winner) {
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
