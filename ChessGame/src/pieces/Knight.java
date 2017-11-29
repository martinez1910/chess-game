package pieces;

public class Knight extends Piece{

	

	public Knight(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2658" : "\u265E");
		// TODO Auto-generated constructor stub
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
