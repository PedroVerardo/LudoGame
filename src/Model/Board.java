package Model;

import java.io.*;

public class Board<T> {
	private static Board<?> instance;
	private static Object[][] board;
	private static int line;
	private static int column;
	
	@SuppressWarnings("unchecked")
	private Board(int line, int column){
		Board.board = (T[][])new Object[line][column];
		Board.line = line;
		Board.column = column;
	}
	
	public static Board getInstance(int line, int column) {
		if(board == null) {
			instance =new Board(line, column);
		}
		
		return instance;
	}
	
	public T[][] getBoardPosition(int line, int column) {
		return (T[][]) board[line][column];
	}
	
	public void setBoardPosition(T object, int line, int column) {
		board[line - 1][column - 1] = object;
	}
	
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static void printa() {
		
		for(int i = 0; i < line; i++) {
			System.out.println();
			for(int j = 0; j < column; j++) {
				if(board[i][j] == null){
					System.out.print(" _");
				}else {
					System.out.print( ANSI_YELLOW + board[i][j] + ANSI_RESET);
				}
			}
		}
	}
}
