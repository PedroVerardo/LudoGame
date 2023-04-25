package Model;

public interface IBoardRules {
	
	public void possibleMove(int position1, int position2);
	
	public boolean sameType(int position1, int position2);
}
