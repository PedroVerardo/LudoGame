package Model;

import java.util.ArrayList;

public class BoardArray implements IBoardRules{
	 private ArrayList<House> board;
	 private int lenght;
	
	BoardArray(int lenght) {
		this.lenght = lenght;
		board = new ArrayList<House>(lenght);
	}
	
	public void setBoard() {
		int i = 0;
		while(i < 52) {
			if(i == 2 || i == 15 || i == 28 || i == 41){
				board.add(new House(true, false));
			}
			else {
				board.add(new House(false, false));
			}
			i++;
		}
	}
	 
	@Override
	public void possibleMove(int position1, int position2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sameType(int position1, int position2) {
		
		return false;
	}	
}
