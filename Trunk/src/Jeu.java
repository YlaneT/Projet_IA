import Interfaces.Interface_Jeu;
import Interfaces.Interface_Othello;

import java.io.File;

//TODO : Implémenter les méthodes

public class Jeu implements Interface_Jeu {

	Othello plateau;
	char joueurSuivant;
	int nbTour;

	public Jeu (Othello othello) {

		joueurSuivant = Interface_Othello.O;
		// Le premier joueur est le rond. Parce que.
		nbTour = 0;
		plateau = othello;
	}

	@Override
	public void deterGagnant () {
		int nbX = 0;
		int nbO = 0;
		for (int i=0 ; i<8 ; i++) {
			for (int j = 0; j < 8; j++) {
				if (plateau.getSymbole(i, j) == Interface_Othello.X) {
					nbX++;
				}
				else if (plateau.getSymbole(i, j) == Interface_Othello.O) {
					nbO++;
				}
			}
		}
		char gagnant;
		if (nbX > nbO) {
			gagnant = Interface_Othello.X;
		}
		else if (nbO > nbX) {
			gagnant = Interface_Othello.O;
		}
		else {
			gagnant = Interface_Othello.VIDE;
		}
		if (gagnant == Interface_Othello.VIDE) {
			egalite();
			remplirHistorique(gagnant);
		}
		else {
			feliciter(gagnant);
			remplirHistorique(gagnant);
		}
	}

	@Override
	public char prochainJoueur () {
		return joueurSuivant;
	}

	@Override
	public void tourJoueur (char symbole) {
		/* TODO : étapes du tour du joueur
		 * Etape 1 : Créer un jeu console interactif pour faire jouer 2 humains.
		 * Etape 2 : Créer une fonction IA qui choisit où placer le pion et
		 * 	l'appeler avec cette fct quand c'est le tour de L'IA
		 * Etape 3 (Opt.) : Au lancement d'une partie, choix J1vsJ2 / J1vsIA / IAvsIA
		 */


		if (nbTour >= 60 && plateau.grillePleine()) {
			this.deterGagnant(); // fixme : retourne un char
		}
	}

	@Override
	public void afficherGrille () {
		for (int i=0 ; i<8 ; i++) {
			System.out.print("| ");
			for (int j=0 ; j<8 ; j++) {
				System.out.print(plateau.getSymbole(i,j) + " | ");
			}
			System.out.println();
		}
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
		//File histo = new File("historique_parties.txt");
		/* TODO : Générer un historique dans un fichier.
		 *  but : avoir des statistiques du taux de victoire de l'IA
		 * Peut-être créer une classe Historique serait interressant
		 */
	}
}
