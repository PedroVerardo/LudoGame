package Model;

class Pawn {
	private short pawn;
	private Player player;
	
	Pawn(PlayerColor pC, Player p){
		this.player = p;
		pawn = pC.getValue();
	}
	
	int getColor() {
		return pawn & 0x0f00;
	}

	
	public Player getPlayer() {
		return player;
	}
	
	
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
