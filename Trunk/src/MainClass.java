import Interfaces.Interface_Jeu;
import Interfaces.Interface_Othello;

public class MainClass {

	public static void main (String[]args) {
		Jeu jeu = new Jeu (new Othello());
		/**while (!jeu.plateau.grillePleine()) {
			char symbole = jeu.prochainJoueur();
			jeu.tourJoueur(symbole);
		}
		*/


	}
}