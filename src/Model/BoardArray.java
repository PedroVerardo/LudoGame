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
	public boolean makeContact(int position1 , int position2) {
		
		if ((board[position1]& 0xf0) == (board[position2]& 0xf0)) {

			board[position2]++;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEqual(int position1 , int position2) {
		
		return false;
	}	
}
