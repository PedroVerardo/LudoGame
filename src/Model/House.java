package Model;

import java.util.LinkedList;

public class House {
	private LinkedList<Pawn> pawnsInHouse = new LinkedList<Pawn>();
	private boolean isSafe;
	private boolean finalHouse;
	
	public House(boolean isSafe, boolean finalHouse){
		this.isSafe = isSafe;
		this.finalHouse = finalHouse;
	}
	
	public void addPawn(Pawn pawn) {
		pawnsInHouse.add(pawn);
	}
	
	public void removePawn(Pawn pawn) {
		pawnsInHouse.remove(pawn);
	}

	public boolean isSafe() {
		return isSafe;
	}

	public boolean isFinalHouse() {
		return finalHouse;
	}
	
	public LinkedList<Pawn> getPawnsInHouse()
	{
		return pawnsInHouse;
	}

}
