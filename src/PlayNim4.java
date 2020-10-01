import java.util.Scanner;

public class PlayNim4 {
	public PlayNim4() {
		g = new Nim4();
	}

	public void doComputerMove() {
		Best compMove = g.chooseMove(Nim4.COMPUTER, 0);  // level 0 call
		System.out.println("Computer plays ROW = " + compMove.row + " STARS = " + compMove.column);
		g.makeMove(Nim4.COMPUTER, compMove.row, compMove.column);
	}

	public void doHumanMove() {
		boolean legal;
		printBoard();
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("stars: ");
			int stars = scan.nextInt();
			legal = g.makeMove(Nim.HUMAN, row, stars);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
	}

	boolean checkAndReportStatus() {
		if (g.winner() == 0) {
			System.out.println("Computer says: I WIN!!");
			return false; // game is done
		}
		if (g.winner() == 1) {
			System.out.println("Computer says: You WIN!!");
			return false; // game is done
		}
		return true;
	}

	// do one round of playing the game, return true at end of game
	public boolean getAndMakeMoves() {
		// Computer goes first
		doComputerMove();
		// System.out.println("count = " + t.getCount());
		if (!checkAndReportStatus())
			return false; // game over
		doHumanMove();
		if (!checkAndReportStatus())
			return false; // game over
		return true;
	}

	void printBoard() {
		String board = g.position();
		System.out.println(board);
	}

	void playOneGame() {
		boolean continueGame = true;
		g.init();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayNim4 ui = new PlayNim4();
		ui.playOneGame();
	}

	private Nim4 g; // g for game
	private Scanner scan = new Scanner(System.in);
}
