package pieces;

/**
 * Class representing the knight piece.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
class Knight extends Piece{
	
	/**
	 * Main constructor. Automatically selects the Unicode symbol depending on the colour.
	 * @param isWhite Colour of the piece
	 * @param position Position of the piece in 'array notation'
	 */
	Knight(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2658" : "\u265E");
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite())//Piece of same colour in new position
			return false;
		
		if(Math.abs(position[0] - newPosition[0]) == 2 && Math.abs(position[1] - newPosition[1]) == 1 //Left-Left-Up/Down and Right-Right-Up/Down movements
				|| Math.abs(position[0] - newPosition[0]) == 1 && Math.abs(position[1] - newPosition[1]) == 2) //Up-Up-Left/Right and Down-Down-Left/Right movements
			return true;
		
		return false;
	}

}
