import java.util.Scanner;

public class PlayNim3 {
	public PlayNim3() {
		g = new Nim();
	}

	public void doComputerMove() {
		System.out.println("Choose a move for the computer.");
		boolean legal;
		printBoard();
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("stars: ");
			int stars = scan.nextInt();
			legal = g.makeMove(Nim.COMPUTER, row, stars);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
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
		// let computer go first...but ask for human input
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
		PlayNim3 ui = new PlayNim3();
		ui.playOneGame();
	}

	private Nim g; // g for game
	private Scanner scan = new Scanner(System.in);
}
