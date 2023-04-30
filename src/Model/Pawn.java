package Model;

public class Pawn {
	private short pawn;
	
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
	
	public static boolean equals(Pawn one, Pawn two) {
		return (one.getColor() == two.getColor());
	}
	
	public boolean isSameType(Pawn p)
	{
		
		if (this.getColor() == p.getColor())
			return true;
		
		return false;
	}
	
	public boolean haveFinished()
	{
		return (this.getTotalMoves() >= 52) ? true : false;
	}
}
