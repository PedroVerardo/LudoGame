package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Player is relative for how color the player represet's 
 * in the Ludo game, that class are used in the Round class too
 * */
class Player {
	private int pawnsInBase = 4;
	private int pawnsFinished = 0;
	private int finalHouse;
	private int startHouse;
	private PlayerColor color;
	private List<Pawn> pawnsBoardposition = new ArrayList<Pawn>(4);
	
	Player(int startHouse, Pawn p1, Pawn p2, Pawn p3, Pawn p4, PlayerColor color){
		this.startHouse = startHouse;
		this.finalHouse = 2 + color.getValue()/256 -1;
		this.color = color;
		
		pawnsBoardposition.add(p1);
		pawnsBoardposition.add(p2);
		pawnsBoardposition.add(p3);
		pawnsBoardposition.add(p4);
	}
	
	
	/**
	 * Function to return the start house of the pawn.
	 * */
	int getStartHouse() {return startHouse;}
	
	/**
	 * Function to understand who is playing
	 * 
	 * @return The enum PlayerColor
	 */
	PlayerColor getPlayerColor() {return color;}
	
	/**
	 * Function to increment the quantities of pawns finished.
	 * 
	 * This gone be useful in the round to see if player win the game.
	 * */
	void incPawnsFinished() {this.pawnsFinished++;}
	
	int getNumberOfFinished() {return pawnsFinished;}
	
	void setPawnsFinishedZero() {pawnsFinished = 0;}
	
	
	/**
	 * Function to see how many pawns in base that player have
	 * @return pawnsInBase The number of pawn in base
	 * */
	int getHowManyPawnsInBase() {return pawnsInBase;}
	
	
	/**
	 * Function to add a pawn in the base
	 * */
	void incPawnsInBase() {this.pawnsInBase++;}
	
	
	/**
	 * Function to decrement one pawn of the base 
	 * */
	void decPawnsInBase() {this.pawnsInBase--;}
	
	
	/**
	 * Function to see if the pawn is in final line.
	 * 
	 * @param p the pawn you want to know the position
	 * 
	 * @return A integer that represent the position in the board.
	 * */
	Integer getPawnPosition(Pawn p) {
		int soma = startHouse + p.getTotalMoves();
		
		if( soma > 51) {
			return soma - 52;
		}
		
		return soma;
	}
	
	/**
	 * Function to see all pawns position of the player object
	 * @return pawnsBoardposition a list with all pawns position
	 * */
	List<Integer> getAllPawnsBoardposition() {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		
		for(Pawn p : pawnsBoardposition) {
			positions.add(p.getPawnPositionInBoard(this));
		}
		
		return positions;
	}
	
	List<Boolean> getIfPawnsInBase(){
		ArrayList<Boolean> inBase = new ArrayList<Boolean>();
		
		for(Pawn p : pawnsBoardposition) {
			inBase.add(p.inBase());
		}
		
		return inBase;
	}
	
	List<Pawn> getPawns(){
		return pawnsBoardposition;
	}
	
	List<Integer> getPawnsTotalMoves(){
		ArrayList<Integer> totalMoves = new ArrayList<Integer>();
		
		for(Pawn p : pawnsBoardposition) {
			totalMoves.add(p.getTotalMoves());
		}
		
		return totalMoves;
	}
	
	List<Integer> getPawnsMoveTypes(SingletonBoard board, int diceRoll){
		ArrayList<Integer> moveTypes = new ArrayList<Integer>();
		
		for(Pawn p : pawnsBoardposition) {
			moveTypes.add(board.possibleMove(p, this, p.getPawnPositionInBoard(this), diceRoll));
		}
		
		return moveTypes;
	}
	
	/**
	 * Function to see if the player won the game or not.
	 * 
	 * @return true if player win the game.
	 * @return false if player not win the game.
	 * */
	boolean isWinner() {return pawnsFinished == 4;}


	public int getFinalHouse() {
		return finalHouse;
	}
}
