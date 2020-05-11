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
		choix_possibles.clear();
		this.nb_pions_avant = game.getBoard().getColor(this.couleur);
		rchChoix();
	}
	
	private void rchChoix () {
		for (int r = 0 ; r < game.getROWS(); r++){
			for (int c = 0 ; c < game.getCOLS(); c++){
				if (game.getBoard().cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					choix_possibles.add(new Choix(r, c, this.nb_pions_avant));
					// TEST
					System.out.println("+++++++++++++++\nAffichage du vrai plateau");
					game.getBoard().draw();
					System.out.println("+++++++++++++++");
				}
			}
		}
	}
	
	
	/**
	 * Retourne le meilleur coup et privilégie les coins dans les cas d'égalités d'utilités
	 * @return le meilleur coup sous forme r,c
	 */
	public Choix meilleurCoup() {
		int max = 0 ;
		ArrayList<Integer> start_end = new ArrayList<Integer>();
		start_end.add(0);
		start_end.add(game.getROWS()-1);
		if (choix_possibles.size() == 1) {
			return choix_possibles.get(0);
		}
		
		for (int i = 0 ; i < choix_possibles.size() ; i++) {
			if (choix_possibles.get(i).getValeur() > max) {
				max = choix_possibles.get(i).getValeur();
			}
		}
		for (int i = 0 ; i < choix_possibles.size() ; i++) {
			if (choix_possibles.get(i).getValeur() == max && start_end.contains(choix_possibles.get(i).getPosition()[0])) {
				if (start_end.contains(choix_possibles.get(i).getPosition()[1])) {
					return choix_possibles.get(i);
				}
			}
		}
		return choix_possibles.get(0); // fixme : on enlève plus les éléments dont la val =/= max
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