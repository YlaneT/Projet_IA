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

	private ArrayList <Choix> choix_possibles;
	private int nb_pions_avant;
	private Value couleur;
	private OthelloGame game;
	
	public IA1 (Value couleur, OthelloGame game) {
		this.choix_possibles = new ArrayList<>();
		this.nb_pions_avant = 2;
		this.couleur = couleur;
		this.game = game;
		Choix.setGame(game);
	}
	
	public void maj_IA1 (){
		Choix.setGame(game);
		rchChoix();
		this.nb_pions_avant = game.getBoard().countColor(this.couleur);
	}
	
	private void rchChoix () {
		choix_possibles.clear();
		for (int r = 0; r < game.getROWS(); r++){
			for (int c = 0; c < game.getCOLS(); c++){
				Board board_copy = (Board) game.getBoard().clone();
				if (board_copy.cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					Choix choix = new Choix(r, c);
					choix.setValeur_IA1(this.nb_pions_avant);
					choix_possibles.add(choix);
				}
			}
		}
	}
	
	/**/
	
	/**
	 * Retourne le meilleur coup et privilégie les coins dans les cas d'égalités d'utilités
	 * @return le meilleur coup sous forme r,c
	 */
	public Choix meilleurCoup() {
		int max = 0 ;
		ArrayList<Integer> start_end = new ArrayList<Integer>();
		start_end.add(1);
		start_end.add(game.getROWS());
		
		for (Choix choix : choix_possibles) {
			if (choix.getValeur() < max) {
				choix_possibles.remove(choix);
			}
			else if (choix.getValeur() > max) {
				max = choix.getValeur();
			}
		}
		if (choix_possibles.size() == 1) {
			Choix choix = choix_possibles.get(0);
			return choix;
		}
		else {
			for (Choix choix : choix_possibles) {
				if (choix.getValeur() < max) {
					choix_possibles.remove(choix);
				}
				else if (start_end.contains(choix.getPosition()[0])) {
					if (start_end.contains(choix.getPosition()[1])) {
						return choix;
					}
					
				}
			}
		}
		System.out.println(choix_possibles.get(0));
		return choix_possibles.get(0);
	}
	
	public int [] jouerTour() {
		maj_IA1();
		rchChoix();
		return meilleurCoup().getPosition();
	}
	
	public Value getCouleur(){
		return this.couleur;
	}
	
}