package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.InvalidMovementException;
import exceptions.NoPieceException;
import exceptions.NotYourTurnException;
import exceptions.PositionOutOfTheBoardException;
import pieces.Board;

public class Main {

	public static void main(String[] args) {
		System.out.println("#########");
		System.out.println("# CHESS #");
		System.out.println("#########");
		System.out.println("\nPress ENTER to play the game.");
		//readLine();
		
		Board board = new Board();
		
		while(true) {
			System.out.println("Write movement (i.e.: b1c3):");
			String str = readLine();
			try {
				board.movePiece(str);
			} catch (PositionOutOfTheBoardException | NoPieceException | InvalidMovementException | NotYourTurnException e) {
				System.out.println(e.getMessage());
			}
			board.print();
		}
	}
	
	
	
	private static String readLine() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
