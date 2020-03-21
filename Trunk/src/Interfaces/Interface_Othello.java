package Interfaces;

public interface Interface_Othello {

	/**
	* Symbole correspondant aux pions du premier joueur
	*/
	char X = 'X' ;

	/**
	* Symbole correspondant aux pions du second joueur
	*/
	char O = 'O' ;

	/**
	* Symbole correspondant à une case vide
	*/
	char VIDE = ' ' ;

	/**
	 * @param x : abscisse
	 * @param y : ordonnée
	 * @return  : Symbole à une position donnée
	 */
	char getSymbole(int x, int y);

	/**
	 * Place un symbole à une position donnée
 	 * @param symbole : symbole à placer
	 * @param x : abscisse
	 * @param y : ordonnée
	 */
	void setSymbole(char symbole, int x, int y);

	/**
	 * Dans certains cas rares, un joueur ne peut pas jouer
	 * @return : Information sur la possibilité de placer un pion
	 */
	boolean peutJouer();

	/**
	 * @param symbole : Joueur
	 * @return : Réponse à "Quel joueur a gagné ?"
	 */
	boolean aGagne(char symbole);

	/**
	 * @return : Réponse à "Est-ce que la grille est pleine ?"
	 */
	boolean grillePleine();

}