import Interfaces.Interface_Jeu;
import Interfaces.Interface_Othello;

//TODO : Implémenter les méthodes

public class Jeu implements Interface_Jeu {

	char joueurSuivant;
	int nbTour;

	public Jeu (Othello othello) {
		for (int i=0 ; i<8 ; i++) {
			for (int j=0 ; j<8 ; j++) {
				System.out.print("["+i+"]["+j+"] ");
			}
			System.out.println();
		}
		joueurSuivant = Interface_Othello.O;
		nbTour = 0;
	}

	@Override
	public boolean estFini () {
		return true;
	}

	@Override
	public char prochainJoueur () {
		return 0;
	}

	@Override
	public void tourJoueur (char symbole) {

	}

	@Override
	public boolean aGagne (char symbole) {
		return false;
	}

	@Override
	public void feliciter (char symbole) {

	}

	@Override
	public void egalite () {
		System.out.println("Egalité.");
		remplirHistorique(Interface_Othello.VIDE);
	}

	@Override
	public void remplirHistorique (char gagnant) {

	}
}
