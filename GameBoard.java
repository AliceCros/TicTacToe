package fr.alice.tictactoejava;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Alice
 *
 */

public class GameBoard {
	
	private char[][] gameBoard;
	private boolean gameOnGoing = true;
	
	/*
	 * Constructor for the GameBoard class
	 */
	public GameBoard() {
		gameBoard = new char[3][3];
		
		for (int row = 0; row < gameBoard.length; row++) {
			Arrays.fill(gameBoard[row], ' ');
			for (int col = 0; col < gameBoard.length; col++) {
				Arrays.fill(gameBoard[col], ' ');
			}
		}
	} // end of Constructor
	
	
	/*
	 * Method to display the GameBoard to the screen
	 */
	public void displayBoard() {
		
		System.out.print("\n-------------------------------------------------\n");
		
		for (int row = 0; row < gameBoard.length; row++) {
			System.out.print("|");
			for (int col = 0; col < gameBoard.length; col++) {
				System.out.print("\t" + gameBoard[row][col] + "\t");
				System.out.print("|");
			}
			System.out.print("\n-------------------------------------------------\n");
		}
	} // End of method displayBoard

	
	/*
	 * Method gameActive returns true if the game is still ongoing
	 */
	public boolean gameActive() {
		return gameOnGoing;
	}
	
	
	/*
	 * Method askPlayer asks the user to pick a row and column, checks if the move is valid
	 * and call the method makeMove()
	 */
	public void askPlayer(char player) {
		Scanner scan = new Scanner(System.in);
		int row, col;
		
		do {
			System.out.printf("Player %s, please enter a row (1-3): ", player);
			row = scan.nextInt();
			System.out.printf("Player %s, please enter a column (1-3): ", player);
			col = scan.nextInt();
		} while (notValid(row, col));
		
		makeMove(player, row - 1, col - 1);
	}
	
	/*
	 * Method notValid checks if the move is between 1 and 3
	 * and if the position is currently empty
	 * @return true if the move is not valid, false otherwise
	 */
	public boolean notValid(int row, int col) {
		if (row < 1 || row > 3 || !isEmpty(row, col))
			return true;
		if (col < 1 || col > 3 || !isEmpty(row, col))
			return true;
		return false;
	}
	
	/*
	 * Method makeMove checks if the player's move is valid (inside the board)
	 */
	public boolean makeMove(char player, int row, int col) {
		
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			
			if (gameBoard[row][col] != ' ') {
				return false;
			} else {
				gameBoard[row][col] = player;
				return true;
			}
			
		} else {
			return false;
		}
	}
	
	
	/*
	 * Method isEmpty checks if a position is empty
	 * @return true if the position is empty, false otherwise
	 */
	public boolean isEmpty(int row, int col) {
		if (gameBoard[row - 1][col - 1] == ' ')
			return true;
		else {
			System.out.println("That position is taken.");
			return false;
		}
	}
	
	
	/*
	 * Method checkForWinner checks if there are 3 X's or O's in a row
	 * @return true if there is a winner, false otherwise
	 */
	public boolean checkForWinner() {
		
		//loop over each row and check for a winner
		for (int row = 0; row < gameBoard.length; row++) {
			if (gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2] 
					&& gameBoard[row][0] != ' ') {
				System.out.println("The winner is " + gameBoard[row][0]);
				gameOnGoing = false;
			}
		}
		
		//loop over each column and check for a winner
		for (int col = 0; col < gameBoard.length; col++) {
			if (gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col] 
					&& gameBoard[0][col] != ' ') {
				System.out.println("The winner is " + gameBoard[0][col]);
				gameOnGoing = false;
			}
		}
		
		//check for diagonals
		if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] 
				&& gameBoard[0][0] != ' ') {
			System.out.println("The winner is " + gameBoard[0][0]);
			gameOnGoing = false;
		}
		if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2]
				&& gameBoard[2][0] != ' ') {
			System.out.println("The winner is " + gameBoard[2][0]);
			gameOnGoing = false;
		}
		
		return gameOnGoing;
		
	}
	
}
