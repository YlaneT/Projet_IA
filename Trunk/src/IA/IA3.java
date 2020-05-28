package src.IA;

import src.othello.OthelloGame;
import java.util.ArrayList;
import src.othello.Value;
import java.util.Random;

public class IA3 extends Intelligences_Artificielles {
	
	private boolean max_or_min;
	
	public IA3 (Value ia_color, OthelloGame game, boolean m_o_m) {
		super(ia_color, game);
		this.max_or_min = m_o_m;
	}
	
	@Override
	protected void rsch_choices () {
		for (int r = 0 ; r < game.getROWS() ; r++) {
			for (int c = 0 ; c < game.getCOLS() ; c++) {
				if (game.getBoard().cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					choices_available.add(new Choix_IA3(r, c, true));
				}
			}
		}
	}
	
	/**
	 * @return Retourne le pire coup et privilégie les coins en cas d'égalité d'utilités
	 */
	public Choix worst_move () {
		if (choices_available.size() == 1) {
			return choices_available.get(0);
		}
		
		int                min       = 0;
		ArrayList<Integer> start_end = new ArrayList<>();
		start_end.add(0);
		start_end.add(game.getROWS() - 1);
		
		for (int i = 0 ; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() <= min) {
				min = choices_available.get(i).getValue();
			}
			else {
				choices_available.remove(i);
				i--;
			}
		}
		
		for (int i = 0 ; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() == min) {
				if (start_end.contains(choices_available.get(i).getPosition()[0])) {
					if (start_end.contains(choices_available.get(i).getPosition()[1])) {
						return choices_available.get(i);
					}
				}
			}
			else {
				choices_available.remove(i);
				i--;
			}
		}
		int rand = new Random().nextInt(choices_available.size());
		return choices_available.get(rand);
	}
	
	@Override
	public int[] playTurn () {
		maj_IA();
		rsch_choices();
		show_choices();
		Choix played;
		if (max_or_min) {
			played = best_move();
		}
		else {
			played = worst_move();
		}
		return played.getPosition();
	}
}
