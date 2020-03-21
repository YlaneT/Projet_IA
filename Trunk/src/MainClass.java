import Interfaces.Interface_Jeu;
import Interfaces.Interface_Othello;

public class MainClass {

	public static void main (String[]args) {
		Interface_Jeu jeu = new Jeu (new Othello());
		while (!jeu.estFini()) {
			char symbole = jeu.prochainJoueur();
			jeu.tourJoueur(symbole);
		}
		if (jeu.aGagne (Interface_Othello.O)) {
			jeu.feliciter(Interface_Othello.O);
			jeu.remplirHistorique();
		}
		else if (jeu.aGagne(Interface_Othello.X)){
			jeu.feliciter (Interface_Othello.X);
		}
		else {
			jeu.egalite();
		}
	}
}
