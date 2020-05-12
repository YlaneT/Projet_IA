package IA;

import java.util.ArrayList;
import othello.*;

/**
 * Cette classe contient une Intelligence artificielle de type
 * Agent focalisé sur l'utilité
 * qui choisit chacun de ses coups de la manière suivante :
 * Prendre un coin +5
 * N pions en plus par rapport au tour précédent +N
 */
public class IA1 {

	private ArrayList <Choix> choices_available;
	private Value ia_color;
	private OthelloGame game;
	
	public IA1 (Value ia_color, OthelloGame game) {
		this.choices_available = new ArrayList<>();
		this.ia_color = ia_color;
		this.game = game;
		Choix.setGame(game);
	}
	
	public void maj_IA1 (){
		choices_available.clear();
	}
	
	private void research_Choices () {
		for (int r = 0 ; r < game.getROWS(); r++){
			for (int c = 0 ; c < game.getCOLS(); c++){
				if (game.getBoard().cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					choices_available.add(new Choix(r, c, game.getBoard()));
				}
			}
		}
	}
	
	private void show_choices () {
		System.out.println("Choix possibles :");
		for (Choix c : choices_available) {
			System.out.println(c.textPosition() + " : " + c.getValue());
		}
	}
	
	/**
	 * Retourne le meilleur coup et privilégie les coins dans les cas d'égalités d'utilités
	 * @return le meilleur coup sous forme r,c
	 */
	public Choix best_move () {
		int max = 0 ;
		ArrayList<Integer> start_end = new ArrayList<Integer>();
		start_end.add(0);
		start_end.add(game.getROWS()-1);
		if (choices_available.size() == 1) {
			return choices_available.get(0);
		}
		
		for (int i = 0; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() > max) {
				max = choices_available.get(i).getValue();
			}
		}
		for (int i = 0; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() == max && start_end.contains(choices_available.get(i).getPosition()[0])) {
				if (start_end.contains(choices_available.get(i).getPosition()[1])) {
					return choices_available.get(i);
				}
			}
		}
		return choices_available.get(0); // fixme : on enlève plus les éléments dont la val =/= max
	}
	
	public int [] playTurn () {
		maj_IA1();
		research_Choices();
		show_choices();
		return best_move().getPosition();
	}
	
	public Value getIa_color (){
		return this.ia_color;
	}
	
}