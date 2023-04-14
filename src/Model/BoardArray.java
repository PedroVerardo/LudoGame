package Model;

import java.util.ArrayList;
import java.util.List;

public class BoardArray {
	private static BoardArray instance;
	private static List<Byte> board;
	
	private BoardArray() {
		
	}
	
	public static BoardArray getInstance(int length) {
		if(instance == null) {
			board = new ArrayList<Byte>(length);
			instance = new BoardArray();
		}
		return instance;
	}

	public static Byte getBoard(int position) {
		return board.get(position);
	}

	public static void setBoard(int position, Byte object) {
		board.set(0, object);
	}
	
	
}
