package Model;

import java.util.LinkedList;


class House {
	private LinkedList<Pawn> pawnsInHouse = new LinkedList<Pawn>();
	private boolean initialHouse;
	private boolean barrierUp;
	private boolean Safe;
	private boolean finalHouse;
	
	House(boolean Safe, boolean finalHouse, boolean initialHouse){
		this.initialHouse = initialHouse;
		this.Safe = Safe;
		this.finalHouse = finalHouse;
	}
	
	void addPawn(Pawn pawn) {
		pawnsInHouse.add(pawn);
	}
	
	void removePawn(Pawn pawn) {
		pawnsInHouse.remove(pawn);
	}
	
	void removePawn() {
		pawnsInHouse.remove();
	}

	boolean isSafe() {
		return Safe;
	}

	boolean isFinalHouse() {
		return finalHouse;
	}
	
	LinkedList<Pawn> getPawnsInHouse()
	{
		return pawnsInHouse;
	}

	boolean isInitialHouse() {
		return initialHouse;
	}

	boolean isBarrierUp() {
		return barrierUp;
	}

	void setBarrierUp(boolean state) {
		this.barrierUp = state;
	}

}
