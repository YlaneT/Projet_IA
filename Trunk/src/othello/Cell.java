package src.othello;

public class Cell {
	public Value value;
	int row, col;
	
	public Cell (int r, int c) {
		this.row = r;
		this.col = c;
		clear();
	}
	
	public void set (Value val) {
		value = val;
	}
	
	public void clear () {
		value = Value.BLANK;
	}
	
	public void draw () {
		switch (value) {
			case BLACK:
				System.out.print("B\t");
				break;
			case WHITE:
				System.out.print("W\t");
				break;
			case BLANK:
				System.out.print("_\t");
				break;
		}
	}
}
