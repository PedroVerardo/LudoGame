package Model;

public class BoardArray implements IBoardRules{
	private static BoardArray instance;
	private static byte[] board;
	
	private BoardArray(int length) {
		board = new byte[length];
	}
	
	public static BoardArray getInstance(int length) {
		if(instance == null) {
			instance = new BoardArray(length);
		}
		return instance;
	}

	public void setBoard(int index, byte b) {
		board[index] = b;
	}
	
	@Override
	public void makeContact(int position1 , int position2) {
		
		if (isEqual(position1, position2)) {

			board[position2]++; 
		}
		return;
	}

	@Override
	public boolean isEqual(int position1 , int position2) {
		if ((board[position1]& 0xf0) == (board[position2]& 0xf0)) {

			return true;
		}
		return false;
	}	
}
