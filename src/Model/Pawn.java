package Model;

import java.io.Serializable;

/**
 * <pre>In this class we implement the pawn in a short and the methods in this class
 * make bit operations to get some rules previous decided.
 * 
 * Pawn ->  0000 0000 0000 0000
 *            |    |   |_____|
 *            |    |       |
 *       (finalL)(color)(distance)
 * </pre> 
 * */
class Pawn implements Serializable{
	private static final long serialVersionUID = 1L;
	private short pawn;
	
	Pawn(PlayerColor pC){
		pawn = pC.getValue();
		pawn |= 0x4000;
	}
	
	Pawn(short val){
		pawn = val;
	}
	
	/**
	 * Function to get the color of the paw represented by a short
	 * 
	 * @return A short with only the first 4 bits after the first byte
	 * */
	int getColor() {return pawn & 0x0f00;}
	
	
	/**
	 * Function to remove pawn in the base.
	 * */
	void removeFromBase() {pawn &= 0xbfff;}
	
	boolean inBase() { return (pawn & 0x4000) != 0;}
	
	void putInBase() {pawn |= 0x4000;}
	
	/**
	 * Function to used to get how many steps need to complete the
	 * turn in the board.
	 * 
	 * @return A short representing the total of moves that pawn did.
	 * */
	int getTotalMoves() {return pawn & 0x00ff;}
	
	/**
	 * Function to know the position of the pawn in the board
	 * 
	 * @param p The player, who control this pawn
	 * @return The player start house + the total of pawn moves % 52(total houses)
	 */
	int getPawnPositionInBoard(Player p) {
		if(this.isInFinalLine()) {
			return this.getTotalMoves();
		}
		
		int totalDist = (p.getStartHouse() + this.getTotalMoves());
		
		if(totalDist > 51) {return totalDist - 52;}
		
		else {return totalDist;}
	}
	
	void pawnResetDist() {
		this.pawn &= 0xff00;
	}
	
	/**
	 * Function to return the pawn to the base.
	 * */
	
	
	/**
	 * Function to add the dice roll to the pawn selected.
	 * 
	 * @param diceRoll the value of the dice in determinate round.
	 * */
	void addMove(int diceRoll) {pawn += diceRoll;}
	
	void setMove(int position) {
		this.pawnResetDist();
		
		pawn += position;
	}
	
	
	/**
	 * Function overwrite the method equals used to compare pawns.
	 * 
	 * @param two the other pawn you want to make the comparison.
	 * */
	boolean equals(Pawn two) {return (this.getColor() == two.getColor());}
	
	
	/**
	 * Function to put the pawn in the final line.
	 * */
	void setFinalLine() {pawn |= 0x8000;}
	
	void resetFinalLine() { pawn &= 0x7fff;}
	
	
	/**
	 * Function to see if the pawn is in final line.
	 * */
	boolean isInFinalLine() {return (pawn & 0x8000) != 0;}
	
	
	/**
	 * Function to see if the pawn complete the final line.
	 * 
	 * @return The boolean that represent the pawn finalize the path.
	 * */
	boolean haveFinished() {return (pawn & 0x2000) != 0;}
	
	void finishedThePath() {pawn |= 0x2000;}
	
	void resetFinished() { pawn &= 0xcfff;}
	
	void setPawn(short pawn) {this.pawn = pawn;}
	
	short getPawn() {return pawn;}

	public String toString() {
		return "Cor: " + this.getColor() + " totalMoves: " + this.getTotalMoves();
	}
	
}
