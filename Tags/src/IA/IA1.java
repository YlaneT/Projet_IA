package src.IA;

import java.util.ArrayList;
import src.othello.*;

/**
 * Cette classe contient une Intelligence artificielle de type
 * Agent focalisé sur l'utilité
 * qui choisit chacun de ses coups de la manière suivante :
 * Prendre un coin +5
 * N pions en plus par rapport au tour précédent +N
 */
import java.util.HashMap;

public class IA1 {

	private HashMap <String,Integer> choix_possibles;
	private int nb_pions_avant;
	private Value couleur;
	private OthelloGame game;
	
	public IA1 (Value couleur, OthelloGame game) {
		this.choix_possibles = new HashMap();
		this.nb_pions_avant = 2;
		this.couleur = couleur;
		this.game = game;
	}
	
	public void maj_IA1 (){
		Board board = game.getBoard();
		rchChoix();
		if (couleur == Value.BLACK) {
			this.nb_pions_avant = board.countBlacks() ;
		}
		else if (couleur == Value.WHITE) {
			this.nb_pions_avant = board.countWhites() ;
		}
	}
	
	private void rchChoix () {
		choix_possibles.clear();
		for (int r = 0; r < game.getROWS(); r++){
			for (int c = 0; c < game.getCOLS(); c++){
				Board board_copy = game.getBoard();
				//board_copy = board_copy.clone();
				/*
				 * TODO : clone ne fonctionne pas mais on en a besoin pour pouvoir
				 *  retourner les pions à chaque fois qu'un coup est valide et compter
				 *  ensuite le nombre de pions de la couleur appartenant à l'IA.
				 */
				if (board_copy.cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, false)) {
					int score = nb_pions_avant - board_copy.countColor(this.couleur);
					choix_possibles.put(r+","+c,score);
				}
			}
		}
	}
	
	/*public void valeurCoups () {
		// Récupérer row et col via choix_possibles.keySet()
		boolean dontFlip = true;
		
		boolean hasFlipped = false;
		Value opposite = (game.getTurn() == Value.BLACK ? Value.WHITE : Value.BLACK);
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int r = row; r > currentRow ; r--)
							putDisc(r, col, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r > currentRow && c < currentCol ; r--, c++)
							putDisc(r, c, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int c = col; c < currentCol; c++)
							putDisc(row, c, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r < currentRow && c < currentCol ; r++, c++)
							putDisc(r, c, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int r = row; r < currentRow; r++)
							putDisc(r, col, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r < currentRow && c > currentCol; r++, c--)
							putDisc(r, c, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int c = col; c > currentCol; c--)
							putDisc(row, c, game.getTurn());
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
					} else if (next == game.getTurn() && neighborIsOpposite) {
						if (dontFlip)
							return true;
						hasFlipped = true;
						for (int r = row, c = col; r > currentRow && c > currentCol; r--, c--)
							putDisc(r, c, game.getTurn());
					}
				} while (currentRow > 0 && currentCol > 0 && next != Value.BLANK);
			}
		}
		return hasFlipped;
	}
	
	/**
	 * Retourne le meilleur coup et privilégie les coins dans les cas d'égalités d'utilités
	 * @return le meilleur coup sous forme r,c
	 */
	public String meilleurCoup() {
		String r = "";
		String c = "";
		return r+","+c ;
	}
	
	public String jouerTour() {
		maj_IA1();
		rchChoix();
		String meilleur_coup = meilleurCoup();
		
		
		return meilleur_coup;
	}
	
	public Value getCouleur(){
		return this.couleur;
	}
}