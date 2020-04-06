package src.IA;

import java.util.ArrayList;
import src.othello.*;

/**
 * Cette classe contient une Intelligence artificielle de type
 * Agent focalisé sur l'utilité
 * qui choisit chacun de ses coups de la manière suivante :
 * Prendre un coin +5
 * N pions en plus par rapport au tour précédent +N
 */
import java.util.HashMap;

public class IA1 {

	private HashMap <String,Integer> choix_possibles;
	private int nb_pions_avant;
	private Value couleur;
	private OthelloGame game;
	
	public IA1 (Value couleur, Board board) {
		this.choix_possibles = new HashMap();
		this.nb_pions_avant = 2;
		this.couleur = couleur;
		this.game = game;
	}
	
	public void maj_IA1 (){
		Board board = game.getBoard();
		rchChoix();
		if (couleur == Value.BLACK) {
			this.nb_pions_avant = board.countBlacks() ;
		}
		else if (couleur == Value.WHITE) {
			this.nb_pions_avant = board.countWhites() ;
		}
	}
	
	private void rchChoix () {
		choix_possibles.clear();
		for (int r = 0; r < game.getROWS(); r++){
			for (int c = 0; c < game.getCOLS(); c++){
				if (game.getBoard().cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					choix_possibles.put(r+","+c,0);
				}
			}
		}
	}
	
	public void valeurCoups () {
		//this.choix_possibles.set
	}
	
	public String jouerTour() {
		String meilleur_coup = "";
		
		
		return meilleur_coup;
	}
	
	public Value getCouleur(){
		return this.couleur;
	}
}