package Model;

public class Pawn {
	private short pawn;
	
	public int getColor()
	{
		return pawn & 0x0f00;
	}
	
	public int getDistGoal()
	{
		return pawn & 0x0ff;
	}
	
	public void setPawnToBase()
	{
		pawn &= 0xf00;
	}
	
	public void madeMove(int diceRoll)
	{
		pawn += diceRoll;
	}
	
	public boolean haveFinished()
	{
		return (this.getDistGoal() >= 52) ? true : false;
	}
}
