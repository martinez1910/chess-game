package pieces;

public class Bishop extends Piece{

	

	public Bishop(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2657" : "\u265D");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
