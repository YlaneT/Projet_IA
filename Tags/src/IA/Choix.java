package IA;

import othello.*;

public class Choix {
	private int [] position;
	private int valeur;
	private static OthelloGame game;
	
	/**
	 * Crée une instance de type choix représentant un endroit où l'IA peut jouer avec une valeur
	 * représentant l'utilité.
	 * @param r : ligne 
	 * @param c : colonne
	 */
	public Choix (int r, int c) {
		this.position = new int [2];
		this.position[0] = r;
		this.position[1] = c;
	}
	
	public int[] getPosition () {
		return position;
	}
	
	public int getValeur () {
		return valeur;
	}
	
	public void setValeur_IA1 (int nb_pions_avant) {
		int val = 0;
		int row = this.position[0];
		int col = this.position[1];
		Board board = (Board) game.getBoard().clone();
		
		
		boolean hasFlipped = false;
		Value opposite = (game.getTurn() == Value.BLACK ? Value.WHITE : Value.BLACK);
		Value next;
		
		if (board.cells[row][col].value == Value.BLANK) {
			
			// try to flip north direction
			if (row > 1 && board.cells[row - 1][col].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row;
				do {
					next = board.cells[--currentRow][col].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {

						for (int r = row; r > currentRow; r--)
							game.putDisc(r, col, game.getTurn());
					}
				} while (currentRow - 1 >= 0 && next != Value.BLANK);
			}
			
			// try to flip northeast direction
			if (row > 1 && col < game.getCOLS() - 2 && board.cells[row - 1][col + 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[--currentRow][++currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {

						for (int r = row, c = col; r > currentRow && c < currentCol; r--, c++)
							game.putDisc(r, c, game.getTurn());
					}
				} while (currentRow - 1 >= 0 && currentCol < game.getCOLS() - 1 && next != Value.BLANK);
			}
			
			// try to flip east direction
			if (col < game.getCOLS() - 2 && board.cells[row][col + 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentCol = col;
				do {
					next = board.cells[row][++currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {
						

						for (int c = col; c < currentCol; c++)
							game.putDisc(row, c, game.getTurn());
					}
				} while (currentCol < game.getCOLS() - 1 && next != Value.BLANK);
			}
			
			// try to flip southeast direction
			if (row < game.getROWS() - 2 && col < game.getCOLS() - 2 && board.cells[row + 1][col + 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[++currentRow][++currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {
						

						for (int r = row, c = col; r < currentRow && c < currentCol; r++, c++)
							game.putDisc(r, c, game.getTurn());
					}
				} while (currentRow < game.getROWS() - 1 && currentCol < game.getCOLS() - 1 && next != Value.BLANK);
			}
			
			// try to flip south direction
			if (row < game.getROWS() - 2 && board.cells[row + 1][col].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row;
				do {
					next = board.cells[++currentRow][col].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {


						for (int r = row; r < currentRow; r++)
							game.putDisc(r, col, game.getTurn());
					}
				} while (currentRow < game.getROWS() - 1 && next != Value.BLANK);
			}
			
			// try to flip southwest direction
			if (row < game.getROWS() - 2 && col > 1 && board.cells[row + 1][col - 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[++currentRow][--currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {


						for (int r = row, c = col; r < currentRow && c > currentCol; r++, c--)
							game.putDisc(r, c, game.getTurn());
					}
				} while (currentRow < game.getROWS() - 1 && currentCol > 0 && next != Value.BLANK);
			}
			
			// try to flip west direction
			if (col > 1 && board.cells[row][col - 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentCol = col;
				do {
					next = board.cells[row][--currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {


						for (int c = col; c > currentCol; c--)
							game.putDisc(row, c, game.getTurn());
					}
				} while (currentCol > 0 && next != Value.BLANK);
			}
			
			// try to flip northwest direction
			if (row > 1 && col > 1 && board.cells[row - 1][col - 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int currentRow = row, currentCol = col;
				do {
					next = board.cells[--currentRow][--currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == game.getTurn() && neighborIsOpposite) {
						

						for (int r = row, c = col; r > currentRow && c > currentCol; r--, c--)
							game.putDisc(r, c, game.getTurn());
					}
				} while (currentRow > 0 && currentCol > 0 && next != Value.BLANK);
			}
		}
		val = game.getBoard().countColor(game.getTurn()) - nb_pions_avant;
		this.valeur = val;
	}
	
	
	public static void setGame (OthelloGame partie) {
		game = partie;
	}
}
