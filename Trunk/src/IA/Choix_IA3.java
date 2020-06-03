package src.IA;

import src.othello.OthelloGame;
import src.othello.Value;

public class Choix_IA3 extends Choix {
	
	private boolean max_min;
	
	/**
	 * Crée une instance de type choix représentant un endroit où l'IA peut jouer avec une valeur représentant l'utilité.
	 *
	 * @param r          ligne
	 * @param c          colonne
	 * @param min_or_max true : max (couleur de l'ia) / false : tour de l'adversaire
	 */
	public Choix_IA3 (int r, int c, boolean min_or_max) {
		super();
		this.position = new int[2];
		this.position[0] = r;
		this.position[1] = c;
		this.game_copy = new OthelloGame(Choix_IA3.game);
		this.max_min = min_or_max;
		this.setValue_IA3();
	}
	
	/**
	 * Joue le pion puis calcule de manière récursive quel est le meilleur coup en fonction des possibilités de l'adversaire
	 */
	public void setValue_IA3 () {
		int row = this.position[0];
		int col = this.position[1];
		/* FIXME : Pas la boucle escomptée (seulement au premier tour)
		 *  Tous les choix ont une valeur égale à 0 à partir du 2è tour.
		 */
		this.game_copy.tryToFlip(row, col, false);
		game_copy.changeTurn();
		if (game_copy.canPlay()) {
			game_copy.create_IA3_for_MIN_MAX(!max_min);
			game_copy.makeMove();
		}
		Value ia_color;
		if (max_min) {
			ia_color = game_copy.getTurn();
		}
		else {
			ia_color = game_copy.getOtherTurn();
		}
		game_copy.getBoard().countColor_IA2(ia_color);
	}
}
