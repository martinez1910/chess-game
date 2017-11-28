package pieces;

public class Queen extends Piece{

	

	public Queen(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2655" : "\u265B");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		// TODO Auto-generated method stub
		return false;
	}

}
