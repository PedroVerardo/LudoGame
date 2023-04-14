package Model;

public class Board {
	private static Board instance;
	private static Byte[][] board;
	
	private Board(int line, int column){
		Board.board = new Byte[line][column];
	}
	
	public static Board getInstance(int line, int column) {
		if(Board.board == null) {
			instance = new Board(line, column);
		}
		
		return instance;
	}
	
	public Byte getBoardPosition(int line, int column) {
		return board[line][column];
	}
	
	public void setBoardPosition(Byte object, int line, int column) {
		board[line - 1][column - 1] = object;
	}
}
