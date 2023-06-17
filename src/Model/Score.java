package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
	static Map<Short, Integer> totalScore(List<Player> list) {
		
		Map<Short, Integer> score = new HashMap<Short, Integer>();
		
		for(Player p: list) {
			score.put(p.getPlayerColor().getValue(), playerScore(p));
		}
		
		return score;
	}
	
	private static int playerScore(Player p) {
		int total = 0;
		List<Pawn> pawns= p.getPawns();
		
		for(Pawn pawn : pawns) {
			if(pawn.getTotalMoves() > 50) {
				int colorPlayerValueShifited = p.getPlayerColor().getValue() >> 8 - 1;
				total += pawn.getTotalMoves() - 6*colorPlayerValueShifited;
			}
			else {
				total += pawn.getTotalMoves();
			}
			
		}
		
		return total;
	}
}
