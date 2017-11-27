package pieces;

public class Knight extends Piece{

	

	public Knight(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2658" : "\u265E");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
