package pieces;

import java.util.Arrays;

public class Queen extends Piece{

	

	public Queen(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2655" : "\u265B");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite()) //Piece of same colour in new position
			return false;
		
		return Rook.isValidAndPathClear(position, newPosition, piecesBoard) || Bishop.isValidAndPathClear(position, newPosition, piecesBoard);
	}
}
