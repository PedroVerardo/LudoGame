package Model;


/**
 * <pre>In this class we implement the pawn in a short and the methods in this class
 * make bit operations to get some rules previous decided.
 * 
 * Pawn ->  0000 0000 0000 0000
 *                 |   |_____|
 *                 |       |
 *              (color)(distance)
 * </pre> 
 * */
class Pawn {
	private short pawn;
	
	Pawn(PlayerColor pC){
		pawn = pC.getValue();
	}
	
	/**
	 * Function to get the color of the paw represented by a short
	 * 
	 * @return A short with only the first 4 bits after the first byte
	 * */
	int getColor() {
		return pawn & 0x0f00;
	}
	
	
	/**
	 * Function to used to get how many steps need to complete the
	 * turn in the board
	 * 
	 * @see
	 * 
	 * @return A short representing the 
	 * */
	int getTotalMoves() {
		return pawn & 0x00ff;
	}
	
	
	void setPawnToBase() {
		pawn &= 0x0f00;
	}
	
	
	void addMove(int diceRoll) {
		pawn += diceRoll;
	}
	
	
	boolean equals(Pawn two) {
		return (this.getColor() == two.getColor());
	}
	
	void setFinalLine() {
		pawn |= 0x8000;
	}
	
	boolean isInFinalLine() {
		return (pawn >> 12) == 8;
	}
	
	boolean haveFinished() {
		return this.getTotalMoves() == 57 ? true : false;
	}

	
}
