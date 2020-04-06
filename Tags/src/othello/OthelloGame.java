package src.othello;

import java.util.StringTokenizer;
import java.util.Scanner;
import src.IA.*;

public class OthelloGame {
	private Board board;
	private GameState state;
	private Value turn;
	private IA1 ia;
	
	private int ROWS;
	private int COLS;

	private static Scanner in = new Scanner(System.in);
	
	
	/**
	 * Crée une nouvelle partie de Othello en préparant le plateau de jeu
	 * classic : si true, plateau de 8x8 et le premier joueur est BLACK,
	 *  sinon, on peut choisir la taille du carré et le joueur qui commence
	 */
	public OthelloGame() {
		boolean classic = true;
		if (classic){
			ROWS = 8;
			COLS = 8;
			turn = Value.BLACK;
		}
		else {
			setRowsAndColumns();
			setStartingPlayer();
		}
		board = new Board(ROWS, COLS);
		setBicoloredSquare();
		state = GameState.IN_PROGRESS;
		
		//Initialisation de l'IA
		ia = new IA1(Value.BLACK, board);
	}
	
	
	public void setBicoloredSquare(){
		board.cells[(ROWS/2)-1][(COLS/2)-1].set(Value.BLACK);
		board.cells[ROWS/2][COLS/2].set(Value.BLACK);
		board.cells[(ROWS/2)-1][COLS/2].set(Value.WHITE);
		board.cells[ROWS/2][(COLS/2)-1].set(Value.WHITE);
	}
	
	
	public void playGame() {
		do {
			board.draw();
			if (!canPlay()) {
				if (board.fullGrid()){
					determineWinner();
				}
				else {
					System.out.println(turn + " cannot make a move!");
					changeTurn();
					// Cas très rare où la partie n'est pas finie mais aucun joueur ne peut jouer
					if (!canPlay()){
						determineWinner();
					}
					System.out.println("It is " + turn + "'s turn again.");
				}
			}
			else {
				System.out.format("%s's turn.\n", turn);
			}
			makeMove();		
			changeTurn();
		} while (state == GameState.IN_PROGRESS);
	}
	
	
	public void rejouer() {
		String sc = in.next().substring(0,1);
		if (sc.equalsIgnoreCase("y")) {
			new OthelloGame();
		}
	}
	
	
	public void determineWinner() {
		if (board.countBlacks() > board.countWhites()) {
			state = GameState.BLACK_WIN;
			System.out.println("Black wins!");
		} else if (board.countBlacks() < board.countWhites()) {
			state = GameState.WHITE_WIN;
			System.out.println("White wins!");
		} else {
			state = GameState.DRAW;
			System.out.println("It's a draw!");
		}
		rejouer();
		// TODO : Créer un log pour faire des statistiques
	}
	
	
	public void makeMove() {
		boolean isValidInput = false;
		
		if (turn == ia.getCouleur()) {
			ia.maj_IA1();
			String move = ia.jouerTour();
			StringTokenizer st = new StringTokenizer(move,",");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
		}
		
		else {
			while (!isValidInput) {
				int row = getBoundedNumber("Row", 1, ROWS);
				int col = getBoundedNumber("Column", 1, COLS);
				
				if (tryToFlip(row, col, false)) isValidInput = true;
				else System.out.println("Illegal move.");
			}
		}
	}
	
	// determines if the current player can make a move somewhere.
	public boolean canPlay() {
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++)
				if (board.cells[r][c].value == Value.BLANK && tryToFlip(r, c, true))
					return true;
		return false;
	}
	
	
	public void putDisc(int row, int col, Value val) {
		board.cells[row][col].set(val);
	}
	
	
	public void changeTurn() {
		turn = (turn == Value.BLACK ? Value.WHITE : Value.BLACK);
	}
	
	
	public boolean tryToFlip(int row, int col, boolean dontFlip) {
		boolean hasFlipped = false;
		Value opposite = (turn == Value.BLACK ? Value.WHITE : Value.BLACK);
		Value next;
		
		if (board.cells[row][col].value == Value.BLANK) {
			
			// try to flip north direction
			if (row > 1 && board.cells[row-1][col].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row;
				do {
					next = board.cells[--currentRow][col].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) {
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int r = row; r > currentRow ; r--)  
							putDisc(r, col, turn);
					} 
				} while (currentRow-1 >= 0 && next != Value.BLANK);
			}
			
			// try to flip northeast direction
			if (row > 1 && col < COLS-2 && board.cells[row-1][col+1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[--currentRow][++currentCol].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r > currentRow && c < currentCol ; r--, c++)  
							putDisc(r, c, turn);
					}
				} while (currentRow-1 >= 0 && currentCol < COLS-1 && next != Value.BLANK);
			}
			
			// try to flip east direction
			if (col < COLS-2 && board.cells[row][col+1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentCol = col;
				do {
					next = board.cells[row][++currentCol].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int c = col; c < currentCol; c++)  
							putDisc(row, c, turn);
					}
				} while (currentCol < COLS-1 && next != Value.BLANK);
			}
			
			// try to flip southeast direction
			if (row < ROWS-2 && col < COLS-2 && board.cells[row+1][col+1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[++currentRow][++currentCol].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r < currentRow && c < currentCol ; r++, c++)  
							putDisc(r, c, turn);
					}
				} while (currentRow < ROWS-1 && currentCol < COLS-1 && next != Value.BLANK);
			}
			
			// try to flip south direction
			if (row < ROWS-2 && board.cells[row+1][col].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row;
				do {
					next = board.cells[++currentRow][col].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int r = row; r < currentRow; r++)  
							putDisc(r, col, turn);
					}
				} while (currentRow < ROWS-1 && next != Value.BLANK);
			}
			
			// try to flip southwest direction
			if (row < ROWS-2 && col > 1 && board.cells[row+1][col-1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[++currentRow][--currentCol].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r < currentRow && c > currentCol; r++, c--) 
							putDisc(r, c, turn);
					}
				} while (currentRow < ROWS-1 && currentCol > 0 && next != Value.BLANK);
			}
			
			// try to flip west direction
			if (col > 1 && board.cells[row][col-1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentCol = col;
				do {
					next = board.cells[row][--currentCol].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int c = col; c > currentCol; c--) 
							putDisc(row, c, turn);
					}
				} while (currentCol > 0 && next != Value.BLANK);
			}
			
			// try to flip northwest direction
			if (row > 1 && col > 1 && board.cells[row-1][col-1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[--currentRow][--currentCol].value;
					if (next == opposite) { 
						neighborIsOpposite= true;
					} else if (next == turn && neighborIsOpposite) { 
						if (dontFlip)	
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r > currentRow && c > currentCol; r--, c--) 
							putDisc(r, c, turn);
					}
				} while (currentRow > 0 && currentCol > 0 && next != Value.BLANK);
			}
		}
		return hasFlipped;
	}
	
	
	public int getBoundedNumber(String what, int min, int max) {
		boolean isValid = false;
		
		do {
			System.out.print(what + ": ");
			int num = in.nextInt() - 1;
			if (num+1 < min || num+1 > max)
				System.out.format("Invalid input. %s must be between 1 and %d inclusive.\n", what, max);
			else
				return num;
		
		} while (!isValid);
		return -1; // never reached
	}
	
	
	public void setStartingPlayer() {
		boolean isValidInput = false;
		System.out.print("Who starts? ");
		while (!isValidInput) {
			String start = in.next();
			if (start.equalsIgnoreCase("b") || start.equalsIgnoreCase("w")) {
				isValidInput = true;
				turn = (start.equalsIgnoreCase("b") ? Value.BLACK : Value.WHITE);
			} else {
				System.out.println("Please type 'b' or 'w'.");
			}
		}
	}
	
	
	public void setRowsAndColumns() {
		boolean isValidInput = false;
		while (!isValidInput) {
			System.out.print("How many rows and columns  ? ");
			int choice = in.nextInt();
			if (choice%2 == 0) {
				isValidInput = true;
				ROWS = choice;
				COLS = choice;
			} else {
				System.out.println("Must be an even integer.");
			}
		}
	}
	
	
	public Board getBoard () {
		return board;
	}
	public int getROWS () {
		return ROWS;
	}
	public int getCOLS () {
		return COLS;
	}
}
