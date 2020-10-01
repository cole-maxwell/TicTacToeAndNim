package games;
import java.util.Scanner;

public class PlayTicTacToe1 {
	public PlayTicTacToe1() {
		g = new TicTacToe5();
	}

	public void doComputerMove() {
		Best compMove = g.chooseMove(TicTacToe5.COMPUTER, 0);  // level 0 call
		System.out.println("Computer plays ROW = " + compMove.row + " COL = " + compMove.column);
		g.playMove(TicTacToe5.COMPUTER, compMove.row, compMove.column);
	}

	public void doHumanMove() {
		boolean legal;
		printBoard();
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("column: ");
			int col = scan.nextInt();
			legal = g.playMove(TicTacToe5.HUMAN, row, col);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
	}

	boolean checkAndReportStatus() {
		if (g.isAWin(TicTacToe5.COMPUTER)) {
			System.out.println("Computer says: I WIN!!");
			return false; // game is done
		}
		if (g.isAWin(TicTacToe5.HUMAN)) {
			System.out.println("Computer says: You WIN!!");
			return false; // game is done
		}
		if (g.boardIsFull()) {
			System.out.println(" Game is a DRAW");
			return false;
		}
		return true;
	}

	// do one round of playing the game, return true at end of game
	public boolean getAndMakeMoves() {
		// let computer go first...
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
		PlayTicTacToe1 ui = new PlayTicTacToe1();
		ui.playOneGame();
	}

	private TicTacToe5 g; // g for game
	private Scanner scan = new Scanner(System.in);
}
