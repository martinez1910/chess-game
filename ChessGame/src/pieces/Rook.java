package pieces;

public class Rook extends Piece{

	

	public Rook(boolean isWhite, int[] position) {
		super(isWhite, position,  isWhite ? "\u2656" : "\u265C");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
