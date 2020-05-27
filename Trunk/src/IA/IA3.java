package src.IA;

import src.othello.OthelloGame;
import src.othello.Value;

import java.util.ArrayList;
import java.util.Random;

public class IA3 extends Intelligences_Artificielles {
	
	public IA3 (Value ia_color, OthelloGame game) {
		super(ia_color, game);
	}
	
	@Override
	protected void rsch_choices () {
		for (int r = 0; r < game.getROWS(); r++) {
			for (int c = 0; c < game.getCOLS(); c++) {
				if (game.getBoard().cells[r][c].value == Value.BLANK && game.tryToFlip(r, c, true)) {
					choices_available.add(new Choix_IA3(r, c, true));
				}
			}
		}
	}
	
	/**
	 * @return Retourne le pire coup et privilégie les coins en cas d'égalité d'utilités
	 */
	public Choix worst_move(){
		if (choices_available.size() == 1) {
			return choices_available.get(0);
		}
		
		int min = 0 ;
		ArrayList<Integer> start_end = new ArrayList<Integer>();
		start_end.add(0);
		start_end.add(game.getROWS()-1);
		
		for (int i = 0; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() <= min) {
				min = choices_available.get(i).getValue();
			}
			else {
				choices_available.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < choices_available.size() ; i++) {
			if (choices_available.get(i).getValue() == min ){
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
}
