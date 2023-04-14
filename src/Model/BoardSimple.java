package Model;

import java.util.ArrayList;
import java.util.List;

public class BoardSimple<T> {
	private static BoardSimple<?> instance;
	private List<T> board = new ArrayList<T>();
	private T b;
	
	private BoardSimple() {
		
	}
	
	public void getInstance() {
		if(instance == null) {
			instance = new BoardSimple<T>();
		}
	}
	
	public void createBoard() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board.add(j, b);
			}
		}
	}
}
