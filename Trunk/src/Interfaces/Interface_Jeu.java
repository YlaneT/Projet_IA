package Interfaces;

public interface Interface_Jeu {



	/**
	* @return : Le symbole correspondant au prochain joueur
	*/
	char prochainJoueur () ;
	
	/**
	* Effectue le tour d’un joueur
	* @param symbole : Symbole correspondant au joueur dont c’est le tour
	*/
	void tourJoueur (char symbole);

	/**
	 * Affiche la grille dans la console.
	 */
	void afficherGrille ();

	/**
	 * determine le gagnant
	 */
	void deterGagnant () ;

	/**
	 * Permet de féliciter le joueur gagnant
	 * @param symbole : le gagnant de la partie
	 */
	void feliciter ( char symbole ) ;

	/**
	 * Permet d'annoncer si aucun joueur n'a gagné
	 */
	void egalite() ;

	/**
	 * Remplis un historique avec les résultats de chaque partie
	 */
	void remplirHistorique(char gagnant);
}