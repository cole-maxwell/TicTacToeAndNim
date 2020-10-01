import java.util.Scanner;

public class PlayTicTacToe2 {
	public PlayTicTacToe2() {
		g = new TicTacToe2();
	}

	public void doComputerMove() {
		Best compMove = g.chooseMove(TicTacToe2.COMPUTER, 0);  // level 0 call
		System.out.println("Computer plays ROW = " + compMove.row + " COL = " + compMove.column);
		g.playMove(TicTacToe2.COMPUTER, compMove.row, compMove.column);
	}
	
	public void computerAsHuman() {
		Best compMove = g.chooseMove(TicTacToe2.HUMAN, 0);  // level 0 call
		System.out.println("HUMAN plays ROW = " + compMove.row + " COL = " + compMove.column);
		g.playMove(TicTacToe2.HUMAN, compMove.row, compMove.column);
	}

	public void doHumanMove() {
		boolean legal;
		printBoard();
		do {
			System.out.println("row (or enter -1 for 'computer as human' option): ");
			int row = scan.nextInt();
			// If the human enters -1 for ROW, they can let the computer choose the move
			if (row == -1)
			{
				computerAsHuman();
				break;
			}
			
			else
			{
				System.out.println("column: ");
				int col = scan.nextInt();
				legal = g.playMove(TicTacToe.HUMAN, row, col);
				if (!legal)
					System.out.println("Illegal move, try again");
			}
			
		} while (!legal);
	}

	boolean checkAndReportStatus() {
		if (g.isAWin(TicTacToe.COMPUTER)) {
			System.out.println("Computer says: I WIN!!");
			return false; // game is done
		}
		if (g.isAWin(TicTacToe.HUMAN)) {
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
		String board = g.positionString();
		System.out.println(board);
	}

	void playOneGame() {
		boolean continueGame = true;
		g.clearBoard();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayTicTacToe2 ui = new PlayTicTacToe2();
		ui.playOneGame();
	}

	private TicTacToe2 g; // g for game
	private Scanner scan = new Scanner(System.in);
}
