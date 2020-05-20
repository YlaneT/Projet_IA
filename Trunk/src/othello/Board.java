package src.othello;

public class Board {
	public Cell[][] cells;
	int ROWS, COLS, numBlack, numWhite;
	
	
	public Board(int row, int col) {
		this.ROWS = row;
		this.COLS = col;
		cells = new Cell[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				cells[r][c] = new Cell(row, col);
			}
		}
	}
	
	public Board (Board board) {
		ROWS = board.ROWS;
		COLS = board.COLS;
		cells = new Cell[ROWS][COLS];
		for (int r = 0; r < this.ROWS; r++) {
			for (int c = 0; c < this.COLS; c++) {
				this.cells[r][c] = new Cell(ROWS, COLS);
				this.cells[r][c].set(board.cells[r][c].value);
			}
		}
	}
	
	public void draw() {
		System.out.print("    ");
		for (int c = 0; c < COLS; c++) {
			System.out.print(c+1 + "\t");
		}
		System.out.println();
		
		for (int r = 0; r < ROWS; r++) {
			System.out.print(r+1 + "\t");
			for (int c = 0; c < COLS; c++) {
				cells[r][c].draw();
			}
			System.out.println();
		}
		
		numBlack = countBlacks();
		numWhite = countWhites();
		System.out.format("%nBlack: %d%nWhite: %d%n%n", numBlack, numWhite);
	}
	
	
	public int countBlacks() {
		int count = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (cells[r][c].value == Value.BLACK) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int countWhites() {
		int count = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (cells[r][c].value == Value.WHITE) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int countColor(Value couleur) {
		int count = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (cells[r][c].value == couleur) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int countColor_IA2(Value couleur) {
		int count = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (cells[r][c].value == couleur) {
					count++;
					if (r == 0 || r+1 == ROWS){
						count++;
					}
					if (c == 0 || c+1 == COLS){
						count++;
					}
				}
			}
		}
		return count;
	}
	
	public boolean fullGrid(){
		for (int i=0; i<ROWS; i++) {
			for (int j=0; j<COLS; j++) {
				if (cells[i][j].value == Value.BLANK) {
					return false;
				}
			}
		}
		return true;
	}
	
}
