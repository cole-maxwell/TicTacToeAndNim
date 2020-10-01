package games;

public interface Game {
	
	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int HUMAN_WIN = 0;
	public static final int COMPUTER_WIN = 3;
	
	// call to API for String[] somewhere in here...
	public void init();	
	public Best chooseMove(int side, int depth);
	public boolean playMove(int side, int row, int column);
	public boolean isAWin(int side);
	public boolean boardIsFull();
	public String position();	
}
