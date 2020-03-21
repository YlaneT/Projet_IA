import Interfaces.Interface_Othello;

//TODO : Implémenter les méthodes

public class Othello implements Interface_Othello {

	@Override
	public char getSymbole (int x, int y) {
		return 0;
	}

	@Override
	public void setSymbole (char symbole, int x, int y) {

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
		return false;
	}
}
