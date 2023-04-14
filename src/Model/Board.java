package Model;

import java.io.*;

public class Board<T> {
	private static Board<? extends Object> instance;
	private static Object[][] board;
	
	@SuppressWarnings("unchecked")
	private Board(int line, int column){
		Board.board = (T[][])new Object[line][column];
	}
	
	public static Board<?> getInstance(int line, int column) {
		if(Board.board == null) {
			instance = new Board<Object>(line, column);
		}
		
		return instance;
	}
	
	public T[][] getBoardPosition(int line, int column) {
		return (T[][]) board[line][column];
	}
	
	public void setBoardPosition(T object, int line, int column) {
		board[line - 1][column - 1] = object;
	}
}
