package Model;


public class App {
	public static void main(String[] args) {
		SingletonBoard board = new SingletonBoard(52);
		
		board.setBoard();
		
		//System.out.println(board.getHousePosition(0));
		
		System.out.println(Short.MAX_VALUE);
	}
}