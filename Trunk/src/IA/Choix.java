package src.IA;

import src.othello.OthelloGame;

public class Choix {
	private int [] position;
	private int value;
	private static OthelloGame game;
	private OthelloGame game_copy;
	
	/**
	 * Crée une instance de type choix représentant un endroit où l'IA peut jouer avec une valeur
	 * représentant l'utilité.
	 * @param r : ligne 
	 * @param c : colonne
	 */
	public Choix (int r, int c, int numIA) {
		this.position = new int [2];
		this.position[0] = r;
		this.position[1] = c;
		this.game_copy = new OthelloGame(Choix.game);
		switch (numIA) {
			case 1 :
				this.setValue_IA1();
				break;
			case 2 :
				this.setValue_IA2();
				break;
			case 3 :
				this.setValue_IA3();
				break;
		}
		
	}
	
	public int[] getPosition () {
		return position;
	}
	
	public int getValue () {
		return value;
	}
	
	// Joue le pion puis compte le nombre de pions de sa couleur.
	public void setValue_IA1 () {
		int row = this.position[0];
		int col = this.position[1];
		
		this.game_copy.tryToFlip(row,col,false);
		
		this.value = game_copy.getBoard().countColor(game_copy.getTurn());
	}
	
	/* Joue le pion,
	   cherche les choix possibles du joueur adverse
	   calcule leur utilité en faisant 100-util_IA1
	 */
	public void setValue_IA2 () {
		int row = this.position[0];
		int col = this.position[1];
		
		this.game_copy.tryToFlip(row,col,false);
		
		this.value = game_copy.getBoard().countColor_IA2(game_copy.getTurn());
		
	}
	
	public void setValue_IA3 () {
	
	}
	
	
	public static void setGame (OthelloGame partie) {
		game = partie;
	}
	
	public String textPosition () {
		return "[" + (position[0]+1) + " , " + (position[1]+1) + "]";
	}
	
	public String toString () {
		return this.textPosition() + " : " + this.getValue();
	}
}
