package pieces;

import java.util.Arrays;

public abstract class Piece {
	private boolean isWhite;
	private int[] position;
	private String unicodeSymbol;
	
	public Piece(boolean isWhite, int[] position, String unicodeSymbol) {
		this.isWhite = isWhite;
		this.position = position;
		this.unicodeSymbol = unicodeSymbol;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	public int[] getPosition(){
		return this.position;
	}
	
	public String getUnicodeSymbol() {
		return this.unicodeSymbol;
	}
	
	public boolean setPosition(int[] newPos, Piece[][] piecesBoard) {
		if(Arrays.equals(position, newPos)) return false;//same position		
		
		if(isNewPositionValid(newPos, piecesBoard) && !leavesKingInCheck(newPos, piecesBoard)) {
			this.position = newPos;
			return true;
		}
		return false;
	}
	
	private boolean leavesKingInCheck(int[] newPos, Piece[][] piecesBoard) {
		boolean result = false;
		//Checking if by moving the piece we allow the opponent to 'check' us. 
		//Temporary board with the valid movement.
		Piece auxPiece1 = piecesBoard[newPos[0]][newPos[1]];
		Piece auxPiece2 = piecesBoard[position[0]][position[1]];
		piecesBoard[newPos[0]][newPos[1]] = this;
		piecesBoard[position[0]][position[1]] = null;
		
		//Search own king
		Piece ownKing = null;
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++)
				if(piecesBoard[i][j] != null && piecesBoard[i][j] instanceof King && piecesBoard[i][j].isWhite == this.isWhite)
					ownKing = piecesBoard[i][j];
					
		//Check no enemy valid movement to king's position
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++) {
				Piece piece = piecesBoard[i][j];
				if(piece != null && piece.isWhite() != this.isWhite() && piece.isNewPositionValid(ownKing.position, piecesBoard))
					result = true;					
			}
		piecesBoard[newPos[0]][newPos[1]] = auxPiece1;
		piecesBoard[position[0]][position[1]] = auxPiece2;
		
		return result;
	}
	
	public boolean simulateSetPosition(int[] newPos, Piece[][] piecesBoard) {
		if(Arrays.equals(position, newPos)) return false;//same position		
		
		if(isNewPositionValid(newPos, piecesBoard) && !leavesKingInCheck(newPos, piecesBoard)) {
			//this.position = newPos; //This commented line allows simulation
			return true;
		}
		return false;
	}

	abstract boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard);
	
}
