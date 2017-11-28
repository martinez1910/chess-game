package pieces;

public class King extends Piece{

	

	public King(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2654" : "\u265A");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite())//Piece of same colour in new position
			return false;
		if((position[0]-newPosition[0] == 0 || Math.abs(position[0]-newPosition[0]) == 1) && (Math.abs(position[1]-newPosition[1]) == 1 || position[1]-newPosition[1] == 0))//Forwards, backwards and diagonal (any)
			return true;
		
		return false;
	}

}
