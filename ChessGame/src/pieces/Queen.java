package pieces;

/**
 * Class representing the queen piece.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
class Queen extends Piece{	

	/**
	 * Main constructor. Automatically selects the Unicode symbol depending on the colour.
	 * @param isWhite Colour of the piece
	 * @param position Position of the piece in 'array notation'
	 */
	Queen(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2655" : "\u265B");
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
