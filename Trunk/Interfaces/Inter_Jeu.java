public interface Inter_Jeu {

    /**
    * @return true : Si et seulement si le jeu est fini
    * (Le gagnant a déjà été annoncé. Il ne reste rien à faire)
    */
	public boolean estFini() ;

	/**
	* @return : Le symbole correspondant au prochain joueur
	*/
	public char prochain Joueur () ;
	
	/**
	* Effectue le tour d’un joueur
	*
	* @param symbole : Symbole correspondant au joueur dont c’est le tour
	*/
	public void tourJoueur (char symbole);

	/**
	* Indique si un joueur a gagne le jeu
	*
	* @param symbole : le joueur qui doit etre teste
	* @return true si et seulement si le joueur symbole a gagne
	*/
	public boolean aGagne (char symbole);
}