import Interfaces.Interface_Othello;

//TODO : Implémenter les méthodes

public class Othello implements Interface_Othello {

	char[][] grille;

	public Othello () {
		grille = new char[8][8];
		for (int i=0 ; i<8 ; i++) {
			for (int j=0 ; j<8 ; j++){
				setSymbole(Interface_Othello.VIDE,i,j);
			}
		}
		setSymbole(Interface_Othello.O,3,3);
		setSymbole(Interface_Othello.O,4,4);
		setSymbole(Interface_Othello.X,3,4);
		setSymbole(Interface_Othello.X,4,3);
	}

	@Override
	public char getSymbole (int x, int y) {
		return this.grille[x][y];
	}

	@Override
	public void setSymbole (char symbole, int x, int y) {

	}

	@Override
	public boolean peutJouer (int x, int y) {
		return false;
	}

	@Override
	public boolean peutJouer () {
		return false;
	}

	@Override
	public boolean aGagne (char symbole) {
		return false;
	}

	@Override
	public boolean grillePleine () {
		for (int i=0 ; i<8 ; i++) {
			for ( int j = 0 ; j<8 ; j++) {
				if (getSymbole(i,j) == Interface_Othello.VIDE) {
					return false;
				}
			}
		}
		return true;
	}

}
