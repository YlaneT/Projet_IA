import Interfaces.Interface_Jeu;
import Interfaces.Interface_Othello;

import java.io.File;
import java.util.Scanner;


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
		afficherGrille();
		
		
		//test
		tourJoueur(joueurSuivant);
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
		else {
			System.out.println("		Tour du joueur:"+ symbole);
			plateau.compteur();
			plateau.coupPossible(adversaire(symbole));
			afficherGrille();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Choissisez un emplacement P");
			System.out.println("Abscisse : ");
			int y= sc.nextInt();
			System.out.println("Ordonnée : ");
			int x= sc.nextInt();
			
			plateau.setSymbole(symbole, x, y);
			plateau.changerSymbole(x, y, symbole, adversaire(symbole));
			plateau.enleverPossibilite();
			afficherGrille();
			
			if(symbole==Interface_Othello.O) {
				symbole=Interface_Othello.X;
			}
			else symbole=Interface_Othello.O;
			
			//////// test sans while ////////
			
			System.out.println("		Tour du joueur:"+ symbole);
			plateau.compteur();
			plateau.coupPossible(adversaire(symbole));
			afficherGrille();
			
			Scanner sca = new Scanner(System.in);
			System.out.println("Choissisez un emplacement P");
			System.out.println("Abscisse : ");
			int yy= sc.nextInt();
			System.out.println("Ordonnée : ");
			int xx= sc.nextInt();
			
			plateau.setSymbole(symbole, xx, yy);
			plateau.changerSymbole(xx, yy, symbole, adversaire(symbole));
			plateau.enleverPossibilite();
			afficherGrille();
			
			if(this.joueurSuivant==Interface_Othello.O) {
				this.joueurSuivant=Interface_Othello.X;
			}
			else this.joueurSuivant=Interface_Othello.O;
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
		System.out.println("Félicitation au joueur " + symbole);
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
	
	/**
	 * 
	 * @param symbole : joueur actuel
	 * @return symbole de l'opposant
	 */
	public char adversaire(char symbole) {
	
		if(symbole==Interface_Othello.O) {
			return Interface_Othello.X;
		}
		else return Interface_Othello.O;
	
	}
	
}