package pieces;

/**
 * Class representing the rook piece.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
class Rook extends Piece{
	
	/**
	 * Main constructor. Automatically selects the Unicode symbol depending on the colour.
	 * @param isWhite Colour of the piece
	 * @param position Position of the piece in 'array notation'
	 */
	Rook(boolean isWhite, int[] position) {
		super(isWhite, position,  isWhite ? "\u2656" : "\u265C");
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite()) //Piece of same colour in new position
			return false;

		return isValidAndPathClear(position, newPosition, piecesBoard);
	}

	/**
	 * Checks if the movement is valid and if the path is 'clear' (that means no piece from actual position to the new one).
	 * Static and non-private method due to code being reused in Queen.
	 * @see Queen.isNewPositionValid
	 * @param position Position of the piece in 'array notation'
	 * @param newPosition New position of the piece in 'array notation'
	 * @param piecesBoard Matrix of the board containing all the pieces
	 * @return 'true' if the movement is valid and the path is clear, 'false' otherwise
	 */
	static boolean isValidAndPathClear(int[] position, int[] newPosition, Piece[][] piecesBoard) {
		//Check movement valid
		if(!(position[0] == newPosition[0] && Math.abs(position[1]-newPosition[1]) > 0 || Math.abs(position[0]-newPosition[0]) > 0 && position[1] == newPosition[1]))  //Left, right and up, down movements)
			return false;
		
		//Check path taken
		
		if(position[0] == newPosition[0] && position[1] > newPosition[1]) //Left
			for(int i = 1; i < position[1]-newPosition[1]; i++) {
				if(piecesBoard[position[0]][position[1]-i] != null)
					return false;
			}
		
		else if(position[0] == newPosition[0]&& newPosition[1] > position[1]) //Right
			for(int i = 1; i < newPosition[1] - position[1]; i++) {
				if(piecesBoard[position[0]][position[1]+i] != null)
					return false;
			}
		
		else if(position[0] > newPosition[0] && position[1] == newPosition[1]) //Up
			for(int i = 1; i < position[0]-newPosition[0]; i++) {
				if(piecesBoard[position[0]-i][position[1]] != null)
					return false;
			}
		
		else if(newPosition[0] > position[0] && position[1] == newPosition[1]) //Down
			for(int i = 1; i < newPosition[0]-position[0]; i++) {
				if(piecesBoard[position[0]+i][position[1]] != null)
					return false;
			}
		return true;
	}

}
