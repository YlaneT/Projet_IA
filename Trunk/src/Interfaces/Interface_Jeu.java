package Interfaces;

public interface Interface_Jeu {

    /**
    * @return true : Si et seulement si le jeu est fini
    * (Le gagnant a déjà été annoncé. Il ne reste rien à faire)
    */
	boolean estFini () ;

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
	* Indique si un joueur a gagne le jeu
	* @param symbole : le joueur qui doit etre teste
	* @return true si et seulement si le joueur symbole a gagne
	*/
	boolean aGagne (char symbole);

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