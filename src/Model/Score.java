package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {
	static Map<PlayerColor, Integer> totalScore(List<Player> list) {
		
		Map<PlayerColor, Integer> score = new HashMap<PlayerColor, Integer>();
		
		for(Player p: list) {
			score.put(p.getPlayerColor(), playerScore(p));
		}
		
		return score;
	}
	
	private static int playerScore(Player p) {
		int total = 0;
		List<Pawn> pawns= p.getPawns();
		
		for(Pawn pawn : pawns) {
			if(pawn.haveFinished()) {
				total += 3;
			}
			
			else if(pawn.isInFinalLine()) {
				total += 1;
			}
		}
		
		return total;
	}
}
