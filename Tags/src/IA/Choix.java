package IA;

import othello.*;

public class Choix {
	private int [] position;
	private int valeur;
	private static OthelloGame game;
	private OthelloGame game_copy;
	
	/**
	 * Crée une instance de type choix représentant un endroit où l'IA peut jouer avec une valeur
	 * représentant l'utilité.
	 * @param r : ligne 
	 * @param c : colonne
	 */
	public Choix (int r, int c, int nb_pions_avant) {
		this.position = new int [2];
		this.position[0] = r;
		this.position[1] = c;
		this.game_copy = new OthelloGame(Choix.game);
		this.setValeur_IA1(nb_pions_avant);
	}
	
	public int[] getPosition () {
		return position;
	}
	
	public int getValeur () {
		return valeur;
	}
	
	public void setValeur_IA1 (int nb_pions_avant) {
		int val;
		int row = this.position[0];
		int col = this.position[1];
		
		System.out.println("game :\n"+game);
		System.out.println(this.game_copy);
		System.out.println("\nboard :\n"+game.getBoard());
		System.out.println(this.game_copy.getBoard());
		
		this.game_copy.tryToFlip(row,col,false);
		
		val = game_copy.getBoard().countColor(game_copy.getTurn()) - nb_pions_avant;
		
		
		
		// TEST
		System.out.println("_______________\nAffichage de la copie");
		game_copy.getBoard().draw();
		System.out.println("_______________");
		
		this.valeur = val;
	}
	
	
	public static void setGame (OthelloGame partie) {
		game = partie;
	}
}
