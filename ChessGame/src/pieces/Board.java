package pieces;

import exceptions.CheckException;
import exceptions.CheckMateException;
import exceptions.InvalidMovementException;
import exceptions.NoPieceException;
import exceptions.NotYourTurnException;
import exceptions.PositionOutOfTheBoardException;

public class Board {
	public enum Tile{
		WHITE, BLACK
	} 
	
	private Tile[][] emptyBoard = new Tile[8][8];
	private Piece[][] piecesBoard = new Piece[8][8];
	private boolean isWhitesTurn = true;
	
	public Board() {
		//System.out.println("Loading Board...");
		this.emptyBoard = createEmptyBoard();
		//print();
		//System.out.println("Loading Pieces...");
		this.piecesBoard = createPiecesBoard();
		print();
	}
		
	
	private Tile[][] createEmptyBoard() {
		Tile[][] emptyBoard = new Tile[8][8];
		
		boolean isWhite = true;
		for(int i=0; i<emptyBoard.length; i++) {
			for(int j=0; j<emptyBoard.length; j++) {
				if(isWhite){
					emptyBoard[i][j] = Tile.WHITE;
					isWhite = false;
				}
				else {
					emptyBoard[i][j] = Tile.BLACK;
					isWhite = true;
				}
			}
			if(isWhite) isWhite = false;
			else isWhite= true;
		}
			
		
		return emptyBoard;
	}
	
	
	private Piece[][] createPiecesBoard() {
		Piece[][] piecesBoard = new Piece[8][8];
		
		//B&W Pawns
		for(int i = 0; i<piecesBoard.length; i++) {
			if(i == 1) //Create black pawns
				for(int j=0; j<piecesBoard.length; j++) {
					piecesBoard[i][j] = new Pawn(false, new int[] {i,j});
				}
			if(i == 6) //Create white pawns
				for(int j=0; j<piecesBoard.length; j++) {
					piecesBoard[i][j] = new Pawn(true, new int[] {i,j});
				}
		}
		
		//Non-pawns Black
		piecesBoard[0][0] = new Rook(false, new int[] {0,0});
		piecesBoard[0][1] = new Knight(false, new int[] {0,1});
		piecesBoard[0][2] = new Bishop(false, new int[] {0,2});
		piecesBoard[0][3] = new Queen(false, new int[] {0,3});
		piecesBoard[0][4] = new King(false, new int[] {0,4});
		piecesBoard[0][5] = new Bishop(false, new int[] {0,5});
		piecesBoard[0][6] = new Knight(false, new int[] {0,6});
		piecesBoard[0][7] = new Rook(false, new int[] {0,7});
			
		//Non-pawns White
		piecesBoard[7][0] = new Rook(true, new int[] {7,0});
		piecesBoard[7][1] = new Knight(true, new int[] {7,1});
		piecesBoard[7][2] = new Bishop(true, new int[] {7,2});
		piecesBoard[7][3] = new Queen(true, new int[] {7,3});
		piecesBoard[7][4] = new King(true, new int[] {7,4});
		piecesBoard[7][5] = new Bishop(true, new int[] {7,5});
		piecesBoard[7][6] = new Knight(true, new int[] {7,6});
		piecesBoard[7][7] = new Rook(true, new int[] {7,7});
		
		return piecesBoard;
	}
	
	
	
	
	//Called from main. "X#X#".Returns if moved
	public void movePiece(String str) throws PositionOutOfTheBoardException, NoPieceException, InvalidMovementException, NotYourTurnException, CheckMateException, CheckException {
		//ADD CONTROL LINES TO CHECK STRING IS CORRECT
		str = str.toLowerCase();
		int[] pos = translatePos(str.substring(0, 2));
		int[] newPos = translatePos(str.substring(2, 4));
		
		if(pos == null || newPos == null)
			throw new PositionOutOfTheBoardException();
		
		if(piecesBoard[pos[0]][pos[1]] == null)
			throw new NoPieceException();
		
		if(piecesBoard[pos[0]][pos[1]].isWhite() != isWhitesTurn)
			throw new NotYourTurnException();
		
		if(piecesBoard[pos[0]][pos[1]].setPosition(newPos, piecesBoard)) {
			piecesBoard[newPos[0]][newPos[1]] = piecesBoard[pos[0]][pos[1]];
			piecesBoard[pos[0]][pos[1]] = null;	
			
			if(isCheckmate()) {
				print();
				if(isWhitesTurn) 
					throw new CheckMateException("WHITE");
				else
					throw new CheckMateException("BLACK");
			}		
			
			isWhitesTurn = !isWhitesTurn;
			
			if(isCheck(piecesBoard[newPos[0]][newPos[1]]))
				throw new CheckException();
		}
		else
			throw new InvalidMovementException();
	
	}
	
	private boolean isCheck(Piece piece) {
		//Search opponent's king
		Piece opponentKing = null;
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++)
				if(piecesBoard[i][j] != null && piecesBoard[i][j] instanceof King && piecesBoard[i][j].isWhite() != piece.isWhite())
					opponentKing = piecesBoard[i][j];
		if(piece.simulateSetPosition(opponentKing.getPosition(), piecesBoard))
			return true;
		return false;
	}
	
	private boolean isCheckmate() {
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++)
				if(piecesBoard[i][j] != null && piecesBoard[i][j].isWhite() != isWhitesTurn)
					for(int a = 0; a<piecesBoard.length; a++) 
						for(int b = 0; b<piecesBoard.length; b++)
							if(piecesBoard[i][j].simulateSetPosition(new int[] {a, b}, piecesBoard)) return false;
							
		return true;
	}
	
	private int[] translatePos(String str) {
		int[] translation = new int[2];
		char letter = str.charAt(0);
		char number = str.charAt(1);
		
		switch(letter) {
			case 'a':
				translation[1] = 0;
				break;
			case 'b':
				translation[1] = 1;
				break;
			case 'c':
				translation[1] = 2;
				break;
			case 'd':
				translation[1] = 3;
				break;
			case 'e':
				translation[1] = 4;
				break;
			case 'f':
				translation[1] = 5;
				break;
			case 'g':
				translation[1] = 6;
				break;
			case 'h':
				translation[1] = 7;
				break;
			default:
				return null;
		}
		
		switch(number) {
		case '8':
			translation[0] = 0;
			break;
		case '7':
			translation[0] = 1;
			break;
		case '6':
			translation[0] = 2;
			break;
		case '5':
			translation[0] = 3;
			break;
		case '4':
			translation[0] = 4;
			break;
		case '3':
			translation[0] = 5;
			break;
		case '2':
			translation[0] = 6;
			break;
		case '1':
			translation[0] = 7;
			break;
		default:
			return null;
		}

		
		return translation;
	}


	public String toString() {
		String str = "#########################################################################";//frame
		for(int i=0; i<emptyBoard.length; i++) {
			//str += i!=0 ? "\n#" : ""; //avoids new line at the beginning and frame
			str += "\n#"; //frame
			for(int j=0; j<emptyBoard.length; j++) {
				if(piecesBoard[i][j] == null)
					str += emptyBoard[i][j]==Tile.WHITE ? "\t\u25A1" : "\t\u25A0"; //Java console colours are inverted due to Eclipse black background.
				else
					str += "\t" +piecesBoard[i][j].getUnicodeSymbol();
			}
			str += "\t# " +(8-i);//frame and prints reference numbers on the right
				
		}
		str += "\n#########################################################################";//frame
		str += "\n\ta\tb\tc\td\te\tf\tg\th"; //prints reference letters at the bottom
		return str;
	}
		
	public void print() {
		System.out.println(toString());
	}
}
