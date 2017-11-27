package pieces;

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
	
	public boolean setPosition(int[] newPos) {
		if(isNewPositionValid(newPos)) {
			this.position = newPos;
			return true;
		}
		return false;
	}
	
	abstract boolean isNewPositionValid(int[] newPosition);
}
