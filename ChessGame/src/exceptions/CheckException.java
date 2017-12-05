package exceptions;

public class CheckException extends Exception {

	@Override
	public String getMessage() {
		String str =  " #########";
		str += "\n## CHECK ##";
		str += "\n #########";
		return str;
	}
}
