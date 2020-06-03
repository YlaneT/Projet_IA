package src.othello;

import src.IA.Intelligences_Artificielles;
import src.IA.IA1;
import src.IA.IA2;
import src.IA.IA3;

import java.util.Scanner;

public class OthelloGame implements Cloneable {
	
	private static Scanner in = new Scanner(System.in);
	private GameState state;
	private int gameType;
	private Board board;
	private Value turn;
	private Intelligences_Artificielles iaA;
	// private String nomIa; // pourra être utile pour le log utilisation dans Constructeur
	private Intelligences_Artificielles iaB;
	private int ROWS;
	private int COLS;
	
	
	/**
	 * Crée une nouvelle partie de Othello
	 *
	 * @param gT (gameType) Joueur vs Joueur / Joueur vs IA / IA vs IA
	 */
	public OthelloGame (int gT) {
		this.setGameType(gT);
		
		// Initialisation du board
		boolean isValidInput = false;
		boolean classic;
		String  line         = null;
		System.out.println("Voulez-vous faire une partie classique ?");
		System.out.println("(Plateau 8x8 / Black commence)");
		while (!isValidInput) {
			line = in.next().substring(0, 1);
			if (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("n") || line.equalsIgnoreCase("o")) {
				isValidInput = true;
			}
			else {
				System.out.println("Répondez par yes/oui ou non/no");
			}
		}
		classic = !line.equalsIgnoreCase("n");
		if (!classic) {
			setRowsAndColumns();
			setStartingPlayer();
		}
		else {
			ROWS = 8;
			COLS = 8;
			turn = Value.BLACK;
		}
		board = new Board(ROWS, COLS);
		setBicoloredSquare();
		state = GameState.IN_PROGRESS;
		
		// Initialisation de l'IA
		if (gT > 1) {
			this.menu_IA();
		}
		else {
			//Empêche les nullPointerException
			iaA = new IA1(Value.BLANK, this);
			iaB = new IA1(Value.BLANK, this);
		}
		
		this.playGame();
	}
	
	/**
	 * Création d'une copie de la partie
	 *
	 * @param game partie à copier
	 */
	@SuppressWarnings("CopyConstructorMissesField")
	public OthelloGame (OthelloGame game) {
		this.state = game.getState();
		this.board = new Board(game.getBoard());
		this.turn = game.getTurn();
		// private String nomIa; // pourra être utile pour le log utilisation dans Constructeur
		
		this.ROWS = game.getROWS();
		this.COLS = game.getCOLS();
		this.gameType = game.getGameType();
		
		in = new Scanner(System.in);
	}
	
	/**
	 * Menu demandant une valeur en entrée jusqu'à ce qu'elle soit valide.
	 *
	 * @return la valeur entrée moins 1 (pour tableau)
	 */
	public static int getBoundedNumber (String what, int min, int max) {
		// boolean isValid = false;
		do {
			System.out.print(what + ": ");
			int num = in.nextInt() - 1;
			if (num + 1 < min || num + 1 > max) {
				System.out.format("Donnée invalide. %s doit être entre %d et %d inclus.\n", what, min, max);
			}
			else {
				return num;
			}
		} while (true); // while (!isValid)
	}
	
	/**
	 * Menu d'initialisation de la partie.
	 */
	public static void menu () {
		int type;
		System.out.println("- - - - - - Menu - - - - - -");
		System.out.println("\t\tType de partie");
		System.out.println("1 :\tJoueur contre joueur");
		System.out.println("2 :\tJoueur contre IA");
		System.out.println("3 :\tIA contre IA");
		
		type = getBoundedNumber("Type", 1, 3);
		
		switch (type) {
			case 0:
				new OthelloGame(1);
				break;
			case 1:
				new OthelloGame(2);
				break;
			case 2:
				new OthelloGame(3);
				break;
		}
	}
	
	/**
	 * Menu d'initialisation des IA.
	 */
	public void menu_IA () {
		String format;
		if (this.gameType == 2) {
			format = "de l'";
		}
		else {
			format = "des ";
		}
		System.out.println("- - - - - - Menu des IA - - - - - -");
		System.out.format("Choisissez le type %sIA (1 à 3) :\n", format);
		switch (this.gameType) {
			case 2:
				int ia = getBoundedNumber("Type d'IA", 1, 3);
				switch (ia) {
					case 0:
						this.iaA = new IA1(Value.WHITE, this);
						break;
					case 1:
						this.iaA = new IA2(Value.WHITE, this);
						break;
					case 2:
						this.iaA = new IA3(Value.WHITE, this, true);
						break;
				}
				//Empêche les nullPointerException
				iaB = new IA1(Value.BLANK, this);
				break;
			case 3:
				ia = getBoundedNumber("Type de l'IA noire", 1, 3);
				switch (ia) {
					case 0:
						this.iaA = new IA1(Value.BLACK, this);
						break;
					case 1:
						this.iaA = new IA2(Value.BLACK, this);
						break;
					case 2:
						this.iaB = new IA3(Value.WHITE, this, true);
						break;
				}
				
				ia = getBoundedNumber("Type de l'IA blanche", 1, 3);
				switch (ia) {
					case 0:
						this.iaB = new IA1(Value.WHITE, this);
						break;
					case 1:
						this.iaB = new IA2(Value.WHITE, this);
						break;
					case 2:
						this.iaB = new IA3(Value.WHITE, this, true);
						break;
				}
				break;
			default:
				break;
		}
	}
	
	private GameState getState () {
		return this.state;
	}
	
	/**
	 * Préparation du plateau de jeu pour une nouvelle partie.
	 */
	public void setBicoloredSquare () {
		board.cells[(ROWS / 2) - 1][(COLS / 2) - 1].set(Value.WHITE);
		board.cells[ROWS / 2][COLS / 2].set(Value.WHITE);
		board.cells[(ROWS / 2) - 1][COLS / 2].set(Value.BLACK);
		board.cells[ROWS / 2][(COLS / 2) - 1].set(Value.BLACK);
	}
	
	/**
	 * Déroulement de la partie.
	 */
	public void playGame () {
		do {
			board.draw();
			if (!canPlay()) {
				System.out.println(turn + " ne peut pas jouer !");
				changeTurn();
				if (!canPlay()) {
					determineWinner();
					return;
				}
				else {
					System.out.println("C'est le tour de " + turn + " à nouveau.");
				}
			}
			else {
				System.out.format("Tour de %s.%n", turn);
			}
			makeMove();
			changeTurn();
		} while (state == GameState.IN_PROGRESS);
	}
	
	public void replay () {
		boolean isValidInput = false;
		String  sc           = null;
		while (!isValidInput) {
			System.out.println("Voulez-vous rejouer ?");
			sc = in.next().substring(0, 1);
			if (sc.equalsIgnoreCase("y") || sc.equalsIgnoreCase("n") || sc.equalsIgnoreCase("o")) {
				isValidInput = true;
			}
			else {
				System.out.println("Répondez par yes/oui ou non/no");
			}
		}
		if (sc.equalsIgnoreCase("n")) {
			System.exit(0);
		}
		else {
			menu();
		}
	}
	
	/**
	 * Détermine qui a gagné la partie.
	 */
	public void determineWinner () {
		if (board.countBlacks() > board.countWhites()) {
			state = GameState.BLACK_WIN;
			System.out.println("Black gagne!");
		}
		else if (board.countBlacks() < board.countWhites()) {
			state = GameState.WHITE_WIN;
			System.out.println("White gagne!");
		}
		else {
			state = GameState.DRAW;
			System.out.println("Egalité !");
		}
		// TODO : Créer un log pour faire des statistiques
		replay();
	}
	
	/**
	 * Détermination du choix du joueur.
	 */
	public void makeMove () {
		boolean isValidInput = false;
		int[]   move         = new int[2];
		// Tour des IA
		if (turn == iaA.getIa_color() || turn == iaB.getIa_color()) {
			if (turn == iaA.getIa_color()) {
				iaA.maj_IA();
				move = iaA.playTurn();
			}
			else if (turn == iaB.getIa_color()) {
				iaB.maj_IA();
				move = iaB.playTurn();
			}
			
			int row = move[0];
			int col = move[1];
			tryToFlip(row, col, false);
		}
		
		else { // Tour de l'humain
			while (!isValidInput) {
				int row = getBoundedNumber("Row", 1, ROWS);
				int col = getBoundedNumber("Column", 1, COLS);
				
				if (tryToFlip(row, col, false)) {
					isValidInput = true;
				}
				else {
					System.out.println("Impossible de jouer ici.");
				}
			}
		}
	}
	
	/**
	 * @return si le joueur dont c'est le tour peut jouer quelque part ou non.
	 */
	public boolean canPlay () {
		for (int r = 0 ; r < ROWS ; r++) {
			for (int c = 0 ; c < COLS ; c++) {
				if (board.cells[r][c].value == Value.BLANK && tryToFlip(r, c, true)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Place un pion d'une couleur donnée à une position donnée
	 *
	 * @param val : couleur
	 */
	public void putDisc (int row, int col, Value val) {
		board.cells[row][col].set(val);
	}
	
	/**
	 * Passer au tour suivant
	 */
	public void changeTurn () {
		turn = (turn == Value.BLACK ? Value.WHITE : Value.BLACK);
	}
	
	/**
	 * Détermine si le joueur dont c'est le tour peut jouer à une position donnée ou Joue à une position donnée.
	 *
	 * @param dontFlip si false, retourne les pions adverse.
	 * @return s'il est possible de jouer à la position donnée
	 */
	public boolean tryToFlip (int row, int col, boolean dontFlip) {
		boolean hasFlipped = false;
		Value   opposite   = (turn == Value.BLACK ? Value.WHITE : Value.BLACK);
		Value   next;
		
		if (board.cells[row][col].value == Value.BLANK) {
			
			// try to flip north direction
			if (row > 1 && board.cells[row - 1][col].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentRow         = row;
				do {
					next = board.cells[--currentRow][col].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int r = row ; r > currentRow ; r--) {
							putDisc(r, col, turn);
						}
					}
				} while (currentRow - 1 >= 0 && next != Value.BLANK);
			}
			
			// try to flip northeast direction
			if (row > 1 && col < COLS - 2 && board.cells[row - 1][col + 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentRow         = row, currentCol = col;
				do {
					next = board.cells[--currentRow][++currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int r = row, c = col ; r > currentRow && c < currentCol ; r--, c++) {
							putDisc(r, c, turn);
						}
					}
				} while (currentRow - 1 >= 0 && currentCol < COLS - 1 && next != Value.BLANK);
			}
			
			// try to flip east direction
			if (col < COLS - 2 && board.cells[row][col + 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentCol         = col;
				do {
					next = board.cells[row][++currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int c = col ; c < currentCol ; c++) {
							putDisc(row, c, turn);
						}
					}
				} while (currentCol < COLS - 1 && next != Value.BLANK);
			}
			
			// try to flip southeast direction
			if (row < ROWS - 2 && col < COLS - 2 && board.cells[row + 1][col + 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentRow         = row, currentCol = col;
				do {
					next = board.cells[++currentRow][++currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int r = row, c = col ; r < currentRow && c < currentCol ; r++, c++) {
							putDisc(r, c, turn);
						}
					}
				} while (currentRow < ROWS - 1 && currentCol < COLS - 1 && next != Value.BLANK);
			}
			
			// try to flip south direction
			if (row < ROWS - 2 && board.cells[row + 1][col].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentRow         = row;
				do {
					next = board.cells[++currentRow][col].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int r = row ; r < currentRow ; r++) {
							putDisc(r, col, turn);
						}
					}
				} while (currentRow < ROWS - 1 && next != Value.BLANK);
			}
			
			// try to flip southwest direction
			if (row < ROWS - 2 && col > 1 && board.cells[row + 1][col - 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentRow         = row, currentCol = col;
				do {
					next = board.cells[++currentRow][--currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int r = row, c = col ; r < currentRow && c > currentCol ; r++, c--) {
							putDisc(r, c, turn);
						}
					}
				} while (currentRow < ROWS - 1 && currentCol > 0 && next != Value.BLANK);
			}
			
			// try to flip west direction
			if (col > 1 && board.cells[row][col - 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentCol         = col;
				do {
					next = board.cells[row][--currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int c = col ; c > currentCol ; c--) {
							putDisc(row, c, turn);
						}
					}
				} while (currentCol > 0 && next != Value.BLANK);
			}
			
			// try to flip northwest direction
			if (row > 1 && col > 1 && board.cells[row - 1][col - 1].value == opposite) {
				boolean neighborIsOpposite = false;
				int     currentRow         = row, currentCol = col;
				do {
					next = board.cells[--currentRow][--currentCol].value;
					if (next == opposite) {
						neighborIsOpposite = true;
					}
					else if (next == turn && neighborIsOpposite) {
						if (dontFlip) {
							return true;
						}
						hasFlipped = true;
						for (int r = row, c = col ; r > currentRow && c > currentCol ; r--, c--) {
							putDisc(r, c, turn);
						}
					}
				} while (currentRow > 0 && currentCol > 0 && next != Value.BLANK);
			}
		}
		return hasFlipped;
	}
	
	/**
	 * Détermine le joueur qui commence le premier si pas partie classique.
	 */
	public void setStartingPlayer () {
		boolean isValidInput = false;
		System.out.print("Qui commence ? ");
		while (!isValidInput) {
			String start = in.next().substring(0,1);
			if (start.equalsIgnoreCase("b") || start.equalsIgnoreCase("w")) {
				isValidInput = true;
				turn = (start.equalsIgnoreCase("b") ? Value.BLACK : Value.WHITE);
			}
			else {
				System.out.println("Entrez 'b' pour black ou 'w' pour white.");
			}
		}
	}
	
	/**
	 * Détermine le nombre de lignes et de colonnes si pas partie classique.
	 */
	public void setRowsAndColumns () {
		boolean isValidInput = false;
		while (!isValidInput) {
			System.out.print("Combien de lignes et de colonnes  ? ");
			int choice = in.nextInt();
			if (choice % 2 == 0) {
				isValidInput = true;
				ROWS = choice;
				COLS = choice;
			}
			else {
				System.out.println("Entrez un chiffre pair.");
			}
		}
	}
	
	public Board getBoard () {
		return board;
	}
	
	public int getROWS () {
		return ROWS;
	}
	
	public int getCOLS () {
		return COLS;
	}
	
	public Value getTurn () {
		return turn;
	}
	
	public Value getOtherTurn () {
		if (turn == Value.BLANK) {
			System.err.println("Fonction getOtherTurn\nParamètre incorrect : turn");
			return Value.BLANK;
		}
		return turn == Value.WHITE ? Value.BLACK : Value.WHITE;
	}
	
	public int getGameType () {
		return gameType;
	}
	
	public void setGameType (int type) {
		this.gameType = type;
	}
	
	public void create_IA3_for_MIN_MAX (boolean max_or_min) {
		this.iaA = new IA3(this.turn, this, max_or_min);
	}
}
