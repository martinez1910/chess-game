package pieces;

/**
 * Class representing the king piece.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
class King extends Piece{

	/**
	 * Main constructor. Automatically selects the Unicode symbol depending on the colour.
	 * @param isWhite Colour of the piece
	 * @param position Position of the piece in 'array notation'
	 */
	King(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2654" : "\u265A");
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite())//Piece of same colour in new position
			return false;
		if(isPositionCheck(newPosition, piecesBoard))
			return false;
			
		if((position[0]-newPosition[0] == 0 || Math.abs(position[0]-newPosition[0]) == 1) && (Math.abs(position[1]-newPosition[1]) == 1 || position[1]-newPosition[1] == 0))//Forwards, backwards and diagonal (any)
			return true;
		
		return false;
	}
	
	
	/**
	 * Returns if the new position is a 'check' one (illegal move).
	 * @param position New position of the piece in 'array notation'
	 * @param piecesBoard Matrix of the board containing all the pieces
	 * @return 'true' if the position is a 'check' one, 'false' otherwise.
	 */
	private boolean isPositionCheck(int[] position, Piece[][] piecesBoard) {
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++) {
				Piece piece = piecesBoard[i][j];
				if(!(piece instanceof King) && piece != null && piece.isWhite() != this.isWhite() && piece.isNewPositionValid(position, piecesBoard))//check that is not a King prevents infinite loop
					return true;					
			}
		return false;
	}

}
