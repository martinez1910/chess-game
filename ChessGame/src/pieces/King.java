package pieces;

public class King extends Piece{

	

	public King(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2654" : "\u265A");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
