package exceptions;

/**
 * Exception used when a piece is 'in check'.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class CheckException extends Exception {

	@Override
	public String getMessage() {
		String str =  " #########";
		str += "\n## CHECK ##";
		str += "\n #########";
		return str;
	}
}
