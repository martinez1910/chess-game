package pieces;

public class Pawn extends Piece{

	

	public Pawn(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2659" : "\u265F");
		// TODO Auto-generated constructor stub
	}

	@Override 
	boolean isNewPositionValid(int[] newPosition) {
		// TODO Auto-generated method stub
		return true;
	}
}
