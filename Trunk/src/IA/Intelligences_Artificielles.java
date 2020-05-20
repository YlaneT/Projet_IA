package src.IA;

import src.othello.OthelloGame;
import src.othello.Value;


import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe contient une Intelligence artificielle de type
 * Agent focalisé sur l'utilité
 * qui choisit chacun de ses coups de la manière suivante :
 * Prendre un coin +5
 * N pions en plus par rapport au tour précédent +N
 */
public abstract class Intelligences_Artificielles {

	protected ArrayList <Choix> choices_available;
	protected Value ia_color;
	protected OthelloGame game;
	
	public Intelligences_Artificielles (Value ia_color, OthelloGame game) {
		this.choices_available = new ArrayList<>();
		this.ia_color = ia_color;
		this.game = game;
		Choix.setGame(game);
	}
	
	public void maj_IA (){
		choices_available.clear();
	}
	
	protected abstract void rsch_choices ();
	
	
	private void show_choices () {
		System.out.println("Choix possibles :");
		for (Choix c : choices_available) {
			System.out.println(c);
		}
	}
	
	/**
	 * Retourne le meilleur coup et privilégie les coins dans les cas d'égalités d'utilités
	 * @return le meilleur coup sous forme r,c
	 */
	public Choix best_move () {
		if (choices_available.size() == 1) {
			return choices_available.get(0);
		}
		
		int max = 0 ;
		ArrayList<Integer> start_end = new ArrayList<Integer>();
		start_end.add(0);
		start_end.add(game.getROWS()-1);
		
		for (int i = 0; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() >= max) {
				max = choices_available.get(i).getValue();
			}
			else {
				choices_available.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() == max ){
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
	
	public int [] playTurn () {
		maj_IA();
		rsch_choices();
		show_choices();
		Choix best = best_move();
		System.out.println("Meilleur coup : " + best);
		return best.getPosition();
	}
	
	public Value getIa_color (){
		return this.ia_color;
	}
	
}