package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.*;
import pieces.Board;

/**
 * Main class that executes the whole program.
 * @author A. Martínez
 * @version 1.0 05/12/2017
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(" #########");
		System.out.println("## CHESS ##");
		System.out.println(" #########");
		System.out.println("\nPress ENTER to play the game.");
		readLine();
		
		Board board = new Board();
		
		while(true) {
			System.out.println("Write movement (i.e.: b1c3):");
			String str = readLine();
			try {
				board.movePiece(str);
			} catch (NoPieceException | InvalidMovementException | NotYourTurnException | InvalidCommandException e) {
				System.out.println(e.getMessage());
			} catch (CheckmateException e) {
				System.out.println(e.getMessage());
				break;	
			}
			
			board.print();
		}
	}
	
	
	/**
	 * Reads the line written by the user.
	 * @return String written by the user.
	 */
	public static String readLine() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
