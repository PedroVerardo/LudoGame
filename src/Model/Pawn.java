package Model;

public class Pawn {
	private short pawn;
	
	Pawn(PlayerColor pC){
		pawn = pC.getValue();
	}
	
	public int getColor()
	{
		return pawn & 0x0f00;
	}
	
	public int getTotalMoves()
	{
		return pawn & 0x0ff;
	}
	
	public void setPawnToBase()
	{
		pawn &= 0xf00;
	}
	
	public void addMove(int diceRoll)
	{
		pawn += diceRoll;
	}
	
	public  boolean equals(Pawn two) {
		return (this.getColor() == two.getColor());
	}
	
	
	public boolean haveFinished()
	{
		return (this.getTotalMoves() >= 52) ? true : false;
	}
}
