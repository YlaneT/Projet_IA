import Interfaces.Interface_Jeu;

//TODO : Implémenter les méthodes

public class Jeu implements Interface_Jeu {

	public Jeu (Othello othello){

	}

	@Override
	public boolean estFini () {
		return false;
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

	}

	@Override
	public void remplirHistorique () {

	}
}
