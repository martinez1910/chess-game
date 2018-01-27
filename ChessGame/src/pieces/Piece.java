package pieces;

import java.util.Arrays;

/**
 * Abstract class used by all the type of pieces.
 * @author A. Martínez
 * @version 1.1 27/01/2017
 *
 */
abstract class Piece {
	private boolean isWhite;
	private int[] position;
	private String unicodeSymbol;
	
	/**
	 * Main constructor.
	 * @param isWhite Colour of the piece
	 * @param position Position of the piece in 'array notation'
	 * @param unicodeSymbol Symbol of the piece in Unicode
	 */
	Piece(boolean isWhite, int[] position, String unicodeSymbol) {
		this.isWhite = isWhite;
		this.position = position;
		this.unicodeSymbol = unicodeSymbol;
	}
	
	/**
	 * Returns 'true' if the colour of the piece is white, 'false' otherwise. 
	 * @return 'true' if the colour is white
	 */
	boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * Returns the position of the piece in 'array notation'.
	 * @return int[] with the position
	 */
	int[] getPosition(){
		return this.position;
	}
	
	/**
	 * Returns the code of the piece's Unicode symbol. Ranging between "\u2654" and "\u265F" ('white king' and 'black pawn'). 
	 * @return String containing Unicode symbol.
	 */
	String getUnicodeSymbol() {
		return this.unicodeSymbol;
	}
	
	/**
	 * Main method used to move a piece. Relies on isNewPositionValid() and leavesKingInCheck().
	 * @param newPos The new position of the piece
	 * @param piecesBoard Matrix of the board containing all the pieces
	 * @return 'true' if it has been moved, 'false' otherwise
	 */
	boolean setPosition(int[] newPos, Piece[][] piecesBoard) {
		if(Arrays.equals(position, newPos)) return false;	
		
		if(isNewPositionValid(newPos, piecesBoard) && !leavesKingInCheck(newPos, piecesBoard)) {
			this.position = newPos;
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if by moving a piece it makes its king to be in a 'check' position (illegal move).
	 * Calling isNewPositionValid() previously is a must.
	 * @param newPos The new position of the piece that has been moved
	 * @param piecesBoard The matrix of the board containing all the pieces.
	 * @return 'true' if the king is 'in check' (illegal move), 'false' otherwise
	 */
	private boolean leavesKingInCheck(int[] newPos, Piece[][] piecesBoard) {
		boolean result = false;
		//Temporary board with the valid movement.
		Piece auxPiece1 = piecesBoard[newPos[0]][newPos[1]];
		Piece auxPiece2 = piecesBoard[position[0]][position[1]];
		piecesBoard[newPos[0]][newPos[1]] = this;
		piecesBoard[position[0]][position[1]] = null;
		
		//Search own king
		int[] ownKingPosition = null;
		outerLoop:
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++)
				if(piecesBoard[i][j] != null && piecesBoard[i][j] instanceof King && piecesBoard[i][j].isWhite == this.isWhite) {
					ownKingPosition = new int[] {i,j};
					break outerLoop;
				}					
					
		//Check no enemy valid movement to king's position
		outerLoop:
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++) {
				Piece piece = piecesBoard[i][j];
				if(piece != null && piece.isWhite() != this.isWhite() && piece.isNewPositionValid(ownKingPosition, piecesBoard)) {
					result = true;
					break outerLoop;
				}								
			}
		piecesBoard[newPos[0]][newPos[1]] = auxPiece1;
		piecesBoard[position[0]][position[1]] = auxPiece2;
		
		return result;
	}
	
	/**
	 * Simulates moving a piece. Simulated version of setPosition() used while looking for a 'check' or 'checkmate' situation. Simulates moving a piece. 
	 * Relies on isNewPositionValid() and leavesKingInCheck().
	 * @param newPos The new position of the piece
	 * @param piecesBoard The matrix of the board containing all the pieces
	 * @return 'true' if it has been moved, 'false' otherwise
	 */
	boolean simulateSetPosition(int[] newPos, Piece[][] piecesBoard) {
		if(Arrays.equals(position, newPos)) return false;		
		
		if(isNewPositionValid(newPos, piecesBoard) && !leavesKingInCheck(newPos, piecesBoard)) {
			//this.position = newPos; //This commented line allows simulation
			return true;
		}
		return false;
	}

	/**
	 * Checks that the movement can be performed by the given type of piece.
	 * @param newPosition The new position of the piece
	 * @param piecesBoard The matrix of the board containing all the pieces
	 * @return 'true' if the movement is valid, 'false' otherwise
	 */
	abstract boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard);
	
}
