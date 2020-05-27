package src.IA;

import src.othello.OthelloGame;
import java.util.ArrayList;

public class Choix_IA3 extends Choix{
	
	private int [] position;
	private int value;
	private static OthelloGame game;
	private OthelloGame game_copy;
	private ArrayList<Choix> enemy_choices;
	private boolean max_min;
	
	public Choix_IA3 (int r, int c, boolean min_or_max) {
		super();
		this.position = new int [2];
		this.position[0] = r;
		this.position[1] = c;
		this.game_copy = new OthelloGame(Choix_IA3.game);
		this.enemy_choices = new ArrayList<>();
		this.max_min = min_or_max;
		this.setValue_IA3();
	}
	
	public int[] getPosition () {
		return position;
	}
	
	public int getValue () {
		return value;
	}
	
	// Joue le pion puis compte le nombre de pions de sa couleur.

	
	/* Joue le pion,
	   cherche les choix possibles du joueur adverse
	   calcule leur utilité en faisant 100-util_IA1
	 */

	
	public void setValue_IA3 () {
		int row = this.position[0];
		int col = this.position[1];
		
		this.game_copy.tryToFlip(row,col,false);
		
		this.value = game_copy.getBoard().countColor_IA2(game_copy.getTurn());
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
