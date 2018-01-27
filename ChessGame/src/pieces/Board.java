package pieces;

import exceptions.*;
import logic.Main;

/**
 * Class representing the board.
 * @author A. Martínez
 * @version 1.1 27/01/2018
 *
 */
public class Board {
	/**
	 * Enumeration representing board tiles (black ones and white ones).
	 * @author A. Martínez
	 * @version 1.0 05/12/2017
	 *
	 */
	private enum Tile{
		WHITE, BLACK
	} 
	
	private Tile[][] emptyBoard = new Tile[8][8];
	private Piece[][] piecesBoard = new Piece[8][8];
	private boolean isWhitesTurn = true;
	
	/**
	 * Main constructor that creates the board with the pieces and prints it.
	 */
	public Board() {
		this.emptyBoard = createEmptyBoard();
		this.piecesBoard = createPiecesBoard();
		print();
	}
		
	/**
	 * Returns a board (matrix) of Tiles.
	 * @return Tile[][] matrix of Tiles
	 * @see Tile
	 */
	private Tile[][] createEmptyBoard() {
		Tile[][] emptyBoard = new Tile[8][8];
		
		boolean isWhite = true;
		for(int i=0; i<emptyBoard.length; i++) {
			for(int j=0; j<emptyBoard.length; j++)				
				if(isWhite){
					emptyBoard[i][j] = Tile.WHITE;
					isWhite = false;
				}else {
					emptyBoard[i][j] = Tile.BLACK;
					isWhite = true;
				}
			
			isWhite = isWhite ? false : true;
		}			
		
		return emptyBoard;
	}
	
	
	/**
	 * Returns a board (matrix) of Pieces.
	 * @return Piece[][] matrix of Pieces
	 * @see Piece
	 */
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
		
		//Black non-pawns
		piecesBoard[0][0] = new Rook(false, new int[] {0,0});
		piecesBoard[0][1] = new Knight(false, new int[] {0,1});
		piecesBoard[0][2] = new Bishop(false, new int[] {0,2});
		piecesBoard[0][3] = new Queen(false, new int[] {0,3});
		piecesBoard[0][4] = new King(false, new int[] {0,4});
		piecesBoard[0][5] = new Bishop(false, new int[] {0,5});
		piecesBoard[0][6] = new Knight(false, new int[] {0,6});
		piecesBoard[0][7] = new Rook(false, new int[] {0,7});
			
		//White non-pawns
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
	
	
	
	
	/**
	 * Main method used to move a piece.
	 * @param str String that should match this regular expression: ([a-h]|[A-H])[1-8]([a-h]|[A-H])[1-8]
	 * @throws NoPieceException if there's no piece in any of the given positions
	 * @throws InvalidMovementException if the piece cannot perform that movement
	 * @throws NotYourTurnException if you are trying to move a piece when it's not that piece's turn
	 * @throws CheckmateException if a 'checkmate' situation is present after moving a piece so the game ends
	 * @throws InvalidCommandException if the command given by the user is wrongly defined
	 */
	public void movePiece(String str) throws NoPieceException, InvalidMovementException, NotYourTurnException, CheckmateException, InvalidCommandException {
		if(!str.matches("([a-h]|[A-H])[1-8]([a-h]|[A-H])[1-8]")) 
			throw new InvalidCommandException();
		
		int[] pos = translatePosition(str.substring(0, 2));
		int[] newPos = translatePosition(str.substring(2, 4));
		
		if(piecesBoard[pos[0]][pos[1]] == null)
			throw new NoPieceException();
		
		if(piecesBoard[pos[0]][pos[1]].isWhite() != isWhitesTurn)
			throw new NotYourTurnException();
		
		if(piecesBoard[pos[0]][pos[1]].setPosition(newPos, piecesBoard)) {
			piecesBoard[newPos[0]][newPos[1]] = piecesBoard[pos[0]][pos[1]];
			piecesBoard[pos[0]][pos[1]] = null;	
			
			if(isPromotion(piecesBoard[newPos[0]][newPos[1]])) {
				handlePromotion(newPos);
			}
			
			if(isCheckmate()) {
				print();
				if(isWhitesTurn) 
					throw new CheckmateException("WHITE");
				else
					throw new CheckmateException("BLACK");
			}		
			
			if(isCheck(piecesBoard[newPos[0]][newPos[1]]))
				handleCheck();
			
			isWhitesTurn = !isWhitesTurn;
			
		}
		else
			throw new InvalidMovementException();
	
	}
	


	/**
	 * Returns if the moved piece makes the opponent's king to be 'in check'.
	 * @param piece Moved piece
	 * @return 'true' if 'in check', 'false' otherwise
	 */
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
	
	/**
	 * Handles a 'check' situation. Prints a message to the user informing about this.
	 */
	private void handleCheck() {
		String str =  " #########";
		str += "\n## CHECK ##";
		str += "\n #########";
		System.out.println(str);		
	}
	
	/**
	 * Returns if there is a 'checkmate' situation so the game ends.
	 * @return 'ture' if 'checkmate', 'false' otherwise
	 */
	private boolean isCheckmate() {
		for(int i = 0; i<piecesBoard.length; i++) 
			for(int j = 0; j<piecesBoard.length; j++)
				if(piecesBoard[i][j] != null && piecesBoard[i][j].isWhite() != isWhitesTurn)
					for(int a = 0; a<piecesBoard.length; a++) 
						for(int b = 0; b<piecesBoard.length; b++)
							if(piecesBoard[i][j].simulateSetPosition(new int[] {a, b}, piecesBoard)) return false;
							
		return true;
	}
	
	/**
	 * Returns if there is a 'promotion' situation.
	 * @param piece Moved piece
	 * @return 'true' if a pawn 'in promotion', 'false' otherwise.
	 */
	private boolean isPromotion(Piece piece) {
		int[] piecePos = piece.getPosition();
		if(piece instanceof Pawn) {
			if(piece.isWhite() && piecePos[0] == 0)
				return true;			
			if(!piece.isWhite() && piecePos[0] == 7)
				return true;
		}		
		return false;
	}
	
	/**
	 * Handles a 'promotion' situation. Asks the user the piece to replace the promoted pawn and sets it into the board.
	 * @param position Position of the pawn to be promoted in the board.
	 */
	private void handlePromotion(int[] position) {
		String str = "You have reached the 8th rank with a pawn, please select one of the followings:";
		str += "\n 1 - Queen";
		str += "\n 2 - Knight";
		str += "\n 3 - Rook";
		str += "\n 4 - Bishop";
		System.out.println(str);
		
		int number;		
		do {
			try {
				number = Integer.parseInt(Main.readLine());
			}catch(NumberFormatException e) { //handles wrong input from user
				number = 0;
			}
			
			switch(number) {
			case 1:
				piecesBoard[position[0]][position[1]] = new Queen(isWhitesTurn, new int[] {position[0],position[1]});
				System.out.println("Queen selected.");
				break;
			case 2:
				piecesBoard[position[0]][position[1]] = new Knight(isWhitesTurn, new int[] {position[0],position[1]});
				System.out.println("Knight selected.");
				break;
			case 3:
				piecesBoard[position[0]][position[1]] = new Rook(isWhitesTurn, new int[] {position[0],position[1]});
				System.out.println("Rook selected.");
				break;
			case 4:
				piecesBoard[position[0]][position[1]] = new Bishop(isWhitesTurn, new int[] {position[0],position[1]});
				System.out.println("Bishop selected.");
				break;
			default: System.out.println("Wrong number. Please, introduce a number between 1 and 4.");
			}
			
		}
		while(number < 1 || number > 4);
		
	}
	
	/**
	 * Translate the string given by the user to 'array notation' that is used internally. Should be invoked twice, one with each half of the string (current position and new position)
	 * @param str Command given by the user
	 * @return int[] Array with first integer being vertical coordinate and second one being the horizontal.
	 */
	private int[] translatePosition(String str) {
		str = str.toLowerCase();
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
		}
		
		return translation;
	}


	@Override
	public String toString() {
		String str = "#########################################################################";//frame
		for(int i=0; i<emptyBoard.length; i++) {
			//str += i!=0 ? "\n#" : ""; //avoids new line at the beginning and frame
			str += "\n#"; //frame
			for(int j=0; j<emptyBoard.length; j++) {
				if(piecesBoard[i][j] == null)
					str += emptyBoard[i][j]==Tile.WHITE ? "\t\u25A1" : "\t\u25A0"; //Java console colours are inverted due to IDE's black background.
				else
					str += "\t" +piecesBoard[i][j].getUnicodeSymbol();
			}
			str += "\t# " +(8-i);//frame and prints reference numbers on the right
				
		}
		str += "\n#########################################################################";//frame
		str += "\n\ta\tb\tc\td\te\tf\tg\th"; //prints reference letters at the bottom
		return str;
	}
		
	/**
	 * Prints the board formated with frame and reference letters and numbers.
	 */
	public void print() {
		System.out.println(toString());
	}
}
