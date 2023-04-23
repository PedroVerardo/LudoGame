package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Player is relative for how color the player represet's 
 * in the ludo game, that class are used in the Round class too
 * */
public class Player {
	private int pawnsInBase = 4;
	private int pawnsFinished = 0;
	private int startHouse;
	private List<Integer> pawnsBoardposition = new ArrayList<Integer>(4);
	
	/**
	 * Function to see how many pawns in base that player have
	 * @return pawnsInBase The number of pawn in base
	 * */
	public int getHowManyPawnsInBase() {
		return pawnsInBase;
	}
	
	/**
	 * Function to add a pawn in the base
	 * */
	public void incPawnsInBase() {
		this.pawnsInBase++;
	}
	
	/**
	 * Function to decrement one pawn of the base 
	 * */
	public void decPawnsInBase() {
		this.pawnsInBase--;
	}
	
	/**
	 * Function to see all pawns position of the player object
	 * @return pawnsBoardposition a list with all pawns position
	 * */
	public List<Integer> getAllPawnsBoardposition() {
		return pawnsBoardposition;
	}
	
	/**
	 *Function to remove a @pawn of the base and
	 *put in the starter house stored on the variable startHouse
	 *
	 *@param pawnposition The position of a pawn if it's in board
	 */
	public void addPawnInBoard(Integer pawnposition) {
		this.pawnsBoardposition.add(pawnposition);
	}
	
	/**
	 *Function to change the position of a specific pawn
	 *
	 *@param index The position in the array to change
	 *@param pawnsBoardposition The new position of the pawn
	 **/
	public void changePawnPosition(int index ,Integer pawnsBoardposition) {
		this.pawnsBoardposition.add(index, pawnsBoardposition);
	}
	
	/**
	 * Function to see if the player won the game or not.
	 * 
	 * @return true if player win the game.
	 * @return false if player not win the game.
	 * */
	public boolean isWinner() {
		if(pawnsFinished == 4) {return true;}
		
		else {return false;}
	}
}
