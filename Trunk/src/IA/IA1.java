package src.IA;

import src.othello.OthelloGame;
import src.othello.Value;

public class IA1 extends Intelligences_Artificielles {
	
	public IA1 (Value ia_color, OthelloGame game) {
		super(ia_color, game);
	}
	
	/**
	 * Crée des choix en fonction de là où l'IA peut jouer.
	 */
	@Override
	protected void rsch_choices () {
		for (int r = 0 ; r < game.getROWS() ; r++) {
			for (int c = 0 ; c < game.getCOLS() ; c++) {
				if (game.getBoard().cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					choices_available.add(new Choix(r, c, 1));
				}
			}
		}
	}
}
