package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Player is relative for how color the player represet's 
 * in the ludo game, that class are used in the Round class too
 * */
class Player {
	private int pawnsInBase = 4;
	private int pawnsFinished = 0;
	private int startHouse;
	private List<Pawn> pawnsBoardposition = new ArrayList<Pawn>(4);
	
	Player(int startHouse){
		this.startHouse = startHouse;
		
		//Pawn p = new Pawn()
		
		for(int i = 0; i < 4; i++) {
			pawnsBoardposition.add(null);
		}
		
	}
	
	/**
	 * Function to see how many pawns in base that player have
	 * @return pawnsInBase The number of pawn in base
	 * */
	int getHowManyPawnsInBase() {
		return pawnsInBase;
	}
	
	/**
	 * Function to add a pawn in the base
	 * */
	void incPawnsInBase() {
		this.pawnsInBase++;
	}
	
	/**
	 * Function to decrement one pawn of the base 
	 * */
	void decPawnsInBase() {
		this.pawnsInBase--;
	}
	
	/**
	 * Function to see all pawns position of the player object
	 * @return pawnsBoardposition a list with all pawns position
	 * */
	List<Pawn> getAllPawnsBoardposition() {
		return pawnsBoardposition;
	}
	
	/**
	 * Function to see if the player won the game or not.
	 * 
	 * @return true if player win the game.
	 * @return false if player not win the game.
	 * */
	boolean isWinner() {
		if(pawnsFinished == 4) {return true;}
		
		else {return false;}
	}
}
