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
			if(pawn.getTotalMoves() > 50) {
				int colorPlayerValueShifited = p.getPlayerColor().getValue() >> 4 - 1;
				total += pawn.getTotalMoves() - 6*colorPlayerValueShifited;
			}
			else {
				total += pawn.getTotalMoves();
			}
			
		}
		
		return total;
	}
}
