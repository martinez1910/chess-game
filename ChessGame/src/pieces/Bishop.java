package pieces;

import java.util.Arrays;

/**
 * Class representing the bishop piece.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
class Bishop extends Piece{

	/**
	 * Main constructor. Automatically selects the Unicode symbol depending on the colour.
	 * @param isWhite Colour of the piece
	 * @param position Position of the piece in 'array notation'
	 */
	Bishop(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2657" : "\u265D");
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite())//Piece of same colour in new position
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
		if(!(Math.abs(position[0] - newPosition[0]) == Math.abs(position[1] - newPosition[1]))) //Diagonal (any)
			return false;
		
		int[] pathPosition = position.clone();
		
		if(position[0] > newPosition[0] && position[1] > newPosition[1])//up-left diagonal
			while(pathPosition[0] != newPosition[0] && pathPosition[1] != newPosition[1]) {
				pathPosition[0]--;
				pathPosition[1]--;
				if(Arrays.equals(pathPosition, newPosition)) break;//This can be included inside while's condition but written here for simplicity purposes.
				if(piecesBoard[pathPosition[0]][pathPosition[1]] != null)
					return false;
			}
		else if(position[0] > newPosition[0] && position[1] < newPosition[1])//up-right diagonal
			while(pathPosition[0] != newPosition[0] && pathPosition[1] != newPosition[1]) {
				pathPosition[0]--;
				pathPosition[1]++;
				if(Arrays.equals(pathPosition, newPosition)) break;//This can be included inside while's condition but written here for simplicity purposes.
				if(piecesBoard[pathPosition[0]][pathPosition[1]] != null)
					return false;
			}
		else if(position[0] < newPosition[0] && position[1] > newPosition[1])//down-left diagonal
			while(pathPosition[0] != newPosition[0] && pathPosition[1] != newPosition[1]) {
				pathPosition[0]++;
				pathPosition[1]--;
				if(Arrays.equals(pathPosition, newPosition)) break;//This can be included inside while's condition but written here for simplicity purposes.
				if(piecesBoard[pathPosition[0]][pathPosition[1]] != null)
					return false;
			}
		else if(position[0] < newPosition[0] && position[1] < newPosition[1])//down-right diagonal
			while(pathPosition[0] != newPosition[0] && pathPosition[1] != newPosition[1]) {
				pathPosition[0]++;
				pathPosition[1]++;
				if(Arrays.equals(pathPosition, newPosition)) break;//This can be included inside while's condition but written here for simplicity purposes.
				if(piecesBoard[pathPosition[0]][pathPosition[1]] != null)
					return false;
			}
		return true;
	}

}
