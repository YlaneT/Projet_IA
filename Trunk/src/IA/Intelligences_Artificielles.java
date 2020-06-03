package src.IA;

import src.othello.OthelloGame;
import java.util.ArrayList;
import src.othello.Value;
import java.util.Random;

/**
 * Cette classe abstraite contient une Intelligence artificielle de type Agent focalisé sur l'utilité.
 * Elle est la base sur laquelle reposent toutes les IA.
 */
public abstract class Intelligences_Artificielles {
	
	protected ArrayList<Choix> choices_available;
	protected Value ia_color;
	protected OthelloGame game;
	
	public Intelligences_Artificielles (Value ia_color, OthelloGame game) {
		this.choices_available = new ArrayList<>();
		this.ia_color = ia_color;
		this.game = game;
		Choix.setGame(game);
		Choix_IA3.setGame(game);
	}
	
	/**
	 * Efface les choix possibles créés au tour précédent.
	 */
	public void maj_IA () {
		choices_available.clear();
	}
	
	protected abstract void rsch_choices ();
	
	/**
	 * Affiche les différents choix possibles à l'écran sous la forme
	 * "[row , col] : valeur"
	 */
	protected void show_choices () {
		System.out.println("Choix possibles :");
		for (Choix c: choices_available) {
			System.out.println(c);
		}
	}
	
	/**
	 * Retourne le meilleur coup et privilégie les coins dans les cas d'égalités d'utilités
	 *
	 * @return le meilleur coup sous forme r,c
	 */
	public Choix best_move () {
		if (choices_available.size() == 1) {
			return choices_available.get(0);
		}
		
		int                max       = 0;
		ArrayList<Integer> start_end = new ArrayList<>();
		start_end.add(0);
		start_end.add(game.getROWS() - 1);
		
		for (int i = 0 ; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() >= max) {
				max = choices_available.get(i).getValue();
			}
			else {
				choices_available.remove(i);
				i--;
			}
		}
		
		for (int i = 0 ; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() == max) {
				if (start_end.contains(choices_available.get(i).getPosition()[0])) {
					if (start_end.contains(choices_available.get(i).getPosition()[1])) {
						return choices_available.get(i);
					}
				}
			}
			else {
				choices_available.remove(i);
				i--;
			}
		}
		int rand = new Random().nextInt(choices_available.size());
		return choices_available.get(rand);
	}
	
	/**
	 * Déroulement du tour.
	 *
	 * @return renvoie la position à laquelle l'IA décide de jouer à OthelloGame.
	 */
	public int[] playTurn () {
		maj_IA();
		rsch_choices();
		show_choices();
		Choix best = best_move();
		System.out.println("Meilleur coup : " + best);
		return best.getPosition();
	}
	
	public Value getIa_color () {
		return this.ia_color;
	}
}