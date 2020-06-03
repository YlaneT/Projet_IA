package src.IA;

import src.othello.OthelloGame;

public class Choix {
	
	protected static OthelloGame game;
	protected int value;
	protected int[] position;
	protected OthelloGame game_copy;
	
	
	/**
	 * Crée une instance de type choix représentant un endroit où l'IA peut jouer avec une valeur représentant l'utilité.
	 *
	 * @param r : ligne
	 * @param c : colonne
	 */
	public Choix (int r, int c, int numIA) {
		this.position = new int[2];
		this.position[0] = r;
		this.position[1] = c;
		this.game_copy = new OthelloGame(Choix.game);
		switch (numIA) {
			case 1:
				this.setValue_IA1();
				break;
			case 2:
				this.setValue_IA2();
				break;
			default:
				System.err.println("Constructeur Choix\nParamètre incorrect : numIA");
				break;
		}
	}
	
	public Choix () {}
	
	/**
	 * Détermine la partie à copier dans chaque choix
	 *
	 * @param partie à copier
	 */
	public static void setGame (OthelloGame partie) {
		game = partie;
	}
	
	/**
	 * 	Joue le pion puis compte le nombre de pions de sa couleur.
 	 */
	public void setValue_IA1 () {
		int row = this.position[0];
		int col = this.position[1];
		
		this.game_copy.tryToFlip(row, col, false);
		
		this.value = game_copy.getBoard().countColor(game_copy.getTurn());
	}
	
	/**
	 *  Joue le pion puis détermine la valeur du coup
	 *  1 pion = 1 point
	 *  1 pion sur un bord = 2 points
	 *  1 pion dans un coin =  3 points
	 */
	public void setValue_IA2 () {
		int row = this.position[0];
		int col = this.position[1];
		
		this.game_copy.tryToFlip(row, col, false);
		
		this.value = game_copy.getBoard().countColor_IA2(game_copy.getTurn());
	}
	
	// Deux fonctions permettant l'affichage d'un choix d'une manière compréhensible pour l'utilisateur
	public String textPosition () {
		return "[" + (position[0] + 1) + " , " + (position[1] + 1) + "]";
	}
	public String toString () {
		return this.textPosition() + " : " + this.getValue();
	}
	
	public int[] getPosition () {
		return position;
	}
	
	public int getValue () {
		return value;
	}
	
}
