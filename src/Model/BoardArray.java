package Model;
import java.util.ArrayList;
import java.util.LinkedList;


//board array de 78
class BoardArray {
	private ArrayList<House> board;
	private int length;
	
	 
	BoardArray(int length) {
		this.length = length;
		board = new ArrayList<House>(length);
	}
	
	
	private boolean isInitialHousePositions(int position) {
		return position == 2 || position == 15 || position == 28 || position == 41;
	}
	
	
	private boolean isSafeHousePosition(int position) {
		return position == 10 || position == 23 || position == 36 || position == 49;
	}
	
	
	private boolean isFinalHouse(int position) {
		return position == 0 || position == 13 || position == 26 || position == 39;
	}
	
	
	/**
	 * Function to initialize the board, considered the board of the phone game Ludo
	 * not the physically game one.
	 * */
	void setBoard() {
		int i = 0;
		while(i < length) {
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
	
	
	boolean comparePawns(Pawn p1, House h2) {
		LinkedList<Pawn> p2 = h2.getPawnsInHouse();
		
		for(int i = 0; i < p2.size(); i++) {
			if(!p1.equals(p2.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	boolean possibleMove(Pawn p, int position1, int diceRoll) {
		
		int position2 = diceRoll + position1;
		if(position2 > 51) {
			position2 -= 51;
		}
		
		
		House h2 = board.get(position2);
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		if(p.isInFinalLine()) {
			if(p.getTotalMoves() + diceRoll <= 57 && h2.getPawnsInHouse() == null) {
				return true;
			}
			
			else {
				return false;
			}
		}
		
		else {
			for(int i = 1; i < diceRoll; i++) {
				if(p.getTotalMoves() + i == 52) {
					return true;
				}
				
				else if(haveBarrier(board.get(position1 + i))) {
					return false;
				}
			}
			
			//don't have pawns
			if(listH2.isEmpty()) {
				return true;
			}
			
			//is safe and don't have same color pawn
			else if(h2.isSafe() && listH2.size() < 2 &&  !comparePawns(p ,h2)){
				return true;
			}
			
			//is initial house and don't have pawns of same color
			else if(h2.isInitialHouse() && !comparePawns(p, h2)){
				return true;
			}
			
			//only have one pawn
			else if(listH2.size() < 2) {
				return true;
			}
			
			return false;
		}
	}
	
	
	/**
	 * The function check's if 
	 * */
	boolean possibleEat(Pawn p, int position1, int position2) {
		House h2 = board.get(position2);
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		
		if(listH2.size() == 1 && !h2.isSafe() && !h2.isInitialHouse()) {
			return true;
		}
		
		return false;
	}
	
	
	/**only move the pawn, don't increment the pawn distance and
	 * don't remove a possible pawn in the position 2
	 **/
	void moveTo(Pawn p, int position1, int position2) {
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		
		if(h1.isBarrierUp()) {
			h1.setBarrierDown();
		}
		
		h1.removePawn(p);
		
		h2.addPawn(p);
	}
	
	
	void eatPawn(Pawn p, int position1, int position2) {
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		
		if(h1.isBarrierUp()) {
			h1.setBarrierDown();
		}
		
		h1.removePawn(p);
		h2.removePawn();
		
		h2.addPawn(p);
	}
	
	
	void makeMove(Pawn p, int position1, int diceRoll) {
		int position2 = position1 + diceRoll;
		House h2 = board.get(position2);
		
		if(possibleMove(p, position1, position2) && p.getTotalMoves() + diceRoll >= 52) {
			position2 = 51 + 6*(p.getColor() - 0x0100) + (p.getTotalMoves() - 51);
			moveTo(p, position1, position2);
			if(p.haveFinished()) {
				Player player = p.getPlayer();
				player.incPawnsFinished();
			}
		}
		
		if(possibleMove(p, position1, position2) && possibleEat(p, position1, position2)) {
			
			eatPawn(p, position1, position2);
		}
		
		else if(possibleMove(p, position1, position2) && comparePawns(p ,h2) && !h2.isSafe()) {
			
			moveTo(p, position1, position2);
			h2.setBarrierUp();
		}
		
		else if(possibleMove(p, position1, position2)) {
			
			moveTo(p, position1, position2);
		}
	}
}
