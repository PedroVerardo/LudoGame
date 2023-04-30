package Model;
import java.util.*;

public class BoardArray implements IBoardRules{
	 private ArrayList<House> board;
	 private int length;
	
	BoardArray(int length) {
		this.length = length;
		board = new ArrayList<House>(length);
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
		
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		LinkedList<Pawn> listH1 = h1.getPawnsInHouse();
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		
		if (!h1.isSafe() && !h2.isSafe())
		{
			Pawn p1 = listH1.getFirst();
			Pawn p2 = listH2.getFirst();
			
			if (listH2.isEmpty() || p2.isSameType(p1))
			{
				h2.addPawn(p1);
				h1.removePawn(p1);
			}
			else
			{
				p2.setPawnToBase();
				p2.add(listH1.getFirst());
				h1.removePawn(listH1.getFirst());
				
			}
		}
		
	}

	@Override
	public boolean sameType(int position1, int position2) {
		return false;
	}	
}
