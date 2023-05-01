package Model;
import java.util.ArrayList;
import java.util.LinkedList;

class BoardArray {
	 private ArrayList<House> board;
	 private int length;
	
	BoardArray(int length) {
		this.length = length;
		board = new ArrayList<House>(length);
	}
	
	boolean isInitialHousePositions(int position) {
		return position == 2 || position == 15 || position == 28 || position == 41;
	}
	
	boolean isSafeHousePosition(int position) {
		return position == 10 || position == 23 || position == 36 || position == 49;
	}
	boolean isFinalHouse(int position) {
		return position == 0 || position == 13 || position == 26 || position == 39;
	}
	
	
	/**
	 * Function to initialize the board, considered the board of the phone game Ludo
	 * not the physically game one.
	 * */
	void setBoard() {
		int i = 0;
		while(i < 52) {
			if(isInitialHousePositions(i)){
				board.add(new House(false, false, true));
			}
			
			else if(isSafeHousePosition(i)) {
				board.add(new House(true, false, false));
			}
			
			else if(isFinalHouse(i)) {
				board.add(new House(false, true, false));
			}
			
			else {
				board.add(new House(false, false, false));
			}
			i++;
		}
	}
	
	House getHousePosition(int pos) {
		return board.get(pos);
	}
	
	boolean haveBarrier(House h) {
		return h.isBarrierUp();
	}
	
	boolean comparePawns(Pawn p1, LinkedList<Pawn> p2) {
		for(int i = 0; i < p2.size(); i++) {
			if(!p1.equals(p2.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	boolean possibleMove(Pawn p, int position1, int position2) {
		
		int distMove = position2 - position1;
		
		House h2 = board.get(position2);
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		
		for(int i = 1; i < distMove; i++) {
			if(haveBarrier(board.get(position1 + i))) {
				return false;
			}
		}
		
		//don't have pawns
		if(listH2.isEmpty()) {
			return true;
		}
		
		//is safe and don't have same color pawn
		else if(h2.isSafe() && listH2.size() < 2 &&  !comparePawns(p ,listH2)){
			return true;
		}
		
		//is initial house and don't have pawns of same color
		else if(h2.isInitialHouse() && !comparePawns(p, listH2)){
			return true;
		}
		
		//only have one pawn
		else if(listH2.size() < 2) {
			return true;
		}
		
		return false;
		
	}
	
	boolean possibleEat(Pawn p, int position1, int position2) {
		House h2 = board.get(position2);
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		
		if(listH2.size() < 2 && !h2.isSafe() && !h2.isInitialHouse()) {
			return true;
		}
		return false;
	}
	
	//only move the pawn, don't increment the pawn distance and
	//don't remove a possible pawn in the position 2
	void moveTo(Pawn p, int position1, int position2) {
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		
		h1.removePawn(p);
		
		h2.addPawn(p);
	}
	
	void eatPawn(Pawn p, int position1, int position2) {
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		
		h1.removePawn(p);
		h2.removePawn();
		
		h2.addPawn(p);
	}
	
	void makeMove(Pawn p, int position1, int position2) {
		if(possibleMove(p, position1, position2) && possibleEat(p, position1, position2)) {
			
			eatPawn(p, position1, position2);
		}
		
		else if(possibleMove(p, position1, position2)) {
			
			moveTo(p, position1, position2);
		}
	}
}
