package pieces;

public class Rook extends Piece{

	

	public Rook(boolean isWhite, int[] position) {
		super(isWhite, position,  isWhite ? "\u2656" : "\u265C");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isNewPositionValid(int[] newPosition, Piece[][] piecesBoard) {
		int[] position = getPosition();
		Piece pieceInNewPosition = piecesBoard[newPosition[0]][newPosition[1]];
		
		if(pieceInNewPosition != null && pieceInNewPosition.isWhite() == this.isWhite()) //Piece of same colour in new position
			return false;
		
		if((position[0] == newPosition[0] && Math.abs(position[1]-newPosition[1]) > 0 || Math.abs(position[0]-newPosition[0]) > 0 && position[1] == newPosition[1]) && isPathClear(position, newPosition, piecesBoard)) //Left, right and up, down movements
			return true;

		return false;
	}

	private boolean isPathClear(int[] position, int[] newPosition, Piece[][] piecesBoard) {
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
		
		else if(position[0] > newPosition[0] && position[1] == newPosition[1])//Up
			for(int i = 1; i < position[0]-newPosition[0]; i++) {
				if(piecesBoard[position[0]-i][position[1]] != null)
					return false;
			}
		
		else if(newPosition[0] > position[0] && position[1] == newPosition[1])//Down
			for(int i = 1; i < newPosition[0]-position[0]; i++) {
				if(piecesBoard[position[0]+i][position[1]] != null)
					return false;
			}
		return true;
	}

}
