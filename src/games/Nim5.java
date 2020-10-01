package games;

public class Nim5 implements Game {

	public static final int UNCLEAR = 2;
	final static int NUM_ROWS = 3;
	private final static int SZ_ROW0 = 5;
	private final static int SZ_ROW1 = 3;
	private final static int SZ_ROW2 = 1;

	// These fields represent the actual position at any time.
	private int[] heap = new int[NUM_ROWS];
	private int nextPlayer;

	/**
	 * Construct an instance of the Nim Game
	 */
	public Nim5() {
	}

	/**
	 * Set up the position ready to play.
	 */
	public void init() {
		heap[0] = SZ_ROW0;
		heap[1] = SZ_ROW1;
		heap[2] = SZ_ROW2;
		nextPlayer = COMPUTER;
	}

	/**
	 * Who has won the game? Returns one of HUMAN, COMPUTER, UNCLEAR
	 * 
	 * @return the winner.
	 */
	public int winner() {
		if (nextPlayer == HUMAN && getStarsLeft() == 0) {
			return HUMAN;
		}
		if (nextPlayer == COMPUTER && getStarsLeft() == 0) {
			return COMPUTER;
		}
		return UNCLEAR;
	}
	
	// Find optimal move
	public Best chooseMove(int side, int depth) {
		
		// initialize variables
		int opp; // The other side
		Best reply; // Opponent's best reply
		int simpleEval; // Result of an immediate evaluation
		int bestRow = -1; // Initialize running value with out-of-range value
		int numStars = -1;
		int value;
		
		// Take care of easy cases
		if ((simpleEval = winner()) != UNCLEAR) 
			return new Best(simpleEval);
		
		// Set up running max or min
		if (side == COMPUTER) {
			opp = HUMAN;
			value = HUMAN_WIN - 1; // impossibly low value
		} else {
			opp = COMPUTER;
			value = COMPUTER_WIN + 1; // impossibly high value
		}
		
		
		// Loop over possible moves
		for (int row = 0; row < 3; row++)
			for (int stars = 0; stars < 5; stars++)
				// make a trial move
				if (isLegal(row, stars)) {
					playMove(side, row, stars);
					// call choose move to get the best reply move
					reply = chooseMove(opp, depth+1);
					playMove(side, row, stars);
					// Update the running max or min
					if (side == COMPUTER && reply.val > value || side == HUMAN && reply.val < value) {
						value = reply.val;
						bestRow = row;
						numStars = stars;
					}
				}
		
		// Don't need this because it's for DP
		// store.put(thisPosition, value);
		
		// return the best move
		return new Best(value, bestRow, numStars);
	}

	/**
	 * Make a move
	 * 
	 * @param side of player making move
	 * @param row 
	 * @param number of stars taken.
	 * @returns false if move is illegal.
	 */
	public boolean playMove(int side, int row, int number) {
		if (side != nextPlayer) {  
			return false;  // wrong player played
		}
		if (!isLegal(row, number)) {
			return false;
		} else {
			nextPlayer = (nextPlayer == COMPUTER ? HUMAN : COMPUTER);
			heap[row] = heap[row] - number;
		}
		return true;
	}

	/**
	 * This method displays current position
	 */
	public String position() {
		StringBuilder board = new StringBuilder("");

		for (int i = 0; i < NUM_ROWS; i++) {
			char c = (char) ((int) 'A' + i);
			board.append(c + ": ");
			for (int j = heap[i]; j > 0; j--) {
				board.append("* ");
			}
			board.append('\n');
		}

		return board.toString();
	}
	
	public boolean boardIsFull()
	{
		return (getStarsLeft() == 0);
	}

	public boolean isAWin(int side)
	{
		return (getStarsLeft() == 0);
	}
	/**
	 * Compute the total number of stars left.
	 */
	private int getStarsLeft() {
		return (heap[0] + heap[1] + heap[2]);
	}

	private boolean isLegal(int row, int stars) {
		return 0 <= row && row <= 2 && stars >= 1 && stars <= heap[row];
	}

	/**
	 * What are the rules of the game? How are moves entered interactively?
	 * 
	 * @return a String with this information.
	 */
	public String help() {
		StringBuffer s = new StringBuffer("\nNim is the name of the game.\n");
		s.append("The board contains three ");
		s.append("rows of stars.\nA move removes stars (at least one) ");
		s.append("from a single row.\nThe player who takes the last star wins.\n");
		s.append("Type Xn (or xn) at the terminal to remove n stars from row X.\n");
		s.append("? displays the current position, q quits.\n");
		return s.toString();
	}

	// unit test
	public static void main(String[] args) {

		Nim5 g = new Nim5();

		g.init();
		System.out.println("Start of game:");
		System.out.println(g.position());
		System.out.println("winner is " + g.winner());

		System.out.println("play with hard coded moves");
		try {
			System.out.println("doiing move: A4");
			g.playMove(Nim5.COMPUTER, 0, 4);// gameStrings.parseMove("A4"));
			System.out.println(g.position());
			System.out.println("doiing move: B2");
			g.playMove(Nim5.HUMAN, 1, 2);// gameStrings.parseMove("B2"));
			System.out.println(g.position());
			System.out.println("doiing move: C1");
			g.playMove(Nim5.COMPUTER, 2, 1);// gameStrings.parseMove("C1"));
			System.out.println(g.position());
			System.out.println("winner is " + g.winner());
			System.out.println("doiing move: B1");
			g.playMove(Nim5.HUMAN, 1, 1);// gameStrings.parseMove("B1"));
			System.out.println(g.position());
			System.out.println("winner is " + g.winner());
			System.out.println("doiing move: A1");
			g.playMove(Nim5.COMPUTER, 0, 1);// gameStrings.parseMove("A1"));
			System.out.println(g.position());
			System.out.println("winner is " + g.winner());

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
