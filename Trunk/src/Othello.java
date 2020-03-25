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
		this.grille[x][y]=symbole;
	}

	@Override
	public boolean peutJouer (int x, int y) {
		if(grille[x][y]==Interface_Othello.VIDE) {
			return true;
		}
		else return false;
	}

	@Override
	public boolean peutJouer () {
		return true;
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
	
	/////////////////////////////
	
	/**
	 * Compte le nombre de changement possible pour la ligne du haut
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de changement pour la ligne du haut 
	 */
	public int coupHaut(int x,int y, char symbole) {
		int changement = 0;
		if(x>1) {
			for(int i=x-1;grille[i][y]==symbole;i--) {
				changement++;
				x--;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x-1][y]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}
	
	/**
	 * Compte le nombre de changement possible pour la ligne du bas
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de changement pour la ligne du bas 
	 */
	public int coupBas(int x,int y, char symbole) {
		
		int changement = 0;
		if(x<6) {
			for(int i=x+1;grille[i][y]==symbole;i++) {
				changement++;
				x++;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x+1][y]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}

	/**
	 * Compte le nombre de changement possible pour la ligne de droite
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de changement pour la ligne de droite 
	 */
	public int coupDroite(int x,int y, char symbole) {
		
		int changement = 0;
		if(y<6) {
			for(int i=y+1;grille[x][i]==symbole;i++) {
				changement++;
				y++;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x][y+1]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}

	/**
	 * Compte le nombre de changement possible pour la ligne de gauche
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de changement pour la ligne de gauche 
	 */
	public int coupGauche(int x,int y, char symbole) {
		
		int changement = 0;
			if(y>1) {
			for(int i=y-1;grille[x][i]==symbole;i++) {
				changement++;
				y--;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x][y-1]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}

	/**
	 * Compte le nombre de changement possible pour la diagonale bas-droite
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de changement pour la diagonale en bas à droite 
	 */
	public int coupBasDroite(int x,int y, char symbole) {
		
		int changement = 0;
		if(x<6&&y<6) {
			for(int i=x+1, j=y+1;grille[i][j]==symbole;i++,j++) {
				changement++;
				y++;
				x++;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x+1][y+1]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}
	
	/**
	 * Compte le nombre de changement possible pour la diagonale bas-gauche
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return  le nombre de changement pour la diagonale en bas à gauche 
	 */
	public int coupBasGauche(int x,int y, char symbole) {
		
		int changement = 0;
		if(x<6&&y>1) {
			for(int i=x+1, j=y-1;grille[i][j]==symbole;i++,j--) {
				changement++;
				y--;
				x++;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x+1][y-1]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}

	/**
	 * Compte le nombre de changement possible pour la diagonale haut-gauche
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return  le nombre de changement pour la diagonale en haut à gauche
	 */
	public int coupHautGauche(int x,int y, char symbole) {
		
		int changement = 0;
		if(x>1&&y>1) {
			for(int i=x-1, j=y-1;grille[i][j]==symbole;i--,j--) {
				changement++;
				y--;
				x--;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x-1][y-1]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}
	
	/**
	 * Compte le nombre de changement possible pour la diagonale haut-droite
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de changement pour la diagonale en haut à droite 
	 */
	public int coupHautDroite(int x,int y, char symbole) {
		
		int changement = 0;
		if(x>1&&y<6) {
			for(int i=x-1, j=y+1;grille[i][j]==symbole;i--,j++) {
				changement++;
				y++;
				x--;
			}
		}
		if(changement==0) {
			return changement;
		}
		else if(grille[x-1][y+1]==Interface_Othello.VIDE) {
			return 0;
		}
		else {
			return changement;
		}
	}
	
	/**
	 * 
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole: symbole à changer
	 * @return le nombre de coup par position
	 */
	public int compteurCoup(int x,int y, char symbole) {
		return coupBas(x,y,symbole)+coupHaut(x,y,symbole)+coupGauche(x,y,symbole)+coupDroite(x,y,symbole)+coupHautGauche(x,y,symbole)+coupHautDroite(x,y,symbole)+coupBasGauche(x,y,symbole)+coupBasDroite(x,y,symbole);
	}

	/**
	 * @param symbole: symbole à changer
	 * @return nombre de coup dans la grille
	 */
	public void coupPossible(char symbole) {
		
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if((compteurCoup(i,j,symbole)!=0)&&(peutJouer(i,j))) {
					setSymbole(Interface_Othello.POSSIBLE,i,j);
				}
			}
		}
		
	}
	
	/**
	 * reset la grille de toutes les possibilites
	 */
	public void enleverPossibilite() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(grille[i][j]==Interface_Othello.POSSIBLE) {
					grille[i][j]=Interface_Othello.VIDE;
				}
			}
		}
	}
	/**
	 * Affiche le nombre de symbole dans la grille
	 */
	public void  compteur() {
		int nbrX=0;
		int nbrO=0;
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(grille[i][j]==Interface_Othello.O) {
					nbrO++;
				}
				if(grille[i][j]==Interface_Othello.X) {
					nbrX++;
				}
			}
		}
		System.out.println("Score: "+ nbrO+" O et "+nbrX+" X" );
	}
	
	/**
	 * Change les symboles entre le pion  et sa première occurence dans toutes les directions
	 * 
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @param symbole : joueur actuel
	 * @param joueurSuivant : joueur adverse
	 */
	public void changerSymbole(int x,int y,char symbole,char joueurSuivant) {
		if(coupHaut(x,y,joueurSuivant)>0) {
			while (grille[x-1][y]!=symbole) {
				grille[x-1][y]=symbole;
				x--;
			}
		}
		if(coupBas(x,y,joueurSuivant)>0) {
			while (grille[x+1][y]!=symbole) {
				grille[x+1][y]=symbole;
				x++;
			}
		}
		if(coupDroite(x,y,joueurSuivant)>0) {
			while (grille[x][y+1]!=symbole) {
				grille[x][y+1]=symbole;
				y++;
			}
		}
		if(coupGauche(x,y,joueurSuivant)>0) {
			while (grille[x][y-1]!=symbole) {
				grille[x][y-1]=symbole;
				y--;
			}
		}
		if(coupHautGauche(x,y,joueurSuivant)>0) {
			while (grille[x-1][y-1]!=symbole) {
				grille[x-1][y-1]=symbole;
				x--;
				y--;
			}
		}
		if(coupHautDroite(x,y,joueurSuivant)>0) {
			while (grille[x-1][y+1]!=symbole) {
				grille[x-1][y+1]=symbole;
				x--;
				y++;
			}
		}
		if(coupBasGauche(x,y,joueurSuivant)>0) {
			while (grille[x+1][y-1]!=symbole) {
				grille[x-1][y-1]=symbole;
				x--;
				y--;
			}
		}
		if(coupBasDroite(x,y,joueurSuivant)>0) {
			while (grille[x+1][y+1]!=symbole) {
				grille[x+1][y+1]=symbole;
				x++;
				y--;
			}
		}
		
	}
	
	
	
}