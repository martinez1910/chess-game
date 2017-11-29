package pieces;

public class Pawn extends Piece{

	private boolean isFirstMove = true;

	public Pawn(boolean isWhite, int[] position) {
		super(isWhite, position, isWhite ? "\u2659" : "\u265F");
		// TODO Auto-generated constructor stub
	}

	@Override 
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		if(isWhite()) {
			if(isFirstMove && position[1] == newPosition[1] && position[0] - 2 == newPosition[0] && pieceInNewPosition == null) {//2 forward steps if no piece in front
				isFirstMove = false;
				return true;
			}
			if(position[1] == newPosition[1] && position[0] - 1 == newPosition[0] && pieceInNewPosition == null) {//forward step if no piece in front
				isFirstMove = false;
				return true;
			}
			if((position[1]-1 == newPosition[1] || position[1]+1 == newPosition[1]) && position[0] -1 == newPosition[0] && pieceInNewPosition != null && !pieceInNewPosition.isWhite()) {//diagonal step if piece there is opposite colour.
				isFirstMove = false;
				return true;
			}
		}
		
		if(!isWhite()) {
			if(isFirstMove && position[1] == newPosition[1] && position[0] + 2 == newPosition[0] && pieceInNewPosition == null) {//2 forward steps if no piece in front
				isFirstMove = false;
				return true;
			}
			if(position[1] == newPosition[1] && position[0] + 1 == newPosition[0] && pieceInNewPosition == null) {//forward step if no piece in front
				isFirstMove = false;
				return true;
			}
			if((position[1] - 1 == newPosition[1] || position[1] + 1 == newPosition[1]) && position[0] + 1 == newPosition[0] && pieceInNewPosition != null && pieceInNewPosition.isWhite()) {//diagonal step if piece there is opposite colour.
				isFirstMove = false;
				return true;
			}
		}
		
		return false;
	}
}
